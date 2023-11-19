package com.example.anvanced_android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductsDataActivity : AppCompatActivity() {
    private val Base_Url="https://fakestoreapi.com/"
    private val TAG="PRODUCT RESPONSE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_data)
        getProductData()
    }

    private fun getProductData() {
        val api= Retrofit.Builder()
            .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CommentApiInterface::class.java)
        api.getFlipkartData().enqueue(object :Callback<List<Products>>{
            override fun onResponse(
                call: Call<List<Products>>,
                response: Response<List<Products>>
            ) {
                if(response.isSuccessful){
                    response.body()?. let {
                        for(i in it){
                            Log.i(TAG,"onResponse: ${i}")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Products>>, t: Throwable) {
            Log.i(TAG,"onFailure: ${t.message}")
            }


        })

    }
}