package com.example.nutribite.presentation.cart


import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.nutribite.R
import com.example.nutribite.data.data_source.menu2
import com.example.nutribite.domain.model.CartItem
import com.example.nutribite.domain.model.Restaurant
import com.example.nutribite.presentation.components.getTimeInMins
import com.google.accompanist.flowlayout.FlowColumn
import kotlin.math.round

@Composable
fun Cart(
    navController: NavHostController,
    viewModel: CartViewModel = hiltViewModel()
) {
    val list by viewModel.cartState


    val context = LocalContext.current as Activity

    context.window.statusBarColor = Color.Gray.toArgb()
    context.window.navigationBarColor = Color.White.toArgb()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFE8E7E7)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(imageVector = Icons.Outlined.Close, contentDescription = stringResource(id = R.string.back))
                }
            }
        }

        item {
            ItemSection(
                list = list.list.filter {
                    it.noOfItems > 0
                },
                onDecreaseClick = { },
                onIncreaseClick = { },
            )
        }

        item {
            CouponBar()
        }
        item {
            DeliverySection(
                Restaurant(
                    name = "Relish",
                    rating = 3.9,
                    noOfRatings = 258,
                    timeInMillis = 1800000,
                    variety = "American, French",
                    place = "Misamari",
                    averagePrice = 1.0,
                    image = R.drawable.pizza,
                    menu = menu2
                )
            )
        }
        item {
            BillSection(
                itemTotal = 6.09
            )
        }
    }
}

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

@Composable
fun BillSection(
    itemTotal: Double
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Card(
            shape = RoundedCornerShape(24.dp),
            elevation = 16.dp
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Bill  Details", fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column {
                        Text(text = "Item total:")
                        Text(text = "Taxes and charges:")
                        Text(text = "Total:")
                    }
                    Column {
                        Text(text = "Rs$itemTotal")
                        Text(text = "Rs${((0.18) * itemTotal).round(2)}")
                        Text(text = "Rs${(itemTotal + ((0.18) * itemTotal).round(2)).round(2)}")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Proceed to Pay")
                    }
                }

            }
        }
    }
}

@Composable
fun DeliverySection(
    restaurant: Restaurant
) {
    Column(modifier = Modifier.padding(16.dp)) {


        Card(
            shape = RoundedCornerShape(24.dp),
            elevation = 16.dp

        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Deliver to:", fontWeight = FontWeight.Bold)
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = "Back",
                            modifier = Modifier.alpha(0.5f),
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column {
                        Text(text = "Location:")
                        Text(text = "Estimated time:")
                    }
                    Column {
                        Text(text = "Bhopal")
                        Text(text = getTimeInMins(restaurant.timeInMillis))
                    }


                }

            }
        }
    }
}

@Composable
fun ItemSection(
    list: List<CartItem>,
    onIncreaseClick: (cartItem: CartItem) -> Unit,
    onDecreaseClick: (cartItem: CartItem) -> Unit

) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        Card(
            shape = RoundedCornerShape(24.dp),
            elevation = 16.dp
        ) {
            Column(Modifier.padding(16.dp)) {
                if (list.isNotEmpty()) {
                    FlowColumn(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        list.forEach {

                            CartItemCard(
                                cartItem = it
                            )
                        }

                    }
                } else {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                    ) {
                        Text(text = "Empty")
                    }
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 8.dp)
                )

                val inputValue = remember { mutableStateOf("") }
                val hintState = remember {
                    mutableStateOf(true)
                }

                Box {
                    BasicTextField(
                        value = inputValue.value,
                        onValueChange = { inputValue.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                            .onFocusChanged {
                                if (inputValue.value == "") {
                                    hintState.value = !it.isFocused
                                }
                            }
                    )

                    if (hintState.value) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Write instruction for restaurant...",
                                modifier = Modifier.alpha(0.5f),
                            )
                            Icon(
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = "Back",
                                modifier = Modifier.alpha(0.5f),
                            )

                        }
                    }
                }
            }
        }

    }
}


@Composable
fun CartItemCard(
    cartItem: CartItem
) {
    var state by remember { mutableStateOf(cartItem.noOfItems) }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {


        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (cartItem.menuItem.isVegetarian) {
                Image(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.ic_veg),
                    contentDescription = "Vegetarian"
                )
            } else {
                Image(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_non_veg),
                    contentDescription = "Non-Vegetarian"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = cartItem.menuItem.dish)

        }


        Column(

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier
                    .border(
                        BorderStroke(1.dp, Color.Black.copy(0.5f)),
                        MaterialTheme.shapes.medium
                    ),
                shape = MaterialTheme.shapes.medium,
                color = Color.White,
                contentColor = MaterialTheme.colors.primary
            ) {
                Row(
                    modifier = Modifier.padding(4.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = stringResource(R.string.subtract),
                        modifier = Modifier
                            .padding(3.dp, 0.dp)
                            .size(20.dp)
                            .clickable {
                                if (state != 0) {
                                    state--
                                }
                            }
                    )

                    Text(text = state.toString())

                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(R.string.add),
                        modifier = Modifier
                            .padding(3.dp, 0.dp)
                            .size(20.dp)
                            .clickable {
                                state++
                            }
                    )


                }

            }
        }


        Text(text = "  Rs  ${cartItem.menuItem.price}", overflow = TextOverflow.Ellipsis)


    }
}

@Composable
fun CouponBar() {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Box(
            contentAlignment = Alignment.CenterEnd,

            ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                placeholder = {
                    Text(
                        text = "Add a coupon code...",
                        modifier = Modifier.alpha(0.5f)
                    )
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 16.dp, shape = CircleShape),

                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    cursorColor = Color.White,

                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                singleLine = true
            )
            Row {
                Text(
                    text = "APPLY",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { },
                    color = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}



