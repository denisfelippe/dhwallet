package com.digitalhouse.dhwallet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.digitalhouse.dhwallet.R
import com.digitalhouse.dhwallet.model.Transaction

private const val HEADER = 0
private const val CONTENT = 1

class TransactionAdapter(
    private val items: MutableList<Transaction>,
    private val title: String,
    private val showArrow: Boolean = false,
    private val action: (Transaction) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        if (viewType == HEADER) {
            return TransactionHeaderViewHolder(inflater.inflate(R.layout.item_header, parent, false))
        }

        return TransactionViewHolder(
            inflater.inflate(R.layout.item_transaction, parent, false),
            action
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TransactionViewHolder -> holder.bind(items[position-1])
            is TransactionHeaderViewHolder -> holder.bind(title, showArrow)
        }
    }

    override fun getItemCount() = items.size + 1

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return HEADER
        }

        return CONTENT
    }
}

class TransactionHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tituloView: TextView = view.findViewById(R.id.title)
    private val arrow: ImageView = view.findViewById(R.id.arrow)

    fun bind(titulo: String, showArrow: Boolean) {
        tituloView.text = titulo
        arrow.isVisible = showArrow
    }
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
