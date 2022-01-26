package com.digitalhouse.dhwallet

import android.content.Intent
import android.os.Bundle
import android.view.View
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
    }

    private fun shareTransaction(transaction: TransactionContent) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, getString(R.string.share_contact, transaction.title, transaction.subtitle, transaction.value))
            type = "text/plain"
        }

        startActivity(Intent.createChooser(sendIntent, ""))
    }

    private fun sendToTransaction() {
        val action = HomeFragmentDirections.actionHomeFragmentToTransactionFragment("R$ 45,50", "R$ 536")
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
