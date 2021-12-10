package com.digitalhouse.dhwallet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.digitalhouse.dhwallet.adapter.CreditCardAdapter
import com.digitalhouse.dhwallet.adapter.TransactionAdapter
import com.digitalhouse.dhwallet.model.Transaction
import com.digitalhouse.dhwallet.util.CustomPageTransformer
import com.digitalhouse.dhwallet.util.decorator.HorizontalMarginItemDecoration

class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transactionList: RecyclerView = view.findViewById(R.id.home_transaction_list)
        val cardList: ViewPager2 = view.findViewById(R.id.home_card_list)

        val fragmentList = MutableList<Fragment>(3) {
            CreditCardItemFragment.newInstance(
                limit = "R$15,00",
                flag = R.drawable.ic_visa,
                number = "5427",
                name = "DENIS F ROCHA",
                expire = "09/24"
            )
        }

        activity?.let {
            cardList.apply {
                adapter = CreditCardAdapter(fragmentList, it, ::sendToDetail)
                offscreenPageLimit = 1
                setPageTransformer(CustomPageTransformer(view.context))
                addItemDecoration(
                    HorizontalMarginItemDecoration(
                        view.context,
                        R.dimen.viewpager_current_item_horizontal_margin
                    )
                )
            }
        }

        transactionList.adapter = TransactionAdapter(
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
        TODO("Not yet implemented")
    }

    private fun sendToDetail() {
        //TODO navigate to next screen
    }
}
