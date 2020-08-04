package com.example.fishtalk

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener


class TimelineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        val viewPager = findViewById<ViewPager>(R.id.viewPager);
        val adapter = PageAdapter(supportFragmentManager, tabLayout.tabCount)
        tabLayout.addTab(tabLayout.newTab().setText("All"))

        val TAGS = listOf("TAG A", "TAG B", "TAG C", "TAG D")
        for (x in TAGS){tabLayout.addTab(tabLayout.newTab().setText(x))}
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
                val whichtab = tab.position + 1
                val toast = Toast.makeText(applicationContext, "TAB " + whichtab.toString() + " selected", Toast.LENGTH_SHORT)
                toast.show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_timeline, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.btnSettings -> {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            true
        }

        android.R.id.home->{
            onBackPressed()
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    //BUTTON ONCLICK FUNCTION
    fun fxAdd(view: View){
        val btnFind = findViewById<Button>(R.id.btnAdd)
        val intent = Intent(this, AddPostActivity::class.java)
        startActivity(intent)
    }
}