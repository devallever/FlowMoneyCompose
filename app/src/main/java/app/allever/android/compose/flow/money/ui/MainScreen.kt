package app.allever.android.compose.flow.money.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.allever.android.compose.flow.money.MainViewModel
import app.allever.android.compose.flow.money.R
import coil.compose.AsyncImage

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
    val rememberIndex = viewModel.pageState
    Box(
        modifier = Modifier
            .height(84.dp)
            .background(Color(0x100000ff))
    ) {
        //底部导航背景
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .align(Alignment.BottomEnd)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color(0x00ff00))
        ) {
            NavigationItem(
                R.drawable.fm_ic_default_icon,
                "明细",
                selected = rememberIndex.intValue == 0,
                modifier = Modifier.weight(1f)
            ) {
                Log.d("NavigationItem", "NavigationItem: 明细")
                rememberIndex.intValue = 0
                viewModel.pageState.intValue = 0
            }

            //统计
            NavigationItem(
                R.drawable.fm_ic_default_icon,
                "统计",
                selected = rememberIndex.intValue == 1,
                modifier = Modifier.weight(1f)
            ) {
                Log.d("NavigationItem", "NavigationItem: 统计")
                rememberIndex.intValue = 1
                viewModel.pageState.intValue = 1
            }

            //记一笔
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        Log.d("NavigationItem", "NavigationItem: 记一笔")
                    },
                contentAlignment = Alignment.Center,
            ) {
                Column(modifier = Modifier) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(54.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xffff00ff))
                    ) {
                        AsyncImage(
                            model = R.drawable.fm_ic_default_icon,
                            contentDescription = "记一笔",
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    //label
                    Text(
                        text = "记一笔",
                        fontSize = 13.sp,
                        lineHeight = 13.sp,
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
            //资产
            NavigationItem(
                R.drawable.fm_ic_default_icon,
                "资产",
                selected = rememberIndex.intValue == 2,
                modifier = Modifier.weight(1f)
            ) {
                rememberIndex.intValue = 2
                viewModel.pageState.intValue = 2
                Log.d("NavigationItem", "NavigationItem: 资产")
            }
            //设置
            viewModel.pageState.intValue = 3
            NavigationItem(
                R.drawable.fm_ic_default_icon,
                "设置",
                selected = rememberIndex.intValue == 3,
                modifier = Modifier.weight(1f)
            ) {
                rememberIndex.intValue = 3
                viewModel.pageState.intValue = 3
                Log.d("NavigationItem", "NavigationItem: 设置")
            }
        }
    }

}

@Composable
fun NavigationItem(
    icon: Int, label: String, selected: Boolean, modifier: Modifier, click: () -> Unit
) {
    val textColor = if (selected) Color.Black else Color.Gray
    val iconColor = if (selected) Color.Black else Color.Gray
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(top = 12.dp)
            .fillMaxHeight()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(10.dp))
                .clickable {
                    click.invoke()
                }) {
            //icon
            AsyncImage(
                model = icon,
                contentDescription = label,
                colorFilter = ColorFilter.tint(iconColor),
                modifier = Modifier
                    .size(22.dp)
                    .align(Alignment.CenterHorizontally)
            )
            //label
            Text(
                text = label,
                color = textColor,
                fontSize = 13.sp,
                lineHeight = 13.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
