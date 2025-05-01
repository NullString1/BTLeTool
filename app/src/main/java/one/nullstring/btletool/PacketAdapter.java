package one.nullstring.btletool;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import one.nullstring.btletool.data.ATTCommand;
import one.nullstring.btletool.data.PacketRecord;

public class PacketAdapter extends RecyclerView.Adapter<PacketAdapter.PacketViewHolder> {

    private List<PacketRecord> packetList;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

    public PacketAdapter() {
        this.packetList = new ArrayList<>();
    }

    @NonNull
    @Override
    public PacketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.packet_item, parent, false);
        return new PacketViewHolder(view);
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition != toPosition) {
            Collections.swap(packetList, fromPosition, toPosition);
            notifyItemMoved(fromPosition, toPosition);
        }
    }

    public void onItemDismiss(int position, Runnable undoCallback) {
        packetList.remove(position);
        notifyItemRemoved(position);
        undoCallback.run();
    }

    public List<PacketRecord> getPacketList() {
        return packetList;
    }

    @Override
    public void onBindViewHolder(@NonNull PacketViewHolder holder, int position) {
        PacketRecord packet = packetList.get(position);
        Date timestamp = new Date(packet.getHeader().getTimestampMilliseconds());
        holder.packetNameText.setText(String.format(Locale.getDefault(), "Packet %d @ %s", position, dateFormat.format(timestamp)));
        holder.packetDataView.setText(packet.getPacketDataString());
        holder.destinationAddressText.setText(packet.getDestinationAddress());

        boolean isSent = packet.getAttHeader().getCommand() == ATTCommand.WRITE_COMMAND;
        if (isSent) {
            holder.packetDirectionImage.setImageResource(R.drawable.ic_sent);
            holder.packetDirectionImage.setContentDescription("Sent");
        } else {
            holder.packetDirectionImage.setImageResource(R.drawable.ic_received);
            holder.packetDirectionImage.setContentDescription("Received");
        }
    }

    @Override
    public int getItemCount() {
        return packetList.size();
    }

    public void updateData(List<PacketRecord> newPacketList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(
                new DiffUtil.Callback() {
                    @Override
                    public int getOldListSize() {
                        return packetList.size();
                    }

                    @Override
                    public int getNewListSize() {
                        return newPacketList.size();
                    }

                    @Override
                    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                        return packetList.get(oldItemPosition).getPacketNumber() == newPacketList.get(newItemPosition).getPacketNumber();
                    }

                    @Override
                    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                        return packetList.get(oldItemPosition).equals(newPacketList.get(newItemPosition));
                    }
                });
        this.packetList = newPacketList;
        diffResult.dispatchUpdatesTo(this);
    }

    public static class PacketViewHolder extends RecyclerView.ViewHolder {
        final TextView packetNameText;
        final TextView packetDataView;
        final TextView destinationAddressText;
        final ImageView packetDirectionImage;

        public PacketViewHolder(@NonNull View itemView) {
            super(itemView);
            packetNameText = itemView.findViewById(R.id.packetNameText);
            packetDataView = itemView.findViewById(R.id.packetDataView);
            packetDirectionImage = itemView.findViewById(R.id.packetDirectionImage);
            destinationAddressText = itemView.findViewById(R.id.destinationAddrText);
        }
    }
}