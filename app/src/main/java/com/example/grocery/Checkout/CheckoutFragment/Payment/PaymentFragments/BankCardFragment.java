package com.example.grocery.Checkout.CheckoutFragment.Payment.PaymentFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.grocery.R;


public class BankCardFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private Spinner cardTypeSpinner;
    private String [] cardType = {"Card Type","Visa Express","Master Card"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_bank_card, container, false);
         //id's
         cardTypeSpinner = view.findViewById(R.id.cardTypeBankCardId);

        ArrayAdapter cardTypeAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,cardType);
        cardTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cardTypeSpinner.setAdapter(cardTypeAdapter);
        cardTypeSpinner.setOnItemSelectedListener(this);
         return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}