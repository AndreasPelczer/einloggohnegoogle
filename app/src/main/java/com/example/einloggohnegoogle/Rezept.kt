package com.example.einloggohnegoogle

import androidx.room.Entity


@Entity(tableName = "rezept_table")
data class Rezept(
    var id: String,
    var name: String = "",
    var zubereitung: String = "",
    var zutaten: String = ""
)