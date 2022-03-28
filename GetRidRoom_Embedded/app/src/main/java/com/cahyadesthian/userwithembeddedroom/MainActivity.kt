package com.cahyadesthian.userwithembeddedroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cahyadesthian.userwithembeddedroom.data.Address
import com.cahyadesthian.userwithembeddedroom.data.Person
import com.cahyadesthian.userwithembeddedroom.data.PersonViewModel
import com.cahyadesthian.userwithembeddedroom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    val adapter by lazy { PersonAdapter()  }
    private lateinit var personViewModel: PersonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        personViewModel = ViewModelProvider(this).get(PersonViewModel::class.java)

        mainBinding.apply {
            rcPerson.adapter = adapter
            rcPerson.layoutManager = LinearLayoutManager(this@MainActivity)

        }


        /*
        * Insert Data Person and Address
        * */
        val addressToInsert = Address("Keputih gg 1C Surabaya Sukolilo", 99999)
        val personToInsert = Person(0, "Nareswara", "Garuda", 109,addressToInsert)
        personViewModel.insertPerson(personToInsert)

        personViewModel.readPerson.observe(this, {
            adapter.setData(it)
        })

    }
}