package com.jesil.projectmvi.mviapplication.retrofit

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

// Entity data class
@Parcelize
data class BlogNetworkEntity(
    @SerializedName("pk")
    @Expose
    var id : Int,

    @SerializedName("title")
    @Expose
    var title : String,

    @SerializedName("body")
    @Expose
    var body : String,

    @SerializedName("image")
    @Expose
    var image : String,

    @SerializedName("category")
    @Expose
    var category : String
): Parcelable {

}