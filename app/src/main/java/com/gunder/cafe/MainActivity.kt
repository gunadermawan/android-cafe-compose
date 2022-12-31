package com.gunder.cafe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
fun CafeApp() {

}

@Preview(showBackground = true)
@Composable
fun CafeAppDefaultPreview() {
    CafeTheme {
        CafeApp()
    }
}