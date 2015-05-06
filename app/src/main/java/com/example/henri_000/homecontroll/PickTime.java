package com.example.henri_000.homecontroll;

import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class PickTime extends ActionBarActivity {

    TimePicker tp;
    int hour, min;
    static int currentunit;
    MainActivity.Networktask networktask;
    String inputIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        inputIP = MainActivity.inputIP;
        networktask = new MainActivity.Networktask();
        Button buttonSetOn = (Button) findViewById(R.id.buttonSetOn);
        Button buttonSetOff = (Button) findViewById(R.id.buttonSetOff);

        buttonSetOn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                networktask.execute();
                try {
                    Thread.sleep(100, 0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                networktask.setTimer(Integer.toString(currentunit), "ON", Integer.toString(hour), Integer.toString(min));

            }
        });

        buttonSetOff.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                networktask.execute();
                try {
                    Thread.sleep(100, 0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                networktask.setTimer(Integer.toString(currentunit), "OFF" ,Integer.toString(hour), Integer.toString(min));
                alertTimerSet();
            }
        });

        tp = (TimePicker)findViewById(R.id.timePicker);
        tp.setIs24HourView(true);
        Calendar now = Calendar.getInstance();
        tp.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
        tp.setCurrentMinute(now.get(Calendar.MINUTE));
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                //Toast.makeText(getApplicationContext(),
                //        "onTimeChanged", Toast.LENGTH_SHORT).show();
                hour = hourOfDay;
                min = minute;
            }
        });
    }


    public void alertTimerSet()
    {
        AlertDialog.Builder myalert = new AlertDialog.Builder(this);
        myalert.setMessage(R.string.timerSet).create();
        myalert.setTitle(R.string.timerSetTitle).create();
        myalert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pick_time, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
