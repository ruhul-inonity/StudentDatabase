package com.android.tonmoy.studentlist;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import DataBase.DBHandler;


public class All_Student extends ActionBarActivity {

    ListView studentList;
    ArrayAdapter students;
    ArrayList<String> allStudents;
    ArrayList<String> allStudentIDs;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_student);

        dbHandler = new DBHandler(this, null, null, 1);
        ArrayList[] std = dbHandler.getStudentList();
        allStudents = std[0];
        allStudentIDs = std[1];

        studentList = (ListView) findViewById(R.id.studentList);

        students = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, allStudents);

        studentList.setAdapter(students);

        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = (String) parent.getAdapter().getItem(position);
                int studentId =(int) parent.getAdapter().getItemId(position);

                String details[] = dbHandler.getStudentDetails(name, Integer.valueOf(allStudentIDs.get(studentId)));

                Intent i = new Intent(getApplicationContext(), Details.class);
                i.putExtra("Details", details);
                startActivity(i);

                //Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_all_student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.newStudent) {
            Intent i = new Intent(this, InputPage.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
