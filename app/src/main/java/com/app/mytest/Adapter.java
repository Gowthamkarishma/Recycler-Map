package com.app.mytest;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.SubscriptionsParentViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<PojoClass> subscriptionslist;
    private Context context;
    public onSubmitListener mListener;

    public Adapter(Activity getActivity, ArrayList<PojoClass> jsonArrayCountry, onSubmitListener mListener) {
        this.context = getActivity;
        layoutInflater = LayoutInflater.from(context);
        this.subscriptionslist = jsonArrayCountry;
        this.mListener = mListener;
    }

    public void updateAdapter(ArrayList<PojoClass> jsonArrayCountry) {
        this.subscriptionslist = subscriptionslist;
        notifyDataSetChanged();
    }

    @Override
    public SubscriptionsParentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.rowview_recycler, parent, false);

        return new SubscriptionsParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubscriptionsParentViewHolder holder, final int position) {

        holder.setPosition(position);

        try {
            Picasso.with(context).load(subscriptionslist.get(position).getImage()).error(R.mipmap.ic_launcher_round).into(holder.imageView);
            holder.textView.setText(subscriptionslist.get(position).getName());
            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    subscriptionslist.get(position).setaBoolean(b);

                    mListener.setOnSubmitListener(position, b);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface onSubmitListener {
        void setOnSubmitListener(int pos, boolean outid);
    }

    @Override
    public int getItemCount() {
        return subscriptionslist.size();
    }

    public class SubscriptionsParentViewHolder extends RecyclerView.ViewHolder {

        private int position = -1;
        private ImageView imageView;
        private TextView textView;
        private CheckBox checkBox;

        public SubscriptionsParentViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.image);
            textView = (TextView) itemView.findViewById(R.id.textview);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }
}