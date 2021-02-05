package com.myprog.photogallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myprog.photogallery.imageviewercreen.view.PhotoViewerScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.activity_main)
        if (currentFragment == null) {
            val fragment = PhotoViewerScreen.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.activity_main, fragment)
                .commit()
        }
    }
}