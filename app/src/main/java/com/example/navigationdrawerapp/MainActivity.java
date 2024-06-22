package com.example.navigationdrawerapp;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawer;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.myDrawerLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mDrawer = findViewById(R.id.myDrawerLayout);

        // Drawer navigation
        navigationView = findViewById(R.id.nav_view);
        setupDrawerContent(navigationView);

    }
    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    private void selectDrawerItem(MenuItem item) {
        Fragment fragment = null;
        Class fragmnetClass;
        int itemId = item.getItemId();
        if (itemId == R.id.nav_first_fragment) {
            fragmnetClass = FirstFragment.class;
        } else if (itemId == R.id.nav_second_fragment) {
            fragmnetClass = SecondFragment.class;
        } else if (itemId == R.id.nav_third_fragment) {
            fragmnetClass = ThirdFragment.class;
        } else {
            fragmnetClass = FirstFragment.class;
        }

        try {
            fragment = (Fragment) fragmnetClass.newInstance();
        } catch (IllegalAccessException e){
            e.printStackTrace();
        } catch (InstantiationException e){
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fill_content,fragment).commit();
        item.setChecked(true);
        setTitle(item.getTitle());
        mDrawer.closeDrawers();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         switch (item.getItemId()){
             case android.R.id.home:
                 mDrawer.openDrawer(GravityCompat.START);
                 return true;

         }
         return super.onOptionsItemSelected(item);

    }

}