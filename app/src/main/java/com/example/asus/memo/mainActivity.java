package com.example.asus.memo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;


public class mainActivity extends AppCompatActivity {
    private LinkedList<Button> buttonList;
    private LinkedList<TextView> textList;
    private LinearLayout mainLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonList = new LinkedList<Button>();
        textList = new LinkedList<TextView>();
        mainLinearLayout = (LinearLayout) findViewById(R.id.MyTable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent intent = new Intent();
                intent.setClass(mainActivity.this, addActivity.class);
                startActivityForResult(intent, 0);
                break;
            default:
        }
        return true;

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            String memoAdd = bundle.getString("text");
            String memoYear = bundle.getString("year");
            String memoMonth = bundle.getString("month");
            String memoDay = bundle.getString("day");
            if (memoAdd != null) {
                LinearLayout innerLinearLayout = new LinearLayout(this);
                TextView textview = new TextView(this);
                Button button = new Button(this);
                button.setText("删除");
                buttonList.add(button);
                textList.add(textview);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = -1;
                        for (int i = 0; i < buttonList.size(); i++) {
                            if (buttonList.get(i) == view) {
                                position = i;
                                break;
                            }
                        }
                        if (position != -1) {
                            buttonList.remove(position);
                            textList.remove(position);
                            mainLinearLayout.removeViewAt(position);
                        }
                    }
                });
                textview.setText(memoYear + "年" + memoMonth + "月" + memoDay + "日" + "\n" + memoAdd);
                innerLinearLayout.addView(textview);
                innerLinearLayout.addView(button);
                mainLinearLayout.addView(innerLinearLayout);
            }
        }

    }
}
