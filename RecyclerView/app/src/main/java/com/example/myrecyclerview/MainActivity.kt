package com.example.myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvHeroes : RecyclerView
    private val heroesList = ArrayList<Hero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvHeroes = findViewById(R.id.rv_heroesUI)
        rvHeroes.setHasFixedSize(true)


        /*
        * Call data form sting values
        * */
        heroesList.addAll(listHeroes)

        showRecyclerList()

    }



    private val listHeroes: ArrayList<Hero>
        get() {
            val dataName = resources.getStringArray(R.array.data_name)
            val dataDescription = resources.getStringArray(R.array.data_description)
            val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

            val listOfHero = ArrayList<Hero>()

            for(i in dataName.indices) {
                val hero = Hero(dataName[i], dataDescription[i], dataPhoto.getResourceId(i,-1))
                listOfHero.add(hero)
            }

        return listOfHero
        }


    private fun showRecyclerList() {
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(heroesList)
        rvHeroes.adapter = listHeroAdapter
    }

}