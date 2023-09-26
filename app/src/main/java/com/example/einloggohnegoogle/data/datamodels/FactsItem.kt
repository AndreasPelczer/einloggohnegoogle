package com.example.catfactsfriday.data.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class FactsItem(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    val fact: String,
    val length: Int
)