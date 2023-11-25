package com.example.anvanced_android.Coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anvanced_android.CommentApiInterface
import com.example.anvanced_android.ProductAdapterItem
import com.example.anvanced_android.Products
import com.example.anvanced_android.ProductsDataActivity
import com.example.anvanced_android.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Soluction_with_coroutines_Activity : AppCompatActivity() {
    lateinit var conunterText: TextView
    private val TAG="Kotlinfun"
    private val Base_Url="https://fakestoreapi.com/"
    private val API_TAG="PRODUCT RESPONSE"
    lateinit var rvView: RecyclerView
    lateinit var product_adapter:ProductAdapterItem

    val ProductsDataActivity = ProductsDataActivity()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soluction_with_coroutines)
        conunterText=findViewById(R.id.counter2)
        rvView=findViewById(R.id.recyclerViewData)


    }
    fun updateCounter(view: View){
        conunterText.text="${conunterText.text.toString().toInt()+1}"
    }
    fun u_call_timetakingTask2(view: View){

        CoroutineScope(Dispatchers.IO).launch {
            Log.d(TAG,"${Thread.currentThread().name}")
        getProductsData()
        }
        MainScope().launch(Dispatchers.Default) {
            Log.d(TAG,"${Thread.currentThread().name}")
        }


    }


    //Soulution 1:
    private fun getProductsData() {
    val api= Retrofit.Builder()
        .baseUrl(Base_Url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CommentApiInterface::class.java)

    api.getFlipkartData().enqueue(object : Callback<ArrayList<Products>> {
        override fun onResponse(
            call: Call<ArrayList<Products>>,
            response: Response<ArrayList<Products>>
        ) {
            if(response.isSuccessful){
                response.body()?. let {
                    Log.i(TAG,"onResponse1:${response.body()}")
                    UpdateUI(response.body()!!)
                }
            }
        }

        override fun onFailure(call: Call<ArrayList<Products>>, t: Throwable) {
            Log.i(TAG,"onFailure: ${t.message}")
        }


    })

}

    private fun UpdateUI(data:ArrayList<Products>) {

        rvView.layoutManager= LinearLayoutManager(this)

        GlobalScope.launch(Dispatchers.Main) {
            product_adapter = ProductAdapterItem(baseContext, data!!)
            rvView.adapter = product_adapter

            Log.d(TAG,"${Thread.currentThread().name}")
        }


    }
    //Solution2:




}