package com.sisada.bookstore

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import com.sisada.bookstore.databinding.ActivityProfileBinding
import java.io.IOException

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var sharedPref:SharedPreferences
    companion object{
        const val SHARED_PREF = "mysharedprefs"
        const val FIRST_NAME = "firstname"
        const val LAST_NAME = "lastname"
        private const val SELECT_IMAGE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = this.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE) ?: return

        setSupportActionBar(findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar))

        val actionBar : ActionBar? = supportActionBar
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        binding.circleImageView.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_IMAGE)

        }

        binding.save.setOnClickListener{
            with(sharedPref.edit()){
                putString(FIRST_NAME, binding.firstName.text.toString())
                putString(LAST_NAME, binding.lastName.text.toString())
                apply()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == SELECT_IMAGE)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                if(data != null){

                    try {
                        val selectImage: Uri? = data.data
                        val youSelectedImage: Bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(selectImage!!))
                        binding.circleImageView.setImageBitmap(youSelectedImage)
                    }catch (e:IOException){
                        Log.e("ProfileActivity" , "image not loaded")
                    }


                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}