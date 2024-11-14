package com.example.qrcodescanner_2025
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.qrcodescanner_2025.databinding.ActivityMainBinding
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


            launchScanner()

    }

    private fun launchScanner() {
        val options = IntentIntegrator(this)
        options.setOrientationLocked(false)
        options.setCaptureActivity(CustomScannerActivity::class.java)
        options.setBeepEnabled(false)
        options.setBarcodeImageEnabled(true)
        options.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        options.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result.contents != null) {
            Toast.makeText(this, "Scan Result: ${result.contents}", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Scan Cancelled", Toast.LENGTH_SHORT).show()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
