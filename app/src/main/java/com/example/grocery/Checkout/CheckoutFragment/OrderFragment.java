package com.example.grocery.Checkout.CheckoutFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.grocery.Checkout.CheckOutActivity;
import com.example.grocery.Checkout.CheckoutFragment.Payment.PaymentFragment;
import com.example.grocery.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.radiobutton.MaterialRadioButton;


public class OrderFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private Button backToPayment, submittedProduct;
    private ImageView creditImage, paypalImage, mobileBankingImage;
    private PaymentFragment paymentFragment;
    private ImageView imageView2, imageView3;
    private TextView shipping, payment, order;
    private View view1, view2;
    private MaterialRadioButton standardRadioBtn;
    private RadioGroup radioGroup;
    private TextView nameAddressEditBtn,cardEditBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        //id's
        backToPayment = view.findViewById(R.id.previousToPayment);
        submittedProduct = view.findViewById(R.id.submittedPaymentBtn);
        shipping = CheckOutActivity.checkOutActivity.findViewById(R.id.shippingTextId);
        payment = CheckOutActivity.checkOutActivity.findViewById(R.id.paymentTextId);
        order = CheckOutActivity.checkOutActivity.findViewById(R.id.orderTextId);
        view1 = CheckOutActivity.checkOutActivity.findViewById(R.id.line1Id);
        view2 = CheckOutActivity.checkOutActivity.findViewById(R.id.line2Id);
        imageView3 = CheckOutActivity.checkOutActivity.findViewById(R.id.checkImageView3);
        imageView2 = CheckOutActivity.checkOutActivity.findViewById(R.id.checkImageView2);
        standardRadioBtn = view.findViewById(R.id.radioStandardDaysId);
        radioGroup = view.findViewById(R.id.orderRadioGroupId);
        nameAddressEditBtn = view.findViewById(R.id.orderShippingToEditTextBtnId);
        cardEditBtn = view.findViewById(R.id.orderPaymentDetailsEditTextBtnId);

        //fragment initiate
        paymentFragment = new PaymentFragment();

        //add listener
        backToPayment.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        submittedProduct.setOnClickListener(this);
        nameAddressEditBtn.setOnClickListener(this);
        cardEditBtn.setOnClickListener(this);
        //auto check radio button
        standardRadioBtn.setChecked(true);


        return view;

    }


    private void fragmentTransaction(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.checkOutFrameLayoutId, fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.previousToPayment) {
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
        if (v.getId() == R.id.submittedPaymentBtn) {
            Intent intent = new Intent(getContext(), SubmitSuccessfulActivity.class);
            startActivity(intent);
            getActivity().onBackPressed();
        }
        if (v.getId() == R.id.orderShippingToEditTextBtnId) {
            final MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(getContext());
            dialogBuilder.setTitle("Warning");
            dialogBuilder.setMessage("Are you sure you want to close application?");
            dialogBuilder.show();
        }
        if (v.getId() == R.id.orderPaymentDetailsEditTextBtnId){
            final MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(getContext());
            dialogBuilder.setTitle("Warning");
            dialogBuilder.setMessage("Are you sure you want to close application?");
            dialogBuilder.show();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radioStandardDaysId:
                Toast.makeText(getContext(), "Checked 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.radioExpressDaysId:
                Toast.makeText(getContext(), "Checked 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.radioPremiumDaysId:
                Toast.makeText(getContext(), "Checked 3", Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
