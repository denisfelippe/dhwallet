package com.digitalhouse.dhwallet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.dhwallet.adapter.ContactAdapter
import com.digitalhouse.dhwallet.model.Contact
import com.digitalhouse.dhwallet.model.ContactType

class TransferFragment : Fragment(R.layout.fragment_transfer) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listContact = MutableList(10) {
            Contact(
                image = "https://avatars.githubusercontent.com/u/1663531?v=4",
                name = "Denis",
                type = ContactType.IRMAO
            )
        }

        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        recycler.adapter = ContactAdapter(listContact)
    }
}