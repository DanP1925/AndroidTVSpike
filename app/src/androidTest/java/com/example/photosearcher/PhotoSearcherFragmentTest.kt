package com.example.photosearcher

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.filters.MediumTest
import com.example.photosearcher.data.Photo
import com.example.photosearcher.data.source.DefaultPhotoRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
class PhotoSearcherFragmentTest {

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: DefaultPhotoRepository

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun displayPhotos() {
        repository.savePhoto(Photo("Title 1", "John Smith", "2022-06-03"))
        launchActivity()
        onView(withText("Title 1")).check(matches(isDisplayed()))
    }

    private fun launchActivity(): ActivityScenario<MainActivity>? =
        launch(MainActivity::class.java)

}