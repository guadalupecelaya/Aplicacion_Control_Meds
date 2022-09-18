package com.example.app_medc;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class RegistroMedicamento extends AppCompatActivity {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_med);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("some") != null) {
                Toast.makeText(getApplicationContext(), "data:" + bundle.getString("some"), Toast.LENGTH_SHORT).show();
            }
        }
    }
}