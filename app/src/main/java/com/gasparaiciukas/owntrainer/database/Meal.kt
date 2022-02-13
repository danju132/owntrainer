package com.gasparaiciukas.owntrainer.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "meal")
data class Meal(
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "instructions") var instructions: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    @ColumnInfo(name = "calories")
    var calories: Double = 0.0
    @ColumnInfo(name = "carbs")
    var carbs: Double = 0.0
    @ColumnInfo(name = "fat")
    var fat: Double = 0.0
    @ColumnInfo(name = "protein")
    var protein: Double = 0.0
}