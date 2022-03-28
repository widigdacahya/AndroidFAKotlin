package com.cahyadesthian.userwithembeddedroom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cahyadesthian.userwithembeddedroom.data.Person
import com.cahyadesthian.userwithembeddedroom.databinding.RowLayoutBinding

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    private var personList = emptyList<Person>()

    inner class PersonViewHolder(val listPersonAdapterBinding: RowLayoutBinding): RecyclerView.ViewHolder(listPersonAdapterBinding.root) {

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonAdapter.PersonViewHolder {
        val view = RowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonAdapter.PersonViewHolder, position: Int) {

        holder.listPersonAdapterBinding.apply {

            tvIdUser.text = personList[position].id.toString()
            tvFirstnameUser.text = personList[position].firstName
            tvLastnameUser.text = personList[position].lastName
            tvAgeUser.text = personList[position].age.toString()

            tvStreetNameUser.text = personList[position].adress.streetName
            tvStreetNumberUser.text = personList[position].adress.streenNumber.toString()

        }

    }

    override fun getItemCount(): Int = personList.size


    fun setData(persons: List<Person>) {
        personList = persons
        notifyDataSetChanged()
    }


}