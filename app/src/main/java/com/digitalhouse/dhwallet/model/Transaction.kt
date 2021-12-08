package com.digitalhouse.dhwallet.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Transaction(
    val title: String,
    val subtitle: String,
    val value: String,
    val image: String,
): Parcelable
