package com.shipsy.utility.utils

import android.content.Context
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object AdvertisingUtil {
    fun getAdsId(context: Context, callback: (String) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val addInfo = AdvertisingIdClient.getAdvertisingIdInfo(context.applicationContext)
                callback(addInfo.id ?: "")
            }catch (e:Exception){
                callback("")
            }

        }
    }

}