package com.digitalhouse.dhwallet.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.digitalhouse.dhwallet.HomeCardItemFragment
import com.digitalhouse.dhwallet.R
import com.digitalhouse.dhwallet.model.Card

class HomeCardAdapter(
    private val items: List<Card>,
    fragment: Fragment,
    private val action: (View, Card) -> Unit
) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount() = items.size

    override fun createFragment(position: Int): Fragment {
        if (items.isEmpty()) {
            throw IllegalStateException("items are empty")
        }

        return items[position].run {
            HomeCardItemFragment.newInstance(
                limit,
                brand,
                number,
                name,
                expireAt
            )
        }
    }

    override fun onBindViewHolder(
        holder: FragmentViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        holder.itemView.setOnClickListener {
            val rootView = it.findViewById<View>(R.id.root_card_item)
            rootView.transitionName = it.context.getString(R.string.home_card_info_transition)
            action.invoke(rootView, items[position])
        }
    }
}