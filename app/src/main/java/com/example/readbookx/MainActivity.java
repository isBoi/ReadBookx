package com.example.readbookx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Database database=new Database(this);
        database.getWritableDatabase();
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(MainActivity.this,BookshelfActivity.class);
        startActivity(intent);
    }
}
