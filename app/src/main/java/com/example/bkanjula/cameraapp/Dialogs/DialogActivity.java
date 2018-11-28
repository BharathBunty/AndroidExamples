package com.example.bkanjula.cameraapp.Dialogs;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.bkanjula.cameraapp.R;

import java.text.DateFormat;
import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogActivity extends AppCompatActivity {


     Calendar mCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);
        mCalendar = Calendar.getInstance();

    }

    @OnClick(R.id.button_fullscreen_picker)
    public void fullScreenclick(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FullScreenDialogFragment newFragment = new FullScreenDialogFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
    }

    @OnClick(R.id.button_time_picker)
    public void timerclick(){
        TimePickerDialog timePickerDialog =
                new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        mCalendar.set(Calendar.MINUTE, minute);
                        String time = DateFormat.getTimeInstance(DateFormat.SHORT).format(mCalendar.getTime());
                        Log.d("MainActivity", "Selected time is " + time);
                    }
                }, mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();

    }

    @OnClick(R.id.button_date_picker)
    public void dateclick(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, monthOfYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(mCalendar.getTime());
                Log.d("MainActivity", "Selected date is " + date);
            }
        }, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    @OnClick(R.id.button_mutliplechoice_picker)
    public void multiChoiceclick(){

        String[] multiChoiceItems = getResources().getStringArray(R.array.dialog_multi_choice_array);
        final boolean[] checkedItems = {false, false, false, false};
        new AlertDialog.Builder(this)
                .setTitle("Select your favourite movies")
                .setMultiChoiceItems(multiChoiceItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int index, boolean isChecked) {
                        Log.d("MainActivity", "clicked item index is " + index);
                    }
                })
                .setPositiveButton("Ok", null)
                .setNegativeButton("Cancel", null)
                .show();

    }


}
