package com.example.tebakgambarujian;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_masuk, btn_keluar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));

        btn_masuk = findViewById(R.id.btn_masuk);
        btn_keluar = findViewById(R.id.btn_keluar);

        btn_masuk.setOnClickListener(this::MulaiPermainan);
        btn_keluar.setOnClickListener(this::KeluarPermainan);
    }

    private boolean MulaiPermainan(View view) {
        startActivity(new Intent(this, PermainanMulai.class));
        return true;
    }

    private void KeluarPermainan(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm")
                .setMessage("Yakin Nih Mau Keluar?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Ya", (dialogInterface, i) -> finish()).show();
    }


}