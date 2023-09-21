package com.example.einloggohnegoogle

data class Rezept(
    var name: String = "",
    var zubereitung: String = "",
    var zutaten: List<String> = listOf()
)