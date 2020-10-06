package com.example.appml._view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appml.MainActivity
import com.example.appml.R

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)




    }

    private fun launchLogin() {

        val nIntent = Intent(this, MainActivity::class.java)
        intent.action?.let { action ->
            nIntent.action = action
            intent.extras?.let { extras ->
                nIntent.putExtras(extras)
            }
        }
        startActivity(nIntent)


    }
}