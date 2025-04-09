package com.example.techtreasure

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.Locale

class UserProfileActivity : AppCompatActivity() {

    private lateinit var profileImage: ImageView
    private lateinit var userName: TextView
    private lateinit var userEmail: TextView
    private lateinit var lastLogin: TextView
    private lateinit var btnEditProfile: Button
    private lateinit var btnLogout: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        // Initialize Views
        profileImage = findViewById(R.id.profileImage)
        userName = findViewById(R.id.userName)
        userEmail = findViewById(R.id.userEmail)
        lastLogin = findViewById(R.id.lastLogin) // New field for last login timestamp
        btnEditProfile = findViewById(R.id.btnEditProfile)
        btnLogout = findViewById(R.id.btnLogout)
        progressBar = findViewById(R.id.progressBar) // New progress bar

        // Load user data from Firebase
        loadUserProfile()

        // Edit Profile
        btnEditProfile.setOnClickListener {
            // Redirect to Edit Profile Activity
            startActivity(Intent(this, EditProfileActivity::class.java))
        }

        // Logout
        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, AppCompatActivity::class.java)) // Redirect to LoginActivity
            finish()
        }
    }

    private fun loadUserProfile() {
        progressBar.visibility = View.VISIBLE // Show progress bar

        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            userName.text = it.displayName ?: "User"
            userEmail.text = it.email ?: "No Email"

            // Set last login timestamp
            val lastSignInTime = it.metadata?.lastSignInTimestamp
            if (lastSignInTime != null) {
                val dateFormat = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
                lastLogin.text = "Last Login: ${dateFormat.format(lastSignInTime)}"
            } else {
                lastLogin.text = "Last Login: Unknown"
            }

            // Load profile image from Firebase Storage
            val storageRef = FirebaseStorage.getInstance().reference
            val profileRef = storageRef.child("profileImages/${it.uid}.jpg")
            profileRef.downloadUrl.addOnSuccessListener { uri ->
                // Use Glide to load the image into the ImageView
                Glide.with(this).load(uri).into(profileImage)
                progressBar.visibility = View.GONE // Hide progress bar
            }.addOnFailureListener {
                progressBar.visibility = View.GONE // Hide progress bar
                Toast.makeText(this, "Failed to load profile image. Setting default image.", Toast.LENGTH_SHORT).show()
                profileImage.setImageResource(R.drawable.ic_profile_placeholder) // Set default image
            }
        } ?: run {
            progressBar.visibility = View.GONE // Hide progress bar
            Toast.makeText(this, "User not logged in!", Toast.LENGTH_SHORT).show()
        }
    }
}