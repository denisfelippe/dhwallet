package com.digitalhouse.dhwallet

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.digitalhouse.dhwallet.adapter.HomeCardAdapter
import com.digitalhouse.dhwallet.adapter.TransactionAdapter
import com.digitalhouse.dhwallet.mock.Mock
import com.digitalhouse.dhwallet.model.Card
import com.digitalhouse.dhwallet.model.TransactionContent
import com.digitalhouse.dhwallet.util.CustomPageTransformer
import com.digitalhouse.dhwallet.util.decorator.HorizontalMarginItemDecoration

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val profile: ImageView?
        get() = view?.findViewById(R.id.profile)


    private val galleryCallback =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val image = it.data?.data
                profile?.setImageURI(image)
            }
        }

    private val cameraCallback =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data

                data?.extras?.get("data")?.let { photo ->
                    profile?.setImageBitmap(photo as Bitmap)
                }
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view.findViewById<ViewPager2>(R.id.list_card)
        viewPager.adapter = HomeCardAdapter(Mock.listCard, this, ::sendToDetail)

        viewPager.addItemDecoration(
            HorizontalMarginItemDecoration(
                view.context,
                R.dimen.viewpager_current_item_horizontal_margin
            )
        )
        viewPager.setPageTransformer(CustomPageTransformer(view.context))
        viewPager.offscreenPageLimit = 1

        val recyclerView = view.findViewById<RecyclerView>(R.id.home_transaction)
        recyclerView.adapter = TransactionAdapter(
            Mock.listTransaction,
            view.context.getString(R.string.transaction_title),
            true,
            ::sendToTransaction,
            ::shareTransaction
        )

        profile?.setOnClickListener {
            dialogPhoto(it.context)
        }
    }

    private fun dialogPhoto(context: Context) {
        val items = arrayOf("Tirar foto", "Buscar da galeria")
        AlertDialog
            .Builder(context)
            .setTitle("Qual você deseja usar?")
            .setItems(items) { dialog, index ->
                when (index) {
                    0 -> getFromCamera(context)
                    1 -> getFromGallery()
                }
                dialog.dismiss()
            }.show()
    }

    private fun getFromCamera(context: Context) {
        val permission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)

        if (permission == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent().apply {
                action = MediaStore.ACTION_IMAGE_CAPTURE
            }

            cameraCallback.launch(intent)
        }
    }

    private fun getFromGallery() {
        val intent = Intent().apply {
            action = Intent.ACTION_PICK
            type = "image/*"
        }

        galleryCallback.launch(intent)
    }

    private fun shareTransaction(transaction: TransactionContent) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                getString(
                    R.string.share_contact,
                    transaction.title,
                    transaction.subtitle,
                    transaction.value
                )
            )
            type = "text/plain"
        }

        startActivity(Intent.createChooser(sendIntent, ""))
    }

    private fun sendToTransaction() {
        val action =
            HomeFragmentDirections.actionHomeFragmentToTransactionFragment("R$ 45,50", "R$ 536")
        findNavController().navigate(action)
    }

    private fun sendToDetail(rootView: View, card: Card) {
        ViewCompat.getTransitionName(rootView)?.let {
            val extras = FragmentNavigatorExtras(rootView to it)
            val action = HomeFragmentDirections.actionHomeFragmentToCardInfoFragment(card)
            findNavController().navigate(action, extras)
        }
    }
}
