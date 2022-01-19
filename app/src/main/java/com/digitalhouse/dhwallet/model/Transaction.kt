package com.digitalhouse.dhwallet.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

sealed class Transaction : Parcelable

@Parcelize
class TransactionContent(
    val title: String,
    val subtitle: String,
    val value: String,
    val image: String,
) : Transaction()

@Parcelize
class TransactionHeader(
    val name: String
) : Transaction()

@Parcelize
class PageTitle(
    val title: String
) : Transaction()
