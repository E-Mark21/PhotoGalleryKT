package com.myprog.photogallery.imageviewercreen.presenter

import android.content.Context
import androidx.fragment.app.Fragment
import com.myprog.photogallery.imageviewercreen.contract.MainContract
import com.myprog.photogallery.imageviewercreen.repository.PhotoViewerRepository

lateinit var mRepository: MainContract.Repository

class PhotoViewerPresenter(view: MainContract.View, context: Context?) : Fragment(), MainContract.Presenter {

    private var mView = view

    init {
        mRepository = PhotoViewerRepository(this, context)
        mRepository.loadIMG()
    }
    override fun updateUI(imgURL: ArrayList<String>) {
        mView.updateAdapter(imgURL)
    }
}