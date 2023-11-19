package com.example.anvanced_android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.anvanced_android.ui.theme.Anvanced_AndroidTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    private val Base_Url="https://jsonplaceholder.typicode.com/"
    private val TAG="COMMENT RESPONSE"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComments()
    }

    private fun getComments() {
        val api=Retrofit.Builder()
            .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CommentApiInterface::class.java)

        api.getCommentData().enqueue(object :Callback<List<Comment>>{

            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>
            ){
                if(response.isSuccessful){
                    response.body()?.let {
                        for(comment in it){
                            Log.i(TAG,"onResponse:${comment.body}")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable
            ){
                Log.i(TAG,"onFailure:${t.message}")

            }

        })

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Anvanced_AndroidTheme {
        Greeting("Android")
    }
}