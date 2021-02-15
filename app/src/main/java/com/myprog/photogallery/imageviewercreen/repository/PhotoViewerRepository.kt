package com.myprog.photogallery.imageviewercreen.repository

import android.content.Context
import com.myprog.photogallery.imageviewercreen.contract.MainContract
import com.myprog.photogallery.imageviewercreen.repository.model.FlickrPhoto
import com.myprog.photogallery.imageviewercreen.repository.model.Photo
import com.myprog.photogallery.imageviewercreen.repository.model.Photos
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val PER_PAGE = 100
private const val API_KEY = "7525b3912ee96a7b91b5c1cfd2f2324e"
private const val BASE_URL = "https://api.flickr.com/services/rest/"
private const val BASE_URL_IMG = "https://live.staticflickr.com/"


class PhotoViewerRepository(presenter: MainContract.Presenter, context: Context?) :
    MainContract.Repository {

    var imgURL: ArrayList<String> = arrayListOf()
    var imgArray: ArrayList<Photo> = arrayListOf()

    var mPresenter = presenter


    private val client = OkHttpClient.Builder()
        .addInterceptor(ChuckInterceptor(context))
        .build()

    private val mRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private var mIAPIFlickr: IAPIFlickr = mRetrofit.create(IAPIFlickr::class.java)

    override fun loadIMG(request: String) {
        var call: Call<FlickrPhoto> = mIAPIFlickr.getPhoto(request, PER_PAGE, API_KEY)
        call.enqueue(object : Callback<FlickrPhoto> {
            override fun onResponse(call: Call<FlickrPhoto>, response: Response<FlickrPhoto>) {
                imgURL.clear()
                val flickrPhoto: FlickrPhoto = response.body()!!
                val photos: Photos = flickrPhoto.photos
                val photo: ArrayList<Photo> = photos.photo
                for (i in 0..photo.size-1) {
                    var url ="$BASE_URL_IMG${photo[i].server}/${photo[i].id}_${photo[i].secret}.jpg"
                    imgURL.add(url)
                    imgArray.add(photo[i])
                }
                mPresenter.updateUI(imgURL, imgArray)
            }

            override fun onFailure(call: Call<FlickrPhoto>, t: Throwable) {
            }
        })
    }
}