package com.nurozkaya.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // sql çalışmalarda genelde try and catch içinde çalışmamızı ister.

        try{
            //veri tabanı açıyoruz.
            SQLiteDatabase database = this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);
            //tabloyu oluşturuyoruz.
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY,name VARCHAR,age INTEGER)"); // başka bi isim de verebilirdim.VARCHAR STRING


            //database.execSQL("INSERT INTO musicians(name,age) VALUES ('James',50)"); // veri kaydettik.
            //database.execSQL("INSERT INTO musicians(name,age) VALUES ('Lars',60)");
            //database.execSQL("INSERT INTO musicians(name,age) VALUES ('Kirk',55)");

            //database.execSQL("UPDATE musicians SET age = 61 WHERE name = 'Lars'"); // güncelleme
            //database.execSQL("UPDATE musicians SET name = 'Kirk Hammett' WHERE id = 3 ");

            //database.execSQL("DELETE FROM musicians WHERE id = 2"); // silme larsı sildik


            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE age > 52 ",null);
            Cursor cursor = database.rawQuery("SELECT * FROM musicians ",null);
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE '%s'",null); // isminin son harfi s olanı alır
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE 'K%'",null); // ilk harfi k olan

            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            int idIx = cursor.getColumnIndex("id");

            while (cursor.moveToNext()) {
                System.out.println("Name: " +cursor.getString(nameIx));
                System.out.println("Age: " +cursor.getInt(ageIx));
                System.out.println("Id: " +cursor.getInt(idIx));

        }
        cursor.close();


        } catch (Exception e) { // hata olması durumunda hatayı yakalar ve gösterir.

        }
    }
}