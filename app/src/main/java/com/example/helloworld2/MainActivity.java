package com.example.helloworld2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private String name ="";
    private String email ="";
    private String age ="";
    private String description ="";
    private String occupation ="";
    private String dateOfBirth ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText txt4 = findViewById(R.id.dateOfBirth);
        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), getString(R.string.date_picker));
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        EditText txt4 = (EditText)findViewById(R.id.dateOfBirth);
        Calendar c = Calendar.getInstance();

        if (year >= 2003) {
            Toast.makeText(this, R.string.not_18, Toast.LENGTH_LONG).show();
            return;
        } else {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String currentDateString = DateFormat.getDateInstance().format(c.getTime());
            txt4.setText(currentDateString);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        EditText txt1 = (EditText)findViewById(R.id.name);
        EditText txt2 = (EditText)findViewById(R.id.email);
        EditText txt3 = (EditText)findViewById(R.id.age);
        EditText txt4 = (EditText)findViewById(R.id.description);
        EditText txt5 = (EditText)findViewById(R.id.occupation);
        EditText txt6 = (EditText)findViewById(R.id.dateOfBirth);
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");
        txt5.setText("");
        txt6.setText("");

    }

    public void goToSecondActivity(View view) {
        EditText txt1 = (EditText)findViewById(R.id.name);
        EditText txt2 = (EditText)findViewById(R.id.email);
        EditText txt3 = (EditText)findViewById(R.id.age);
        EditText txt4 = (EditText)findViewById(R.id.description);
        EditText txt5 = (EditText)findViewById(R.id.occupation);
        EditText txt6 = (EditText)findViewById(R.id.dateOfBirth);
        name       = txt1.getText().toString();
        email       = txt2.getText().toString();
        age         = txt3.getText().toString();
        description = txt4.getText().toString();
        occupation  = txt5.getText().toString();
        dateOfBirth = txt6.getText().toString();

        while (name.isEmpty()) {
            txt1.setError(getString(R.string.name_cannot_be_empty));
            return;
        }

        while((email.isEmpty()) || (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"))) {
            txt2.setError(getString(R.string.invalid_email));
            return;
        }

        while ((age.isEmpty())) {
            txt4.setError(getString(R.string.age_cannot_be_empty));
            return;
        }

        while ((description.isEmpty())) {
            txt4.setError(getString(R.string.description_cannot_be_empty));
            return;
        }

        while ((occupation.isEmpty())) {
            txt4.setError(getString(R.string.occupation_cannot_be_empty));
            return;
        }

        while ((dateOfBirth.isEmpty())) {
            txt4.setError(getString(R.string.dateOfBirth_cannot_be_empty));
            return;
        }

        Intent intent = new Intent(MainActivity.this, Main2Activity.class);

        intent.putExtra(Constants.KEY_NAME, name);
        intent.putExtra(Constants.KEY_EMAIL, email);
        intent.putExtra(Constants.KEY_AGE, age);
        intent.putExtra(Constants.KEY_DESCRIPTION, description);
        intent.putExtra(Constants.KEY_OCCUPATION, occupation);
        intent.putExtra(Constants.KEY_DATE_OF_BIRTH, dateOfBirth);

        startActivity(intent);
    }
}
