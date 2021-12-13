package com.digitalhouse.dhwallet

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
import com.digitalhouse.dhwallet.model.Transaction
import com.digitalhouse.dhwallet.util.CustomPageTransformer
import com.digitalhouse.dhwallet.util.decorator.HorizontalMarginItemDecoration

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listCards: MutableList<Fragment> = MutableList(3) {
            HomeCardItemFragment.newInstance(
                "R$ 18,00",
                R.drawable.ic_visa,
                "1020",
                "DENIS F ROCHA",
                "10/24"
            )
        }

        val viewPager = view.findViewById<ViewPager2>(R.id.list_card)
        viewPager.adapter = HomeCardAdapter(listCards, this) {
            sendToDetail(it)
        }

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
            MutableList(10) {
                Transaction(
                    "Spotify Family",
                    "Pagamento",
                    "- R$ 163",
                    "https://cdn.icon-icons.com/icons2/2429/PNG/512/spotify_logo_icon_147234.png"
                )
            },
            view.context.getString(R.string.transaction_title),
            true
        ) {
            sendToTransaction()
        }
    }

    private fun sendToTransaction() {
        val action = HomeFragmentDirections.actionHomeFragmentToTransactionFragment("R$ 45,50", "R$ 536")
        findNavController().navigate(action)
    }

    private fun sendToDetail(rootView: View) {
        ViewCompat.getTransitionName(rootView)?.let {
            val extras = FragmentNavigatorExtras(rootView to it)
            val action = HomeFragmentDirections.actionHomeFragmentToCardInfoFragment("R$ 18,24", "1289")
            findNavController().navigate(action, extras)
        }
    }
}
