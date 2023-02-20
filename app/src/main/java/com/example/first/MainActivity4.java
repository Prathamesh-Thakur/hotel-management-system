package com.example.first;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {
    RatingBar r1, r2;
    TextView afeedback, nameone;
    SQLiteDatabase db1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        r1 = (RatingBar)findViewById(R.id.foodrating);
        r2 = (RatingBar)findViewById(R.id.servicerating);
        afeedback = findViewById(R.id.additionalfeedback);
        nameone = findViewById(R.id.nameofperson);

        db1=openOrCreateDatabase("Feedback", Context.MODE_PRIVATE, null);
        db1.execSQL("CREATE TABLE IF NOT EXISTS Feedback(Name VARCHAR, Food_rating VARCHAR,Service_rating VARCHAR,Additonal_Feedback VARCHAR);");
    }

    public void sendFeedback(View View)
    {
        String rating1 = String.valueOf(r1.getRating());
        String rating2 = String.valueOf(r2.getRating());
        String feedback = afeedback.getText().toString();
        String name = nameone.getText().toString();

        if (name.trim().length() == 0)
        {
            showMessage("Error", "Please enter name");
            return;
        }
        db1.execSQL("INSERT INTO Feedback VALUES('"+name+"','"+rating1+"','"+rating2+
                "','"+feedback+"');");
        showMessage("Success", "Thank you for your feedback.");
        clearText();
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void clearText()
    {
        r1.setRating(0);
        r2.setRating(0);
        afeedback.setText("");
        nameone.setText("");
    }
}