package com.anvata.banksearch.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.anvata.banksearch.R;
import com.anvata.banksearch.server.model.BankResponse;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<BankResponse> names;

    public CustomAdapter(ArrayList<BankResponse> names) {
        this.names = names;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_bank_detail, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BankResponse bankResponse = names.get(position);
       holder.textBankName.setText(bankResponse.getBank_name());
        holder.branchName.setText(bankResponse.getBranch());
        holder.ifscCode.setText(bankResponse.getIfsc());
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textBankName,ifscCode,branchName;

        ViewHolder(View itemView) {
            super(itemView);

            textBankName = (TextView) itemView.findViewById(R.id.bank_name);
            ifscCode = (TextView)itemView.findViewById(R.id.ifsc_code);
            branchName = (TextView)itemView.findViewById(R.id.branch_name);

        }
    }
    public void filterList(ArrayList<BankResponse> filterdNames) {
        this.names = filterdNames;
        notifyDataSetChanged();
    }
}
