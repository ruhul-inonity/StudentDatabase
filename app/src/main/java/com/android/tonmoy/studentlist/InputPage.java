package com.android.tonmoy.studentlist;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import DataBase.DBHandler;
import DataBase.Students;


public class InputPage extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    EditText name;
    EditText email;
    EditText regNo;
    EditText phoneNo;
    Spinner department;
    RadioGroup gender;
    RadioButton male;
    RadioButton female;

    TextView birthDay;
    Calendar calendar;
    DatePickerDialog.OnDateSetListener date;

    String department_name;
    String department_id;
    String _gender;
    String gender_id;

    Students students;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_page);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        regNo = (EditText) findViewById(R.id.regNo);
        phoneNo = (EditText) findViewById(R.id.phoneNo);

        department = (Spinner) findViewById(R.id.department);
        ArrayAdapter spinner = ArrayAdapter.createFromResource(this, R.array.department, android.R.layout.simple_spinner_item);
        department.setAdapter(spinner);
        department.setOnItemSelectedListener(this);

        gender = (RadioGroup) findViewById(R.id.gender);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);


        birthDay = (TextView) findViewById(R.id.birthday);
        calendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.YEAR, year);
                String format = "dd/MM/yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.US);
                birthDay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };

        dbHandler = new DBHandler(this, null, null, 1);
    }

    public void onClickSave(View view) {
        int g_id = gender.getCheckedRadioButtonId();

        if (male.getId() == g_id) {
            _gender = "Male";
        } else if (female.getId() == g_id) {
            _gender = "Female";
        } else {
            _gender = "";
        }
        gender_id = String.valueOf(g_id);

        students = new Students(name.getText().toString(), regNo.getText().toString(), phoneNo.getText().toString(), email.getText().toString(), birthDay.getText().toString(), _gender, gender_id, department_name, department_id);

        dbHandler.addStudent(students);
        Toast.makeText(getApplicationContext(), name.getText().toString() + " has been successfully saved.", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, All_Student.class);
        startActivity(i);
    }

    public void activeDatePicker(View view) {
        new DatePickerDialog(this, date, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input_page, menu);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        department_id = String.valueOf(parent.getAdapter().getItemId(position));
        department_name = String.valueOf(parent.getAdapter().getItem(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
