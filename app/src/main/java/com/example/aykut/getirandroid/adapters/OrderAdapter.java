package com.example.aykut.getirandroid.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aykut.getirandroid.R;
import com.example.aykut.getirandroid.models.Order;
import com.shalan.mohamed.itemcounterview.IncDecView;

import java.util.List;

/**
 * Created by aykut on 17.02.2018.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {


    private List<Order> orderList;


    public OrderAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public IncDecView counter;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.itemName);
            counter = (IncDecView) view.findViewById(R.id.itemCounter);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.name.setText(order.getItemName());
        //holder.counter.setStartCounterValue(order.getCounter());
    }



    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
