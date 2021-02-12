package com.myprog.photogallery.imageviewercreen.contract

interface MainContract {
    interface View {
        fun updateAdapter(images: ArrayList<String>)
    }

    interface Presenter {
        fun updateUI(imgURL: ArrayList<String>)
    }

    interface Repository {
        fun loadIMG()
    }
}