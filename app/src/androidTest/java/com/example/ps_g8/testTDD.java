package com.example.ps_g8;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

import android.content.Intent;
import android.text.InputType;
import android.widget.EditText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
// clase creada para testear la funcionalidad del botón de mostrar contraseña
public class testTDD {
    @Rule
    // Asignar el intent con el que se inicia la actividad
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<MainActivity>(new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), MainActivity.class));

    @Test
    public void testShowPasswordTDD(){
        ActivityScenario<MainActivity> btn = activityRule.getScenario().onActivity(activity-> {
            //Hacer click en el botón
            onView(withId(R.id.showPassButton)).perform(click());
            EditText pass = activity.findViewById(R.id.id_Password);
            assertEquals(InputType.TYPE_CLASS_TEXT,
                    pass.getInputType());
            //Hacer click en el botón otra vez
            onView(withId(R.id.showPassButton)).perform(click());
            assertEquals(129,
                    pass.getInputType());
        });
    }

}
