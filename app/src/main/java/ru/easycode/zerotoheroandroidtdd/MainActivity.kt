package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.view.Gravity
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.core.view.setPadding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var rootLinearLayout: LinearLayout
    private lateinit var contentLinearLayout: LinearLayout
    private lateinit var topLinearLayout: LinearLayout
    private lateinit var inputEditText: TextInputEditText
    private lateinit var createActionButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LinearLayout(this).apply {
            id = R.id.rootLinearLayout
            rootLinearLayout = this
            gravity = Gravity.CENTER_HORIZONTAL
            orientation = LinearLayout.VERTICAL
            setPadding(resources.getDimensionPixelSize(R.dimen.topLinearLayoutPadding))

            layoutParams = LinearLayout.LayoutParams(
                /* width = */ LinearLayout.LayoutParams.MATCH_PARENT,
                /* height = */ LinearLayout.LayoutParams.WRAP_CONTENT
            )
            addView(
                LinearLayout(this@MainActivity).apply {
                    id = R.id.topLayout
                    topLinearLayout = this
                    gravity = Gravity.CENTER
                    orientation = LinearLayout.HORIZONTAL

                    layoutParams = LinearLayout.LayoutParams(
                        /* width = */ LinearLayout.LayoutParams.MATCH_PARENT,
                        /* height = */ LinearLayout.LayoutParams.WRAP_CONTENT
                    )

                    addView(
                        TextInputEditText(this@MainActivity).apply {
                            id = R.id.inputEditText
                            inputEditText = this
                            text = Editable.Factory().newEditable("")
                            minWidth = resources.getDimensionPixelSize(R.dimen.editTextMinSize)
                            hint = "Enter some text"
                            inputType = InputType.TYPE_CLASS_TEXT
                            imeOptions = EditorInfo.IME_ACTION_DONE
                            gravity = Gravity.CENTER

                            layoutParams = LinearLayout.LayoutParams(
                                /* width = */ LinearLayout.LayoutParams.WRAP_CONTENT,
                                /* height = */ LinearLayout.LayoutParams.WRAP_CONTENT,
                            ).also { it.weight = 1f }
                        }
                    )

                    addView(
                        MaterialButton(this@MainActivity).apply {
                            id = R.id.actionButton
                            createActionButton = this
                            text = "create"
                            minWidth = resources.getDimensionPixelSize(R.dimen.createButtonMinWidth)

                            layoutParams = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                            )
                        }
                    )
                }
            )
            addView(
                LinearLayout(this@MainActivity).apply {
                    id = R.id.contentLayout
                    contentLinearLayout = this
                    orientation = LinearLayout.VERTICAL

                    layoutParams = ViewGroup.LayoutParams(
                        /* width = */ ViewGroup.LayoutParams.MATCH_PARENT,
                        /* height = */ ViewGroup.LayoutParams.WRAP_CONTENT,
                    )
                })
        }

        setContentView(rootLinearLayout)

        createActionButton.setOnClickListener {
            val inputText: String = inputEditText.text.toString()
            addTextView(inputText)
            inputEditText.text?.clear()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val list = contentLinearLayout.children.map { (it as TextView).text.toString() }.toList()
        outState.putStringArrayList(STATE_KEY, ArrayList(list))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val list = savedInstanceState.getStringArrayList(STATE_KEY) ?: ArrayList()
        list.forEach { inputText ->
            addTextView(inputText)
        }
    }

    fun addTextView(inputText: String) {
        contentLinearLayout.addView(
            TextView(this).apply {
                text = inputText
                gravity = Gravity.CENTER

                layoutParams = LinearLayout.LayoutParams(
                    /* width = */ LinearLayout.LayoutParams.MATCH_PARENT,
                    /* height = */ LinearLayout.LayoutParams.WRAP_CONTENT
                )
            }
        )
    }

    companion object {
        private const val STATE_KEY = "list"
    }
}
