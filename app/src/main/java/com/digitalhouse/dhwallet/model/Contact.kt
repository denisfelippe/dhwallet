package com.digitalhouse.dhwallet.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Contact(
    val id: Int,
    val image: String,
    val name: String,
    val type: ContactType
): Parcelable

enum class ContactType(val description: String) {
    IRMA("Irmã"),
    IRMAO("Irmão"),
    ESPOSA("Esposa"),
    AMIGA("Amiga"),
    AMIGO("Amigo"),
    MAE("Mãe"),
}
