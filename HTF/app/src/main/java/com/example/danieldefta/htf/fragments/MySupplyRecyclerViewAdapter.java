package com.example.danieldefta.htf.fragments;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danieldefta.htf.R;
import com.example.danieldefta.htf.fragments.SuppliesFragment.OnListFragmentInteractionListener;
import com.example.danieldefta.htf.models.Supply;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MySupplyRecyclerViewAdapter extends RecyclerView.Adapter<MySupplyRecyclerViewAdapter.ViewHolder> {

    private final List<Supply> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MySupplyRecyclerViewAdapter(List<Supply> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_supply, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.lat.setText(mValues.get(position).getLat());
        holder.lng.setText(mValues.get(position).getLng());

        byte[] decodedString = Base64.decode(mValues.get(position).getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0,decodedString.length);
        holder.supplyImage.setImageBitmap(decodedByte);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView lat;
        public final TextView lng;
        public final ImageView supplyImage;
        public Supply mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            lat = (TextView) view.findViewById(R.id.lat);
            lng = (TextView) view.findViewById(R.id.lng);
            supplyImage = (ImageView) view.findViewById(R.id.supplyImage);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
