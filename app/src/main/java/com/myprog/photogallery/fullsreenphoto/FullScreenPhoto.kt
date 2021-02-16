package com.myprog.photogallery.fullsreenphoto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.myprog.photogallery.R
import com.myprog.photogallery.imageviewercreen.repository.model.Photo
import com.squareup.picasso.Picasso

const val EXTRA_URL = "url_image"
const val EXTRA_DATA = "photo_data"
lateinit var mImgURL: String
lateinit var mImgData: Photo
lateinit var mImageView: ImageView
lateinit var mIDView: TextView
lateinit var mOwner: TextView
lateinit var mSecret: TextView
lateinit var mServer: TextView
lateinit var mTitle: TextView

class FullScreenPhoto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_photo)
        mImageView = findViewById(R.id.imageView)
        mIDView = findViewById(R.id.ID_view)
        mOwner = findViewById(R.id.owner_View)
        mSecret = findViewById(R.id.secret_View)
        mServer = findViewById(R.id.server_View)
        mTitle = findViewById(R.id.title_View)
        if (intent.extras !== null) {
            mImgURL = intent.extras?.get(EXTRA_URL) as String
            mImgData = intent.extras?.get(EXTRA_DATA) as Photo
            Picasso.with(applicationContext).load(mImgURL)
                .into(mImageView)

            mIDView.setText(mImgData.id)
            mOwner.setText(mImgData.owner)
            mSecret.setText(mImgData.secret)
            mServer.setText(mImgData.server.toString())
            mTitle.setText(mImgData.title)
        }

    }
}