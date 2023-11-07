package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val linearLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            this.layoutParams = layoutParams
        }
        val textView = TextView(this).apply {
            text = context.getString(R.string.i_am_an_android_developer)
            id = R.id.titleTextView
            val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            this.layoutParams = layoutParams
        }
        linearLayout.addView(textView, textView.layoutParams)
        setContentView(linearLayout)
    }
}
