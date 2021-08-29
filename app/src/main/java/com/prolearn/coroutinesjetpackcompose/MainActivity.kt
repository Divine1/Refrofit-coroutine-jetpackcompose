package com.prolearn.coroutinesjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.prolearn.coroutinesjetpackcompose.ui.theme.CoroutinesjetpackComposeTheme
import com.prolearn.coroutinesjetpackcompose.viewmodel.AppViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoroutinesjetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android--")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val appViewModel:AppViewModel = viewModel();
    val footballdata = appViewModel.footballState.value;
    Column() {
        Text(text = "Hello $name!")
        Text(text = "name ${footballdata.name}")
        Text(text = "description ${footballdata.description}")
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoroutinesjetpackComposeTheme {
        Greeting("Android")
    }
}