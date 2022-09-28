package com.example.agendaalura;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.agendaalura.ui.activity.StudentFormActivity;
import com.example.agendaalura.ui.activity.StudentsListActivity;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule<StudentsListActivity> activityRule
            = new ActivityScenarioRule(StudentsListActivity.class);

    @Test
    public void addStudent() {
        onView(withId(R.id.activity_students_list_fab))
                .perform(click());
        onView(withId(R.id.activity_students_form_name))
                .perform(click())
                .perform(typeText("Aluno"));
        onView(withId(R.id.activity_students_form_phone))
                .perform(click())
                .perform(typeText("22334455"));
        onView(withId(R.id.activity_students_form_email))
                .perform(click())
                .perform(typeText("aluno@aluno.com.br"));
        onView(withId(R.id.activity_students_form_salvar))
                .perform(click());
    }
}