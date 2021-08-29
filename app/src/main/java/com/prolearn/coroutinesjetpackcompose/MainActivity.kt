package com.prolearn.coroutinesjetpackcompose

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.prolearn.coroutinesjetpackcompose.ui.theme.CoroutinesjetpackComposeTheme
import com.prolearn.coroutinesjetpackcompose.viewmodel.AppViewModel

//class MainActivity : ComponentActivity() {
class MainActivity : AppCompatActivity() {
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

@SuppressLint("UnrememberedMutableState")
@Composable
fun Greeting(name: String) {
//    val appViewModel:AppViewModel = viewModel();
//    val footballdata = appViewModel.footballState.value;

    var showhide =  mutableStateOf("");
    Column() {
//        Text(text = "Hello $name!")
//        Text(text = "name ${footballdata.name}")
//        Text(text = "description ${footballdata.description}")


        var imageUri = remember {
            mutableStateOf<Uri?>(null)
        };
        var launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()){
            imageUri.value = it
        }
        Button(onClick = {
            Log.d("appdevelop","buttonclicked")
            launcher.launch("image/*")
        }) {
            Text(text = "Pick")
        }
        Text(text = "image uri ${imageUri.value}")

        if(imageUri.value !=null){
//            Image(
//                painter = painterResource(id = imageUri.value),
//                contentDescription = "content desc"

            Image(
                painter = rememberImagePainter(
                    data = imageUri.value,
                    builder = {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }
                ),
                modifier = Modifier.size(72.dp),
                contentDescription = "content desc"
            )
        }
        Button(onClick = {
            Log.d("appdevelop","upload image")

        }) {
            Text(text = "Upload Image")
        }

    }

}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoroutinesjetpackComposeTheme {
        Greeting("Android")
    }
}

