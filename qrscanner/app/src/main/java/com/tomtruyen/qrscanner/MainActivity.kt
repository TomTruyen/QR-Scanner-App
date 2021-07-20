package com.tomtruyen.qrscanner

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.startScannerButton).setOnClickListener {
            startScanner()
        }

        startScanner()
    }

    private fun startScanner() {
        val scanner = IntentIntegrator(this)
        scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        scanner.setOrientationLocked(true)
        scanner.setBeepEnabled(false)
        scanner.setPrompt("Place the QR code inside the rectangle")
        scanner.captureActivity = QrScanAcitivity::class.java
        scanner.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Failed to scan QR code", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("URL", result.contents)
                    startActivity(intent)

                    Toast.makeText(this, "QR code scanned successfully", Toast.LENGTH_SHORT).show()
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
}