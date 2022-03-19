package com.cahyadesthian.thesharedpreferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.cahyadesthian.thesharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mUserPreference: UserPreference

    private var isPreferenceEmpty = false
    private lateinit var userModel: UserModel

    private lateinit var mainBinding: ActivityMainBinding



    /*
    * [ Call Form User Preference to get the result ]
    * Di sini kita lihat, untuk memulai activity kita tidak menggunakan startActivity,
    * melainkan menggunakan metode launch dari ActivityResultLauncher hasil dari
    * registerForActivityForResult.
    *
    * Apa bedanya? Bedanya yaitu registerForActivityResult tidak hanya berpindah Activity,
    * namun juga mendapatkan result (hasil) dari Activity tersebut.
    * */
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {result: ActivityResult ->
        if (result.data != null && result.resultCode == FormUserPreferenceActivity.RESULT_CODE) {
            userModel = result.data?.getParcelableExtra<UserModel>(FormUserPreferenceActivity.EXTRA_RESULT) as UserModel
            populateView(userModel)
            checkForm(userModel)
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        supportActionBar?.title = "My User Preference"

        mUserPreference = UserPreference(this)

        showExistingPreference()

        mainBinding.btnSaveMainUI.setOnClickListener(this)
    }


    private fun showExistingPreference() {
        userModel = mUserPreference.getUser()
        populateView(userModel)
        checkForm(userModel)

    }

    private fun populateView(userModel: UserModel) {
        mainBinding.apply {
            tvNamedataMainUI.text = if(userModel.name.toString().isEmpty()) "Not Exist" else userModel.name
            tvEmaildataMainUI.text = if(userModel.email.toString().isEmpty()) "Not Exist" else userModel.email
            tvAgedataMainUI.text = if(userModel.age.toString().isEmpty()) "Not Exist" else userModel.age.toString()
            tvHandphonedataMainUI.text = if(userModel.phoneNumber.toString().isEmpty()) "Not Exist" else userModel.phoneNumber.toString()
            tvLoveherdataMainUI.text = if(userModel.isLove) "Yes" else "No"
        }
    }

    private fun checkForm(userModel: UserModel) {
        when {
            userModel.name.toString().isNotEmpty() -> {
                mainBinding.btnSaveMainUI.text = getString(R.string.change)
                isPreferenceEmpty = false
            }
            else -> {
                mainBinding.btnSaveMainUI.text = getString(R.string.save)
                isPreferenceEmpty = true
            }
        }
    }

    override fun onClick(v: View?) {

        if(v?.id == R.id.btn_save_mainUI) {
            val intent = Intent(this@MainActivity, FormUserPreferenceActivity::class.java)
            when {
                /**
                 * Ketika isPreferenceEmpty bernilai true,
                 * maka kita mengirim data ke FormUserPreferenceActivity dengan data TYPE_ADD,
                 * sedangkan jika bernilai false akan mengirim data TYPE_EDIT.
                 *
                 * pengaturan isPreference itu empty atau tidak ada di fungsi atau method checkForm
                 * */
                isPreferenceEmpty -> {
                    intent.putExtra(
                        FormUserPreferenceActivity.EXTRA_TYPE_FORM,
                        FormUserPreferenceActivity.TYPE_ADD
                    )
                    intent.putExtra("USER", userModel)
                }
                else -> {
                    intent.putExtra(
                        FormUserPreferenceActivity.EXTRA_TYPE_FORM,
                        FormUserPreferenceActivity.TYPE_EDIT
                    )
                    intent.putExtra("USER", userModel)
                }
            }
            resultLauncher.launch(intent)
        }


    }


}