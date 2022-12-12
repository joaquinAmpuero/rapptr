package com.rapptrlabs.androidtest.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.rememberNavController
import com.rapptrlabs.androidtest.R
import com.rapptrlabs.androidtest.databinding.ActivityMainBinding
import com.rapptrlabs.androidtest.ui.navigation.NavGraph
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main screen that lets you navigate to all other screens in the app.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = colorResource(id = R.color.button_blue)
            ) {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}