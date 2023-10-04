package com.example.imagepicker

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableStateOf
import  androidx.compose.runtime.setValue
import  androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.imagepicker.ui.theme.ImagepickerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImagepickerTheme {
                // A surface container using the 'background' color from the theme
                MultiplePhotoPickerScreen()
            }
        }
    }
}

@Composable
fun MultiplePhotoPickerScreen(){


    var selectedImageUris by remember {
        mutableStateOf<List<Uri?>>(emptyList())
    }

    val multiplePhotoPickerLauncher= rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(
            maxItems = 10
        ),
        onResult = {
            selectedImageUris=it
        })


    Surface (modifier = Modifier.fillMaxSize()){

        Column  (modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        ){
            Text(
                text ="Choose a Photo",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color= Color(0xFF6650a4)

                )
            )
            Spacer(modifier = Modifier.size(20.dp))

            Button( modifier= Modifier
                .fillMaxWidth()
                .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6650a4)
                ),
                onClick = {
                    multiplePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },) {

                Row (modifier=Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically){
                    Icon(modifier= Modifier
                        .padding(10.dp)
                        .size(20.dp),
                        painter = painterResource(id = R.drawable.icons8_add_30),
                        contentDescription =""
                    )
                    Text(text = "Pick  Photos",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium

                        )

                    )
                }

            }
            Spacer(modifier = Modifier.size(13.dp))
           LazyColumn{
               items(selectedImageUris){selectedImageUris->
                   AsyncImage( modifier= Modifier
                       .fillMaxWidth()
                       .height(240.dp)
                       .clip(RoundedCornerShape(12.dp))
                       .padding(4.dp),
                       model =selectedImageUris ,
                       contentDescription = null,
                       contentScale = ContentScale.FillBounds

                   )
               }
           }

        }
    }
}

@Preview
@Composable
fun MultiplePhotoPickerScreenPreview(){
    MultiplePhotoPickerScreen()
}