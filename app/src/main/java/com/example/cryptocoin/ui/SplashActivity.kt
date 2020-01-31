package com.example.cryptocoin.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptocoin.R

class SplashActivity : AppCompatActivity() {

    val SPLASH_TIME_INTERVEL: Int? = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        runSplashThread()
    }


    private fun runSplashThread() {
        val background = object : Thread() {
            override fun run() = try {
                sleep(SPLASH_TIME_INTERVEL?.toLong()!!)
                var intent = Intent(this@SplashActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        background.start()
    }

    override fun onPause() {

        super.onPause()
    }
}
