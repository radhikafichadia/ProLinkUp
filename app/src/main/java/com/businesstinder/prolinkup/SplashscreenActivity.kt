package com.businesstinder.prolinkup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth

class SplashscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val user = FirebaseAuth.getInstance().currentUser

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // HERE WE ARE TAKING THE REFERENCE OF OUR IMAGE
        // SO THAT WE CAN PERFORM ANIMATION USING THAT IMAGE
        val backgroundImage: ImageView = findViewById(R.id.splashscreen)
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_splash)
        backgroundImage.startAnimation(slideAnimation)

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
    Handler(Looper.getMainLooper()).postDelayed({

        if(user == null){
            startActivity(Intent(this, MainSliderActivity::class.java))
        }
        else {
            startActivity(Intent(this, MainActivity::class.java))
        }
        finish()
        }, 3000) // 3000 is the delayed time in milliseconds.
    }

}