package com.example.einloggohnegoogle.data.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "rezept_table")
data class Rezept(
    @PrimaryKey(autoGenerate = false)
    var id: String="",
    var name: String = "",
    var zubereitung: String = "",
    var zutaten: String = "",
    var videoupload:String = ""
)