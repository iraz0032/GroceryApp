package com.example.grocery.Checkout.CheckoutFragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.grocery.Checkout.CheckOutActivity;
import com.example.grocery.Checkout.CheckoutFragment.Payment.PaymentFragment;
import com.example.grocery.Checkout.CheckoutFragment.Payment.PaymentFragments.PaypalFragment;
import com.example.grocery.R;


public class OrderFragment extends Fragment implements View.OnClickListener {

    private Button backToPayment;
    private ImageView creditImage, paypalImage, mobileBankingImage;
    private PaymentFragment paymentFragment;
    private ImageView imageView2,imageView3;
    private TextView shipping, payment, order;
    private View view1, view2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        backToPayment = view.findViewById(R.id.previousToPayment);
        shipping = CheckOutActivity.checkOutActivity.findViewById(R.id.shippingTextId);
        payment = CheckOutActivity.checkOutActivity.findViewById(R.id.paymentTextId);
        order = CheckOutActivity.checkOutActivity.findViewById(R.id.orderTextId);
        view1 = CheckOutActivity.checkOutActivity.findViewById(R.id.line1Id);
        view2 = CheckOutActivity.checkOutActivity.findViewById(R.id.line2Id);
        imageView3 = CheckOutActivity.checkOutActivity.findViewById(R.id.checkImageView3);
        imageView2 = CheckOutActivity.checkOutActivity.findViewById(R.id.checkImageView2);

        paymentFragment = new PaymentFragment();
        backToPayment.setOnClickListener(this);
        return view;
    }
    private void fragmentTransaction(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.checkOutFrameLayoutId, fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.previousToPayment){
            fragmentTransaction(paymentFragment);
            view2.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            view2.setVisibility(View.VISIBLE);
            imageView3.setVisibility(View.VISIBLE);
            imageView3.setImageResource(R.drawable.ic_baseline_radio_button_checked_24);
            payment.setTextColor(getResources().getColor(R.color.colorBlack));
            shipping.setTextColor(Color.GRAY);
            order.setVisibility(View.VISIBLE);
            order.setTextColor(Color.GRAY);
            imageView2.setImageResource(R.drawable.ic_round_error_24);
        }
    }
}