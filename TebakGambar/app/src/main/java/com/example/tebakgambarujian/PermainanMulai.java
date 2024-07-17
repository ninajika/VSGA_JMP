package com.example.tebakgambarujian;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;


public class PermainanMulai extends AppCompatActivity {
    ImageView iv_instagram, iv_Upin, iv_Ipin, iv_whatsapp, iv_mastodon, iv_line, iv_javascript, iv_react, iv_twitter, iv_python, iv_threads;
    Button btn_kembali;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_pilihgambar);
        setIninitial();
        onClickGambar();
        btn_kembali = findViewById(R.id.btn_kembali);
        btn_kembali.setOnClickListener(this::kembaliMenu);
    }

    private boolean kembaliMenu(View view) {
        startActivity(new Intent(this, MainActivity.class));
        return true;
    }


    private void onClickGambar() {
        iv_instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTebakActivity("instagram");
            }
        });
        iv_Upin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTebakActivity("upin");
            }
        });
        iv_Ipin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTebakActivity("ipin");
            }
        });
        iv_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTebakActivity("whatsapp");
            }
        });

        iv_mastodon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTebakActivity("mastodon");
            }
        });
        iv_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTebakActivity("line");
            }
        });
        iv_javascript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTebakActivity("javascript");
            }
        }); 
        iv_react.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTebakActivity("react");
            }
        });
        iv_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTebakActivity("twitter");
            }
        });
        iv_python.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTebakActivity("python");
            }
        });
        iv_threads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTebakActivity("threads");
            }
        });

    }

    private void startTebakActivity(String iconName)  {
        Intent intent = new Intent(PermainanMulai.this, TebakActivity.class);
        intent.putExtra("nama_icon", iconName);
        startActivity(intent);
    }


    private void setIninitial() {
        iv_instagram = findViewById(R.id.iv_instagram);
        iv_Upin = findViewById(R.id.iv_upin);
        iv_Ipin = findViewById(R.id.iv_ipin);
        iv_whatsapp = findViewById(R.id.iv_whatsapp);
        iv_mastodon = findViewById(R.id.iv_mastodon);
        iv_line = findViewById(R.id.iv_line);
        iv_javascript = findViewById(R.id.iv_javascript);
        iv_react = findViewById(R.id.iv_react);
        iv_twitter = findViewById(R.id.iv_twitter);
        iv_python = findViewById(R.id.iv_python);
        iv_threads = findViewById(R.id.iv_threads);
    }


}