package com.example.hackunam.arvum;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Planta> plantas = new ArrayList<>();
    MILVAdapter milvAdapter;
    ListView listView;

    Button btn_paraCulti_main, btn_agregar_main, btn_config_main;
    ImageView vacio;
    Boolean sesion=false;
    TextView nombre_main;
    String nombre="";


    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    static final int REQUEST=1;
    static final int REQUEST_SESION=2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences=this.getSharedPreferences("app", Context.MODE_PRIVATE);
        editor=preferences.edit();

        nombre_main= (TextView) findViewById(R.id.nombre_main);
        nombre_main.setText(preferences.getString("nombre-persis", "DefaultValue222"));



        //sesion=preferences.getBoolean("state", false);

        if(sesion!=true){
            Intent intent=new Intent(getApplicationContext(), Registro.class);
            startActivityForResult(intent, REQUEST_SESION);
            sesion=true;
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listView= (ListView) findViewById(R.id.mListView);
        milvAdapter=new MILVAdapter(plantas, getApplicationContext());
        listView.setAdapter(milvAdapter);

        vacio= (ImageView) findViewById(R.id.iv_vacio);
        if (plantas.size()>0){
            vacio.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
        else{
            vacio.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }


        btn_paraCulti_main= (Button) findViewById(R.id.btn_paraCulti_main);
        btn_agregar_main= (Button) findViewById(R.id.btn_agregar_main);
        btn_config_main= (Button) findViewById(R.id.btn_config_main);


        btn_agregar_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AgregarPlanta.class);
                startActivityForResult(intent, REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case(REQUEST):{
                if(resultCode==RESULT_OK){
                    String comollamas=data.getStringExtra("comoLlamas");
                    String especie=data.getStringExtra("especie");
                    //Log.e("comollamas", comollamas);
                    //Log.e("especie", especie);
                    plantas.add(new Planta(comollamas, especie));
                    milvAdapter.notifyDataSetChanged();
                    if (plantas.size()>0){
                        vacio.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                    }
                    else{
                        vacio.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                    }


                }
            }

            case (REQUEST_SESION):{
                if(resultCode==RESULT_OK){
                    editor.putBoolean("state", data.getBooleanExtra("sesion", false));
                    sesion=preferences.getBoolean("state", false);

                    editor.putString("nombre-persis", data.getStringExtra("nombre"));
                    //nombre=data.getStringExtra("nombre");

                    nombre_main.setText(data.getStringExtra("nombre"));
                    //nombre_main.setText(preferences.getString("nombre-persis", "DefaultValue"));
                    //nombre_main.setText(data.getStringExtra("nombre"));
                    //nombre_main.setText(preferences.getString("nombre","DefaultValue"));
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_calendar) {
            // Handle the camera action
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
