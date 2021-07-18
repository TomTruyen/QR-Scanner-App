package com.tomtruyen.qrscanner

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds


class ResultActivity : AppCompatActivity() {
    private lateinit var mAdView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        supportActionBar?.title = "Result"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        MobileAds.initialize(this ) { }
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        val url = intent.getStringExtra("URL")

        findViewById<TextView>(R.id.urlText).text = url

        findViewById<Button>(R.id.browserButton).setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            } catch (e : Exception) {
                Toast.makeText(this, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}