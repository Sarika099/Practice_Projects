package com.example.anvanced_android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 class ProductsDataActivity : AppCompatActivity() {
    private val Base_Url="https://fakestoreapi.com/"
    private val TAG="PRODUCT RESPONSE"
    lateinit var rvView: RecyclerView
    lateinit var product_adapter:ProductAdapterItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_data)
        rvView=findViewById(R.id.recyclerViewData)
        rvView.layoutManager= LinearLayoutManager(this)
        rvView.setHasFixedSize(true)
        getProductData()
    }


     private fun getProductData() {
        val api= Retrofit.Builder()
            .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CommentApiInterface::class.java)

        api.getFlipkartData().enqueue(object :Callback<ArrayList<Products>>{
            override fun onResponse(
                call: Call<ArrayList<Products>>,
                response: Response<ArrayList<Products>>
            ) {
                if(response.isSuccessful){
                    response.body()?. let {
                        product_adapter = ProductAdapterItem(baseContext, response.body()!!)
                        rvView.adapter = product_adapter
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<Products>>, t: Throwable) {
            Log.i(TAG,"onFailure: ${t.message}")
            }


        })

    }
}