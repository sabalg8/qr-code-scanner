package com.example.qrcodescanner_2025
import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.util.AttributeSet
import com.journeyapps.barcodescanner.BarcodeView
import com.journeyapps.barcodescanner.Size

class TopRectBarcodeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BarcodeView(context, attrs, defStyleAttr) {

    // Override getFramingRectSize method
    override fun getFramingRectSize(): Size {
        // Return the frame size in the correct format
        return Size(resources.displayMetrics.widthPixels, dpToPx(212)) // example size
    }



    override fun calculateFramingRect(container: Rect?, surface: Rect?): Rect {
        val screenWidth = Resources.getSystem().displayMetrics.widthPixels
        val screenHeight = Resources.getSystem().displayMetrics.heightPixels

        val frameWidth = dpToPx(240)
        val frameHeight = dpToPx(180)

        // Calculate left and top to center the frame
        val left = (screenWidth - frameWidth) / 2
        val top = (screenHeight - frameHeight) / 2

        return Rect(left, top, left + frameWidth, top + frameHeight)
    }

    // Helper function to convert dp to pixels
    private fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}
