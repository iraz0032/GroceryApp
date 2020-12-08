package com.example.grocery.Checkout.CheckoutFragment.Payment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.grocery.Checkout.CheckOutActivity;
import com.example.grocery.Checkout.CheckoutFragment.OrderFragment;
import com.example.grocery.Checkout.CheckoutFragment.Payment.PaymentFragments.BankCardFragment;
import com.example.grocery.Checkout.CheckoutFragment.Payment.PaymentFragments.PaypalFragment;
import com.example.grocery.Checkout.CheckoutFragment.ShippingFragment;
import com.example.grocery.R;


public class PaymentFragment extends Fragment implements View.OnClickListener {
    private BankCardFragment bankCardFragment;
    private PaypalFragment paypalFragment;
    private CardView creditCard, paypalCard, mobileBankingCard;
    private LinearLayout paypalLinear, creditCardLinear, mobileBankingLinear;
    private ImageView creditImage, paypalImage, mobileBankingImage;
    private Button paymentNextBtn,previousToShipping;
    private OrderFragment orderFragment;
    private ImageView imageView1,imageView2,imageView3;
    private TextView shipping, payment, order;
    private View view1, view2;
    private ShippingFragment shippingFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment, container, false);
        creditCard = (CardView) view.findViewById(R.id.creditCardPaymentId);
        mobileBankingCard = view.findViewById(R.id.mobileBankingCardId);
        paypalCard = view.findViewById(R.id.paypalPayId);
        paypalLinear = view.findViewById(R.id.paypalLinearId);
        creditCardLinear = view.findViewById(R.id.creditCardLinearId);
        mobileBankingLinear = view.findViewById(R.id.mobileBankingLinearId);
        creditImage = view.findViewById(R.id.creditCardImageId);
        paypalImage = view.findViewById(R.id.paypalImageId);
        mobileBankingImage = view.findViewById(R.id.mobileBankingImageId);
        paymentNextBtn = view.findViewById(R.id.paymentNextBtnId);
        shipping = CheckOutActivity.checkOutActivity.findViewById(R.id.shippingTextId);
        payment = CheckOutActivity.checkOutActivity.findViewById(R.id.paymentTextId);
        order = CheckOutActivity.checkOutActivity.findViewById(R.id.orderTextId);
        view1 = CheckOutActivity.checkOutActivity.findViewById(R.id.line1Id);
        view2 = CheckOutActivity.checkOutActivity.findViewById(R.id.line2Id);
        imageView3 = CheckOutActivity.checkOutActivity.findViewById(R.id.checkImageView3);
        imageView1 = CheckOutActivity.checkOutActivity.findViewById(R.id.checkImageView1);
        imageView2 = CheckOutActivity.checkOutActivity.findViewById(R.id.checkImageView2);
        previousToShipping = view.findViewById(R.id.previousToShipping);

        bankCardFragment = new BankCardFragment();
        paypalFragment = new PaypalFragment();
        orderFragment = new OrderFragment();
        shippingFragment = new ShippingFragment();

        creditCard.setOnClickListener(this);
        paypalCard.setOnClickListener(this);
        mobileBankingCard.setOnClickListener(this);

        paymentNextBtn.setOnClickListener(this);
        previousToShipping.setOnClickListener(this);
        fragmentTransaction(bankCardFragment);

        checkOutStepNext();
        return view;
    }

    private void checkOutStepNext() {
        view2.setVisibility(View.VISIBLE);
        imageView3.setVisibility(View.VISIBLE);
        imageView3.setImageResource(R.drawable.ic_baseline_radio_button_checked_24);
        payment.setTextColor(getResources().getColor(R.color.colorBlack));
        shipping.setTextColor(Color.GRAY);
        order.setVisibility(View.VISIBLE);
        order.setTextColor(Color.GRAY);
    }

    private void fragmentTransaction(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.payFrameId, fragment);
        transaction.commit();
    }

    private void fragmentTransaction2(Fragment fragment1) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.checkOutFrameLayoutId, fragment1);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.creditCardPaymentId) {
            mobileBankingImage.setColorFilter(Color.GRAY);
            paypalImage.setColorFilter(Color.GRAY);

            creditImage.setColorFilter(getResources().getColor(R.color.colorPrimary));
            fragmentTransaction(bankCardFragment);
            mobileBankingLinear.setBackground(getResources().getDrawable(R.drawable.payment_method_card_bg_without_stroke));
            creditCardLinear.setBackground(getResources().getDrawable(R.drawable.payment_method_card_bg));
            paypalLinear.setBackground(getResources().getDrawable(R.drawable.payment_method_card_bg_without_stroke));


        }
        if (v.getId() == R.id.paypalPayId) {
            paypalImage.setColorFilter(getResources().getColor(R.color.colorPrimary));
            creditImage.setColorFilter(Color.GRAY);
            mobileBankingImage.setColorFilter(Color.GRAY);
            fragmentTransaction(paypalFragment);
            mobileBankingLinear.setBackground(getResources().getDrawable(R.drawable.payment_method_card_bg_without_stroke));
            paypalLinear.setBackground(getResources().getDrawable(R.drawable.payment_method_card_bg));
            creditCardLinear.setBackground(getResources().getDrawable(R.drawable.payment_method_card_bg_without_stroke));
        }

        if (v.getId() == R.id.mobileBankingCardId) {
            mobileBankingImage.setColorFilter(getResources().getColor(R.color.colorPrimary));
            paypalImage.setColorFilter(Color.GRAY);
            creditImage.setColorFilter(Color.GRAY);
            mobileBankingLinear.setBackground(getResources().getDrawable(R.drawable.payment_method_card_bg));
            creditCardLinear.setBackground(getResources().getDrawable(R.drawable.payment_method_card_bg_without_stroke));
            paypalLinear.setBackground(getResources().getDrawable(R.drawable.payment_method_card_bg_without_stroke));

        }
        if (v.getId() == R.id.paymentNextBtnId) {
            view2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            imageView2.setImageResource(R.drawable.check_icon);
            imageView3.setImageResource(R.drawable.ic_round_error_24);
            order.setTextColor(getResources().getColor(R.color.colorBlack));
            shipping.setTextColor(Color.GRAY);
            payment.setTextColor(Color.GRAY);
            fragmentTransaction2(orderFragment);
        }
        if(v.getId()==R.id.previousToShipping)
        {
            view1.setVisibility(View.VISIBLE);
            view1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            imageView2.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
            imageView3.setVisibility(View.INVISIBLE);
            view1.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            imageView1.setImageResource(R.drawable.ic_round_error_24);
            imageView2.setImageResource(R.drawable.ic_baseline_radio_button_checked_24);
            order.setVisibility(View.INVISIBLE);
            shipping.setTextColor(getResources().getColor(R.color.colorBlack));
            fragmentTransaction2(shippingFragment);
            payment.setTextColor(Color.GRAY);
        }
    }

}