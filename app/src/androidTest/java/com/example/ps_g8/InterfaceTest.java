package com.example.ps_g8;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasBackground;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.widget.ImageButton;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.atomic.AtomicReference;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class InterfaceTest {

    @BeforeClass
    public static void setUp() {
        // Crear la base de datos de prueba
        try {
            Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "Administracion", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            admin.agregarPelis(db);
            // Prune database
            db.execSQL("DELETE FROM usuario;");
            db.execSQL("DELETE FROM relacion;");
            // Delete all movies except the first one
            db.execSQL("DELETE FROM pelicula WHERE id != 1;");
            // Insert test user
            db.execSQL("INSERT INTO usuario (email, contraseña) VALUES ('user1@example.com', '1234');");
            db.execSQL("INSERT INTO relacion (id, usuario, gusta, visto) VALUES (1, 'user1@example.com', 0, 0);");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Rule
    // Asignar el intent con el que se inicia la actividad
    public ActivityScenarioRule<MainActivity2> activityRule = new ActivityScenarioRule<MainActivity2>(new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), MainActivity2.class).putExtra("usuario", "user1@example.com"));

    @Test
    public void testMeGustaButtonChangesIcon() {
        // Buscar el botón de "me gusta" por su ID
        onView(withId(R.id.imageButton1))
                // Comprobar que el botón se muestra en pantalla
                .check(matches(isDisplayed()));
        int oldIconId = R.drawable.ic_baseline_favorite_border_24;
        Drawable oldIcon = InstrumentationRegistry.getInstrumentation().getTargetContext().getDrawable(oldIconId);
        onView(withId(R.id.imageButton1))
                // Hacer clic en el botón
                .perform(click());
        // Obtener el icono del botón
        activityRule.getScenario().onActivity(activity -> {
            ImageButton button = activity.findViewById(R.id.imageButton1);
            Drawable icon = button.getBackground();
            // Comprobar que el icono cambia
            assertNotEquals(oldIcon, icon);
        });
    }

    @Test
    public void testVistoButtonChangesIcon() {
        // Buscar el botón de "visto" por su ID
        onView(withId(R.id.imageButton2))
                // Comprobar que el botón se muestra en pantalla
                .check(matches(isDisplayed()));
        int oldIconId = R.drawable.ic_baseline_not_interested_24;
        Drawable oldIcon = InstrumentationRegistry.getInstrumentation().getTargetContext().getDrawable(oldIconId);
        onView(withId(R.id.imageButton2))
                // Hacer clic en el botón
                .perform(click());
        // Obtener el icono del botón
        activityRule.getScenario().onActivity(activity -> {
            ImageButton button = activity.findViewById(R.id.imageButton2);
            Drawable icon = button.getBackground();
            // Comprobar que el icono cambia
            assertNotEquals(oldIcon, icon);
        });
    }

    @Test
    public void testMenuDesplegable() {
        // Hacer click en el botón de opciones
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        // Comprobar que se muestra el menú desplegable
        onView(withText("Log Out")).check(matches(isDisplayed()));
    }
}
