package app.allever.android.compose.flow.money.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import app.allever.android.compose.flow.money.MainViewModel


@Composable
fun InputScreen(viewModel: MainViewModel, navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "InputScreen", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
    }
}
