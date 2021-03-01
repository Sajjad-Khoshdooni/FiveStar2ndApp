package com.example.fivestarnewedition.Main;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fivestarnewedition.ADDSection.AddActivity;
import com.example.fivestarnewedition.Account.AccountManageActivity;
import com.example.fivestarnewedition.Constant.Account;
import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.Constant.ControlEnum;
import com.example.fivestarnewedition.Drawer.SettingPanelActivity;
import com.example.fivestarnewedition.Drawer.SimCardOption;
import com.example.fivestarnewedition.Log.LogActivity;
import com.example.fivestarnewedition.R;
import com.example.fivestarnewedition.Security.PasswordActivity;
import com.example.fivestarnewedition.Senario.SenarioActivity;
import com.example.fivestarnewedition.ifThen.IfThenActivity;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.navigation.NavigationView;



import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;


import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class MainActivity extends AppCompatActivity {
    private BottomAppBar bottomAppBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initialize();
        initializeImages();
        init();

        setUpBottomAppBar();

        //click event over FAB
        findViewById(R.id.mainfab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent my = new Intent(MainActivity.this, AddActivity.class);
                startActivity(my);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initialize();
    }

    private void initialize() {
        recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        MainActivityRecyclerAdapter adapter = new MainActivityRecyclerAdapter(
                MainActivity.this, Constant.getDevices(MainActivity.this));
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager ml = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(ml);
    }

    public void ifThenIntent(View view) {
        Intent myIntent = new Intent(MainActivity.this, IfThenActivity.class);
        MainActivity.this.startActivity(myIntent);
        MainActivity.this.finish();
    }

    public void senarioIntent(View view) {
        Intent myIntent = new Intent(MainActivity.this, SenarioActivity.class);
        MainActivity.this.startActivity(myIntent);
        MainActivity.this.finish();
    }

    public void cameraIntent(View view) {
        /**
         * Outer switch
         */
    }

    public void securityIntent(View view) {
        Intent my = new Intent(MainActivity.this, PasswordActivity.class);
        if (Constant.getPassword(MainActivity.this) == null) {
            my.putExtra("code", "0");
        } else {
            my.putExtra("code", "1");
        }
        startActivity(my);
    }

    public void musicIntent(View view) {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_MUSIC_PLAYER);
        startActivity(intent);
    }

    public void logIntent(View view) {
        Intent my = new Intent(MainActivity.this, LogActivity.class);
        startActivity(my);
    }

    private void initializeImages() {
        List<Integer> image = new ArrayList<>();
        image.add(R.drawable.a);
        image.add(R.drawable.aa);
        image.add(R.drawable.bb);
        image.add(R.drawable.c);
        image.add(R.drawable.cc);
        image.add(R.drawable.d);
        image.add(R.drawable.dd);
        image.add(R.drawable.e);
        image.add(R.drawable.ee);
        image.add(R.drawable.f);
        image.add(R.drawable.ff);
        image.add(R.drawable.g);
        image.add(R.drawable.gg);
        image.add(R.drawable.h);
        image.add(R.drawable.hh);
        image.add(R.drawable.i);
        image.add(R.drawable.ii);
        image.add(R.drawable.j);
        image.add(R.drawable.jj);
        image.add(R.drawable.k);
        image.add(R.drawable.kk);
        image.add(R.drawable.l);
        image.add(R.drawable.ll);
        image.add(R.drawable.m);
        image.add(R.drawable.mm);
        image.add(R.drawable.n);
        image.add(R.drawable.nn);
        image.add(R.drawable.o);
        image.add(R.drawable.oo);
        image.add(R.drawable.p);
        image.add(R.drawable.pp);
        image.add(R.drawable.q);
        image.add(R.drawable.qq);
        image.add(R.drawable.r);
        image.add(R.drawable.rr);
        image.add(R.drawable.s);
        image.add(R.drawable.t);
        image.add(R.drawable.u);
        image.add(R.drawable.v);
        image.add(R.drawable.w);
        image.add(R.drawable.x);
        image.add(R.drawable.y);
        image.add(R.drawable.z);
        Constant.setImages(image);
    }

    private void init() {

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

            DrawerLayout drawer = findViewById(R.id.main_drawer_layout);
            NavigationView navigationView = findViewById(R.id.main_activity_nav);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.drawer_account:
                            Intent my = new Intent(MainActivity.this, AccountManageActivity.class);
                            startActivity(my);
                            break;
                        case R.id.drawer_sim_card:
                            if (Constant.getMainAccount(MainActivity.this) == null) {
                                Toast.makeText(getApplicationContext(), "There is no Account", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            Intent mys = new Intent(MainActivity.this, SimCardOption.class);
                            startActivity(mys);
                            break;
                        case R.id.drawer_add_device_group:
                            Intent mya = new Intent(MainActivity.this, AddActivity.class);
                            startActivity(mya);
                            break;
                        case R.id.drawer_panel_setting:
                            Intent mym = new Intent(MainActivity.this, SettingPanelActivity.class);
                            startActivity(mym);
                            break;
                    }
                    return false;
                }
            });


    }

        /**
         * set up Bottom Bar
         */
        private void setUpBottomAppBar () {
            //find id
            bottomAppBar = findViewById(R.id.mainbar);

            //set bottom bar to Action bar as it is similar like Toolbar
            setSupportActionBar(bottomAppBar);

            //click event over Bottom bar menu item
            bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Toast.makeText(MainActivity.this, "Notification clicked.", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });


        }
}