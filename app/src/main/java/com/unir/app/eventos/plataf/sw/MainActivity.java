package com.unir.app.eventos.plataf.sw;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerEvent;
    EditText dateInit;
    EditText dateEnd;
    EditText hourInit;
    EditText hourEnd;

    private int lastYear, lastMonth, lastDayMonthYear;
    private int lastHours, lastMinutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerEvent = findViewById(R.id.idSpiner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.eventType, android.R.layout.simple_spinner_item);
        spinnerEvent.setAdapter(adapter);

        dateInit = findViewById(R.id.dateInit);
        dateEnd = findViewById(R.id.dateEnd);
        hourInit = findViewById(R.id.hourInit);
        hourEnd = findViewById(R.id.hourEnd);
        final Calendar calendar = Calendar.getInstance();
        lastYear = calendar.get(Calendar.YEAR);
        lastMonth = calendar.get(Calendar.MONTH);
        lastDayMonthYear = calendar.get(Calendar.DAY_OF_MONTH);
        lastHours = calendar.get(Calendar.HOUR_OF_DAY);
        lastMinutes = calendar.get(Calendar.MINUTE);

        refreshDateText(dateInit);
        refreshDateText(dateEnd);
        refreshHourText(hourInit);
        refreshHourText(hourEnd);

        dateInit.setOnClickListener(v -> {
            DatePickerDialog dialogDateInit = new DatePickerDialog(MainActivity.this, listenerInitDatePicker, lastYear, lastMonth, lastDayMonthYear);
            dialogDateInit.show();
        });

        dateEnd.setOnClickListener(v -> {
            DatePickerDialog dialogDateEnd = new DatePickerDialog(MainActivity.this, listenerEndDatePicker, lastYear, lastMonth, lastDayMonthYear);
            dialogDateEnd.show();
        });

        hourInit.setOnClickListener(v -> {
            TimePickerDialog dialogHourInit = new TimePickerDialog(MainActivity.this, onTimeSetListenerInit, lastHours, lastMinutes, false);
            dialogHourInit.show();
        });

        hourEnd.setOnClickListener(v -> {
            TimePickerDialog dialogHourEnd = new TimePickerDialog(MainActivity.this, onTimeSetListenerEnd, lastHours, lastMinutes, false);
            dialogHourEnd.show();
        });

    }

    private final TimePickerDialog.OnTimeSetListener onTimeSetListenerInit = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
            lastHours = hours;
            lastMinutes = minutes;
            refreshHourText(hourInit);
        }
    };

    private final TimePickerDialog.OnTimeSetListener onTimeSetListenerEnd = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
            lastHours = hours;
            lastMinutes = minutes;
            refreshHourText(hourEnd);
        }
    };

    private final DatePickerDialog.OnDateSetListener listenerInitDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            lastYear = year;
            lastMonth = month;
            lastDayMonthYear = day;
            refreshDateText(dateInit);

        }
    };

    private final DatePickerDialog.OnDateSetListener listenerEndDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            lastYear = year;
            lastMonth = month;
            lastDayMonthYear = day;
            refreshDateText(dateEnd);

        }
    };

    public void refreshDateText(EditText editText) {
        String date = String.format(Locale.getDefault(), "%02d-%02d-%02d", lastYear, lastMonth+1, lastDayMonthYear);
        editText.setText(date);
    }

    public void refreshHourText(EditText editText) {
        String hours = String.format(Locale.getDefault(), "%02d:%02d", lastHours, lastMinutes);
        editText.setText(hours);
    }

}