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

public class PopularProduct extends RecyclerView.Adapter<PopularProduct.PopularProductVH> {
    int[] categoryImages;
    Context context;
    private String[] itemsTitle;

    public PopularProduct(Context context, String[] itemsTitle, int[] categoryImages) {
        this.context = context;
        this.itemsTitle = itemsTitle;
        this.categoryImages = categoryImages;
    }

    @NonNull
    @Override
    public PopularProductVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_items, parent, false);
        return new PopularProductVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularProductVH holder, int position) {

        holder.title.setText(itemsTitle[position]);
        holder.imageView.setImageResource(categoryImages[position]);
    }

    @Override
    public int getItemCount() {
        return itemsTitle.length;
    }

    public class PopularProductVH extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;

        public PopularProductVH(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.popularProductImageId);
            title = itemView.findViewById(R.id.popularProductTitleId);
        }
    }
}
