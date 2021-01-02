package com.example.grocery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.grocery.Checkout.CheckOutActivity;
import com.example.grocery.adapters.RelatedProduct;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.ms.square.android.expandabletextview.ExpandableTextView;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener, RelatedProduct.OnRelatedProductItemClickListener {
    private MaterialButton buyNowBtn, addToCart;
    private ExpandableTextView expTv1;
    private CoordinatorLayout snackBarLayout;
    private ImageView wishListImage, increaseItemsBtn, decreaseItemBtn;
    private int flag = 1;
    private BottomNavigationView bottomNavigationView;
    private TextView totalProductText;
    private int product = 1;
    private RecyclerView relatedProductRecycler;
    private Toolbar toolbar;
    private int [] relatedProductImage = {R.drawable.apple,R.drawable.broccoli,R.drawable.fish,R.drawable.meat};
    private String [] relatedProductTitle = {"apple","broccoli","fish","meat"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        //id's
        toolbar=findViewById(R.id.productDetailsToolBar);
        buyNowBtn = findViewById(R.id.productDetailsBuyNowBtnId);
        addToCart = findViewById(R.id.productDetailsAddToCartBtnId);
        expTv1 = findViewById(R.id.expand_text_view);
        snackBarLayout = findViewById(R.id.snackbarLayout);
        wishListImage = findViewById(R.id.ProductDetailsAddWishListImageId);
        increaseItemsBtn = findViewById(R.id.productDetailsCartAddItemsImageId);
        decreaseItemBtn = findViewById(R.id.productDetailsMinusItemsImageId);
        totalProductText = findViewById(R.id.productDetailsTotalProductId);
        relatedProductRecycler = findViewById(R.id.productDetailsRecyclerViewId);

        //set tool bar
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //remove app title from toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //add listener
        buyNowBtn.setOnClickListener(this);
        addToCart.setOnClickListener(this);
        wishListImage.setOnClickListener(this);
        increaseItemsBtn.setOnClickListener(this);
        decreaseItemBtn.setOnClickListener(this);

        // IMPORTANT - call setText on the ExpandableTextView to set the text content to display
        expTv1.setText(getString(R.string.dummy));

        //related product recyclerView
        relatedProductRecycler.setItemAnimator(new DefaultItemAnimator());
        relatedProductRecycler.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL);
        relatedProductRecycler.setLayoutManager(staggeredGridLayoutManager);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);

        //adapter for recyclerView
        RelatedProduct relatedProduct = new RelatedProduct(this,relatedProductTitle,relatedProductImage);
        relatedProductRecycler.setAdapter(relatedProduct);
        //add listener to related product items
        relatedProduct.setOnRelatedProductItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //here stored id
        int id = v.getId();


        if (id == R.id.productDetailsBuyNowBtnId) {
            Intent intent = new Intent(this, CheckOutActivity.class);
            startActivity(intent);
        }
        //when user select 'add to cart' its show a toast message
        if (id == R.id.productDetailsAddToCartBtnId) {
            Snackbar snackbar = Snackbar.make(snackBarLayout, "Added to cart successfully", Snackbar.LENGTH_LONG);

            snackbar.setAction("Go to cart", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ProductDetailActivity.this, MainActivity.class);
                    intent.putExtra("tag", "1");
                    startActivity(intent);

                }
            });
            snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
            snackbar.setAnchorView(buyNowBtn);
            snackbar.setActionTextColor(Color.parseColor("#FF7F50"));
            snackbar.show();
        }
        if (id == R.id.ProductDetailsAddWishListImageId) {

            final ProgressDialog progress = new ProgressDialog(this,R.style.myAlertStyle);

            if (flag == 1) {
                wishListImage.setImageResource(R.drawable.wishlist_fill);
                Log.d("Tag", "okkkk");
                flag = 0;
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setIndeterminate(true);
                progress.setProgress(2);
                progress.setProgress(0);
                progress.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progress.dismiss();
                    }
                }, 500);

            } else if (flag == 0) {
                wishListImage.setImageResource(R.drawable.favorite);
                Log.d("Tag", "okkkk2");
                flag = 1;
                progress.dismiss();
            }
        }
        if (id == R.id.productDetailsCartAddItemsImageId) {
            product++;
            final ProgressDialog progress = new ProgressDialog(this,R.style.myAlertStyle);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.setProgress(2);
            progress.setProgress(0);
            progress.show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progress.dismiss();
                }
            }, 500);
            totalProductText.setText(String.valueOf(product));
            Log.d("Tag", String.valueOf(product));
        }
        if (id == R.id.productDetailsMinusItemsImageId) {
            if (product > 1) {
                product--;
                totalProductText.setText(String.valueOf(product));
            }
        }
    }

    @Override
    public void onClickListener(int position) {
        for(int i = 0; i<relatedProductTitle.length; i++){
            if (i==position){
                Toast.makeText(this, "Items :"+relatedProductTitle[position],Toast.LENGTH_SHORT).show();
            }
        }
    }
}