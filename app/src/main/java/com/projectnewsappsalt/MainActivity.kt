package com.projectnewsappsalt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.projectnewsappsalt.navigation.MainNavGraph
import com.projectnewsappsalt.ui.theme.ProjectNewsAppSaltTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectNewsAppSaltTheme {
                val navController = rememberNavController()

                MainNavGraph(navController = navController)
            }
        }
    }
}
