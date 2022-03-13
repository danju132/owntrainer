package com.gasparaiciukas.owntrainer.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.gasparaiciukas.owntrainer.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class UserDaoTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setup() {
        hiltRule.inject()
        userDao = database.userDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertUser() = runTest {
        val currentDate = LocalDate.now()
        val user = User(
            userId = 1,
            sex = "Male",
            ageInYears = 25,
            heightInCm = 180,
            weightInKg = 80.0,
            lifestyle = "Lightly active",
            year = currentDate.year,
            month = currentDate.monthValue,
            dayOfYear = currentDate.dayOfYear,
            dayOfMonth = currentDate.dayOfMonth,
            dayOfWeek = currentDate.dayOfWeek.value
        )
        userDao.insertUser(user)

        val userRoom = userDao.getUser().asLiveData().getOrAwaitValue()
        assertThat(userRoom).isEqualTo(user)
    }

    @Test
    fun updateUser() = runTest {
        val currentDate = LocalDate.now()
        var user = User(
            userId = 1,
            sex = "Male",
            ageInYears = 25,
            heightInCm = 180,
            weightInKg = 80.0,
            lifestyle = "Lightly active",
            year = currentDate.year,
            month = currentDate.monthValue,
            dayOfYear = currentDate.dayOfYear,
            dayOfMonth = currentDate.dayOfMonth,
            dayOfWeek = currentDate.dayOfWeek.value
        )
        userDao.insertUser(user)
        user = userDao.getUser().asLiveData().getOrAwaitValue()
        user.heightInCm = 155
        userDao.updateUser(user)
        val userRoom = userDao.getUser().asLiveData().getOrAwaitValue()
        assertThat(userRoom).isEqualTo(user)
    }

    @Test
    fun deleteUser() = runTest {
        val currentDate = LocalDate.now()
        val user = User(
            userId = 1,
            sex = "Male",
            ageInYears = 25,
            heightInCm = 180,
            weightInKg = 80.0,
            lifestyle = "Lightly active",
            year = currentDate.year,
            month = currentDate.monthValue,
            dayOfYear = currentDate.dayOfYear,
            dayOfMonth = currentDate.dayOfMonth,
            dayOfWeek = currentDate.dayOfWeek.value
        )
        userDao.insertUser(user)
        val userId = userDao.getUser().asLiveData().getOrAwaitValue().userId
        userDao.deleteUserById(userId)
        val userRoom = userDao.getUser().asLiveData().getOrAwaitValue()
        assertThat(userRoom).isEqualTo(null)
    }
}