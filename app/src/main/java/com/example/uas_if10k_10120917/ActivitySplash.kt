package com.example.uas_if10k_10120917

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ActivitySplash : AppCompatActivity() {

    private val splash = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        android.os.Handler().postDelayed({
            val intent = Intent(this@ActivitySplash, WalkTroughMainActivity::class.java)
            startActivity(intent)
            finish()
        }, splash.toLong())
    }
}