package com.example.fivestarnewedition.Main;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fivestarnewedition.ADDSection.AddActivity;
import com.example.fivestarnewedition.Account.AccountManageActivity;
import com.example.fivestarnewedition.Constant.Constant;
import com.example.fivestarnewedition.Constant.Device;
import com.example.fivestarnewedition.Drawer.SettingPanelActivity;
import com.example.fivestarnewedition.Drawer.SimCardOption;
import com.example.fivestarnewedition.Log.LogActivity;
import com.example.fivestarnewedition.R;
import com.example.fivestarnewedition.Security.PasswordActivity;
import com.example.fivestarnewedition.Senario.SenarioActivity;
import com.example.fivestarnewedition.ifThen.IfThenActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setIcon(null);


        initialize();
        initializeImages();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initialize();
    }

    private void initialize(){
        recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        MainActivityRecyclerAdapter adapter = new MainActivityRecyclerAdapter(
                MainActivity.this, Constant.getDevices(MainActivity.this));
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager ml = new GridLayoutManager(this,4);
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
        }else {
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
        image.add(R.drawable.aaa);
        image.add(R.drawable.b);
        image.add(R.drawable.bb);
        image.add(R.drawable.bbb);
        image.add(R.drawable.c);
        image.add(R.drawable.cc);
        image.add(R.drawable.ccc);
        image.add(R.drawable.d);
        image.add(R.drawable.dd);
        image.add(R.drawable.ddd);
        image.add(R.drawable.e);
        image.add(R.drawable.ee);
        image.add(R.drawable.eee);
        image.add(R.drawable.f);
        image.add(R.drawable.ff);
        image.add(R.drawable.fff);
        image.add(R.drawable.g);
        image.add(R.drawable.gg);
        image.add(R.drawable.ggg);
        image.add(R.drawable.h);
        image.add(R.drawable.hh);
        image.add(R.drawable.hhh);
        image.add(R.drawable.i);
        image.add(R.drawable.ii);
        image.add(R.drawable.iii);
        image.add(R.drawable.j);
        image.add(R.drawable.jj);
        image.add(R.drawable.jjj);
        image.add(R.drawable.k);
        image.add(R.drawable.kk);
        image.add(R.drawable.kkk);
        image.add(R.drawable.l);
        image.add(R.drawable.ll);
        image.add(R.drawable.lll);
        image.add(R.drawable.m);
        image.add(R.drawable.mm);
        image.add(R.drawable.mmm);
        image.add(R.drawable.n);
        image.add(R.drawable.nn);
        image.add(R.drawable.nnn);
        image.add(R.drawable.o);
        image.add(R.drawable.oo);
        image.add(R.drawable.ooo);
        image.add(R.drawable.p);
        image.add(R.drawable.pp);
        image.add(R.drawable.q);
        image.add(R.drawable.qq);
        image.add(R.drawable.r);
        image.add(R.drawable.rr);
        image.add(R.drawable.s);
        image.add(R.drawable.ss);
        image.add(R.drawable.t);
        image.add(R.drawable.tt);
        image.add(R.drawable.u);
        image.add(R.drawable.uu);
        image.add(R.drawable.v);
        image.add(R.drawable.vv);
        image.add(R.drawable.w);
        image.add(R.drawable.ww);
        image.add(R.drawable.x);
        image.add(R.drawable.xx);
        image.add(R.drawable.y);
        image.add(R.drawable.yy);
        image.add(R.drawable.z);
        image.add(R.drawable.zz);
        Constant.setImages(image);
    }

    private void init(){

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
                switch (menuItem.getItemId()){
                    case R.id.drawer_account:
                        Intent my = new Intent(MainActivity.this, AccountManageActivity.class);
                        startActivity(my);
                        break;
                    case R.id.drawer_sim_card:
                        if (Constant.getMainAccount(MainActivity.this) == null){
                            Toast.makeText(getApplicationContext(),"There is no Account",Toast.LENGTH_SHORT).show();
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
}