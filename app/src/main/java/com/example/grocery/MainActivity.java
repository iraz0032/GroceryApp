package com.example.grocery;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grocery.adapters.AllCategoryAdapter;
import com.example.grocery.navbar.CartFragment;
import com.example.grocery.navbar.HomeFragment;
import com.example.grocery.navbar.WishlistFragment;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private TextView titleText;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment;
    private WishlistFragment wishlistFragment;
    private CartFragment cartFragment;
    public static MainActivity mainActivity = null;
    private BadgeDrawable badge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //id
        toolbar = findViewById(R.id.mainActivityToolbar);
        titleText = findViewById(R.id.titleTexId);
        navigationView = findViewById(R.id.navigationDrawerId);
        drawerLayout = findViewById(R.id.drawerLayoutId);
        toolbar.setNavigationIcon(R.drawable.drawer_icon);
        bottomNavigationView = findViewById(R.id.bottomNavId);
        //set toolbar
        setSupportActionBar(toolbar);


        //remove app title from toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //change app title color
        titleText.setText(getResources().getString(R.string.app_name));

        //initiate fragment call method
        fragmentInitiate();

        // fragment class initiate
        homeFragment = new HomeFragment();
        wishlistFragment = new WishlistFragment();
        cartFragment = new CartFragment();

        //auto set home fragment when open app [using method]
        changeFragment(homeFragment);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNav,
                R.string.CloseNav
        );

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        //add listener to navigation drawer
        navigationView.setNavigationItemSelectedListener(this);

        //add listener to bottom navBar
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        //change navigation drawer icon
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.drawer_icon);


        int menuItemId = bottomNavigationView.getMenu().getItem(2).getItemId();
         badge = bottomNavigationView.getOrCreateBadge(menuItemId);
        badge.setNumber(cartFragment.getCartItemSize());


        getIntentResponse();

    }

    private void getIntentResponse() {
        Intent intent = getIntent();
        String data = intent.getStringExtra("tag");
        if(data!=null && data.contentEquals("1"))
        {
            bottomNavigationView.setSelectedItemId(R.id.cartNavId);
        }

    }

    //inflate menu items
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.toolbar_items, menu);

        return true;
    }

    //this is for actionbar items selected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolNotificationId:
                Toast.makeText(this, "Worked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.toolSearchId:
                Intent intent = new Intent(MainActivity.this, SearchItemsActivity.class);
                startActivity(intent);
        }
        return true;
    }

    //navigation listener method
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //bottom navigation items listener
        if (item.getItemId() == R.id.homeNavId) {
            changeFragment(homeFragment);
            drawerLayout.closeDrawers();
        }
        if (item.getItemId() == R.id.favoriteNavId) {
            changeFragment(wishlistFragment);
            drawerLayout.closeDrawers();
        }
        if (item.getItemId() == R.id.cartNavId) {
            changeFragment(cartFragment);
            drawerLayout.closeDrawers();
            badge.clearNumber();
            badge.clearColorFilter();
        }
        if (item.getItemId() == R.id.categoryId) {
            categoryNavItems();
        }
        if (item.getItemId() == R.id.changePassword) {
            changePassword();
        }

        return true;
    }

    //this method for change password
    private void changePassword() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Change Password");
        //inflate a custom layout to show a custom dialog

        View view = getLayoutInflater().inflate(R.layout.change_password, null);
        builder.setView(view);
        builder.setIcon(R.drawable.warning);

        builder.show();
        drawerLayout.closeDrawers();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void fragmentInitiate() {
        homeFragment = new HomeFragment();
        wishlistFragment = new WishlistFragment();
        cartFragment = new CartFragment();
    }

    private void categoryNavItems() {
        //data for adapter
        int[] categoryImages = {R.drawable.meat, R.drawable.fish, R.drawable.broccoli, R.drawable.soda,
                R.drawable.water, R.drawable.medicine, R.drawable.apple};
        String[] categoryTitle;

        categoryTitle = getResources().getStringArray(R.array.category);
        //when click view all show a alert dialog
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
        builder.setTitle("Categories");

        //inflate layout
        View customLayout = getLayoutInflater().inflate(R.layout.all_category_dialog, null);

        //show data to alert dialog by adapter
        AllCategoryAdapter adapter = new AllCategoryAdapter(this, categoryTitle, categoryImages);
        //set recyclerview to alert dialog
        RecyclerView dialogRecycler = customLayout.findViewById(R.id.allCategoryRecyclerDialog);
        dialogRecycler.setHasFixedSize(true);
        dialogRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        dialogRecycler.setAdapter(adapter);

        //set custom layout to alert dialog
        builder.setView(customLayout);

        //set  cancel dialog button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
        Toast.makeText(this, "All Category", Toast.LENGTH_SHORT).show();
        drawerLayout.closeDrawers();
    }

    private void changeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrameLayoutId, fragment);
        fragmentTransaction.commit();
    }

}