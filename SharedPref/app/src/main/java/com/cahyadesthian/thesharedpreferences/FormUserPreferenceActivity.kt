package com.cahyadesthian.thesharedpreferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.cahyadesthian.thesharedpreferences.databinding.ActivityFormUserPreferenceBinding

class FormUserPreferenceActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var userModel: UserModel
    private lateinit var formBinding: ActivityFormUserPreferenceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        formBinding = ActivityFormUserPreferenceBinding.inflate(layoutInflater)
        setContentView(formBinding.root)

        formBinding.btnSaveFormUI.setOnClickListener(this)

        //untuk ambil data dari Intent
        userModel = intent.getParcelableExtra<UserModel>("USER") as UserModel
        val formType = intent.getIntExtra(EXTRA_TYPE_FORM,0)

        var actionBarTitle = ""
        var btnTitle = ""

        when(formType) {

            TYPE_ADD -> {
                actionBarTitle = "Tambah Baru"
                btnTitle = "Simpan"
            }
            TYPE_EDIT -> {
                actionBarTitle = "Ubah"
                btnTitle = "update"
                showPreferenceInForm()
            }

        }

        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        formBinding.btnSaveFormUI.text = btnTitle

    }

    override fun onClick(v: View?) {

        if(v?.id == R.id.btn_save_formUI) {

            val name : String
            val email : String
            val age: String
            val phoneNo : String
            val isLoveHer : Boolean

            formBinding.apply {
                name = edtNameFormUI.text.toString().trim()
                email = edtEmailFormUI.text.toString().trim()
                age = edtAgeFormUI.text.toString().trim()
                phoneNo = edtPhoneFormUI.text.toString().trim()
                isLoveHer = rgLoveherFormUI.checkedRadioButtonId == R.id.rb_yes_formUI

                if(name.isEmpty()) {
                    edtNameFormUI.error = FIELD_REQUIRED
                    return
                }

                if(email.isEmpty()) {
                    edtEmailFormUI.error = FIELD_REQUIRED
                    return
                }

                if(!isValidEmail(email)) {
                    edtEmailFormUI.error = FIELD_IS_NOT_VALID
                    return
                }

                if(age.isEmpty()) {
                    edtAgeFormUI.error = FIELD_REQUIRED
                    return
                }

                if(phoneNo.isEmpty()) {
                    edtPhoneFormUI.error = FIELD_REQUIRED
                    return
                }

                if(!TextUtils.isDigitsOnly(phoneNo)) {
                    edtPhoneFormUI.error = FIELD_DIGIT_ONLY
                    return
                }

            }

            saveUser(name,email,age,phoneNo,isLoveHer)

            /*
            * memberikan result (hasil) dengan cara berikut
            * Kemudian diterima pada onActivityResult, dengan menyamakan resultCode-nya.
            *
            * kode dari MainActivity yang ada
            * result.data != null && result.resultCode == FormUserPreferenceActivity.RESULT_CODE
            *
            * */
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_RESULT, userModel)
            setResult(RESULT_CODE, resultIntent)

            finish()
        }


    }


    private fun saveUser(nameSaved: String, emailSaved: String, ageSaved: String,phoneNoSaved: String, isLoveHerSaved:Boolean ) {

        val userPreference = UserPreference(this)

        userModel.name = nameSaved
        userModel.email = emailSaved
        userModel.age = Integer.parseInt(ageSaved)
        userModel.phoneNumber = phoneNoSaved
        userModel.isLove = isLoveHerSaved

        userPreference.setUser(userModel)
        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
    }

    private fun isValidEmail(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun showPreferenceInForm() {
        formBinding.apply {
            edtNameFormUI.setText(userModel.name)
            edtEmailFormUI.setText(userModel.email)
            edtAgeFormUI.setText(userModel.age.toString())
            edtPhoneFormUI.setText(userModel.phoneNumber)
            if(userModel.isLove) rbYesFormUI.isChecked = true else rbNoFormUI.isChecked = true

        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


    companion object {
        const val EXTRA_TYPE_FORM = "extra_type_form"
        const val EXTRA_RESULT = "extra_result"
        const val RESULT_CODE = 101

        const val TYPE_ADD = 1
        const val TYPE_EDIT = 2

        private const val FIELD_REQUIRED = "Field Must be filled"
        private const val FIELD_DIGIT_ONLY = "Numeric Only"
        private const val FIELD_IS_NOT_VALID = "Invalid Email"
    }



}