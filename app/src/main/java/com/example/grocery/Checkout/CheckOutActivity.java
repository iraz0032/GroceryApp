package com.example.grocery.Checkout;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.grocery.Checkout.CheckoutFragment.OrderFragment;
import com.example.grocery.Checkout.CheckoutFragment.Payment.PaymentFragment;
import com.example.grocery.Checkout.CheckoutFragment.ShippingFragment;
import com.example.grocery.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class CheckOutActivity extends AppCompatActivity {

    boolean isTrue = true;
    private Button nextBtn;
    private int flag = 1;
    private int flag2 = 1;
    private View view1, view2;
    private RelativeLayout relativeLayout;
    public ImageView imageView1, imageView2, imageView3;
    public TextView shipping, payment, order;
    public ShippingFragment shippingFragment;
    private PaymentFragment paymentFragment;
    private OrderFragment orderFragment;
    private List<Fragment> fragmentList = new ArrayList<>();
    public static CheckOutActivity checkOutActivity=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        checkOutActivity = this;
        nextBtn = findViewById(R.id.checkOutNextBtnId);
        imageView1 = findViewById(R.id.checkImageView1);
        imageView2 = findViewById(R.id.checkImageView2);
        imageView3 = findViewById(R.id.checkImageView3);
        relativeLayout = findViewById(R.id.checkMainLayout);
        shipping = findViewById(R.id.shippingTextId);
        payment = findViewById(R.id.paymentTextId);
        order = findViewById(R.id.orderTextId);
        view1 = findViewById(R.id.line1Id);
        view2 = findViewById(R.id.line2Id);

        //initiate_fragment
        shippingFragment = new ShippingFragment();
        paymentFragment = new PaymentFragment();
        orderFragment = new OrderFragment();

        //auto selected page
        fragmentTransaction(shippingFragment);

      //  nextBtn.setOnClickListener(this);

    }

//    @Override
//    public void onClick(View v) {
//        if (flag == 1) {
//            view1.setVisibility(View.VISIBLE);
//            view1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//            imageView2.setVisibility(View.VISIBLE);
//            imageView1.setImageResource(R.drawable.check_icon);
//            imageView2.setImageResource(R.drawable.ic_round_error_24);
//            shipping.setTextColor(getResources().getColor(R.color.colorBlack));
//            flag = 2;
//            fragmentTransaction(shippingFragment);
//
//        }
//        if (flag == 2) {
//            view2.setVisibility(View.VISIBLE);
//            imageView3.setVisibility(View.VISIBLE);
//            imageView3.setImageResource(R.drawable.ic_baseline_radio_button_checked_24);
//            payment.setTextColor(getResources().getColor(R.color.colorBlack));
//            shipping.setTextColor(Color.GRAY);
//            fragmentTransaction(paymentFragment);
//            order.setVisibility(View.VISIBLE);
//            order.setTextColor(Color.GRAY);
//            flag = 3;
//        }
//       else if (flag == 3) {
//            view2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//            imageView2.setImageResource(R.drawable.check_icon);
//            imageView3.setImageResource(R.drawable.ic_round_error_24);
//            order.setTextColor(getResources().getColor(R.color.colorBlack));
//            shipping.setTextColor(Color.GRAY);
//            payment.setTextColor(Color.GRAY);
//            fragmentTransaction(orderFragment);
//        }
//
//
//    }

    private void fragmentTransaction(Fragment fragment) {
        if(fragment!=null)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.checkOutFrameLayoutId, fragment);
            transaction.commit();
        }

    }

//    @Override
//    public void onBackPressed() {
//
//        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(this);
//        dialogBuilder.setTitle("Warning");
//        dialogBuilder.setMessage("If you go back you will lost these data");
//        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//        dialogBuilder.create();
//        dialogBuilder.show();
//
//    }
}