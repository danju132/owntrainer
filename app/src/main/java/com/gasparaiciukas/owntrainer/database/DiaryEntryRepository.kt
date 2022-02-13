package com.gasparaiciukas.owntrainer.database

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DiaryEntryRepository @Inject constructor(
    private val diaryEntryDao: DiaryEntryDao,
    private val diaryEntryWithMealsDao: DiaryEntryWithMealsDao
) {
    suspend fun insertDiaryEntry(diaryEntry: DiaryEntry) = diaryEntryDao.insertDiaryEntry(diaryEntry)

    suspend fun removeDiaryEntry(year: Int, dayOfYear: Int) = diaryEntryDao.deleteDiaryEntryByYearAndDayOfYear(year, dayOfYear)

    fun getDiaryEntry(year: Int, dayOfYear: Int) =
        diaryEntryDao.getDiaryEntryByYearAndDayOfYear(year, dayOfYear)

    fun getDiaryEntryWithMeals(year: Int, dayOfYear: Int) =
        diaryEntryWithMealsDao.getDiaryEntryWithMeals(year, dayOfYear)

    suspend fun insertDiaryEntryMealCrossRef(crossRef: DiaryEntryMealCrossRef) =
        diaryEntryWithMealsDao.insertDiaryEntryMealCrossRef(crossRef)

    suspend fun deleteDiaryEntryMealCrossRef(diaryEntryId: Int, mealId: Int) =
        diaryEntryWithMealsDao.deleteDiaryEntryMealCrossRef(diaryEntryId, mealId)
}