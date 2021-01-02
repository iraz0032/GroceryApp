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
    private int[] categoryImages;
    private Context context;
    private String[] itemsTitle;
    private OnItemClickListener onItemClickListener;

    //this for click on items listener interface method
    public interface OnItemClickListener {
        void OnClickListener(int position);
    }

    public void setOnclickListener(OnItemClickListener onClickListener) {
        onItemClickListener = onClickListener;
    }

    public PopularProduct(Context context, String[] itemsTitle, int[] categoryImages) {
        this.context = context;
        this.itemsTitle = itemsTitle;
        this.categoryImages = categoryImages;
    }

    @NonNull
    @Override
    public PopularProductVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.popular_items, parent, false);
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
        private TextView title;
        private ImageView imageView;

        public PopularProductVH(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.popularProductImageId);
            title = itemView.findViewById(R.id.popularProductTitleId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (onItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListener.OnClickListener(position);
                        }
                    }

                }
            });
        }
    }
}
