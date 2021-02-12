package com.myprog.photogallery.imageviewercreen.repository.model

import com.google.gson.annotations.SerializedName

class Photo {
    @SerializedName("id")
    lateinit var id: String
    @SerializedName("owner")
    lateinit var owner: String
    @SerializedName("secret")
    lateinit var secret: String
    @SerializedName("server")
    var server: Int = 0
    @SerializedName("farm")
    var farm: Int = 0
    @SerializedName("title")
    lateinit var title: String
    @SerializedName("ispublic")
    var isPublic: Int = 0
    @SerializedName("isfriend")
    var isFriend: Int = 0
    @SerializedName("isfamily")
    var isFamily: Int = 0
}