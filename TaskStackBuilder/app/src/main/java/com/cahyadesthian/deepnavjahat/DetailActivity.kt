package com.cahyadesthian.deepnavjahat

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cahyadesthian.deepnavjahat.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailBinding

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_MESSAGE = "extra_message"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)


        val title = intent.getStringExtra(EXTRA_TITLE)
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        detailBinding.tvTitleDetailUI.text = title
        detailBinding.tvMessageDetailUI.text = message
    }




}