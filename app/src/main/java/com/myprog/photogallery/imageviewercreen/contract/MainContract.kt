package com.myprog.photogallery.imageviewercreen.contract

import com.myprog.photogallery.imageviewercreen.repository.model.Photo

interface MainContract {
    interface View {
        fun updateAdapter(images: ArrayList<String>, imgArray: ArrayList<Photo>)
    }

    interface Presenter {
        fun updateUI(imgURL: ArrayList<String>, imgArray: ArrayList<Photo>)
        fun searchIMG(request: String)
    }

    interface Repository {
        fun loadIMG(request: String)
        fun loadLastIMG()
    }
}