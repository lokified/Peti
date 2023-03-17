package com.loki.peti.util.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun String.toBitmap(): Bitmap {
    val imageData = this.split(",").last()
    val imageBytes = android.util.Base64.decode(imageData, android.util.Base64.DEFAULT)
    val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

    return bitmap
}