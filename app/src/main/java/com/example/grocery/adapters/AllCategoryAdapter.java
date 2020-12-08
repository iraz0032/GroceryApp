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

public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.HolderView> {
    private Context context;
    private String[] categoryTitle;
    private int[] categoryImages;
    public AllCategoryAdapter(Context context, String[] categoryTitle, int[] categoryImages) {
        this.context = context;
        this.categoryTitle = categoryTitle;
        this.categoryImages = categoryImages;
    }

    @NonNull
    @Override
    public HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.allcategories_sample, parent, false);

        return new HolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderView holder, int position) {

        holder.text.setText(categoryTitle[position]);
        holder.image.setImageResource(categoryImages[position]);
    }

    @Override
    public int getItemCount() {
        return categoryTitle.length;
    }

    public static class HolderView extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;

        public HolderView(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.cateImage);
            text = itemView.findViewById(R.id.catName);
        }
    }
}
