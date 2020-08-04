package com.example.fishtalk

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.Button

class FishrodActivity : Activity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fishrod)
    }

    fun fxView(view: View){
        val btnView = findViewById<Button>(R.id.btnFind)
        val intent = Intent(this, TimelineActivity::class.java)
        startActivity(intent)
    }

    fun fxSettings(view: View){
        val btnSettings = findViewById<Button>(R.id.btnSettings)
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    fun fxShop(view: View){
        val btnShop = findViewById<Button>(R.id.btnShop)
        val intent = Intent(this, ShopActivity::class.java)
        startActivity(intent)
    }

    fun fxFind(view: View){
        val btnFind = findViewById<Button>(R.id.btnFind)
        val intent = Intent(this, ReelActivity::class.java)
        startActivity(intent)
    }

    fun fxAdd(view: View){
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val prevPage = "FishrodActivity"
        val intent = Intent(this, AddPostActivity::class.java)
        startActivity(intent)
    }



}