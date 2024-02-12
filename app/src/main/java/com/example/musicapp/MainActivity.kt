package com.example.musicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var myRecyclerView: RecyclerView
    lateinit var myAdapter: MyAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRecyclerView = findViewById(R.id.recyclerView)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIInterface::class.java)

        val retrofitData = retrofitBuilder.getData(query = "eminem")

        retrofitData.enqueue(object : Callback<musicData?> {
            override fun onResponse(
                call: Call<musicData?>,
                response: Response<musicData?>
            ) {
                // if the API call is a success then this is executed
                val dataList = response.body()?.data!!
//                val textView = findViewById<TextView>(R.id.helloTextView)
//                textView.text = dataList.toString()

                myAdapter = MyAdaptor(this@MainActivity, dataList)
                myRecyclerView.adapter = myAdapter
                myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                Log.d("TAG : onResponse", "onResponse:" + response.body())
            }

            override fun onFailure(call: Call<musicData?>, t: Throwable) {
                // if the API call is a failure then this is executed
                Log.d("TAG : onFailure", "onFailure:" + t.message)
            }
        })
    }
}