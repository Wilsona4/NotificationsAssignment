package com.funcrib.weekthreeassignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_secondary.*

class SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        /*Get Extras passed by intent*/
        val inactive = intent.getStringExtra("INACTIVE")

        secTextView.text = intent.getStringExtra("ACTIVE") ?: inactive
    }

    /*Return to Main Activity onBack Pressed*/
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}