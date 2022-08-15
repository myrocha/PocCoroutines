package com.example.poccoroutines.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.poccoroutines.LocalTestRule
import org.junit.Rule
import org.junit.rules.TestRule


internal class GitHubRepositoryViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val localTestRule = LocalTestRule()


}