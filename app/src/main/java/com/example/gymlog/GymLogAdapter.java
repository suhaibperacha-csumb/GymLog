package com.example.gymlog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymlog.database.entities.GymLog;

import java.util.List;

public class GymLogAdapter extends RecyclerView.Adapter<GymLogAdapter.GymLogViewHolder> {

    private List<GymLog> gymLogs;

    public void setGymLogs(List<GymLog> gymLogs) {
        this.gymLogs = gymLogs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GymLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gym_log_item, parent, false);
        return new GymLogViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GymLogViewHolder holder, int position) {
        GymLog currentLog = gymLogs.get(position);
        holder.itemText.setText(currentLog.toString());
    }

    @Override
    public int getItemCount() {
        return gymLogs == null ? 0 : gymLogs.size();
    }

    static class GymLogViewHolder extends RecyclerView.ViewHolder {
        TextView itemText;

        public GymLogViewHolder(@NonNull View itemView) {
            super(itemView);
            itemText = itemView.findViewById(R.id.item_text);
        }
    }
}
