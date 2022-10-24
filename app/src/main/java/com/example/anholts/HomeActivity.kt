package com.example.anholts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pusher.pushnotifications.PushNotifications;


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        PushNotifications.start(getApplicationContext(), "26c8ed71-3f83-4e24-be6e-b0267bcfc106");
        PushNotifications.addDeviceInterest("hello");

    }
}