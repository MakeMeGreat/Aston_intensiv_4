package com.example.aston_intensiv_4.data

import java.io.Serializable

data class User(
    val id: Int,
    var imageURI: String = "https://placebeard.it/640x360",
    var name: String,
    var lastName: String,
    var number: String
) : Serializable