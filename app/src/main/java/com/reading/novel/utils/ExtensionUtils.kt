package com.reading.novel.utils

import android.content.Context
import android.content.res.Resources
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import java.io.File
import java.io.InputStream


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}

fun View.show() {
//    alpha = 0f
    visibility = View.VISIBLE

    // Animate the content view to 100% opacity and clear any animation
    // listener set on the view.
//    animate()
//        .alpha(1f)
//        .setDuration(500)
//        .setListener(null)
}

fun View.hide() {
//    animate()
//        .alpha(0f)
//        .setDuration(500)
//        .setListener(object : AnimatorListenerAdapter() {
//            override fun onAnimationEnd(animation: Animator) {
    visibility = View.GONE
//            }
//        })

}

fun View.visibleHide() {
    visibility = View.INVISIBLE
}

fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also {
        it.setAction("Ok") { _ ->
            it.dismiss()
        }
    }.show()
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun String.capitalizeWords(): String =
    split(" ").joinToString(" ") { it.toLowerCase().capitalize() }

fun String.soundName(): String = replace(" ", "_").toLowerCase()

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Double.formatDoubleTo1Decimal(): Double {
    return String.format("%.1f", this).toDouble()
}


fun View.slideUp(duration: Int = 1000) {
    visibility = View.VISIBLE
    val animate = TranslateAnimation(0f, 0f, this.height.toFloat(), 0f)
    animate.duration = duration.toLong()
    animate.fillAfter = true
    this.startAnimation(animate)
}

fun View.slideDown(duration: Int = 500) {
    visibility = View.VISIBLE
    val animate = TranslateAnimation(0f, 0f, 0f, this.height.toFloat())
    animate.duration = duration.toLong()
    animate.fillAfter = true
    this.startAnimation(animate)
}

fun InputStream.saveToFile(filePath: String, fileName: String): File = use { input ->
    val file = File(filePath, fileName)
    file.outputStream().use { output ->
        input.copyTo(output)
    }
    input.close()
    file
}

fun getMonthShortNameFromNumber(number: String): String {
    return when (number) {
        "01" -> "Jan"
        "02" -> "Feb"
        "03" -> "Mar"
        "04" -> "Apr"
        "05" -> "May"
        "06" -> "Jun"
        "07" -> "Jul"
        "08" -> "Aug"
        "09" -> "Sep"
        "10" -> "Oct"
        "11" -> "Nov"
        "12" -> "Dec"
        else -> ""
    }
}

fun isJsonObject(jsonString: String): Boolean {
    return try {
        val obj = JsonParser.parseString(jsonString)
        obj.isJsonObject
    } catch (e: JsonSyntaxException) {
        false
    }

}