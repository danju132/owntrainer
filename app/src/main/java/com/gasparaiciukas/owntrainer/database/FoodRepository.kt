package com.gasparaiciukas.owntrainer.database

import com.gasparaiciukas.owntrainer.BuildConfig
import com.gasparaiciukas.owntrainer.network.GetService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodRepository @Inject constructor(
    private val getService: GetService,
    private val foodEntryDao: FoodEntryDao
) {
    suspend fun getFoods(query: String) =
        getService.getFoods(BuildConfig.API_KEY, query).foods ?: listOf()

    suspend fun insertFood(foodEntry: FoodEntry) = foodEntryDao.insertFoodEntry(foodEntry)
    suspend fun deleteFoodById(foodEntryId: Int) = foodEntryDao.deleteFoodEntryById(foodEntryId)
}