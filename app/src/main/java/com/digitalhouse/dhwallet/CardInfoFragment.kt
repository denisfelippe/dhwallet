package com.digitalhouse.dhwallet

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater

class CardInfoFragment : Fragment(R.layout.fragment_card_info) {
    private val args: CardInfoFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.explode)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val limitView = view.findViewById<TextView>(R.id.card_info_limit)
        val numberView = view.findViewById<TextView>(R.id.card_info_number4)
        val buttonTransfer = view.findViewById<Button>(R.id.btn_transfer)
        val buttonTransaction = view.findViewById<Button>(R.id.btn_transaction)
        val buttonPayment = view.findViewById<Button>(R.id.btn_payment)
        val background = view.findViewById<View>(R.id.background_header)

        limitView.text = args.argCard.limit
        numberView.text = args.argCard.number
        background.transitionName = view.context.getString(R.string.home_card_info_transition)

        buttonTransfer.setOnClickListener { navigateToTransfer() }
        buttonTransaction.setOnClickListener { navigateToTransaction() }
        buttonPayment.setOnClickListener { navigateToPayment() }
    }

    private fun navigateToPayment() {
        TODO("Not yet implemented")
    }

    private fun navigateToTransaction() {
        val action = CardInfoFragmentDirections.actionCardInfoFragmentToTransactionFragment(
            "R$ 45,35",
            "R$ 536"
        )
        findNavController().navigate(action)
    }

    private fun navigateToTransfer() {
        val action = CardInfoFragmentDirections.actionCardInfoFragmentToTransferFragment()
        findNavController().navigate(action)
    }
}
