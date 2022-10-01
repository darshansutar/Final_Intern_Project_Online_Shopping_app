package com.OnlineShopping.login;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.OnlineShopping.login.com.hypermart.login.DatabaseHelper;



import java.util.ArrayList;
import java.util.List;

import utils.adapter.ShoeItemAdapter;
import utils.model.ShoeCart;
import utils.model.ShoeItem;
import viewmodel.CartViewModel;

public class MainActivity extends AppCompatActivity implements ShoeItemAdapter.ShoeClickedListeners {

    private RecyclerView recyclerView;
    private List<ShoeItem> shoeItemList;
    private ShoeItemAdapter adapter;
    private CartViewModel viewModel;
    private List<ShoeCart> shoeCartList;
    //    private CoordinatorLayout coordinatorLayout;
    private ImageView cartImageView;
    private DrawerLayout dl;
    private ActionBarDrawerToggle adt;
    private String strText;
    DatabaseHelper db;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initializeVariables();
        setUpList();

        adapter.setShoeItemList(shoeItemList);
        recyclerView.setAdapter(adapter);



        cartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);

        dl = this.findViewById(R.id.coordinatorLayout);
        adt = new ActionBarDrawerToggle(this, dl, toolbar, R.string.open, R.string.close);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        db =  new DatabaseHelper(navigationView.getContext());


        dl.addDrawerListener(adt);
        adt.syncState();

        if (savedInstanceState == null) {

         // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

            navigationView.setCheckedItem(R.id.home);

        }

        View header = navigationView.getHeaderView(0);
        TextView profile_name = (TextView) header.findViewById(R.id.profile_name);
        TextView profile_email = (TextView) header.findViewById(R.id.profile_email);

        SharedPreferences sp1 = this.getSharedPreferences("Login",Context.MODE_PRIVATE);

        strText = sp1.getString("CurrentUser", null);


        Cursor rs = db.getDataById(strText);

        if(rs.getCount()==0){

            return;

        }

        else {

            while(rs.moveToNext()){

                String newUserName=rs.getString(0);
                String newEmail= rs.getString(1);



                profile_name.setText(newUserName);

                profile_email.setText(newEmail);


            }

        }


    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.home:

                startActivity(new Intent(MainActivity.this, MainActivity.class));
                break;


            case R.id.account:
                 getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MyAccountFragment()).commit();
                 recyclerView.setVisibility(View.INVISIBLE);

                break;

            case R.id.cart:
                startActivity(new Intent(MainActivity.this, CartActivity.class));
                break;

            case R.id.logout:
                new AlertDialog.Builder(this)
                        .setTitle("Logout Confirmation")

                        .setMessage("Are you sure you want to logout?").setIcon(R.drawable.warning)
                        .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                MainActivity.this.startActivity(new Intent(MainActivity.this, SignIn.class));

                            }
                        }).setNegativeButton("NO",null).show();


        }

        dl.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            NavigationView navigationView = findViewById(R.id.nav_view);
            super.onBackPressed();

        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        viewModel.getAllCartItems().observe(this, new Observer<List<ShoeCart>>() {
            @Override
            public void onChanged(List<ShoeCart> shoeCarts) {
                shoeCartList.addAll(shoeCarts);
            }
        });
    }

    private void setUpList() {
        shoeItemList.add(new ShoeItem("Gaming Monitor", "Logitech", R.drawable.key, 50000));
        shoeItemList.add(new ShoeItem("Ryzen 5 Processor", "AMD", R.drawable.amd, 20000));
        shoeItemList.add(new ShoeItem("i7 processor", "INTEL", R.drawable.intel, 30000));
        shoeItemList.add(new ShoeItem("ROG Book", "ASUS", R.drawable.lap01, 90000));
        shoeItemList.add(new ShoeItem("ROG zenBook", "ASUS", R.drawable.lap, 95000));
        shoeItemList.add(new ShoeItem("Gaming Keyboard", "Logitech", R.drawable.keyboard, 20000));
        shoeItemList.add(new ShoeItem("RTX 580", "NVIDIA", R.drawable.gpu, 30000));
        shoeItemList.add(new ShoeItem("Gaming Motherboard", "ASUS", R.drawable.motherboard, 15000));

    }

    private void initializeVariables() {

        cartImageView = findViewById(R.id.cartIv);
        dl = findViewById(R.id.coordinatorLayout);
        shoeCartList = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
        shoeItemList = new ArrayList<>();
        recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));


        adapter = new ShoeItemAdapter(this);

    }

    @Override
    public void onCardClicked(ShoeItem shoe) {

        Intent intent = new Intent(MainActivity.this, DeltailedActivity.class);
        intent.putExtra("shoeItem", shoe);
        startActivity(intent);
    }

    @Override
    public void onAddToCartBtnClicked(ShoeItem shoeItem) {
        ShoeCart shoeCart = new ShoeCart();
        shoeCart.setShoeName(shoeItem.getShoeName());
        shoeCart.setShoeBrandName(shoeItem.getShoeBrandName());
        shoeCart.setShoePrice(shoeItem.getShoePrice());
        shoeCart.setShoeImage(shoeItem.getShoeImage());

        final int[] quantity = {1};
        final int[] id = new int[1];

        if (!shoeCartList.isEmpty()) {
            for (int i = 0; i < shoeCartList.size(); i++) {
                if (shoeCart.getShoeName().equals(shoeCartList.get(i).getShoeName())) {
                    quantity[0] = shoeCartList.get(i).getQuantity();
                    id[0] = shoeCartList.get(i).getId();
                    quantity[0]++;

                }
            }
        }

        Log.d("TAG", "onAddToCartBtnClicked: " + quantity[0]);

        if (quantity[0] == 1) {
            shoeCart.setQuantity(quantity[0]);
            shoeCart.setTotalItemPrice(quantity[0] * shoeCart.getShoePrice());
            viewModel.insertCartItem(shoeCart);
        } else {
            viewModel.updateQuantity(id[0], quantity[0]);
            viewModel.updatePrice(id[0], quantity[0] * shoeCart.getShoePrice());
        }

        makeSnackBar("Item Added To Cart");
    }

    private void makeSnackBar(String msg) {
        Snackbar.make(dl, msg, Snackbar.LENGTH_SHORT)
                .setAction("Go to Cart", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this, CartActivity.class));
                    }
                }).show();
    }
}




