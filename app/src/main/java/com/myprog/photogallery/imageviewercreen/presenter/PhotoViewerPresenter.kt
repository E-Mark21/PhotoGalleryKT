package com.myprog.photogallery.imageviewercreen.presenter

import android.content.Context
import androidx.fragment.app.Fragment
import com.myprog.photogallery.imageviewercreen.contract.MainContract
import com.myprog.photogallery.imageviewercreen.repository.PhotoViewerRepository
import com.myprog.photogallery.imageviewercreen.repository.model.Photo

lateinit var mRepository: MainContract.Repository

class PhotoViewerPresenter(view: MainContract.View, context: Context?) : Fragment(), MainContract.Presenter {

    private var mView = view

    init {
        mRepository = PhotoViewerRepository(this, context)
        mRepository.loadLastIMG()
    }
    override fun updateUI(imgURL: ArrayList<String>, imgArray: ArrayList<Photo>) {
        mView.updateAdapter(imgURL, imgArray)
    }

    override fun searchIMG(request: String) {
        mRepository.loadIMG(request)
    }

    override fun loadLastIMG() {
        mRepository.loadLastIMG()
    }
}