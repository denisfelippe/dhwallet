package com.digitalhouse.dhwallet

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

private const val ARG_LIMIT = "arg_limit"
private const val ARG_CARD_NUMBER = "arg_card_number"

class CardInfoFragment : Fragment(R.layout.fragment_card_info) {
    private var limit: String? = null
    private var cardNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            limit = it.getString(ARG_LIMIT)
            cardNumber = it.getString(ARG_CARD_NUMBER)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val limitView = view.findViewById<TextView>(R.id.card_info_limit)
        val numberView = view.findViewById<TextView>(R.id.card_info_number4)
        val buttonTransfer = view.findViewById<Button>(R.id.btn_transfer)
        val buttonTransaction = view.findViewById<Button>(R.id.btn_transaction)
        val buttonPayment = view.findViewById<Button>(R.id.btn_payment)

        limit?.let { limitView.text = it }
        cardNumber?.let { numberView.text = it }

        buttonTransfer.setOnClickListener { sendToButtonTransfer() }
        buttonTransaction.setOnClickListener { sendToButtonTransaction() }
        buttonPayment.setOnClickListener { sendToButtonPayment() }
    }

    private fun sendToButtonPayment() {
        TODO("Not yet implemented")
    }

    private fun sendToButtonTransaction() {
        TODO("Not yet implemented")
    }

    private fun sendToButtonTransfer() {
        TODO("Not yet implemented")
    }

    companion object {
        fun newInstance(limit: String, cardNumber: String) =
            CardInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_LIMIT, limit)
                    putString(ARG_CARD_NUMBER, cardNumber)
                }
            }
    }
}
