package com.example.grocery.navbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocery.R;
import com.example.grocery.adapters.WishListAdapter;

public class WishlistFragment extends Fragment {
    private RecyclerView wishListRecyclerView;
    private int[] itemsImages = {R.drawable.apple, R.drawable.apple, R.drawable.apple};
    private String[] itemsTitles = {"apple", "apple", "apple"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);

        //id
        wishListRecyclerView = view.findViewById(R.id.wishListRecyclerViewId);


        wishListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //adapter class
        WishListAdapter adapter = new WishListAdapter(getContext(), itemsImages, itemsTitles);
        wishListRecyclerView.setAdapter(adapter);


        return view;
    }
}