package com.example.tebakgambarujian;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class TebakActivity extends AppCompatActivity  {
    ImageView imageView_tebak;
    EditText editText_jawabb;
    Button button_cek, btn_kembali_menu;
    private String jawaban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tebak_gambar);
        setInitalisasi();
        cekIntent();
        onClickJawaban();

    }


    private void onClickJawaban() {
        button_cek.setOnClickListener(view -> {
            String jawaban = editText_jawabb.getText().toString();
            if (jawaban.equalsIgnoreCase(this.jawaban)) {
                Toast.makeText(TebakActivity.this, "Jawaban anda benar!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(TebakActivity.this, "Jawaban anda salah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cekIntent() {
        Intent cek = getIntent();
        String nama_icon = cek.getStringExtra("nama_icon");
        int drawableResource = 0;
        switch (Objects.requireNonNull(nama_icon).toLowerCase()) {
            case "instagram":
                drawableResource = R.drawable.instagram;
                jawaban = "instagram";
                break;
            case "upin":
                drawableResource = R.drawable.u;
                jawaban = "upin";
                break;
            case "ipin":
                drawableResource = R.drawable.i;
                jawaban = "ipin";
                break;
            case "javascript":
                drawableResource = R.drawable.javascript;
                jawaban = "javascript";
                break;
            case "line":
                drawableResource = R.drawable.line;
                jawaban = "line";
                break;
            case "logo":
                drawableResource = R.drawable.logo;
                jawaban = "logo";
                break;
            case "mastodon":
                drawableResource = R.drawable.mastodon;
                jawaban = "mastodon";
                break;
            case "python":
                drawableResource = R.drawable.python;
                jawaban = "python";
                break;
            case "react":
                drawableResource = R.drawable.react;
                jawaban = "react";
                break;
            case "snapchat":
                drawableResource = R.drawable.snapchat;
                jawaban = "snapchat";
                break;
            case "threads":
                drawableResource = R.drawable.threads;
                jawaban = "threads";
                break;
            case "twitter":
                drawableResource = R.drawable.twitter;
                jawaban = "twitter";
                break;
            case "whatsapp":
                drawableResource = R.drawable.whatsapp;
                jawaban = "whatsapp";
                break;
            case "youtube":
                drawableResource = R.drawable.youtube;
                jawaban = "youtube";
                break;
            default:
                drawableResource = R.drawable.twitter;
                jawaban = "twitter";
                break;
        }
        imageView_tebak.setImageResource(drawableResource);

    }

    private void setInitalisasi() {
        imageView_tebak = findViewById(R.id.imageView_tebak);
        editText_jawabb = findViewById(R.id.editText_jawab);
        button_cek = findViewById(R.id.buttonCek);
    }
}