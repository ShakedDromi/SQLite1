package com.example.sqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class GRADES extends AppCompatActivity {

    SQLiteDatabase db;
    HelperDB hlp;
    EditText etNAME, etQUAR, etGRADE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_GRADES);

        etNAME = (EditText) findViewById(R.id.etNAME);
        etQUAR = (EditText) findViewById(R.id.etQUAR);
        etGRADE = (EditText) findViewById(R.id.etGRADE);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();
    }

    /**
     * put in variables the edittext input
     * put in content value the field name and the value
     * @param view
     */

    public void entergrades(View view) {
        String name, strQuar, strGrade;

        int Grade, Quar;
        name = etNAME.getText().toString();
        strQuar = etQUAR.getText().toString();
        Quar = Integer.parseInt(strQuar);
        strGrade = etGRADE.getText().toString();
        Grade = Integer.parseInt(strGrade);

        ContentValues cv = new ContentValues();
        cv.put(GradesC.NAME, name);
        cv.put(GradesC.QUARTER, Quar);
        cv.put(GradesC.GRADE, Grade);

        db = hlp.getWritableDatabase();
        db.insert(GradesC.TABLE_GRADES, null, cv);
        db.close();

    }
    /**
     * creates options menu that moves to other activities
     * @param menu
     * @return
     */

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        String st = item.getTitle().toString();
        if (st.equals("Enter Data")) {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        if (st.equals("Info")){
            Intent si = new Intent(this, delete.class);
            startActivity(si);
        }
        if(st.equals("Grades")){
            Intent si = new Intent(this, GRADES.class);
            startActivity(si);
        }
        if (st.equals("Sort")){
            Intent si = new Intent(this, miun.class);
            startActivity(si);
        }
        return true;
    }
}