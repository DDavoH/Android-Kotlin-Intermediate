package com.davoh.shared_preferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        edit_text.setText(sharedPref.getString("stringKey", "default"))


        save_button.setOnClickListener {
            with (sharedPref.edit()) {
                putString("stringKey", edit_text.text.toString())
                apply()
            }
            Toast.makeText(this, "String: ${edit_text.text} saved", Toast.LENGTH_SHORT).show()
        }

    }
}