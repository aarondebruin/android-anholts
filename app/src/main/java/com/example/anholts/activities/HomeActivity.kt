package com.example.anholts.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anholts.ApiInterface
import com.example.anholts.MyAdapter
import com.example.anholts.R
import com.example.anholts.dataModelItem
import com.pusher.pushnotifications.PushNotifications;
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.Executors


const val BASE_URL = "https://order.anholts.nl/"

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var myAdapter: MyAdapter
        lateinit var linearLayoutManager: LinearLayoutManager

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        PushNotifications.start(getApplicationContext(), "26c8ed71-3f83-4e24-be6e-b0267bcfc106");
        PushNotifications.addDeviceInterest("hello");

        recyclerview_buttons.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerview_buttons.layoutManager = linearLayoutManager

        getdataModel()

        loop()

    }



    private fun loop() {
        CoroutineScope(IO).launch {
            delay(1000)
            CoroutineScope(Main).launch {
                getdataModel()
                loop()
            }
        }
    }




    private fun getdataModel() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<dataModelItem>?> {
            override fun onResponse(
                call: Call<List<dataModelItem>?>,
                response: Response<List<dataModelItem>?>
            ) {
                val responseBody = response.body()!!

                var myAdapter = MyAdapter(baseContext, responseBody)
                myAdapter.notifyDataSetChanged()
                recyclerview_buttons.adapter = myAdapter


            }

            override fun onFailure(call: Call<List<dataModelItem>?>, t: Throwable) {
                Log.d("HomeActivity", "onFailure:" +t.message)
            }
        })
    }



}

