package com.example.grocery.navbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocery.Checkout.CheckOutActivity;
import com.example.grocery.R;
import com.example.grocery.adapters.CartAdapter;
import com.google.android.material.button.MaterialButton;


public class CartFragment extends Fragment {
    private RecyclerView cartRecyclerView;
    private Toolbar toolbar;
    private MaterialButton checkOutBtn;
    private int[] itemsImages = {R.drawable.apple, R.drawable.apple, R.drawable.apple, R.drawable.apple, R.drawable.apple, R.drawable.apple, R.drawable.apple, R.drawable.apple, R.drawable.apple, R.drawable.apple, R.drawable.apple, R.drawable.apple};
    private String[] itemsTitles = {"apple", "apple", "apple", "apple", "apple", "apple", "apple", "apple", "apple", "apple", "apple", "apple"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        cartRecyclerView = view.findViewById(R.id.cartRecyclerViewId);
        checkOutBtn = view.findViewById(R.id.cartCheckOutBtn);
        cartRecyclerView.setHasFixedSize(true);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        CartAdapter adapter = new CartAdapter(getContext(), itemsImages, itemsTitles);
        cartRecyclerView.setAdapter(adapter);

        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CheckOutActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}