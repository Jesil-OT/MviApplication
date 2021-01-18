package com.jesil.projectmvi.mviapplication.model


// domain model data class

data class Blog(
    var id : Int,
    var title : String,
    var body : String,
    var image : String,
    var category : String
)