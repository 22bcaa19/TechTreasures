package com.example.techtreasure
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the ImageViews by their IDs
        val imageButton1: View = findViewById(R.id.imageButton2)
        val imageButton2: View = findViewById(R.id.imageButton1)

        // Set click listeners for the ImageViews
        imageButton1.setOnClickListener {
            // Handle redirection for button 1
            startActivity(Intent(this, intelbuild::class.java))
        }

        imageButton2.setOnClickListener {
            // Handle redirection for button 2
            startActivity(Intent(this, amdbuild::class.java))
        }
        val btnUserProfile: View = findViewById(R.id.btnUserProfile)
        btnUserProfile.setOnClickListener {
            startActivity(Intent(this, UserProfileActivity::class.java))
        }
    }
}
