package com.digitalhouse.dhwallet.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


class AirplaneBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            return
        }

        val isAirplaneMode = intent.extras?.getBoolean("state", false) ?: return

        if (isAirplaneMode) {
            Toast.makeText(context, "Modo avião", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Saiu do modo avião", Toast.LENGTH_LONG).show()
        }
    }
}

fun main() {
    val fruits = listOf(
        Fruta("amarelo", "banana"),
        Fruta("vermelho", "maça"),
        Fruta("verde", "abacate"),
        Fruta("amarelo", "melão")
    )

    val group = fruits.groupBy{ it.cor }
    println(group)
}

data class Fruta(val cor: String, val nome: String)