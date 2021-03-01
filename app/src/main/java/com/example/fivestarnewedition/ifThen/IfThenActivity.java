package com.example.fivestarnewedition.ifThen;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.fivestarnewedition.ADDSection.AddActivity;
import com.example.fivestarnewedition.Account.AccountManageActivity;
import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.Drawer.SettingPanelActivity;
import com.example.fivestarnewedition.Drawer.SimCardOption;
import com.example.fivestarnewedition.Log.LogActivity;
import com.example.fivestarnewedition.Main.MainActivity;
import com.example.fivestarnewedition.R;
import com.example.fivestarnewedition.Security.PasswordActivity;
import com.example.fivestarnewedition.Senario.SenarioActivity;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.navigation.NavigationView;

public class IfThenActivity extends AppCompatActivity {
    private BottomAppBar bottomAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_if_then);


        initialize();
        init();

        setUpBottomAppBar();

        //click event over FAB
        findViewById(R.id.ifthenfab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addIfThen();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initialize();
    }

    private void initialize() {
        RecyclerView recyclerView = findViewById(R.id.if_then_recycler);
        IfThenAdapter adapter = new IfThenAdapter(IfThenActivity.this, Constant.getIfThens(IfThenActivity.this));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void homeIntent(View view) {
        Intent my = new Intent(IfThenActivity.this, MainActivity.class);
        startActivity(my);
        finish();
    }

    public void senarioIntent(View view) {
        Intent my = new Intent(IfThenActivity.this, SenarioActivity.class);
        startActivity(my);
        finish();
    }

    public void addIfThen() {
        Intent my = new Intent(IfThenActivity.this, AddIfThenActivity.class);
        startActivity(my);
    }

    public void logIntent(View view) {
        Intent my = new Intent(IfThenActivity.this, LogActivity.class);
        startActivity(my);
    }

    public void cameraIntent(View view) {
        /**
         * Outer switch
         */
    }

    public void securityIntent(View view) {
        Intent my = new Intent(IfThenActivity.this, PasswordActivity.class);
        if (Constant.getPassword(IfThenActivity.this) == null) {
            my.putExtra("code", "0");
        }else {
            my.putExtra("code", "1");
        }
        startActivity(my);
    }

    public void musicIntent(View view) {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_MUSIC_PLAYER);
        startActivity(intent);
    }

    private void init(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        DrawerLayout drawer = findViewById(R.id.ifthen_drawer_layout);
        NavigationView navigationView = findViewById(R.id.if_then_activity_nav);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.drawer_account:
                        Intent my = new Intent(IfThenActivity.this, AccountManageActivity.class);
                        startActivity(my);
                        break;
                    case R.id.drawer_sim_card:
                        if (Constant.getMainAccount(IfThenActivity.this) == null){
                            Toast.makeText(getApplicationContext(),"There is no Account",Toast.LENGTH_SHORT).show();
                            break;
                        }
                        Intent mys = new Intent(IfThenActivity.this, SimCardOption.class);
                        startActivity(mys);
                        break;
                    case R.id.drawer_add_device_group:
                        Intent mya = new Intent(IfThenActivity.this, AddActivity.class);
                        startActivity(mya);
                        break;
                    case R.id.drawer_panel_setting:
                        Intent mym = new Intent(IfThenActivity.this, SettingPanelActivity.class);
                        startActivity(mym);
                        break;
                }
                return false;
            }
        });
    }

    private void setUpBottomAppBar() {
        //find id
        bottomAppBar = findViewById(R.id.ifthenbar);

        //set bottom bar to Action bar as it is similar like Toolbar
        setSupportActionBar(bottomAppBar);

        //click event over Bottom bar menu item
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(IfThenActivity.this, "Notification clicked.", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}