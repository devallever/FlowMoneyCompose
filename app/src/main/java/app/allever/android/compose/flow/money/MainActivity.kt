package app.allever.android.compose.flow.money

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.allever.android.compose.flow.money.theme.FlowMoneyTheme
import app.allever.android.compose.flow.money.theme.PageBg
import app.allever.android.compose.flow.money.ui.InputScreen
import app.allever.android.compose.flow.money.ui.MainScreen

class MainActivity : ComponentActivity() {
    private val mViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlowMoneyTheme(false) {
                AppNav()
            }
        }
    }

    @Composable
    fun AppNav() {
        val navController = rememberNavController()
        val backStackEntry by navController.currentBackStackEntryAsState()
        backStackEntry?.destination?.route

        Scaffold(modifier = Modifier.fillMaxSize().background(PageBg)) { innerPadding ->
            NavHost(
                navController,
                startDestination = "home",
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                composable("home") {
                    //整个页面
                    MainScreen(
                        mViewModel, navController,  modifier = Modifier
                    )
                }
                composable("input", arguments = listOf(
                )) {
                    //记一笔
                    InputScreen(mViewModel, navController)
                }
            }

        }
    }
}
