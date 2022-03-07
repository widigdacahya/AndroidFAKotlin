package com.example.myflexiblefragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        * Proses penanaman or implement or placed
        * home fragment to the activity
        *
        * */
        val mFragmentManager = supportFragmentManager
        val mHomeFragment = HomeFragment()
        val fragment = mFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)

        if(fragment !is HomeFragment) {
            Log.d("MyFlexibleFragment", "Fragment Name: " + HomeFragment::class.java.simpleName)


            //[ code below change with KTX ;D ]
            //mFragmentManager
            //    .beginTransaction()
            //    .add(R.id.frame_containerUI, mHomeFragment, HomeFragment::class.java.simpleName)
            //    .commit()

            mFragmentManager.commit {
                add(R.id.frame_containerUI,mHomeFragment,HomeFragment::class.java.simpleName)
            }
        }

    }




}