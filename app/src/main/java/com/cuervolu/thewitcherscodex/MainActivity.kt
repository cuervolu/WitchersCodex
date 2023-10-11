package com.cuervolu.thewitcherscodex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.cuervolu.thewitcherscodex.domain.usecases.AppEntryUseCases
import com.cuervolu.thewitcherscodex.ui.presentation.onboarding.OnBoardingScreen
import com.cuervolu.thewitcherscodex.ui.presentation.onboarding.OnBoardingViewModel
import com.cuervolu.thewitcherscodex.ui.theme.TheWitchersCodexTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appEntryUseCases: AppEntryUseCases
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        lifecycleScope.launch {
            appEntryUseCases.readAppEntry().collect {

                Timber.d("AppEntry: $it")
            }
        }
        setContent {
            TheWitchersCodexTheme {
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    val viewModel: OnBoardingViewModel = hiltViewModel()
                    OnBoardingScreen(
                        event = viewModel::onEvent
                    )
                }
            }
        }
    }
}
