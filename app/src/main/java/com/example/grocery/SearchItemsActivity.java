package com.example.grocery;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class SearchItemsActivity extends AppCompatActivity {
    private TextInputEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_items);

        editText = findViewById(R.id.searchTextBarId);

        editText.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

    }
}