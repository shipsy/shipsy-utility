package com.shipsy.utility

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import com.shipsy.utility.utils.AdvertisingUtil


class MainActivity : ComponentActivity() {
    private val adsIdLiveData = MutableLiveData("")

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adsIdLiveData.observe(this) { adsId ->
            setContent {
                Scaffold(
                    content = {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Ads Id",
                                fontSize = 24.sp
                            )
                            Text(
                                text = adsId,
                                fontSize = 12.sp
                            )
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { shareText(adsId) }
                        ) {
                            Icon(
                                Icons.Filled.Share,
                                "contentDescription",
                            )
                        }
                    }
                )
            }
        }


        fetchAdsId()
    }

    private fun shareText(text: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, text)
        val title = "Ads Id"
        startActivity(Intent.createChooser(intent, title))
    }

    private fun fetchAdsId() {
        AdvertisingUtil.getAdsId(this) {
            adsIdLiveData.postValue(it)
        }
    }
}