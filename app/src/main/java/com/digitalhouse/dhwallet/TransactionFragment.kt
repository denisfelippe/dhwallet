package com.digitalhouse.dhwallet

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.dhwallet.adapter.TransactionAdapter
import com.digitalhouse.dhwallet.model.Transaction
import com.digitalhouse.dhwallet.model.TransactionContent

class TransactionFragment : Fragment(R.layout.fragment_transaction) {

    private val myArgs: TransactionFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listTransaction: List<Transaction> = listOf(
            TransactionContent(
                "Spotify",
                "Pagamento",
                "- R$53,40",
                "https://cdn.icon-icons.com/icons2/2429/PNG/512/spotify_logo_icon_147234.png"
            )
        )

        val transactionList = view.findViewById<RecyclerView>(R.id.transaction_transaction_list)
        transactionList.adapter = TransactionAdapter(
            listTransaction,
            view.context.getString(R.string.title_transfer),
            true,
            ::sendToTransfer
        ) {}

        val entrada = view.findViewById<TextView>(R.id.transaction_entrada)
        val saida = view.findViewById<TextView>(R.id.transaction_saida)

        entrada.text = myArgs.argEntrada
        saida.text = myArgs.argSaida
    }

    private fun sendToTransfer() {
        val action = TransactionFragmentDirections.actionTransactionFragmentToTransferFragment()
        findNavController().navigate(action)
    }
}
