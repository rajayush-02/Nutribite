package com.example.nutribite.presentation.signup

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Button
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Surface
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextField
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nutribite.presentation.util.Screen


@Composable
fun Signup (navController: NavController){
    var scrollstate : ScrollState = rememberScrollState()
    Surface(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollstate)) {
        Box (modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(text = "Enter your Details",
                    fontSize = 24.sp)
                Surface(modifier = Modifier
                    .size(350.dp)
                    .padding(20.dp),
                    shape = RoundedCornerShape(40.dp)) {
                    Column(horizontalAlignment =Alignment.CenterHorizontally) {
                        RegisterNameField()
                        Spacer(modifier = Modifier.height(16.dp))
                        RegisterEmailField()
                        Spacer(modifier = Modifier.height(16.dp))
                        PasswordFieldR()
                        Spacer(modifier = Modifier.height(16.dp))
                        RegisterButtonR(navController = navController)
                        Spacer(modifier = Modifier.height(16.dp))
                        GetBackButtonR(navController = navController)
                    }
                }
            }
        }

    }
}


//Register Screen Composables
@Composable
fun RegisterNameField(){
    var name by remember { mutableStateOf("") }
    TextField(
        value = name,
        onValueChange = { name = it },
        modifier = Modifier.width(280.dp),
        placeholder = {
            Text(
                text = "Enter name",
                modifier = Modifier.alpha(0.5f)
            )
        },
        shape = RoundedCornerShape(9.dp),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        singleLine = true
    )
}

@Composable
fun RegisterEmailField(){
    var emailid by remember { mutableStateOf("") }
    TextField(
        value = emailid,
        onValueChange = { emailid = it },
        modifier = Modifier.width(280.dp),
        placeholder = {
            Text(
                text = "Enter email",
                modifier = Modifier.alpha(0.5f)
            )
        },
        shape = RoundedCornerShape(9.dp),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        singleLine = true
    )
}

@Composable
fun GetBackButtonR(navController: NavController){
    Button(onClick = { navController.navigate(route = Screen.LoginScreen.route) },
        modifier = Modifier.width(200.dp)
    ) {
        Text(text = "Get Back")
    }

}

@Composable
fun RegisterButtonR(navController: NavController){
    Button(onClick =  { navController.navigate(route = Screen.SignupDetails.route) },
        modifier = Modifier.width(200.dp)
    ) {
        Text(text = "Register")
    }
}

@Composable
fun PasswordFieldR(){
    var password by rememberSaveable { mutableStateOf("") }
    TextField(
        value = password,
        onValueChange = { password = it },
        modifier = Modifier.width(280.dp),
        placeholder = {
            Text(
                text = "set password",
                Modifier.alpha(0.5f)
            )
        },
        visualTransformation = PasswordVisualTransformation(),
        shape = RoundedCornerShape(9.dp),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        singleLine = true
    )
}



