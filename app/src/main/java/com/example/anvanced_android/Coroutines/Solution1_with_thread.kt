package com.example.anvanced_android.Coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.anvanced_android.R
import kotlin.concurrent.thread

class Solution1_with_thread : AppCompatActivity() {
    lateinit var conunterText: TextView
    private val TAG="Kotlinfun"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solution1_with_thread)
        conunterText=findViewById(R.id.counter1)

    }

    fun updateCounter(view: View){
        conunterText.text="${conunterText.text.toString().toInt()+1}"
    }

    private fun timeTakingTask(){
        for(i in 1..1000000000L){}
    }

    fun u_call_timetakingTask(view: View){
        //check how its reacting on app it blocks counter when ur using the main threads
        thread(start = true) {
            timeTakingTask()
        }
    }
}