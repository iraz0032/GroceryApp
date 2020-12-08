package com.example.grocery.Checkout.CheckoutFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.grocery.Checkout.CheckOutActivity;
import com.example.grocery.Checkout.CheckoutFragment.Payment.PaymentFragment;
import com.example.grocery.R;

public class ShippingFragment extends Fragment implements View.OnClickListener {
    private Button button;
    private ImageView imageView1, imageView2, imageView3;
    private TextView shipping, payment, order;
    private View view1, view2;
    private PaymentFragment paymentFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shipping, container, false);
        button = view.findViewById(R.id.checkOutNextBtnId);

        imageView1 = CheckOutActivity.checkOutActivity.findViewById(R.id.checkImageView1);
        imageView2 = CheckOutActivity.checkOutActivity.findViewById(R.id.checkImageView2);
        imageView3 = CheckOutActivity.checkOutActivity.findViewById(R.id.checkImageView3);
        view1 = CheckOutActivity.checkOutActivity.findViewById(R.id.line1Id);
        view2 = CheckOutActivity.checkOutActivity.findViewById(R.id.line2Id);
        shipping = CheckOutActivity.checkOutActivity.findViewById(R.id.shippingTextId);

        paymentFragment = new PaymentFragment();

        button.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.checkOutNextBtnId) {
            view1.setVisibility(View.VISIBLE);
            view1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            imageView2.setVisibility(View.VISIBLE);
            imageView1.setImageResource(R.drawable.check_icon);
            imageView2.setImageResource(R.drawable.ic_round_error_24);
            shipping.setTextColor(getResources().getColor(R.color.colorBlack));
            fragmentTransaction(paymentFragment);
        }
    }

    private void fragmentTransaction(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.checkOutFrameLayoutId, fragment);
            transaction.commit();
        }
    }
}