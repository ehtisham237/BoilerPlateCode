package com.reading.novel.network

import android.content.Context
import android.content.Intent
import com.enbridgegas.meterapp.constant.AppConstant
import com.reading.novel.utils.AppUtil
import com.reading.novel.utils.LogUtil
import com.reading.novel.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response


class NetworkConnectionInterceptor(
    private val context: Context
) : Interceptor {

    private val applicationContext = context.applicationContext

    @Throws(NoInternetException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!AppUtil.isInternetAvailable(applicationContext)) throw NoInternetException(AppConstant.INTERNET_ERROR)
//        return chain.proceed(chain.request())

        val response = chain.proceed(chain.request())
        if (response.code == 400) {
            val responseBodyCopy = response.peekBody(Long.MAX_VALUE)
            val jsonData = responseBodyCopy.string()

            LogUtil.e("jsonData=> $jsonData")
            if (jsonData.lowercase().contains("http status 400")) {
                val intent = Intent(AppConstant.ACTION_400_ERROR)
                //LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
            }
        }
        return response
    }
}