package com.example.nutribite.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.nutribite.presentation.common.SplashViewModel
import com.example.nutribite.presentation.util.SetupNavigation
import com.example.nutribite.ui.theme.FoodikeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

        @Inject
        lateinit var splashViewModel: SplashViewModel


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            installSplashScreen().setKeepOnScreenCondition {
                splashViewModel.isLoading.value
            }

            setContent {
                FoodikeTheme {
                    val screen by splashViewModel.startDestination

                    Surface(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        SetupNavigation(
                            startDestination = screen
                        )
                    }
                }
            }
        }
}





