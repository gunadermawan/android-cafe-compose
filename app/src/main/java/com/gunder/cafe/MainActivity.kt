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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gunder.cafe.model.Menu
import com.gunder.cafe.model.dummyBestSeller
import com.gunder.cafe.model.dummyCategory
import com.gunder.cafe.model.dummyMenu
import com.gunder.cafe.ui.components.*
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
@Preview(showBackground = true)
fun CategoryRowPreview() {
    CategoryRaw()
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

@Composable
fun CafeApp() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
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
//        SectionText(stringResource(R.string.section_category))
//        CategoryRaw()
//        SectionText(stringResource(R.string.section_favorite_menu))
//        MenuRow(dummyMenu)
//        SectionText(stringResource(R.string.section_best_seller_menu))
//        MenuRow(listMenu = dummyBestSeller)
    }
}

@Preview(showBackground = true)
@Composable
fun CafeAppDefaultPreview() {
    CafeTheme {
        CafeApp()
    }
}