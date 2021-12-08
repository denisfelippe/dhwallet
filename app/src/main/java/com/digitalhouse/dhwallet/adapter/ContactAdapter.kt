package com.digitalhouse.dhwallet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.digitalhouse.dhwallet.R
import com.digitalhouse.dhwallet.model.Contact

private const val HEADER = 0
private const val CONTENT = 1

class ContactAdapter(private val items: List<Contact>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (viewType == HEADER) {
            return ContactHeaderViewHolder(inflater.inflate(R.layout.item_header_contact, parent, false))
        }

        return ContactViewHolder(inflater.inflate(R.layout.item_contact, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ContactViewHolder -> holder.bind(items[position-1])
            is ContactHeaderViewHolder -> holder.bind(holder.itemView.context.getString(R.string.title_contact))
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

class ContactHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tituloView: TextView = view.findViewById(R.id.title)

    fun bind(titulo: String) {
        tituloView.text = titulo
    }
}

class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val image: ImageView = view.findViewById(R.id.image)
    private val title: TextView = view.findViewById(R.id.item_contact_title)
    private val subtitle: TextView = view.findViewById(R.id.item_contact_subtitle)

    fun bind(item: Contact) {
        Glide.with(image.context).load(item.image).circleCrop().into(image)
        title.text = item.name
        subtitle.text = item.type.description
    }
}
