package com.example.employeecontactmanagerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.VH> {

    public interface OnEmployeeClick {
        void onClick(Employee e);
    }

    private final ArrayList<Employee> list;
    private final OnEmployeeClick listener;

    public EmployeeAdapter(ArrayList<Employee> list, OnEmployeeClick listener) {
        this.list = list;
        this.listener = listener;
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvName, tvInfo;

        VH(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvInfo = itemView.findViewById(R.id.tvInfo);
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Employee e = list.get(position);
        holder.tvName.setText(e.name);
        holder.tvInfo.setText("ID: " + e.empId + " | " + e.dept + " | " + e.phone);

        holder.itemView.setOnClickListener(v -> listener.onClick(e));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}