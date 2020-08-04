package com.example.fishtalk


import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

val db = Firebase.firestore
class AddPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_add_post, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.btnPost -> {
            val post = hashMapOf(
                "content" to "example of content",
                "timestamp" to "038-01-19 03:14:07",
                "userID" to "user1idhere",
                "tag" to "Tag A"
            )
            db.collection("posts")
                //.document("tagA").collection("posts")    <--- for nested data
                .add(post)
                .addOnSuccessListener { documentReference ->
                    Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                    val toast = Toast.makeText(applicationContext, "Posted successfully!", Toast.LENGTH_SHORT)
                    toast.show()
                }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error adding document", e)
                    val toast = Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_SHORT)
                    toast.show()
                }


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
}