package dev.saurabhmishra.redditclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import dev.saurabhmishra.redditclone.theme.ReddJetTheme
import dev.saurabhmishra.redditclone.ui.signup.SignupScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReddJetTheme {
                SignupScreen()
            }
        }
    }
}
