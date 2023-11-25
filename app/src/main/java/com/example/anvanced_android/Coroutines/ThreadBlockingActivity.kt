package com.example.anvanced_android.Coroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.anvanced_android.R

class ThreadBlockingActivity : AppCompatActivity() {
    lateinit var conunterText:TextView
    private val TAG="Kotlinfun"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_blocking)
        conunterText=findViewById(R.id.counter)
        //code to check the current trhread
        Log.d(TAG,"${Thread.currentThread().name}")

    }

    fun updateCounter(view: View){
        conunterText.text="${conunterText.text.toString().toInt()+1}"
    }

    fun u_call_timetakingTask(view:View){
        //check how its reacting on app it blocks counter when ur using the main threads
        timeTakingTask()
    }

    private fun timeTakingTask() {
        for(i in 1..1000000000L){}
    }
    fun solution_with_threads(view:View){
        startActivity(Intent(this, Solution1_with_thread::class.java))
    }

    fun solution_with_corotines(view:View){
        startActivity(Intent(this, Soluction_with_coroutines_Activity::class.java))
    }

}