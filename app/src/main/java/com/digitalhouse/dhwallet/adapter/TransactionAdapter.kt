package com.digitalhouse.dhwallet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.digitalhouse.dhwallet.R
import com.digitalhouse.dhwallet.model.Transaction

class TransactionAdapter(
    private val items: MutableList<Transaction>,
    private val action: (Transaction) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TransactionViewHolder(
            inflater.inflate(R.layout.item_transaction, parent, false),
            action
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TransactionViewHolder -> holder.bind(items[position])
        }
    }

    override fun getItemCount() = items.size
}

class TransactionViewHolder(view: View, action: (Transaction) -> Unit) :
    RecyclerView.ViewHolder(view) {
    private val image: ImageView = view.findViewById(R.id.image)
    private val title: TextView = view.findViewById(R.id.item_transaction_title)
    private val subtitle: TextView = view.findViewById(R.id.item_transaction_subtitle)
    private var itemCorrente: Transaction? = null

    init {
        view.setOnClickListener {
            itemCorrente?.let {
                action.invoke(it)
            }
        }
    }

    fun bind(item: Transaction) {
        itemCorrente = item
        Glide.with(image.context).load(item.image).circleCrop().into(image)
        title.text = item.title
        subtitle.text = item.subtitle
    }
}
