package com.gunder.cafe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gunder.cafe.model.*
import com.gunder.cafe.ui.components.CategoryItem
import com.gunder.cafe.ui.components.HomeSection
import com.gunder.cafe.ui.components.MenuItem
import com.gunder.cafe.ui.components.SearchBar
import com.gunder.cafe.ui.theme.CafeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CafeTheme {
                // A surface container using the 'background' color from the theme
                CafeApp()
            }
        }
    }
}


@Composable
fun CafeApp(modifier: Modifier = Modifier) {
    Scaffold(bottomBar = { BottomBar() }) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(it)
        ) {
            Banner()
//        slot based layout
            HomeSection(
                title = stringResource(R.string.section_category),
                content = { CategoryRaw() })
            HomeSection(
                title = stringResource(R.string.section_favorite_menu),
                content = { MenuRow(dummyMenu) })
            HomeSection(title = stringResource(R.string.section_best_seller_menu),
                content = {
                    MenuRow(dummyBestSeller)
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CafeAppDefaultPreview() {
    CafeTheme {
        CafeApp()
    }
}

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    BottomNavigation(modifier = modifier) {
        val navItem = listOf(
            BottomBarItem(title = stringResource(R.string.menu_home), icon = Icons.Default.Home),
            BottomBarItem(
                title = stringResource(R.string.menu_favorite),
                icon = Icons.Default.Favorite
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle
            ),
        )
        navItem.map {
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.title
                    )
                },
                selected = it.title == navItem[0].title,
                onClick = {},
                label = { Text(it.title) }
            )
        }
    }
}

@Composable
fun Banner(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(160.dp)
        )
        SearchBar()
    }
}

@Composable
fun CategoryRaw(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(dummyCategory, key = { it.textCategory }) { category ->
            CategoryItem(category)
        }
    }
}

@Composable
fun MenuRow(listMenu: List<Menu>, modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listMenu, key = { it.title }) { menu ->
            MenuItem(menu)
        }
    }
}
