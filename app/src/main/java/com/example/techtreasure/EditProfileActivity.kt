package com.example.techtreasure

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class EditProfileActivity : AppCompatActivity() {

    private lateinit var editProfileImage: ImageView
    private lateinit var editUserName: EditText
    private lateinit var editUserEmail: EditText
    private lateinit var btnSaveProfile: Button
    private lateinit var btnChangeProfileImage: Button

    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        // Initialize Views
        editProfileImage = findViewById(R.id.editProfileImage)
        editUserName = findViewById(R.id.editUserName)
        editUserEmail = findViewById(R.id.editUserEmail)
        btnSaveProfile = findViewById(R.id.btnSaveProfile)
        btnChangeProfileImage = findViewById(R.id.btnChangeProfileImage)

        // Load existing user data
        loadUserProfile()

        // Change profile image
        btnChangeProfileImage.setOnClickListener {
            pickImageFromGallery()
        }

        // Save profile changes
        btnSaveProfile.setOnClickListener {
            saveProfileChanges()
        }
    }

    private fun loadUserProfile() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            editUserName.setText(it.displayName ?: "")
            editUserEmail.setText(it.email ?: "")

            // Load profile image from Firebase Storage
            val storageRef = FirebaseStorage.getInstance().reference
            val profileRef = storageRef.child("profileImages/${it.uid}.jpg")
            profileRef.downloadUrl.addOnSuccessListener { uri ->
                // Use Glide or Picasso to load the image
                // Glide.with(this).load(uri).into(editProfileImage)
            }.addOnFailureListener {
                // Handle errors
            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            selectedImageUri = data?.data
            editProfileImage.setImageURI(selectedImageUri)
        }
    }

    private fun saveProfileChanges() {
        val user = FirebaseAuth.getInstance().currentUser
        val newName = editUserName.text.toString().trim()
        val newEmail = editUserEmail.text.toString().trim()

        if (newName.isEmpty() || newEmail.isEmpty()) {
            Toast.makeText(this, "Name and Email cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        // Update user profile
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(newName)
            .build()

        user?.updateProfile(profileUpdates)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                updateProfileImage(user.uid)
                Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to update profile", Toast.LENGTH_SHORT).show()
            }
        }

        // Update user email
        user?.updateEmail(newEmail)?.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Toast.makeText(this, "Failed to update email", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateProfileImage(userId: String) {
        selectedImageUri?.let { uri ->
            val storageRef = FirebaseStorage.getInstance().reference
            val profileImageRef = storageRef.child("profileImages/$userId.jpg")

            profileImageRef.putFile(uri).addOnSuccessListener {
                Toast.makeText(this, "Profile image updated", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to upload profile image", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }
}