package com.example.anholts.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.anholts.ApiInterface
import com.example.anholts.R
import com.example.anholts.dataModelItem
import com.pusher.pushnotifications.PushNotifications;
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.android.synthetic.main.activity_home.*


const val BASE_URL = "https://order.anholts.nl/"

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        PushNotifications.start(getApplicationContext(), "26c8ed71-3f83-4e24-be6e-b0267bcfc106");
        PushNotifications.addDeviceInterest("hello");

        getdataModel()

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

                val myStringBuilder = StringBuilder()

                for (dataModel in responseBody) {
                    myStringBuilder.append("Locatie: ", dataModel.button_location)
                    myStringBuilder.append("\n")
                }


                txtId.text = myStringBuilder

            }

            override fun onFailure(call: Call<List<dataModelItem>?>, t: Throwable) {
                Log.d("HomeActivity", "onFailure:" +t.message)
            }
        })
    }
}