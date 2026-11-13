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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.allever.android.compose.flow.money.MainViewModel
import app.allever.android.compose.flow.money.R
import app.allever.android.compose.flow.money.lib.log
import app.allever.android.compose.flow.money.lib.toast
import app.allever.android.compose.flow.money.theme.GreenDeep
import coil.compose.AsyncImage

@Composable
fun MainScreen(mainViewModel: MainViewModel, navController: NavController, modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        //top
        TopBar(mainViewModel)
        //content
        Content(mainViewModel, navController, modifier = Modifier.weight(1f))
        //navigation
        Navigation(mainViewModel, navController)
    }
}

@Composable
fun TopBar(viewModel: MainViewModel) {
    val rememberIndex by viewModel.pageState
    val title = viewModel.navigationList[rememberIndex]
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = R.mipmap.ic_launcher,
                contentDescription = "logo",
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Column(modifier = Modifier.padding(start = 8.dp).weight(1f)) {
                //明细
                Text(
                    text = title,
                    fontSize = 18.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                //flowMoney
                Text(
                    text = "FlowMoney-随心记",
                    fontSize = 11.sp,
                    lineHeight = 12.sp,
                    modifier = Modifier
                )
            }

            AsyncImage(
                model = R.mipmap.ic_launcher,
                contentDescription = "logo",
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(90.dp))
            )
        }
    }
}

@Composable
fun Content(viewModel: MainViewModel, navController: NavController, modifier: Modifier) {
    var rememberIndex by viewModel.pageState
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        when (rememberIndex) {
            0 -> {
                log("Content: 明细")
                FlowListScreen(viewModel)
                //明细
            }

            1 -> {
                log("Content: 统计")
                StaticScreen(viewModel, navController)
                //统计
            }

            2 -> {
                log("Content: 资产")
                WealthScreen(viewModel, navController)
                //资产
            }

            3 -> {
                log("Content: 设置")
                SettingScreen(viewModel, navController)
                //设置
            }
        }
    }
}

@Composable
fun Navigation(viewModel: MainViewModel, navController: NavController) {
    var rememberIndex by viewModel.pageState
    Box(
        modifier = Modifier
            .height(84.dp)
    ) {
        //底部导航背景
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .align(Alignment.BottomEnd)
                .background(Color(0xffE6F2EA))
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            NavigationItem(
                R.drawable.fm_ic_default_icon,
                "明细",
                selected = rememberIndex == 0,
                modifier = Modifier
                    .weight(1f)
            ) {
                rememberIndex = 0
                toast("明细, index = ${rememberIndex}")
            }

            //统计
            NavigationItem(
                R.drawable.fm_ic_default_icon,
                "统计",
                selected = rememberIndex == 1,
                modifier = Modifier
                    .weight(1f)
            ) {
                rememberIndex = 1
                toast("统计, index = ${rememberIndex}")
            }

            //记一笔
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        navController.navigate("input")
                    },
                contentAlignment = Alignment.Center,
            ) {
                Column(modifier = Modifier) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(54.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(GreenDeep)
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
                selected = rememberIndex == 2,
                modifier = Modifier
                    .weight(1f)
            ) {
                rememberIndex = 2
                toast("资产, index = ${rememberIndex}")
            }
            //设置
            NavigationItem(
                R.drawable.fm_ic_default_icon,
                "设置",
                selected = rememberIndex == 3,
                modifier = Modifier
                    .weight(1f)
            ) {
                rememberIndex = 3
                toast("设置, index = ${rememberIndex}")
            }
        }
    }

}

@Composable
fun NavigationItem(
    icon: Int, label: String, selected: Boolean, modifier: Modifier, click: () -> Unit
) {
    log("NavigationItem: $label, $selected")
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
