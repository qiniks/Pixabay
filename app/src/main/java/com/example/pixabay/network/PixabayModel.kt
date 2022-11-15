package com.example.pixabay.network

data class PixabayModel(
    val hits: List<ImageModel>,
)

data class ImageModel(
    val previewURL: String,
)
