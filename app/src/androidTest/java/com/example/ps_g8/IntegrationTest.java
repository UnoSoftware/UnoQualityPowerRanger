package com.example.ps_g8;

import static org.junit.Assert.assertEquals;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class IntegrationTest {

    @BeforeClass
    public static void setUpDB() {
        try {
            Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "Administracion", null, 1);
            SQLiteDatabase baseDatos = admin.getWritableDatabase();

            baseDatos.execSQL("insert into relacion (id,usuario, gusta, visto)values (1, 'email@email.com',0,0);");


        } catch(Exception e){

        }
    }
    @Test
    public void testDatabase() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "Administracion", null, 1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();

        String email = "email@email.com";
        String query = "select * from relacion ";

        Cursor c = baseDatos.rawQuery(query, null);
        ContentValues cv = new ContentValues();

        if (c.moveToFirst() && c.getCount() >= 1) {
            do {
                @SuppressLint("Range") int id = c.getInt(c.getColumnIndex("id"));
                cv.put("usuario", email);
                cv.put("id", id);
                cv.put("visto", 0);
                cv.put("gusta", 0);

            } while (c.moveToNext());
        }
        c.close();
        baseDatos.close();


        assertEquals(email, cv.get("usuario"));
        assertEquals(0, cv.get("visto"));
        assertEquals(0, cv.get("gusta"));

    }
}
