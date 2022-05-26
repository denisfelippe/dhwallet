package com.digitalhouse.dhwallet.mock

import com.digitalhouse.dhwallet.R
import com.digitalhouse.dhwallet.model.Card
import com.digitalhouse.dhwallet.model.PageTitle
import com.digitalhouse.dhwallet.model.TransactionContent
import com.digitalhouse.dhwallet.model.TransactionHeader

object Mock {
    val listCard = listOf(
        Card(
            limit = "R$ 18,00",
            brand = R.drawable.ic_visa,
            number = "1020",
            name = "DENIS F ROCHA",
            expireAt = "10/24"
        ),
        Card(
            limit = "R$ 15,00",
            brand = R.drawable.ic_visa,
            number = "1020",
            name = "DENIS F ROCHA",
            expireAt = "10/24"
        ),
        Card(
            limit = "R$ 10,00",
            brand = R.drawable.ic_visa,
            number = "1020",
            name = "DENIS F ROCHA",
            expireAt = "10/24"
        )
    )

    val listTransaction = listOf(
        PageTitle(title = "Transações"),
        TransactionHeader(name = "Hoje"),
        TransactionContent(
            "Spotify Family",
            "Pagamento",
            "- R$ 163",
            "https://cdn.icon-icons.com/icons2/2429/PNG/512/spotify_logo_icon_147234.png"
        ),
        TransactionContent(
            "Netflix",
            "Pagamento",
            "- R$ 63",
            "https://cdn.icon-icons.com/icons2/2429/PNG/512/spotify_logo_icon_147234.png"
        ),
        TransactionHeader(
            name = "Qua., 10 de Nov."
        ),
        TransactionContent(
            "Spotify Family",
            "Pagamento",
            "- R$ 163",
            "https://cdn.icon-icons.com/icons2/2429/PNG/512/spotify_logo_icon_147234.png"
        ),
        TransactionContent(
            "Spotify Family",
            "Pagamento",
            "- R$ 163",
            "https://cdn.icon-icons.com/icons2/2429/PNG/512/spotify_logo_icon_147234.png"
        ),
        TransactionContent(
            "Spotify Family",
            "Pagamento",
            "- R$ 163",
            "https://cdn.icon-icons.com/icons2/2429/PNG/512/spotify_logo_icon_147234.png"
        ),
        TransactionContent(
            "Spotify Family",
            "Pagamento",
            "- R$ 163",
            "https://cdn.icon-icons.com/icons2/2429/PNG/512/spotify_logo_icon_147234.png"
        ),
    )
}
