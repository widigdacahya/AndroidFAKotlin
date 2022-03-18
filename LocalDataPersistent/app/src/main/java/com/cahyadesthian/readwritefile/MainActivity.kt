package com.cahyadesthian.readwritefile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.cahyadesthian.readwritefile.FileHelper.readFromFile
import com.cahyadesthian.readwritefile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNewMainUI.setOnClickListener(this)
        binding.btnOpenMainUI.setOnClickListener(this)
        binding.btnSaveMainUI.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_new_mainUI -> newFile()
            R.id.btn_open_mainUI -> showFileList()
            R.id.btn_save_mainUI -> saveFile()
        }
    }

    //[btn new]
    private fun newFile() {
        binding.etEditTitleMainUI.setText("")
        binding.etEditFileMainUI.setText("")
        Toast.makeText(this, "Clearing file", Toast.LENGTH_SHORT).show()
    }


    //[btn open]
    private fun showFileList() {
        val items = fileList()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose file : ")
        builder.setItems(items) { dialog,item ->
            loadData(items[item].toString())
        }
        val alert = builder.create()
        alert.show()

    }


    private fun loadData(title:String) {
        val fileModel = FileHelper.readFromFile(this,title)
        binding.etEditTitleMainUI.setText(fileModel.filename)
        binding.etEditFileMainUI.setText(fileModel.data)
        Toast.makeText(this,"Loading " + fileModel.filename + " data", Toast.LENGTH_SHORT ).show()

    }



    //[btn save]
    private fun saveFile() {
        when {
            binding.etEditTitleMainUI.text.toString().isEmpty() -> Toast.makeText(this, "Title must be filled", Toast.LENGTH_SHORT).show()
            binding.etEditFileMainUI.text.toString().isEmpty() -> Toast.makeText(this, "COntent need to be filled", Toast.LENGTH_SHORT).show()
            else -> {
                val title = binding.etEditTitleMainUI.text.toString()
                val text = binding.etEditFileMainUI.text.toString()
                val fileModel = FileModel()
                fileModel.filename = title
                fileModel.data = text
                FileHelper.writeToFile(fileModel,this)
                Toast.makeText(this,"Saving " + fileModel.filename + " file", Toast.LENGTH_SHORT).show()
            }

        }
    }
}