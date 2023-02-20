package com.example.first;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.database.sqlite.SQLiteDatabase;
import android.app.AlertDialog.Builder;
import android.widget.Spinner;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity3 extends AppCompatActivity {

    SQLiteDatabase db;
    Spinner spinnerLanguages;
    String location;
    EditText checkname, checkpeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        spinnerLanguages=findViewById(R.id.spinner_location);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.locations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerLanguages.setAdapter(adapter);

        db=openOrCreateDatabase("Reservations", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Reservations(Location VARCHAR,Name VARCHAR,People VARCHAR);");

        checkname = (EditText)findViewById(R.id.no_of_people);
        checkpeople = (EditText)findViewById(R.id.name);
    }

    public void reserveTable(View View)
    {
        if (checkname.getText().toString().trim().length() == 0 || checkpeople.getText().toString().trim().length() == 0)
        {
            showMessage("Error", "Please enter all values");
            return;
        }

        location = spinnerLanguages.getSelectedItem().toString();
        db.execSQL("INSERT INTO Reservations VALUES('"+location+"','"+checkname.getText()+
                "','"+checkpeople.getText()+"');");
        showMessage("Success", "Reservation communicated.");
        clearText();
    }

    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clearText()
    {
        checkpeople.setText("");
        checkname.setText("");
    }
}

