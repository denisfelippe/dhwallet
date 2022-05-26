package com.digitalhouse.dhwallet

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.digitalhouse.dhwallet.adapter.ContactAdapter
import com.digitalhouse.dhwallet.model.Contact
import com.digitalhouse.dhwallet.model.ContactType
import com.digitalhouse.dhwallet.util.SharedPref

class TransferFragment : Fragment(R.layout.fragment_transfer) {

    private val permissionsResultCallback = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(requireContext(), "Permitido!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), "Não Permitido!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listContact = MutableList(10) {
            Contact(
                id = it,
                image = "https://avatars.githubusercontent.com/u/1663531?v=4",
                name = "Denis",
                type = ContactType.IRMAO
            )
        }

        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        recycler.adapter = ContactAdapter(listContact, SharedPref(view.context))

        view.findViewById<Button>(R.id.btn_scan).setOnClickListener {
            val permission = ContextCompat.checkSelfPermission(it.context, Manifest.permission.CAMERA)

            if (permission != PackageManager.PERMISSION_GRANTED) {
                permissionsResultCallback.launch(Manifest.permission.CAMERA)
            } else {
                Toast.makeText(requireContext(), "Já está permitido!", Toast.LENGTH_LONG).show()
            }
        }
    }
}
