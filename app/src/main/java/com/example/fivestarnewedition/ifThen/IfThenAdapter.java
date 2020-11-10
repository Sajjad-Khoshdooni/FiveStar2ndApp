package com.example.fivestarnewedition.ifThen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fivestarnewedition.Constant.IfThen;
import com.example.fivestarnewedition.R;

import java.util.List;

public class IfThenAdapter extends RecyclerView.Adapter<IfThenAdapter.ViewHolder> {
    private List<IfThen> ifThens;

    public IfThenAdapter(List<IfThen> ifThens){
        this.ifThens = ifThens;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IfThenAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.senario_activity_recycler_style, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IfThen a = ifThens.get(position);
        holder.aSwitch.setChecked(a.isActive());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * Do Something
                 */
            }
        });
    }

    @Override
    public int getItemCount() {
        return ifThens.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Switch aSwitch;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            aSwitch = itemView.findViewById(R.id.ifthen_switch);
        }
    }
}
