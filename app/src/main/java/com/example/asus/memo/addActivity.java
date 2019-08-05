package com.example.asus.memo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class addActivity extends AppCompatActivity {
    int yearSave, monthSave;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        intent = this.getIntent();
        final String[] months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String[] years = {"2019", "2020", "2021", "2022", "2023", "2024"};
        String[] days;
        Spinner year = (Spinner) findViewById(R.id.year);
        Spinner month = (Spinner) findViewById(R.id.month);
        final Spinner day = (Spinner) findViewById(R.id.day);

        ArrayAdapter dayAdapter;

        ArrayAdapter yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        year.setAdapter(yearAdapter);


        ArrayAdapter monthAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, months);
        month.setAdapter(monthAdapter);


        days = tools.createDay(tools.checkMonth(Integer.parseInt(String.valueOf(year.getSelectedItem())), Integer.parseInt(String.valueOf(month.getSelectedItem()))));
        dayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, days);
        day.setAdapter(dayAdapter);

        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                yearSave = pos + 2019;
                String[] days = tools.createDay(tools.checkMonth(yearSave, monthSave));
                ArrayAdapter dayAdapter1 = new ArrayAdapter<>(addActivity.this, android.R.layout.simple_spinner_item, days);
                day.setAdapter(dayAdapter1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                monthSave = pos + 1;
                String[] days = tools.createDay(tools.checkMonth(yearSave, monthSave));
                ArrayAdapter dayAdapter1 = new ArrayAdapter<>(addActivity.this, android.R.layout.simple_spinner_item, days);
                day.setAdapter(dayAdapter1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void addMemo(View view) {
        EditText addContent = (EditText) findViewById(R.id.addContent);
        String addText = addContent.getText().toString();
        Spinner year = (Spinner) findViewById(R.id.year);
        String yearString = String.valueOf(year.getSelectedItem());
        Spinner month = (Spinner) findViewById(R.id.month);
        String monthString = String.valueOf(month.getSelectedItem());
        Spinner day = (Spinner) findViewById(R.id.day);
        String dayString = String.valueOf(day.getSelectedItem());
        Bundle bundle = new Bundle();
        bundle.putString("year", yearString);//添加要返回给页面1的数据
        bundle.putString("month", monthString);
        bundle.putString("day", dayString);
        bundle.putString("text", addText);
        intent.putExtras(bundle);
        this.setResult(Activity.RESULT_OK, intent);//返回页面1
        this.finish();
    }

}
