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
import com.digitalhouse.dhwallet.model.PageTitle
import com.digitalhouse.dhwallet.model.Transaction
import com.digitalhouse.dhwallet.model.TransactionContent
import com.digitalhouse.dhwallet.model.TransactionHeader

private const val HEADER = 0
private const val CONTENT = 1
private const val TRANSACTION_HEADER = 2

class TransactionAdapter(
    private val items: List<Transaction>,
    private val title: String,
    private val showArrow: Boolean = false,
    private val action: () -> Unit,
    private val detailAction: (TransactionContent) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            HEADER -> TransactionHeaderViewHolder(inflater.inflate(R.layout.item_header, parent, false), action)
            TRANSACTION_HEADER -> TransactionDateHeaderViewHolder(inflater.inflate(R.layout.item_header, parent, false))
            else -> TransactionViewHolder(inflater.inflate(R.layout.item_transaction, parent, false), detailAction)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TransactionDateHeaderViewHolder -> holder.bind((items[position] as TransactionHeader).name)
            is TransactionViewHolder -> holder.bind(items[position] as TransactionContent)
            is TransactionHeaderViewHolder -> holder.bind(title, showArrow)
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]) {
            is TransactionContent -> CONTENT
            is TransactionHeader -> TRANSACTION_HEADER
            is PageTitle -> HEADER
        }
    }
}

class TransactionHeaderViewHolder(view: View, action: () -> Unit) : RecyclerView.ViewHolder(view) {
    private val tituloView: TextView = view.findViewById(R.id.title)
    private val arrow: ImageView = view.findViewById(R.id.arrow)

    init {
        view.setOnClickListener {
            action.invoke()
        }
    }

    fun bind(titulo: String, showArrow: Boolean) {
        tituloView.text = titulo
        arrow.isVisible = showArrow
    }
}



class TransactionDateHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tituloView: TextView = view.findViewById(R.id.title)
    private val arrow: ImageView = view.findViewById(R.id.arrow)

    init {
        arrow.isVisible = false
    }

    fun bind(titulo: String) {
        tituloView.text = titulo
    }
}

class TransactionViewHolder(view: View, detailAction: (TransactionContent) -> Unit) :
    RecyclerView.ViewHolder(view) {
    private val image: ImageView = view.findViewById(R.id.image)
    private val title: TextView = view.findViewById(R.id.item_transaction_title)
    private val subtitle: TextView = view.findViewById(R.id.item_transaction_subtitle)
    private var currentContent: TransactionContent? = null

    init {
        view.setOnClickListener {
            currentContent?.let { transaction ->
                detailAction.invoke(transaction)
            }
        }
    }

    fun bind(item: TransactionContent) {
        Glide.with(image.context).load(item.image).circleCrop().into(image)
        title.text = item.title
        subtitle.text = item.subtitle
        currentContent = item
    }
}
