package com.example.ps_g8;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.assertEquals;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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

@LargeTest
@RunWith(AndroidJUnit4.class)
public class InterfaceTest {

    @BeforeClass // Crear la base de datos antes de ejecutar los tests llamando a AdminSQLiteOpenHelper
    public static void setUp() {
        // Crear el usuario de prueba que es al que se le pasará la clave en MainActivity2
        try (AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(InstrumentationRegistry.getInstrumentation().getTargetContext(), "administracion", null, 1)) {
            admin.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Rule
    public ActivityScenarioRule<MainActivity2> activityRule = new ActivityScenarioRule<>(MainActivity2.class);

    @Test
    public void testMeGustaButtonChangesIcon() {
        // Seleccionar la referencia del botón
        ActivityScenario<MainActivity2> btn = activityRule.getScenario().onActivity(new ActivityScenario.ActivityAction<MainActivity2>() {
            @Override
            public void perform(MainActivity2 activity) {
                Resources res = InstrumentationRegistry.getInstrumentation().getTargetContext().getResources();
                int expectedIcon = res.getIdentifier("ic_baseline_favorite_border_24", "drawable", "com.example.ps_g8");
                Drawable expectedDrawable = res.getDrawable(expectedIcon);
                // Comprobar que el botón tiene el icono correcto (sin rellenar) al principio
                ImageButton btn = activity.findViewById(R.id.imageButton1);
                assertEquals(expectedDrawable, btn.getDrawable());
                // Hacer click en el botón
                onView(withId(R.id.imageButton1)).perform(click());
                // Comprobar que el botón tiene el icono correcto (rellenado) al final
                expectedIcon = res.getIdentifier("ic_baseline_favorite_24", "drawable", "com.example.ps_g8");
                expectedDrawable = res.getDrawable(expectedIcon);
                assertEquals(expectedDrawable, btn.getDrawable());
                // Hacer click en el botón otra vez
                onView(withId(R.id.imageButton1)).perform(click());
                // Comprobar que el botón tiene el icono correcto (sin rellenar) al final
                expectedIcon = res.getIdentifier("ic_baseline_favorite_border_24", "drawable", "com.example.ps_g8");
                expectedDrawable = res.getDrawable(expectedIcon);
                assertEquals(expectedDrawable, btn.getDrawable());
            }
        });
    }

    @Test
    public void testVistoButtonChangesIcon() {
        // Seleccionar la referencia del botón
        ActivityScenario<MainActivity2> btn = activityRule.getScenario().onActivity(new ActivityScenario.ActivityAction<MainActivity2>() {
            @Override
            public void perform(MainActivity2 activity) {
                Resources res = InstrumentationRegistry.getInstrumentation().getTargetContext().getResources();
                int expectedIcon = res.getIdentifier("ic_baseline_not_interested_eye_24", "drawable", "com.example.ps_g8");
                Drawable expectedDrawable = res.getDrawable(expectedIcon);
                // Comprobar que el botón tiene el icono correcto (no visto) al principio
                ImageButton btn = activity.findViewById(R.id.imageButton2);
                assertEquals(expectedDrawable, btn.getDrawable());
                // Hacer click en el botón
                onView(withId(R.id.imageButton2)).perform(click());
                // Comprobar que el botón tiene el icono correcto (visto) al final
                expectedIcon = res.getIdentifier("ic_baseline_remove_red_eye_24", "drawable", "com.example.ps_g8");
                expectedDrawable = res.getDrawable(expectedIcon);
                assertEquals(expectedDrawable, btn.getDrawable());
                // Hacer click en el botón otra vez
                onView(withId(R.id.imageButton2)).perform(click());
                // Comprobar que el botón tiene el icono correcto (no visto) al final
                expectedIcon = res.getIdentifier("ic_baseline_not_interested_eye_24", "drawable", "com.example.ps_g8");
                expectedDrawable = res.getDrawable(expectedIcon);
                assertEquals(expectedDrawable, btn.getDrawable());
            }
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
