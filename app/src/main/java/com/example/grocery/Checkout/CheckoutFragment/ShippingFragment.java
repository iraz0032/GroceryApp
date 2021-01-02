package com.example.grocery.Checkout.CheckoutFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.grocery.Checkout.CheckOutActivity;
import com.example.grocery.Checkout.CheckoutFragment.Payment.PaymentFragment;
import com.example.grocery.R;

public class ShippingFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Button button;
    private ImageView checkOutProgressCurrent, checkOutProgressNext, checkOutProgressLoading;
    private TextView shippingText, paymentText, orderText;
    private View checkOutProgressLine1, checkOutProgressLine2;
    private PaymentFragment paymentFragment;
    private Spinner countrySpinner;
    private String[] countryList = {"Country", "America", "Bangladesh", "India", "Pakistan", "Sri-lanka", "Nepal", "Bhutan"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shipping, container, false);

        //id's
        button = view.findViewById(R.id.checkOutNextBtnId);
        checkOutProgressCurrent = CheckOutActivity.checkOutActivity.findViewById(R.id.checkImageView1);
        checkOutProgressNext = CheckOutActivity.checkOutActivity.findViewById(R.id.checkImageView2);
        checkOutProgressLoading = CheckOutActivity.checkOutActivity.findViewById(R.id.checkImageView3);
        checkOutProgressLine1 = CheckOutActivity.checkOutActivity.findViewById(R.id.line1Id);
        checkOutProgressLine2 = CheckOutActivity.checkOutActivity.findViewById(R.id.line2Id);
        shippingText = CheckOutActivity.checkOutActivity.findViewById(R.id.shippingTextId);
        countrySpinner = view.findViewById(R.id.countrySpinnerId);

        //fragment initiate
        paymentFragment = new PaymentFragment();

        //add listener
        button.setOnClickListener(this);

        //add adapter on spinner
        ArrayAdapter CountryAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, countryList);
        CountryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        countrySpinner.setAdapter(CountryAdapter);
        countrySpinner.setOnItemSelectedListener(this);
        return view;
    }

    //listener method
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.checkOutNextBtnId) {
            checkOutProgressLine1.setVisibility(View.VISIBLE);
            checkOutProgressLine1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            checkOutProgressNext.setVisibility(View.VISIBLE);
            checkOutProgressCurrent.setImageResource(R.drawable.check_icon);
            checkOutProgressNext.setImageResource(R.drawable.ic_round_error_24);
            shippingText.setTextColor(getResources().getColor(R.color.colorBlack));
            fragmentTransaction(paymentFragment);
        }
    }

    //fragment transaction method
    private void fragmentTransaction(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.checkOutFrameLayoutId, fragment);
            transaction.commit();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}