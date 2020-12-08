package com.example.grocery.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.grocery.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class ImageSliderAdapter extends SliderViewAdapter<ImageSliderAdapter.SliderViewAdapter> {
    Context context;
    int[] sliderImageItems;

    public ImageSliderAdapter(Context context, int[] sliderImageItems) {
        this.context = context;
        this.sliderImageItems = sliderImageItems;
    }

    @Override
    public SliderViewAdapter onCreateViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.slider_image_sample, parent, false);

        return new SliderViewAdapter(v);
    }

    @Override
    public void onBindViewHolder(SliderViewAdapter viewHolder, int position) {

        viewHolder.imageView.setImageResource(sliderImageItems[position]);

    }

    @Override
    public int getCount() {
        return sliderImageItems.length;
    }

    public class SliderViewAdapter extends com.smarteist.autoimageslider.SliderViewAdapter.ViewHolder {
        ImageView imageView;

        public SliderViewAdapter(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.sliderImageView);
        }
    }
}
