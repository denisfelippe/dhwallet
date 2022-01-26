package com.digitalhouse.dhwallet.util.extension

import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import android.view.View
import java.lang.Exception

fun View.printScreen(): Bitmap? {
    var screen: Bitmap? = null

    try {
        screen = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(screen)
        draw(canvas)
    } catch (e: Exception) {
        Log.e("IMAGE_ERROR", e.message.toString())
    }

    return screen
}
