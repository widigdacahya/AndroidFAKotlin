package com.cahyadesthian.learnexercisethree.ui.activity

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.cahyadesthian.learnexercisethree.R
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }


    /**
     * Test untuk mengganti mode(day/night)
     * 1. buka aplikasi
     * 2. cek ada tombol menu ganti mode
     * 3. klik tombol menu ganti mode
     * 4. cek tombol untuk ganti mode
     * 5. klik tombol untuk ganti mode
     * */

    @Test
    fun checkThemeChanges() {
        onView(withId(R.id.setting_mode)).check(matches(isDisplayed()))
        onView(withId(R.id.setting_mode)).perform(click())
        onView(withId(R.id.switch_btn_change_settingUI)).check(matches(isDisplayed()))
        onView(withId(R.id.switch_btn_change_settingUI)).perform(click())
    }

}