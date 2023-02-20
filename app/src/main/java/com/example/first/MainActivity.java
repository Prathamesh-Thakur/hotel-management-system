package com.example.first;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showMenu(View View){
        Intent menu = new Intent(this, MainActivity2.class);
        startActivity(menu);
    }

    public void reserve(View View){
        Intent reservation = new Intent(this, MainActivity3.class);
        startActivity(reservation);
    }

    public void feedBack(View View){
        Intent feedback = new Intent(this, MainActivity4.class);
        startActivity(feedback);
    }
}