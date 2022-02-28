package com.example.mycustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

class MyButton : AppCompatButton {

    private var enabledBackground: Drawable? = null
    private var disabledBackground: Drawable? = null
    private var txtColor: Int = 0

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context,attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context,attrs,defStyleAttr) {
        init()
    }


    /**
     * To change shape of the button
     * use onDraw() method, bawaan dari kelas view
     * */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        background = when {
            isEnabled -> enabledBackground
            else -> disabledBackground
        }

        setTextColor(txtColor)
        textSize = 12f

        //make object in button to the center
        gravity = Gravity.CENTER
        text = when {
            isEnabled -> "Send ✈"
            else -> "Fill it first ⛔"
        }


    }


    private fun init() {
        txtColor = ContextCompat.getColor(context, android.R.color.background_light)
        enabledBackground = ContextCompat.getDrawable(context, R.drawable.bg_button)
        disabledBackground = ContextCompat.getDrawable(context,R.drawable.bg_button_disable)
    }

}