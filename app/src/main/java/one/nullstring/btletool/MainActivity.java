package one.nullstring.btletool;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.documentfile.provider.DocumentFile;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.InputStream;

import one.nullstring.btletool.data.BTSnoopFile;
import one.nullstring.btletool.data.BTSnoopParser;
import one.nullstring.btletool.data.PacketRecord;

public class MainActivity extends AppCompatActivity {

    RecyclerView packetListView;
    Button openFileButton;
    private static final String TAG = "BTLeTool";
    PacketAdapter packetAdapter;
    private ActivityResultLauncher<Intent> filePickerLauncher;
    final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            packetAdapter.onItemMove(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            PacketRecord removedPacket = packetAdapter.getPacketList().get(position);
            packetAdapter.onItemDismiss(position, () -> Snackbar.make(packetListView, "Packet deleted", Snackbar.LENGTH_LONG).setAction("Undo", v -> {
                packetAdapter.getPacketList().add(position, removedPacket);
                packetAdapter.notifyItemInserted(position);
            }).show());
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        openFileButton = findViewById(R.id.openFIleButton);
        openFileButton.setOnClickListener(v -> openFile());

        packetAdapter = new PacketAdapter();
        packetListView = findViewById(R.id.packetRecycler);
        packetListView.setAdapter(packetAdapter);
        packetListView.setLayoutManager(new LinearLayoutManager(this));
        itemTouchHelper.attachToRecyclerView(packetListView);

        filePickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                if (data != null) {
                    Uri uri = data.getData();
                    if (uri != null) {
                        handleFileResult(uri);
                    }
                }
            }
        });
    }

    private void openFile() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/octet-stream");
        intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"application/octet-stream", "text/plain"});
        filePickerLauncher.launch(intent);
    }

    public void handleFileResult(Uri uri) {
        InputStream inputStream = null;
        try {
            DocumentFile file = DocumentFile.fromSingleUri(this, uri);
            if (file != null && file.getName() != null && file.getName().toLowerCase().endsWith(".log")) {
                Log.d(TAG, "Log file selected: " + file.getName());
                Toast.makeText(this, "Log file selected: " + file.getName(), Toast.LENGTH_SHORT).show();

                inputStream = getContentResolver().openInputStream(uri);
                if (inputStream != null) {
                    byte[] bytes = inputStream.readAllBytes();
                    BTSnoopFile result = BTSnoopParser.parseBTSnoopFile(bytes);
                    packetAdapter.updateData(result.getPackets());
                    Log.d(TAG, "Parsed BTSnoopFile: " + result);
                } else {
                    Log.e(TAG, "Could not open input stream for URI: " + uri);
                    Toast.makeText(this, "Error opening file stream", Toast.LENGTH_SHORT).show();
                }
            } else {
                Log.w(TAG, "Selected file is not a .log file or name is null.");
                Toast.makeText(this, "Please select a .log file", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            Log.e(TAG, "Error reading file: " + uri, e);
            Toast.makeText(this, "Error reading file", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Error accessing file: " + uri, e);
            Toast.makeText(this, "Error accessing file", Toast.LENGTH_SHORT).show();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error closing input stream", e);
                }
            }
        }

    }
}