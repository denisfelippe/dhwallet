package com.digitalhouse.dhwallet

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

private const val ARG_CARD_LIMIT = "arg_limit"
private const val ARG_CARD_FLAG = "arg_flag"
private const val ARG_CARD_NUMBER = "arg_card_end_number"
private const val ARG_CARD_NAME = "arg_card_name"
private const val ARG_CARD_EXPIRE = "arg_card_expire"

class CreditCardItemFragment : Fragment(R.layout.item_card) {
    private var limit: String? = null
    private var flag: Int? = null
    private var number: String? = null
    private var name: String? = null
    private var expire: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            limit = it.getString(ARG_CARD_LIMIT)
            flag = it.getInt(ARG_CARD_FLAG)
            number = it.getString(ARG_CARD_NUMBER)
            name = it.getString(ARG_CARD_NAME)
            expire = it.getString(ARG_CARD_EXPIRE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        limit?.let { view.findViewById<TextView>(R.id.item_card_limit).text = it }
        flag?.let {
            view.findViewById<ImageView>(R.id.item_card_flag)
                .setImageDrawable(ContextCompat.getDrawable(view.context, it))
        }
        number?.let { view.findViewById<TextView>(R.id.item_card_number4).text = it }
        name?.let { view.findViewById<TextView>(R.id.item_card_name).text = it }
        expire?.let { view.findViewById<TextView>(R.id.item_card_expire).text = it }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            limit: String,
            @DrawableRes flag: Int,
            number: String,
            name: String,
            expire: String
        ) = CreditCardItemFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_CARD_LIMIT, limit)
                putInt(ARG_CARD_FLAG, flag)
                putString(ARG_CARD_NUMBER, number)
                putString(ARG_CARD_NAME, name)
                putString(ARG_CARD_EXPIRE, expire)
            }
        }
    }
}
