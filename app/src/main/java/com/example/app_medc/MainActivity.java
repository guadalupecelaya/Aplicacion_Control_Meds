package com.example.app_medc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle= getIntent().getExtras();
        if(bundle!=null){
            if(bundle.getString("some")!=null){
                Toast.makeText(getApplicationContext(),"data:"+bundle.getString("some"), Toast.LENGTH_SHORT).show();
            }
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Medicamentos()).commit();
            navigationView.setCheckedItem(R.id.nav_medicamento);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_medicamento:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Medicamentos()).commit();
                break;
            case R.id.nav_enfermedades:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EnfermedadesFragment()).commit();
                break;
            case R.id.nav_estado:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Estado()).commit();
                break;
            case R.id.nav_perfil:
                setContentView(R.layout.activity_perfil);
                Intent newAct = new Intent(this, Perfil.class);
                startActivity(new Intent(MainActivity.this, ProfileAct.class));
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                  //      new Perfil()).commit();
                break;
            case R.id.nav_Alarma:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Alerta()).commit();
                break;
            case R.id.nav_liquido:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Liquido()).commit();
                break;
            case R.id.nav_logout:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Dialogo_salida()).commit();
                break;
            case R.id.nav_cuidadores:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Cuidador()).commit();
                break;

        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}