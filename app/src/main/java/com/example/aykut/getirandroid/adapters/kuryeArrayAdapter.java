package com.example.aykut.getirandroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aykut.getirandroid.R;
import com.example.aykut.getirandroid.activities.GiveOrder;
import com.example.aykut.getirandroid.models.kurye;

import java.util.ArrayList;

public class kuryeArrayAdapter extends RecyclerView.Adapter<kuryeArrayAdapter.ViewHolder> {

    private Context mContext;
    private int listKuryeLayout;
    private ArrayList<kurye> kuryeList;

    public kuryeArrayAdapter(int layoutId, ArrayList<kurye>  kuryeList, Context context){
        mContext = context;
        listKuryeLayout = layoutId;
        this.kuryeList = kuryeList;
    }

    @Override
    public int getItemCount() {
        return kuryeList == null ? 0 : kuryeList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(listKuryeLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);

        Button make_order = (Button) view.findViewById(R.id.make_order);
        make_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, GiveOrder.class);
                mContext.startActivity(i);
            }
        });


        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int listPosition) {
        final TextView item = holder.kuryeler;
        item.setText(kuryeList.get(listPosition).getName());

        holder.kuryeler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(item);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.kurye_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.contact_info:
                    Toast.makeText(mContext, "Contact Info", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView kuryeler;
        public Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            kuryeler = (TextView) itemView.findViewById(R.id.kurye_ad);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //Log.d("onclick", "onClick " + getLayoutPosition() + " " + kuryeler.getText());
        }
    }
}
