package com.example.lab4final

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab4final.ui.theme.Lab4FinalTheme
import coil.compose.rememberImagePainter

import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.painter.Painter


data class ItemData(var obj: String, var url: String)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab4FinalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldsAndButtons() {

    val contentList = remember { mutableStateListOf<ItemData>() }
    var obj by remember { mutableStateOf("") }
    var url by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text("Bienvenido, ingresa un nombre de un objeto y una imagen en formato URL del objeto ")
        TextField(value = obj, onValueChange = { obj = it }, label = { Text("Nombre del objeto: ") })
        TextField(value = url, onValueChange = { url = it }, label = { Text("URL: ") })
        Button(onClick = {
            contentList.add(ItemData(obj, url))
            obj = ""
            url = ""
        }) {
            Text("Agregar")
        }
        LazyColumn {
            itemsIndexed(contentList) { index, item -> SimpleCard(item.url)
                card(item)}
        }
    }
}

@Composable
fun card(item: ItemData){
    Row(){
        Text(item.obj)
    }
}
@Composable
fun SimpleCard(imageUrl: String) {
    val painter = rememberImagePainter(data = imageUrl)

    Column {
        Image(
            painter = painter,
            contentDescription = null, // Set to null if the image doesn't need a content description
            modifier = Modifier
                .size(100.dp) // Adjust the size as needed
                .clip(MaterialTheme.shapes.medium) // Apply clipping if necessary
        )
    }
}





@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab4FinalTheme {

    }
}