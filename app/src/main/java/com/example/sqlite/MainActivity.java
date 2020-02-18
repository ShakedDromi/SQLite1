package com.example.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Shaked Dromi
 * @since 18.2.2020
 * @version alpha
 */
public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    HelperDB hlp;
    EditText etNAME, etADD, etNUM, etHNUM, etMNAME, etMNUM, etDNAME, etDNUM;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNAME = (EditText) findViewById(R.id.etNAME);
        etADD = (EditText) findViewById(R.id.etADD);
        etNUM = (EditText) findViewById(R.id.etNUM);
        etHNUM = (EditText) findViewById(R.id.etHNUM);
        etMNAME = (EditText) findViewById(R.id.etMNAME);
        etMNUM = (EditText) findViewById(R.id.etMNUM);
        etDNAME = (EditText) findViewById(R.id.etDNAME);
        etDNUM = (EditText) findViewById(R.id.etDNUM);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();
    }

    /**
     * put in variables the edittext input
     * put in content value the field name and the value
     * @param view
     */
    public void enterdata(View view) {
        String name, address, strNum, strHnum, mName, strMnum, dName, strDnum;
        int num, Hnum, Mnum, Dnum;
        name = etNAME.getText().toString();
        address = etADD.getText().toString();
        strNum = etNUM.getText().toString();
        num = Integer.parseInt(etNUM.getText().toString());
        strHnum = etHNUM.getText().toString();
        Hnum = Integer.parseInt(etHNUM.getText().toString());
        mName = etMNAME.getText().toString();
        strMnum = etMNUM.getText().toString();
        Mnum = Integer.parseInt(etMNUM.getText().toString());
        dName = etDNAME.getText().toString();
        strDnum = etDNUM.getText().toString();
        Dnum = Integer.parseInt(etDNUM.getText().toString());

        ContentValues cv = new ContentValues();
        cv.put(Users.NAME, name);
        cv.put(Users.ADDRESS, address);
        cv.put(Users.PHONE, num);
        cv.put(Users.HOME_P, Hnum);
        cv.put(Users.MOM_NAME, mName);
        cv.put(Users.MOM_NUM, Mnum);
        cv.put(Users.DAD_NAME, dName);
        cv.put(Users.DAD_NUM, Dnum);

        db = hlp.getWritableDatabase();
        db.insert(Users.TABLE_USERS, null, cv);
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
