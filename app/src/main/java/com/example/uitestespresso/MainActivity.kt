package com.example.uitestespresso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Keep in mind to disable animation on device u are testing

        button_next_activity.setOnClickListener {
            val intent = Intent(this,SecondaryActivity::class.java)
            startActivity(intent)
        }
    }
}
