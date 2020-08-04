package com.example.fishtalk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Button
import android.widget.Toast

class ReelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reel)
    }

    fun fxBack(view: View){
        onBackPressed()
    }

    fun fxReel(view: View){
        val toast = Toast.makeText(applicationContext, "Reeling... Please wait...", Toast.LENGTH_SHORT)
        toast.show()
    }
}