package com.myprog.photogallery.fullsreenphoto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myprog.photogallery.R

const val EXTRA_URL = "url_image"
const val EXTRA_DATA = "photo_data"

class FullScreenPhoto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_photo)
    }
}