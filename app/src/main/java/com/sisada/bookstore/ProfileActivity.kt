package com.sisada.bookstore

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import com.sisada.bookstore.databinding.ActivityMainBinding
import com.sisada.bookstore.databinding.ActivityProfileBinding
import java.io.IOException

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    companion object{
        private const val SELECT_IMAGE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

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