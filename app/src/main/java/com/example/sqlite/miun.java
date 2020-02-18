package com.example.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.sqlite.STUDENTS.TABLE_USERS;

public class miun extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TABLE_GRADES ="Grades";
    ListView lvT, lvS, lvSo;
    SQLiteDatabase db;
    HelperDB hlp;
    Cursor crsr;
    ArrayList<String> tblRec, tblFiled;
    ArrayList<String> tbl = new ArrayList<>();
    ArrayAdapter<String> adpTables, adpFields, adpData;
    int table;
    String[] tables, fields;
    String tbl2sort;
    String[] columns = null;
    String selection = null;
    String[] selectionArgs = null;
    String groupBy = null;
    String having = null;
    String orderBy = null;
    String limit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miun);

        lvT=(ListView)findViewById(R.id.lvT);
        lvS=(ListView)findViewById(R.id.lvS);
        lvSo= (ListView) findViewById(R.id.lvSo);

        hlp=new HelperDB(this);
        db=hlp.getWritableDatabase();
        db.close();
        /**
         sets the listView
         */

        lvT.setOnItemClickListener(this);
        lvT.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvS.setOnItemClickListener(this);
        lvS.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        table=-1;

        tables= new String[]{TABLE_USERS, TABLE_GRADES};
        ArrayAdapter<String> adpTables=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,tables);
        lvT.setAdapter(adpTables);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (lvT.equals(parent)) {
            table = position;
            /**
             * options for sorting
             */
            if (table==0) {
                tbl2sort= TABLE_USERS;
                fields= new String[]{Users.KEY_ID, Users.NAME};
            } else {
                tbl2sort= TABLE_GRADES;
                fields= new String[]{Users.KEY_ID, GradesC.GRADE};
            }
            ArrayAdapter<String> adpFields=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,fields);
            lvS.setAdapter(adpFields);
        } else if (lvS.equals(parent)) {
            if (table != -1) {
                tbl = new ArrayList<>();
                db=hlp.getReadableDatabase();
                orderBy=fields[position];
                /**
                 * sorts the data according to the option the user clicked
                 */

                if (table == 0)
                    crsr=db.query(TABLE_USERS, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
                    int co1 = crsr.getColumnIndex(Users.KEY_ID);
                    int co2 = crsr.getColumnIndex(Users.NAME);
                    int co3 = crsr.getColumnIndex(Users.ADDRESS);
                    int co4 = crsr.getColumnIndex(Users.PHONE);
                    int co5 = crsr.getColumnIndex(Users.HOME_P);
                    int co6 = crsr.getColumnIndex(Users.MOM_NAME);
                    int co7 = crsr.getColumnIndex(Users.MOM_NUM);
                    int co8 = crsr.getColumnIndex(Users.DAD_NAME);
                    int co9 = crsr.getColumnIndex(Users.DAD_NUM);


                    crsr.moveToFirst();
                    while (!crsr.isAfterLast()) {
                        int key = crsr.getInt(co1);
                        String name = crsr.getString(co2);
                        String add = crsr.getString(co3);
                        int pho = crsr.getInt(co4);
                        int Hpho = crsr.getInt(co5);
                        String Mname = crsr.getString(co6);
                        int Mnum = crsr.getInt(co7);
                        String Dname = crsr.getString(co8);
                        int Dnum = crsr.getInt(co9);
                        String stUsers = "" + key + ", " + name + ", " + add + "," + pho + ", " + Hpho + ", " + Mname + ", " + Mnum + ", " + Dname + ", " + Dnum;
                        tbl.add(stUsers);
                        crsr.moveToNext();
                    }
                } else {
                    crsr=db.query(TABLE_GRADES, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
                    int co1 = crsr.getColumnIndex(GradesC.KEY_ID);
                    int co2 = crsr.getColumnIndex(GradesC.NAME);
                    int co3 = crsr.getColumnIndex(GradesC.QUARTER);
                    int co4 = crsr.getColumnIndex(GradesC.GRADE);
                    crsr.moveToFirst();
                    while (!crsr.isAfterLast()) {
                        int key = crsr.getInt(co1);
                        String name = crsr.getString(co2);
                        int qua = crsr.getInt(co3);
                        int grade = crsr.getInt(co4);
                        String stGrades = "" + key + "," + name + "," + qua + "," + grade;
                        tbl.add(stGrades);
                        crsr.moveToNext();
                    }
                }
                crsr.close();
                db.close();
                adpData = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tbl);
                lvSo.setAdapter(adpData);
            } else {
                Toast.makeText(this, "Choose table first", Toast.LENGTH_LONG).show();
            }
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