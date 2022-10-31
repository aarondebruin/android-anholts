package com.example.anholts.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.anholts.R
import java.util.Timer
import kotlin.concurrent.timerTask


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        redirectToLogin()

    }

    fun redirectToLogin(){
        if(!isDestroyed){
            //Gaat naar deze activity
            val intent = Intent(this, HomeActivity::class.java)

            val tmtask = timerTask {
                if(!isDestroyed){
                    startActivity(intent)
                    finish()
                }
            }
            //initialize timer
        val timer = Timer()
            // set timer interval of 2000
            timer.schedule(tmtask, 3000)
        }
    }

}