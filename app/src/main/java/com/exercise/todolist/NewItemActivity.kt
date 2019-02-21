package com.exercise.todolist

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewItemActivity : AppCompatActivity() {

    private lateinit var editTitle: EditText
    private lateinit var editDescription: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_item)
        editTitle = findViewById(R.id.editTitle)
        editDescription = findViewById(R.id.editTitle)

        val saveButton = findViewById<FloatingActionButton>(R.id.saveButton)
        saveButton.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTitle.text) || TextUtils.isEmpty(editDescription.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val title = editTitle.text.toString()
                replyIntent.putExtra(TITLE, title)
                val description = editDescription.text.toString()
                replyIntent.putExtra(DESCRIPTION, description)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val TITLE = "com.exercise.android.TITLE"
        const val DESCRIPTION = "com.exercise.android.DESCRIPTION"
    }
}
