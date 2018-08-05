package com.dsource.idc.jellowintl.verbiage_model;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dsource.idc.jellowintl.DataBaseHelper;
import com.dsource.idc.jellowintl.R;
import com.dsource.idc.jellowintl.makemyboard.UtilityClasses.IconDatabase;
import com.dsource.idc.jellowintl.utility.JellowIcon;
import com.dsource.idc.jellowintl.utility.LanguageHelper;

import java.util.ArrayList;

public class ConverterActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        final ImageView imageView=findViewById(R.id.temp_image);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v("Rename","Permission is granted");
            }
            else
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        }

        final MyNewThread thread=new MyNewThread();
        (findViewById(R.id.create_json)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerbiageDatabaseHelper databaseHelper = new VerbiageDatabaseHelper(ConverterActivity.this,new DataBaseHelper(ConverterActivity.this).getWritableDatabase());
                databaseHelper.createTable();
            }
        });
        (findViewById(R.id.delete_database)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new VerbiageDatabaseHelper(ConverterActivity.this,new DataBaseHelper(ConverterActivity.this).getWritableDatabase()).dropTable();
            }
        });
        (findViewById(R.id.re_create_database)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new VerbiageDatabaseHelper(ConverterActivity.this,new DataBaseHelper(ConverterActivity.this).getWritableDatabase()).dropTable();
                new VerbiageDatabaseHelper(ConverterActivity.this,new DataBaseHelper(ConverterActivity.this).getWritableDatabase()).createTable();
                Toast.makeText(ConverterActivity.this,"Created",Toast.LENGTH_SHORT).show();
            }
        });
        (findViewById(R.id.delete_database)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new VerbiageDatabaseHelper(ConverterActivity.this,new DataBaseHelper(ConverterActivity.this).getWritableDatabase()).dropTable();
            }
        });
        (findViewById(R.id.re_create_database)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new VerbiageDatabaseHelper(ConverterActivity.this,new DataBaseHelper(ConverterActivity.this).getWritableDatabase()).dropTable();
                new VerbiageDatabaseHelper(ConverterActivity.this,new DataBaseHelper(ConverterActivity.this).getWritableDatabase()).createTable();
                Toast.makeText(ConverterActivity.this,"Created",Toast.LENGTH_SHORT).show();
            }
        });
        (findViewById(R.id.create_new_jellowdatabase)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IconDatabase dataBaseHelper = new IconDatabase(ConverterActivity.this,new DataBaseHelper(ConverterActivity.this).getWritableDatabase());
                dataBaseHelper.dropTable();
                dataBaseHelper.createTable();
            }
        });
        (findViewById(R.id.print_new_database)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<JellowIcon> list =new IconDatabase(ConverterActivity.this).getAllIcons();
                for(int i=0;i<list.size();i++) {
                    Log.d("Icon "+i,""+list.get(i).IconTitle);
                }
                Toast.makeText(ConverterActivity.this,"Created",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext((LanguageHelper.onAttach(newBase)));
    }

    public class MyNewThread extends AsyncTask {


        @Override
        protected void onPostExecute(Object o) {
            Log.d("Status","Finished");
            Toast.makeText(context,"Json file created",Toast.LENGTH_SHORT).show();
        }

        Context context;
        public String ts;
        @Override
        protected Object doInBackground(Object[] objects) {
            Context context=(Context)objects[0];
            this.context=context;

            VerbiageDatabaseHelper databaseHelper=new VerbiageDatabaseHelper(context,new DataBaseHelper(context).getWritableDatabase());
            databaseHelper.createTable();


            //CODES TO CREATE JSON
/*            IconDataBaseHelper d=new IconDataBaseHelper(context);
            final ArrayList<JellowIcon> levelOne=d.getLevelOneIcons();
            final ArrayList<JellowIcon> levelTwo=d.getLevelTwoIcons();
            final ArrayList<JellowIcon> levelThree=d.getLevelThreeIcons();
            final Verbiage verbiage=new Verbiage(context);
            ts=verbiage.createJson(levelOne,levelTwo,levelThree);
            new Verbiage(ConverterActivity.this).createNomenclatureDrawables();*/

            return null;
        }

    }

}
