package com.digitalhouse.dhwallet

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class CardInfoFragment : Fragment(R.layout.fragment_card_info) {
    private val args: CardInfoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val limitView = view.findViewById<TextView>(R.id.card_info_limit)
        val numberView = view.findViewById<TextView>(R.id.card_info_number4)
        val buttonTransfer = view.findViewById<Button>(R.id.btn_transfer)
        val buttonTransaction = view.findViewById<Button>(R.id.btn_transaction)
        val buttonPayment = view.findViewById<Button>(R.id.btn_payment)

        limitView.text = args.argLimit
        numberView.text = args.argCardNumber

        buttonTransfer.setOnClickListener { sendToButtonTransfer() }
        buttonTransaction.setOnClickListener { sendToButtonTransaction() }
        buttonPayment.setOnClickListener { sendToButtonPayment() }
    }

    private fun sendToButtonPayment() {
        TODO("Not yet implemented")
    }

    private fun sendToButtonTransaction() {
        val action = CardInfoFragmentDirections.actionCardInfoFragmentToTransactionFragment(
            "R$ 45,35",
            "R$ 536"
        )
        findNavController().navigate(action)
    }

    private fun sendToButtonTransfer() {
        val action = CardInfoFragmentDirections.actionCardInfoFragmentToTransferFragment()
        findNavController().navigate(action)
    }
}
