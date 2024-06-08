package com.reading.novel.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import java.text.DecimalFormat


object AppUtil {

    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
                    else -> false
                }
            }
        }
        return result
    }

    fun hideKeyboardForce(activity: Activity) {
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        val imm =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showAlert(context: Context, title: String, msg: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setCancelable(false)
        builder.setMessage(msg)
        builder.setPositiveButton("Ok") { _, _ ->
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()


    }

    fun showAlert(
        context: Context,
        title: String,
        msg: String,
        posBtnText: String,
        negBtnText: String,
        isCancelable: Boolean = true,
        listener: View.OnClickListener
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setPositiveButton(posBtnText) { dialog, _ ->
            listener.onClick(null)
            dialog.dismiss()
        }
        builder.setNegativeButton(negBtnText) { dialog, _ ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.setCancelable(isCancelable)
        dialog.show()
    }

    fun showAlert(
        context: Context,
        title: String,
        msg: String,
        posBtnText: String,
        isCancelable: Boolean = true,
        listener: View.OnClickListener
    ): AlertDialog? {
        try {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(msg)
            builder.setPositiveButton(posBtnText) { dialog, _ ->
                listener.onClick(null)
                dialog.dismiss()
            }
            val dialog: AlertDialog = builder.create()
            dialog.setCancelable(isCancelable)
            dialog.show()

            return dialog
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    //check device time and return "Good morning, Good afternoon, Good evening"
    fun getGreetingMessage(): String {
        val hour = DateTimeUtil.getCurrentHour()
        return when (hour) {
            in 0..11 -> "Good morning"
            in 12..15 -> "Good afternoon"
            in 16..20 -> "Good evening"
            in 21..23 -> "Good night"
            else -> "Hello"
        }
    }

    fun removeCharactersBetweenParentheses(input: String): String {
        val regex = Regex("\\([^)]*\\)")
        return input.replace(regex, "")
    }

    fun openDialPad(context: Context, phoneNum: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNum")
        context.startActivity(intent)
    }

    fun convertCurrencyNameToSymbol(currencyName: String?): String {
        if (currencyName.isNullOrEmpty()) return ""
        return when (currencyName) {
            "USD" -> "$"
            "EUR" -> "€"
            "GBP" -> "£"
            "INR" -> "₹"
            "AUD" -> "$"
            "CAD" -> "$"
            "SGD" -> "$"
            "CHF" -> "Fr"
            "MYR" -> "RM"
            "JPY" -> "¥"
            "CNY" -> "¥"
            "NZD" -> "$"
            "THB" -> "฿"
            "HUF" -> "Ft"
            "AED" -> "د.إ"
            "HKD" -> "$"
            "MXN" -> "$"
            "ZAR" -> "R"
            "PHP" -> "₱"
            "SEK" -> "kr"
            "IDR" -> "Rp"
            "SAR" -> "﷼"
            "BRL" -> "R$"
            "TRY" -> "₺"
            "KES" -> "KSh"
            "KRW" -> "₩"
            "EGP" -> "£"
            "IQD" -> "ع.د"
            "NOK" -> "kr"
            "KWD" -> "د.ك"
            "RUB" -> "₽"
            "DKK" -> "kr"
            "PKR" -> "₨"
            "ILS" -> "₪"
            "PLN" -> "zł"
            "QAR" -> "﷼"
            "XAU" -> "XAU"
            "OMR" -> "﷼"
            "COP" -> "$"
            "CLP" -> "$"
            "TWD" -> "NT$"
            "ARS" -> "$"
            "CZK" -> "Kč"
            "VND" -> "₫"
            "MAD" -> "د.م."
            "JOD" -> "د.ا"
            "BHD" -> "ب.د"
            "XOF" -> "Fr"
            "LKR" -> "₨"
            "UAH" -> "₴"
            "NGN" -> "₦"

            else -> ""
        }
    }

    fun deletePreference(preferenceUtil: PreferenceUtil) {
        preferenceUtil.remove(PreferenceUtil.KEY_COOKIE)
    }

    fun priceFormatter(price: String): String {
        val formatter = DecimalFormat("###,###.00")
        return formatter.format(price.toDouble())
    }
}