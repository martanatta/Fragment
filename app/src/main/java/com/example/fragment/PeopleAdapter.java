package com.example.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private List<People> mList;
    private PeopleClickListener mClickListener;

    public PeopleAdapter(Context ctx, List<People> items) {
        inflater = LayoutInflater.from(ctx);
        this.mList = items;
    }

    @Override
    public PeopleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_people, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PeopleAdapter.MyViewHolder holder, final int position) {
        People item = mList.get(position);
        holder.mTextName.setText(item.name);
        holder.mTextEmail.setText(item.email);
        holder.mTextPhone.setText(item.phone);

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onClick(position);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnItemClickListener(PeopleClickListener listener) {
        mClickListener = listener;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private MaterialCardView mCardView;
        private TextView mTextName;
        private TextView mTextEmail;
        private TextView mTextPhone;

        MyViewHolder(View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.card_view);
            mTextName = itemView.findViewById(R.id.text_name);
            mTextEmail = itemView.findViewById(R.id.text_email);
            mTextPhone = itemView.findViewById(R.id.text_phone);
        }
    }
}