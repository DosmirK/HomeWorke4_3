package com.example.homeworke4_3

data class PixabayModel(
    val hits: List<ImageModel>
)
data class ImageModel(
    val largeImageURL: String,
    val likes: Int
)
