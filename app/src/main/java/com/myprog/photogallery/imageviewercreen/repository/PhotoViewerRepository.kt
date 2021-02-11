package com.myprog.photogallery.imageviewercreen.repository

import android.content.Context
import com.myprog.photogallery.imageviewercreen.contract.MainContract
import com.myprog.photogallery.imageviewercreen.repository.model.FlickrPhoto
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val PER_PAGE = 20
private const val API_KEY = "7525b3912ee96a7b91b5c1cfd2f2324e"
private const val BASE_URL = "https://api.flickr.com/services/rest/"

class PhotoViewerRepository(presenter: MainContract.Presenter, context: Context?) : MainContract.Repository {

    var mPresenter = presenter

    var client = OkHttpClient.Builder()
        .addInterceptor(ChuckInterceptor(context))
        .build()

    private val mRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private var mIAPIFlickr: IAPIFlickr = mRetrofit.create(IAPIFlickr::class.java)

    override fun loadIMG() {
        var call: Call<FlickrPhoto> = mIAPIFlickr.getPhoto("cat", PER_PAGE, API_KEY)
        call.enqueue(object : Callback<FlickrPhoto> {
            override fun onResponse(call: Call<FlickrPhoto>, response: Response<FlickrPhoto>) {
                //var flickrPhoto: FlickrPhoto = response.body()!!
                // var photos: Photos = flickrPhoto.photos
                // var photo: ArrayList<Photo> = photos.photo
                /*for (i in 0..photo.size){

                }*/


            }

            override fun onFailure(call: Call<FlickrPhoto>, t: Throwable) {
                var fg = 5

            }
        })
    }
}