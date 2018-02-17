package com.example.android.myapplication;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by fahrianil on 17.02.2018.
 */

public class orderArrayAdapter extends RecyclerView.Adapter<orderArrayAdapter.ViewHolder> {

    private int listOrderLayout;
    private ArrayList<PrevOrders> orderList;

    public orderArrayAdapter(int layoutId, ArrayList<PrevOrders>  orderList){
        listOrderLayout = layoutId;
        this.orderList = orderList;
    }

    @Override
    public int getItemCount() {
        return orderList == null ? 0 : orderList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(listOrderLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int listPosition) {
        TextView item = holder.myOrders;
        item.setText(orderList.get(listPosition).getItem());
        item = holder.whoIsCarrier;
        item.setText(orderList.get(listPosition).getCarrier());
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView myOrders;
        public TextView whoIsCarrier;

        public ViewHolder(View itemView) {
            super(itemView);
            myOrders = (TextView) itemView.findViewById(R.id.item_name);
            whoIsCarrier = (TextView) itemView.findViewById(R.id.carrier_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("onclick", "onClick " + getLayoutPosition() + " " + myOrders.getText());
            Log.d("onclick", "onClick " + getLayoutPosition() + " " + whoIsCarrier.getText());
        }
    }
}