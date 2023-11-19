package com.example.anvanced_android

import retrofit2.Call
import retrofit2.http.GET

interface CommentApiInterface {
    @GET("comments")
    fun getCommentData(): Call<List<Comment>>

    //Flipkart API
    @GET("products")
    fun getFlipkartData(): Call<List<Products>>
}