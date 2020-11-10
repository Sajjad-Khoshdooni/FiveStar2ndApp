package com.example.fivestarnewedition.Account;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fivestarnewedition.Constant.Account;
import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.R;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {
    private List<Account> accounts;
    private AppCompatActivity activity;

    public AccountAdapter(AppCompatActivity activity, List<Account> accounts){
        this.activity = activity;
        this.accounts = accounts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AccountAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.account_recycler_style, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Account account = accounts.get(position);
        holder.name.setText(account.getName());
        holder.address.setText(account.getAddress());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant.removeAccount(activity,account);
                accounts = Constant.getAccounts(activity);
                AccountAdapter.this.notifyDataSetChanged();
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent my = new Intent(activity,EditAccountActivity.class);
                my.putExtra("edit","1");
                my.putExtra("position",String.valueOf(position));
                activity.startActivity(my);
            }
        });
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,address;
        ImageButton delete,edit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.account_recycler_name);
            address = itemView.findViewById(R.id.account_recycler_address);
            delete = itemView.findViewById(R.id.account_recycler_delete);
            edit = itemView.findViewById(R.id.account_recycler_edit);
        }
    }
}
