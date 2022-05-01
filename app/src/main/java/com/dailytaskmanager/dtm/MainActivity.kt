package com.dailytaskmanager.dtm

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionbar = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#17EBDE"))
        actionbar?.setBackgroundDrawable(colorDrawable)
        setContentView(R.layout.activity_main)
    }
}