package com.example.grocery.navbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.grocery.ProductDetailActivity;
import com.example.grocery.R;
import com.example.grocery.adapters.AllCategoryAdapter;
import com.example.grocery.adapters.ImageSliderAdapter;
import com.example.grocery.adapters.PopularProduct;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;


public class HomeFragment extends Fragment implements View.OnClickListener, PopularProduct.OnItemClickListener {

    private int[] categoryImages = {R.drawable.meat, R.drawable.fish, R.drawable.broccoli, R.drawable.soda,
            R.drawable.water, R.drawable.medicine, R.drawable.apple};

    private String[] categoryTitle;
    private RecyclerView categoryRecycler, popularProduct;
    private TextView allCategoryViewAllBtn;
    // private PopularProduct popularProductAdapter;
    private AllCategoryAdapter adapter;
    private SliderView sliderView;
    private View view;

    private int[] sliderImageItems = {R.drawable.p_bg, R.drawable.p_bg, R.drawable.p_bg, R.drawable.p_bg};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        //id

        categoryRecycler = view.findViewById(R.id.allCategoryRecyclerViewItem);
        allCategoryViewAllBtn = view.findViewById(R.id.categoryViewAllTextBtn);
        sliderView = view.findViewById(R.id.imageSlider);
        popularProduct = view.findViewById(R.id.popularProductId);

        //get title from resource string file
        categoryTitle = getResources().getStringArray(R.array.category);

        //set recyclerview position
        categoryRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        popularProduct.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
        popularProduct.setItemAnimator(new DefaultItemAnimator());
        //add adapter class for send data
        adapter = new AllCategoryAdapter(getContext(), categoryTitle, categoryImages);

        PopularProduct popularProductAdapter = new PopularProduct(getContext(), categoryTitle, categoryImages);
        categoryRecycler.setAdapter(adapter);
        popularProduct.setAdapter(popularProductAdapter);

        allCategoryViewAllBtn.setOnClickListener(this);

        popularProductAdapter.setOnclickListener(HomeFragment.this);

        //slider image method
        slider();
        return view;
    }

    private void slider() {
        ImageSliderAdapter adapter = new ImageSliderAdapter(getContext(), sliderImageItems);

        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.BLACK);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3); //set scroll delay in seconds :
        sliderView.startAutoCycle();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        //show all category
        if (id == R.id.categoryViewAllTextBtn) {
            showCategoryDialog();
        }
    }

    public void showCategoryDialog() {
        //when click view all show a alert dialog
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
        builder.setTitle("Categories");

        //inflate layout
        View customLayout = getLayoutInflater().inflate(R.layout.all_category_dialog, null);

        //set recyclerview to alert dialog
        RecyclerView dialogRecycler = customLayout.findViewById(R.id.allCategoryRecyclerDialog);
        dialogRecycler.setHasFixedSize(true);
        dialogRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
        dialogRecycler.setAdapter(adapter);

        //set custom layout to alert dialog
        builder.setView(customLayout);

        //set  cancel dialog button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }


    @Override
    public void OnClickListener(int position) {

        for (int i = 0; i < categoryTitle.length; i++) {
            if (i == position) {
                Intent intent = new Intent(getContext(), ProductDetailActivity.class);
                startActivity(intent);
            }
        }
    }
}