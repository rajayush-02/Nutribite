package com.example.nutribite.presentation.signup

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material.Checkbox
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.CheckboxDefaults
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Surface
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nutribite.presentation.util.Screen
import com.example.nutribite.ui.theme.LightBlue


@Composable
fun SignupDetails(navController: NavController){
    var scrollstate :ScrollState = rememberScrollState()
    Surface (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(scrollstate)){

        Box (modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){

            Surface (modifier = Modifier.fillMaxSize(0.9f),
                shape = RoundedCornerShape(30.dp) ){

                Column(verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Enter the details below",
                        fontSize = 24.sp)

                    Row {
                        AgeField()
                        Spacer(modifier = Modifier.size(20.dp))
                        WeightField()
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    HeightField()
                    Spacer(modifier = Modifier.size(16.dp))
                    GoalField()
                    Spacer(modifier = Modifier.size(16.dp))
                    ConditionsField()
                    GetBackButton(navController = navController)
                    ProceedButton(navController = navController)
                }
            }
        }
    }
}

@Composable
fun AgeField(){
    var age by remember { mutableStateOf("") }
    TextField( value = age,
        onValueChange = {age=it},
        modifier = Modifier.width(110.dp),
        placeholder = {
            Text(
                text = "Age",
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
fun WeightField(){
    var weight by remember { mutableStateOf("") }
    TextField( value = weight,
        onValueChange = {weight=it},
        placeholder = {
            Text(
                text = "weight",
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
fun HeightField(){
    var height by remember { mutableStateOf("") }
    TextField( value = height,
        onValueChange = {height=it},
        placeholder = {
            Text(
                text = "height",
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
fun GoalField(){
    var goal by remember { mutableStateOf("") }
    val isChecked1 = remember { mutableStateOf(false) }
    val isChecked2 = remember { mutableStateOf(false) }
    Text(text = "Your Goals",
    )
    Row(modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top) {
        Checkbox(
                checked = isChecked1.value,
                onCheckedChange = { isChecked1.value = it },
                enabled = true,
                colors = CheckboxDefaults.colors(checkedColor = LightBlue, uncheckedColor = LightBlue)
        )
        Text(text = "Regular Fitness",
            modifier = Modifier.padding(top = 2.dp),
        )
        Checkbox(
            checked = isChecked2.value,
            onCheckedChange = { isChecked2.value = it },
            enabled = true,
            colors = CheckboxDefaults.colors(checkedColor = LightBlue, uncheckedColor = LightBlue)
        )
        Text(text = "Health issue",
            modifier = Modifier.padding(top = 2.dp),
        )
    }
}

@Composable
fun ConditionsField(){
    var conditions = mutableListOf(String)
   Text(text = "Medical Conditions",
       style = TextStyle(color = LightBlue, fontSize = 20.sp)
   )

  Row {
    val hasDiabetes = remember { mutableStateOf(false) }
    Checkbox(
        checked = hasDiabetes.value,
        onCheckedChange = { hasDiabetes.value = it },
        enabled = true,
        colors = CheckboxDefaults.colors(checkedColor = LightBlue, uncheckedColor = LightBlue)
    )
    Text(text = "Diabetes",
        modifier = Modifier.padding(top = 2.dp)
    )
      val hasDairyissue = remember { mutableStateOf(false) }
      Checkbox(
          checked = hasDairyissue.value,
          onCheckedChange = { hasDairyissue.value = it },
          enabled = true,
          colors = CheckboxDefaults.colors(checkedColor = LightBlue, uncheckedColor = LightBlue)
      )
      Text(text = "Dairy issue",
          modifier = Modifier.padding(top = 2.dp)
      )

      val hasBP = remember { mutableStateOf(false) }
      Checkbox(
          checked = hasBP.value,
          onCheckedChange = { hasBP.value = it },
          enabled = true,
          colors = CheckboxDefaults.colors(checkedColor = LightBlue, uncheckedColor = LightBlue)
      )
      Text(text = "BP",
          modifier = Modifier.padding(top = 2.dp)
      )
  }

    Row {
        val hasCholestrol = remember { mutableStateOf(false) }
        Checkbox(
            checked = hasCholestrol.value,
            onCheckedChange = { hasCholestrol.value = it },
            enabled = true,
            colors = CheckboxDefaults.colors(checkedColor = LightBlue, uncheckedColor = LightBlue)
        )
        Text(text = "Cholestrol",
            modifier = Modifier.padding(top = 2.dp)
        )
        val hasHypertension = remember { mutableStateOf(false) }
        Checkbox(
            checked = hasHypertension.value,
            onCheckedChange = { hasHypertension.value = it },
            enabled = true,
            colors = CheckboxDefaults.colors(checkedColor = LightBlue, uncheckedColor = LightBlue)
        )
        Text(text = "Hypertension",
            modifier = Modifier.padding(top = 2.dp)
        )

        val hasCeliac = remember { mutableStateOf(false) }
        Checkbox(
            checked = hasCeliac.value,
            onCheckedChange = { hasCeliac.value = it },
            enabled = true,
            colors = CheckboxDefaults.colors(checkedColor = LightBlue, uncheckedColor = LightBlue)
        )
        Text(text = "Celiac issues",
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

@Composable
fun GetBackButton(navController: NavController){
    Button(onClick = { navController.navigate(route = Screen.Signup.route)},
        modifier = Modifier.width(200.dp)
    ) {
        Text(text = "Get Back")
    }

}

@Composable
fun ProceedButton(navController: NavController){
    Button(onClick = { navController.navigate(route = Screen.LoginScreen.route) },
        modifier = Modifier.width(200.dp)
    ) {
        Text(text = "Proceed")
    }
}





