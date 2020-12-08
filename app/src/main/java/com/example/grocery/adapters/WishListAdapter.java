package com.example.grocery.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocery.R;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolderW> {
    Context context;
    int[] itemsImages;
    String[] itemsTitles;

    public WishListAdapter(Context context, int[] itemsImages, String[] itemsTitles) {
        this.context = context;
        this.itemsImages = itemsImages;
        this.itemsTitles = itemsTitles;
    }

    @NonNull
    @Override
    public ViewHolderW onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wishlist_sample, parent, false);
        return new ViewHolderW(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolderW holder, int position) {

        holder.imageView.setImageResource(itemsImages[position]);
        holder.textView.setText(itemsTitles[position]);
    }

    @Override
    public int getItemCount() {
        return itemsTitles.length;
    }

    public class ViewHolderW extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolderW(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.wishListImageItemId);
            textView = itemView.findViewById(R.id.wishListTitleId);
        }
    }
}
