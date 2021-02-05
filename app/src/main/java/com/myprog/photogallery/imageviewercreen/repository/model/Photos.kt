package com.myprog.photogallery.imageviewercreen.repository.model

import com.google.gson.annotations.SerializedName

class Photos {

    @SerializedName("page")
    var page: Int = 0
    @SerializedName("pages")
    val pages: Int = 0
    @SerializedName("perpage")
    val perpage: Int = 0
    @SerializedName("total")
    val total: Int = 0
    @SerializedName("photo")
    lateinit var photo: ArrayList<Photo>

}