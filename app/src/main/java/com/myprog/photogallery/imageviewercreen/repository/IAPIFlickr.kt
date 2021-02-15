package com.myprog.photogallery.imageviewercreen.repository

import com.myprog.photogallery.imageviewercreen.repository.model.FlickrPhoto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IAPIFlickr {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1")
    fun getPhoto(@Query("tags") tags: String, @Query("per_page") per_page: Int, @Query("api_key") api_key: String): Call<FlickrPhoto>
    @GET("?method=flickr.photos.getRecent&format=json&nojsoncallback=1")
    fun getLastPhoto(@Query("per_page") per_page: Int, @Query("api_key") api_key: String): Call<FlickrPhoto>
}