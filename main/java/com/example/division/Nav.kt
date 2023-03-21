package com.example.division

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.division.ui.theme.ui.theme.DivisionTheme
import kotlinx.coroutines.launch

class Nav : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppMainScreen()
        }
    }
}

sealed class DrawerScreens(val icon: ImageVector, val title: String, val route: String) {
    object Home : DrawerScreens(Icons.Default.Home, "首頁", "home")
    object Account : DrawerScreens(Icons.Default.Info, "展覽館介紹", "account")
    object Help : DrawerScreens(Icons.Default.ShoppingCart, "售票區", "help")
    object TicketView : DrawerScreens(Icons.Default.List, "持有票券", "ticket")
}

private val screens = listOf(
    DrawerScreens.Home,
    DrawerScreens.Account,
    DrawerScreens.Help,
    DrawerScreens.TicketView
)

@Composable
fun AppMainScreen() {
    val navController = rememberNavController()
    val db = Room.databaseBuilder(
        LocalContext.current,
        Ticketdb::class.java,
        "db3"
    )
        .allowMainThreadQueries()
        .build()
    Surface(color = MaterialTheme.colors.background) {
        val drawerState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        val openDrawer = {
            scope.launch {
                drawerState.drawerState.open()
            }
        }
        Scaffold(
            scaffoldState = drawerState,
            drawerContent = {
                Drawer(
                    onDestinationClicked = { route ->
                        scope.launch {
                            drawerState.drawerState.close()
                        }
                        navController.navigate(route) {
                            launchSingleTop = true
                        }
                    }
                )
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = DrawerScreens.Home.route
            ) {
                composable(DrawerScreens.Home.route) {
                    Home(
                        openDrawer = {
                            openDrawer()
                        }
                    )
                }
                composable(DrawerScreens.Account.route) {
                    Account(
                        openDrawer = {
                            openDrawer()
                        }
                    )
                }
                composable(DrawerScreens.Help.route) {
                    Help(
                        openDrawer = {
                            openDrawer()
                        },
                        db.ticketdao()
                    )
                }
                composable(DrawerScreens.TicketView.route) {
                    TicketView(
                        openDrawer = {
                            openDrawer()
                        },
                        db.ticketdao()
                    )
                }
            }
        }
    }
}

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 36.dp)
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.logo1_new),
            contentDescription = "App icon",
        )
        Spacer(modifier = Modifier.height(30.dp))
        screens.forEach { screen ->
            Row {
                Icon(
                    imageVector = screen.icon,
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(Modifier.width(12.dp))
                Text(
                    text = screen.title,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.clickable {
                        onDestinationClicked(screen.route)
                    }
                )
            }
        }
    }
}


@Composable
fun TopBar(title: String = "", buttonIcon: ImageVector, onButtonClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            IconButton(onClick = { onButtonClicked() }) {
                Icon(buttonIcon, contentDescription = "")
            }
        },
        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}

@Composable
fun Home(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "首頁",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        DivisionTheme {
            homePage()
            Information()
        }
    }
}

@Composable
fun Account(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "南港展覽館相關介紹",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        Greeting()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Help(openDrawer: () -> Unit, ticketdao: Ticketdao) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "售票中心",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Ticket(ttname = "2023 台北國際自行車展", price = 5600, "跟著白子一起騎車去未知的地方", ticketdao)
                Ticket(ttname = "人生", price = 2500, "ㄟ國國農", ticketdao)
                Ticket(ttname = "AGA成人展", price = 200, "成人展找妹妹約", ticketdao)
                Ticket(ttname = "2023 台北國際自行車展", price = 5600, "跟著白子一起騎車去未知的地方", ticketdao)
                Ticket(ttname = "挖 牛逼到家了", price = 2500, "生鮮海底撈海牛肉", ticketdao)
                Ticket(ttname = "AGA成人展", price = 200, "成人展找妹妹約", ticketdao)
            }
        }
    }
}

@Composable
fun TicketView(openDrawer: () -> Unit, ticketdao: Ticketdao) {
    var ticketList = remember {
        mutableStateOf(ticketdao.readALL())
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        TopBar(
            buttonIcon = Icons.Filled.Menu,
            title = "已購票券",
            onButtonClicked = { openDrawer() }
        )
        LazyColumn() {
            items(ticketList.value) {
                Column() {
                    Card(
                        elevation = 3.dp,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        backgroundColor = Color.Transparent
                    ) {
                        Column() {
                            Row(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = it.tname,
                                    style = MaterialTheme.typography.h6,
                                    modifier = Modifier.weight(8f)
                                )
                                IconButton(onClick = {
                                    ticketdao.delete(it)
                                    ticketList.value = ticketdao.readALL()
                                }) {
                                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)

                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))
                            Row() {
                                Text(
                                    text = "姓名：" + it.name, modifier = Modifier
                                        .weight(5f)
                                        .padding(8.dp)
                                )
                                Text(
                                    text = "電話：" + it.phone.toString(), modifier = Modifier
                                        .weight(5f)
                                        .padding(8.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun Prieview() {
    AppMainScreen()
}