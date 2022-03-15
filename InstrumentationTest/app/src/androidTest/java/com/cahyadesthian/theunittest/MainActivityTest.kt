package com.cahyadesthian.theunittest

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    private val dummyVolume = "504.0"
    private val dummyCircumference = "100.0"
    private val dummySurfaceArea = "396.0"
    private val dummyLength = "12.0"
    private val dummyWidth = "7.0"
    private val dummyHeight = "6.0"
    private val emptyInput = ""
    private val fieldEmpty = "Field ini tida bisa kosong"
    private val fieldEmptyWidht = "This Field Width Can't be empty"
    private val fieldEmptytLength = "This Field Length Can't be empty"
    private val fieldEmptytHeight = "This Field Height Can't be empty"


    //untuk tell which activity to execute
    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    /*
    * [Skenario Pengujian keliling balok.]
    * Aplikasi terbuka dan menampilkan beberapa view.
    * Memberi tindakan input pada edt_lenght, edt_width dan edt_height.
    * Memastikan Button btn_save telah ditampilkan.
    * Memberi tindakan klik pada btn_save.
    * Memastikan Button btn_calculate_circuference telah ditampilkan.
    * Memberi tindakan klik pada btn_calculate_circuference.
    * Memastikan TextView tv_result telah ditampilkan.
    * Memastikan hasil yang tampil sesuai  ekspektasi.
    * */
    @Test
    fun assertGetCircumference() {
        onView(withId(R.id.edt_length)).perform(typeText(dummyLength),closeSoftKeyboard())
        onView(withId(R.id.edt_height)).perform(typeText(dummyHeight), closeSoftKeyboard())
        onView(withId(R.id.edt_width)).perform(typeText(dummyWidth), closeSoftKeyboard())

        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())

        onView(withId(R.id.btn_calc_circumference)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_calc_circumference)).perform(click())

        onView(withId(R.id.tv_result)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_result)).check(matches(withText(dummyCircumference)))

    }


    /*[Pengujian volume balok]
    * Aplikasi terbuka dan menampilkan beberapa view.
    * Memberi tindakan input pada edt_lenght, edt_width dan edt_height.
    * Memastikan Button btn_save telah ditampilkan.
    * Memberi tindakan klik pada btn_save.
    * Memastikan Button btn_calculate_volume telah ditampilkan.
    * Memberi tindakan klik pada btn_calculate_volume.
    * Memastikan TextView tv_result telah ditampilkan.
    * Memastikan hasil yang ditampilkan sesuai dengan ekspektasi.
    * */
    @Test
    fun assertGetVolume() {

        onView(withId(R.id.edt_height)).perform(typeText(dummyHeight), closeSoftKeyboard())
        onView(withId(R.id.edt_width)).perform(typeText(dummyWidth), closeSoftKeyboard())
        onView(withId(R.id.edt_length)).perform(typeText(dummyLength), closeSoftKeyboard())

        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())

        onView(withId(R.id.btn_calc_volume)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_calc_volume)).perform(click())

        onView(withId(R.id.tv_result)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_result)).check(matches(withText(dummyVolume)))


    }



    /*
    * [Skenario Pengujian luas permukaan balok.]
    * Memberi tindakan input pada edt_lenght, edt_width dan edt_height.
    * Memastikan Button btn_save telah ditampilkan.
    * Memberi tindakan klik pada btn_save.
    * Memastikan Button btn_calculate_surface_area telah ditampilkan.
    * Memberi tindakan klik pada btn_calculate_surface_area.
    * Memastikan TextView tv_result telah ditampilkan.
    * Memastikan hasil yang tampil sesuai  ekspektasi.
    * */
    @Test
    fun assertGetSurfaceArea() {
        onView(withId(R.id.edt_length)).perform(typeText(dummyLength), closeSoftKeyboard())
        onView(withId(R.id.edt_width)).perform(typeText(dummyWidth), closeSoftKeyboard())
        onView(withId(R.id.edt_height)).perform(typeText(dummyHeight), closeSoftKeyboard())

        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())

        onView(withId(R.id.btn_calc_area)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_calc_area)).perform(click())

        onView(withId(R.id.tv_result)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_result)).check(matches(withText(dummySurfaceArea)))
    }



    /*
    * [Pengujian input panjang, lebar dan tinggi balok.]
    * Aplikasi terbuka dan menampilkan beberapa view.
    * Memberi tindakan empty input pada edt_lenght.
    * Memastikan Button btn_save telah ditampilkan.
    * Memberi tindakan klik pada btn_save.
    * Memastikan eror yang tampil sesuai ekspektasi.
    * Memberi tindakan input pada edt_lenght.
    * Memberi tindakan empty input pada edt_width.
    * Memastikan Button btn_save telah ditampilkan.
    * Memberi tindakan klik pada btn_save.
    * Memastikan eror yang tampil sesuai ekspektasi.
    * Memberi tindakan input pada edt_width.
    * Memberi tindakan empty input pada edt_height.
    * Memastikan Button btn_save telah ditampilkan.
    * Memberi tindakan klik pada btn_save.
    * Memastikan eror yang tampil sesuai ekspektasi.
    * Memberi tindakan input pada edt_height.
    * Memastikan Button btn_save telah ditampilkan.
    * Memberi tindakan klik pada btn_save.
    * */
    @Test
    fun assertEmptyInput() {

        //cek inpiut length
        onView(withId(R.id.edt_length)).perform(typeText(emptyInput), closeSoftKeyboard())

        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())

        //onView(withId(R.id.edt_length)).check(matches(hasErrorText(fieldEmpty)))
        onView(withId(R.id.edt_length)).check(matches(hasErrorText(fieldEmptytLength)))
        onView(withId(R.id.edt_length)).perform(typeText(dummyLength), closeSoftKeyboard())

        //cek input width
        onView(withId(R.id.edt_width)).perform(typeText(emptyInput), closeSoftKeyboard())

        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())

        onView(withId(R.id.edt_width)).check(matches(hasErrorText(fieldEmptyWidht)))
        onView(withId(R.id.edt_width)).perform(typeText(dummyWidth), closeSoftKeyboard())


        //cek input height
        onView(withId(R.id.edt_height)).perform(typeText(emptyInput), closeSoftKeyboard())

        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())

        onView(withId(R.id.edt_height)).check(matches(hasErrorText(fieldEmptytHeight)))
        onView(withId(R.id.edt_height)).perform(typeText(dummyHeight), closeSoftKeyboard())




        //click save
        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_save)).perform(click())


    }


}