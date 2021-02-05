package com.myprog.photogallery.imageviewercreen.repository.model

import com.google.gson.annotations.SerializedName

class FlickrPhoto {
    @SerializedName("photos")
    lateinit var photos: Photos
    @SerializedName("start")
    lateinit var start: String

}