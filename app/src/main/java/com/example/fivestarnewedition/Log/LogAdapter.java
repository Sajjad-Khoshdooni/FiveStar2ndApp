package com.example.fivestarnewedition.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fivestarnewedition.R;

import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.ViewHolder> {
    private List<Log> logs;
    public LogAdapter(List<Log> logs){
        this.logs = logs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LogAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.log_recycler_style, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log log = logs.get(position);
        holder.date.setText(log.getDate());
        holder.name.setText(log.getLog());

    }

    @Override
    public int getItemCount() {
        return logs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.log_date);
            name = itemView.findViewById(R.id.log_name);
        }
    }
}
