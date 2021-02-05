package com.myprog.photogallery.imageviewercreen.repository

import retrofit2.http.GET
import retrofit2.http.Query

interface IAPIFlickr {
    @GET("?method=flickr.photos.search")
    fun getPhoto(@Query("tags") tags: String, 0)
}