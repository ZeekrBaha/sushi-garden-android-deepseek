package com.baha.sushigarden

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.baha.sushigarden.navigation.SushiGardenNavHost
import com.baha.sushigarden.ui.designsystem.SushiGardenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SushiGardenTheme {
                SushiGardenNavHost()
            }
        }
    }
}
