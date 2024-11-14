package com.example.qrcodescanner_2025
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.qrcodescanner_2025.databinding.ActivityCustomScannerBinding
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView

class CustomScannerActivity : AppCompatActivity() {
    private lateinit var capture: CaptureManager
    private lateinit var binding: ActivityCustomScannerBinding
    private lateinit var barcodeScannerView: DecoratedBarcodeView
    private var isFlashOn = false  // Track flashlight state
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        barcodeScannerView = binding.zxingBarcodeScanner

        binding.flashButton.setOnClickListener {
            toggleFlashlight()  // Toggle flashlight on button click
        }

        initializeQrScanner(savedInstanceState)

    }

    private fun initializeQrScanner(savedInstanceState: Bundle?) {
        capture = CaptureManager(this, barcodeScannerView)
        capture.initializeFromIntent(intent, savedInstanceState)
        capture.setShowMissingCameraPermissionDialog(false)
        capture.decode()

        // Configure scanner settings (optional)
        val cameraSettings = com.journeyapps.barcodescanner.camera.CameraSettings()
        cameraSettings.isAutoFocusEnabled = true
        barcodeScannerView.cameraSettings = cameraSettings
    }




    private fun toggleFlashlight() {
        if (isFlashOn) {
            barcodeScannerView.setTorchOff()  // Turn flashlight off
            binding.flashButton.setImageResource(R.drawable.baseline_flashlight_off_24)  // Set icon to "off"
        } else {
            binding.flashButton.setImageResource(R.drawable.baseline_flashlight_on_24)  // Set icon to "on"
            barcodeScannerView.setTorchOn()  // Turn flashlight on
        }
        isFlashOn = !isFlashOn  // Toggle the flashlight state
    }

    override fun onResume() {
        super.onResume()
        capture.onResume()
    }

    override fun onPause() {
        super.onPause()
        capture.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        capture.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        capture.onSaveInstanceState(outState)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event)
    }
}
