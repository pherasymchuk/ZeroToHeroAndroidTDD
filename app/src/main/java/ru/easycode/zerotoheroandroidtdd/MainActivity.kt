package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.changeButton)
        button.setOnClickListener {
            findViewById<TextView>(R.id.titleTextView).text = getString(
                R.string
                    .i_am_an_android_developer
            )
        }
    }
}
