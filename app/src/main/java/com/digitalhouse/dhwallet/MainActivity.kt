package com.digitalhouse.dhwallet

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.digitalhouse.dhwallet.receiver.AirplaneBroadcastReceiver

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    lateinit var receiver: AirplaneBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiver = AirplaneBroadcastReceiver()
        IntentFilter().apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            registerReceiver(receiver, this)
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}
