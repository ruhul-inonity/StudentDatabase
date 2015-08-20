package com.android.tonmoy.studentlist;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import DataBase.DBHandler;


public class Details extends ActionBarActivity {

    TextView name;
    TextView gender;
    TextView birthday;
    TextView department;
    TextView regNo;
    TextView phoneNo;
    TextView email;

    DBHandler dbHandler;

    private String[] details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name = (TextView) findViewById(R.id.name);
        gender = (TextView) findViewById(R.id.gender);
        birthday = (TextView) findViewById(R.id.birthday);
        department = (TextView) findViewById(R.id.department);
        regNo = (TextView) findViewById(R.id.regNo);
        phoneNo = (TextView) findViewById(R.id.phoneNo);
        email = (TextView) findViewById(R.id.email);

        this.details = getIntent().getExtras().getStringArray("Details");

        name.setText(this.details[0]);
        gender.setText(this.details[1]);
        birthday.setText(this.details[2]);
        department.setText(this.details[3]);
        regNo.setText(this.details[4]);
        phoneNo.setText(this.details[5]);
        email.setText(this.details[6]);

        dbHandler = new DBHandler(this, null, null, 1);

    }

    public void onClickDelete(View view) {
        dbHandler.deleteStudent(this.details[0], Integer.valueOf(this.details[7]));
        Toast.makeText(this, this.details[0] + " deleted successfully", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, All_Student.class);
        startActivity(i);
    }

    public void onClickEdit(View view) {
        String details[] = dbHandler.getStudentFullDetails(this.details[0], Integer.valueOf(this.details[7]));
        Intent i = new Intent(this, EditPage.class);
        i.putExtra("Details", details);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.goBack) {
            Intent i = new Intent(this, All_Student.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
