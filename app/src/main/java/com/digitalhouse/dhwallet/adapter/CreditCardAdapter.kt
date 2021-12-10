package com.digitalhouse.dhwallet.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import java.lang.IllegalStateException

class CreditCardAdapter(
    private val fragments: List<Fragment>,
    fragmentActivity: FragmentActivity,
    private val action: () -> Unit
): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int): Fragment {
        if (fragments.isEmpty()) {
            throw IllegalStateException("The fragment list is empty")
        } else {
            return fragments[position]
        }
    }

    override fun onBindViewHolder(
        holder: FragmentViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        (holder.itemView as ViewGroup).setOnClickListener { action.invoke() }
        super.onBindViewHolder(holder, position, payloads)
    }
}