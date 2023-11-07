package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.LinearLayout.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LinearLayout(this).apply {
            gravity = Gravity.CENTER
            orientation = LinearLayout.VERTICAL
            this.layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)

            TextView(this@MainActivity).apply {
                id = R.id.titleTextView
                text = context.getString(R.string.i_am_an_android_developer)
                this.layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            }.let { this.addView(it, it.layoutParams) }

            MaterialButton(this@MainActivity).apply {
                text = context.getString(android.R.string.ok)
                this.layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            }.let { this.addView(it, it.layoutParams) }
        }.let { setContentView(it) }
    }
}
