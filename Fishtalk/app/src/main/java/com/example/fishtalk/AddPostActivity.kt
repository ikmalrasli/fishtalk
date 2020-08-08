package com.example.fishtalk


import android.content.DialogInterface
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FieldValue
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
            val txtField = findViewById<EditText>(R.id.txtfield).text.toString()
            if(txtField != ""){
                tagDialog()
            }else{
                val toast = Toast.makeText(applicationContext, "Text field is empty, please type something..", Toast.LENGTH_SHORT)
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

    fun tagDialog(){
        val builder = AlertDialog.Builder(this)
        val taglist = arrayOf("Tag A", "Tag B", "Tag C")
        var tag = ""
        builder.setTitle("Pick a tag")
            .setItems(taglist,
                DialogInterface.OnClickListener { dialog, which ->
                    // The 'which' argument contains the index position
                    // of the selected item
                    tag= taglist.get(which)
                    postFx(tag)
                })

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(true)
        alertDialog.show()
    }

    fun postFx(tag : String){
        val txtField = findViewById<EditText>(R.id.txtfield)
        val content = txtField.text.toString()
        val timestamp = FieldValue.serverTimestamp()
        val post = hashMapOf(
            "content" to content,
            "timestamp" to timestamp,
            "userID" to "user1idhere",
            "tag" to tag
        )
        db.collection("posts")
            //.document("tagA").collection("posts")    <--- for nested data
            .add(post)
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                val toast = Toast.makeText(applicationContext, "Posted successfully! \n content: " + content + "\n tag: " + tag, Toast.LENGTH_SHORT)
                toast.show()
                this.finish()
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
                val toast = Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_SHORT)
                toast.show()
            }
    }
}