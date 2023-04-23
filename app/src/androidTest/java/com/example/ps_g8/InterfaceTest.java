package com.example.ps_g8;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.assertEquals;

import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class InterfaceTest {

    @BeforeClass
    public static void setUp() {
    // Crear la base de datos de prueba
        try (AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(InstrumentationRegistry.getInstrumentation().getTargetContext(), "Administracion", null, 1)) {
            SQLiteDatabase db = admin.getWritableDatabase();
            admin.agregarPelis(db);
            db.execSQL("INSERT INTO usuario (email, contraseña) VALUES ('user1@example.com', '1234');");
            db.execSQL("INSERT INTO relacion (id, usuario, gusta, visto) VALUES (1, 'user1@example.com', 0, 0);");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Mock intent para que el getintent de la clase no falle
        Intent intent = Mockito.mock(Intent.class);
        // Mock bundle hace falta para que el getextras de la clase no falle
        Bundle bundle = Mockito.mock(Bundle.class);
        // Ponemos el email del usuario en el bundle, la clave que necesita la clase es "usuario"
        Mockito.when(bundle.getString("usuario")).thenReturn("user1@example.com");
        // Poner el bundle en el intent
        Mockito.when(intent.getExtras()).thenReturn(bundle);

    }

    @Rule
    public ActivityScenarioRule<MainActivity2> activityRule = new ActivityScenarioRule<>(MainActivity2.class);

    @Test
    public void testMeGustaButtonChangesIcon() {
        // Seleccionar la referencia del botón
        ActivityScenario<MainActivity2> btn = activityRule.getScenario().onActivity(activity -> {
            Resources res = InstrumentationRegistry.getInstrumentation().getTargetContext().getResources();
            int expectedIcon = res.getIdentifier("ic_baseline_favorite_border_24", "drawable", "com.example.ps_g8");
            Drawable expectedDrawable = res.getDrawable(expectedIcon);
            // Comprobar que el botón tiene el icono correcto (sin rellenar) al principio
            ImageButton btn1 = activity.findViewById(R.id.imageButton1);
            assertEquals(expectedDrawable, btn1.getDrawable());
            // Hacer click en el botón
            onView(withId(R.id.imageButton1)).perform(click());
            // Comprobar que el botón tiene el icono correcto (rellenado) al final
            expectedIcon = res.getIdentifier("ic_baseline_favorite_24", "drawable", "com.example.ps_g8");
            expectedDrawable = res.getDrawable(expectedIcon);
            assertEquals(expectedDrawable, btn1.getDrawable());
            // Hacer click en el botón otra vez
            onView(withId(R.id.imageButton1)).perform(click());
            // Comprobar que el botón tiene el icono correcto (sin rellenar) al final
            expectedIcon = res.getIdentifier("ic_baseline_favorite_border_24", "drawable", "com.example.ps_g8");
            expectedDrawable = res.getDrawable(expectedIcon);
            assertEquals(expectedDrawable, btn1.getDrawable());
        });
    }

    @Test
    public void testVistoButtonChangesIcon() {
        // Seleccionar la referencia del botón
        ActivityScenario<MainActivity2> btn = activityRule.getScenario().onActivity(activity -> {
            Resources res = InstrumentationRegistry.getInstrumentation().getTargetContext().getResources();
            int expectedIcon = res.getIdentifier("ic_baseline_not_interested_eye_24", "drawable", "com.example.ps_g8");
            Drawable expectedDrawable = res.getDrawable(expectedIcon);
            // Comprobar que el botón tiene el icono correcto (no visto) al principio
            ImageButton btn1 = activity.findViewById(R.id.imageButton2);
            assertEquals(expectedDrawable, btn1.getDrawable());
            // Hacer click en el botón
            onView(withId(R.id.imageButton2)).perform(click());
            // Comprobar que el botón tiene el icono correcto (visto) al final
            expectedIcon = res.getIdentifier("ic_baseline_remove_red_eye_24", "drawable", "com.example.ps_g8");
            expectedDrawable = res.getDrawable(expectedIcon);
            assertEquals(expectedDrawable, btn1.getDrawable());
            // Hacer click en el botón otra vez
            onView(withId(R.id.imageButton2)).perform(click());
            // Comprobar que el botón tiene el icono correcto (no visto) al final
            expectedIcon = res.getIdentifier("ic_baseline_not_interested_eye_24", "drawable", "com.example.ps_g8");
            expectedDrawable = res.getDrawable(expectedIcon);
            assertEquals(expectedDrawable, btn1.getDrawable());
        });
    }

    @Test
    public void testMenuDesplegable() {
        // Iniciar la actividad
        ActivityScenario<MainActivity2> scenario = ActivityScenario.launch(MainActivity2.class);
        // Hacer click en el botón de opciones
        onView(withId(R.menu.acordeon_opciones)).perform(click());
        // Comprobar que se despliega el menú
        onView(withId(R.menu.acordeon_opciones)).check(matches(isDisplayed()));
    }
}
