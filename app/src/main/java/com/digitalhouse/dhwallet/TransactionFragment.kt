package com.digitalhouse.dhwallet

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.dhwallet.adapter.TransactionAdapter
import com.digitalhouse.dhwallet.model.Transaction

private const val ARG_ENTRADA = "arg_entrada"
private const val ARG_SAIDA = "arg_saida"

class TransactionFragment : Fragment(R.layout.fragment_transaction) {
    private var paramEntrada: String? = null
    private var paramSaida: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramEntrada = it.getString(ARG_ENTRADA)
            paramSaida = it.getString(ARG_SAIDA)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listTransaction = MutableList(20) {
            Transaction(
                "Spotify",
                "Pagamento",
                "- R$53,40",
                "https://cdn.icon-icons.com/icons2/2429/PNG/512/spotify_logo_icon_147234.png"
            )
        }

        val transactionList = view.findViewById<RecyclerView>(R.id.transaction_transaction_list)
        transactionList.adapter = TransactionAdapter(listTransaction, view.context.getString(R.string.title_transfer), true) {
            sendToTransfer()
        }

        val entrada = view.findViewById<TextView>(R.id.transaction_entrada)
        val saida = view.findViewById<TextView>(R.id.transaction_saida)

        paramEntrada?.let { entrada.text = it }
        paramSaida?.let { saida.text = it }
    }

    private fun sendToTransfer() {
        TODO("Not yet implemented")
    }

    companion object {
        fun newInstance(paramEntrada: String, paramSaida: String) =
            TransactionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ENTRADA, paramEntrada)
                    putString(ARG_SAIDA, paramSaida)
                }
            }
    }
}
