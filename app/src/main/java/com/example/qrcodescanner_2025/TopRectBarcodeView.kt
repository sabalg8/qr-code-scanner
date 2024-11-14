package com.example.qrcodescanner_2025
import android.content.Context
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

    // Function to convert dp to px
    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

    // Override calculateFramingRect method
    override fun calculateFramingRect(container: Rect?, surface: Rect?): Rect {
        val intersection = Rect(container)
        intersection.left = dpToPx(26)
        intersection.top = dpToPx(198)
        intersection.right = framingRectSize.width - dpToPx(26)
        intersection.bottom = framingRectSize.height + dpToPx(190)
        return intersection
    }
}
