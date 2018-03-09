package com.ysn.moncy.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.ysn.moncy.R
import com.ysn.moncy.view.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val handler = Handler()
        handler.postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, 1000 * 3)
    }
}
