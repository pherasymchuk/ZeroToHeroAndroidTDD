package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.contains

class MainActivity : AppCompatActivity() {
    private lateinit var rootLayout: LinearLayout
    private lateinit var titleTextView: TextView
    private lateinit var removeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rootLayout = findViewById(R.id.rootLayout)
        titleTextView = findViewById(R.id.titleTextView)
        removeButton = findViewById(R.id.removeButton)

        removeButton.setOnClickListener {
            rootLayout.removeView(titleTextView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val isTextViewRemoved = !rootLayout.contains(titleTextView)
        outState.putBoolean("key", isTextViewRemoved)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.getBoolean("key")) removeButton.performClick()
    }
}
