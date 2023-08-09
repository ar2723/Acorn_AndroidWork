package com.example.step08gameview

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val startBtn: Button = findViewById(R.id.startBtn)
        startBtn.setOnClickListener{
            /*
                자바에서 사용했던 클래스 type value 얻어오는 방법
                1. java => 클래스명.class
                2. kotlin => 클래스명::class.java
             */
            val intent = Intent(this,GameActivity::class.java)
            startActivity(intent)
        }
    }
}