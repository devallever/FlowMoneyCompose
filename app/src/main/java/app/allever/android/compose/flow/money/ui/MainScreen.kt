package app.allever.android.compose.flow.money.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.allever.android.compose.flow.money.MainViewModel

@Composable
fun MainScreen(mainViewModel: MainViewModel, navController: NavController, modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        //top
        TopBar(mainViewModel, navController)
        //content
        Content(mainViewModel, navController, modifier = Modifier.weight(1f))
        //navigation
        Navigation(mainViewModel, navController)
    }
}

@Composable
fun TopBar(viewModel: MainViewModel, naviController: NavController) {
    Box(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
            .background(Color(0x1000ff00))
    )
}

@Composable
fun Content(viewModel: MainViewModel, navController: NavController, modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0x10ffff00))
    )
}

@Composable
fun Navigation(viewModel: MainViewModel, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(84.dp)
            .background(Color(0x100000ff))
    )
}
