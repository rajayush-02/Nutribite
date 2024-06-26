

package com.example.nutribite.presentation.details

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.nutribite.R
import com.example.nutribite.domain.model.MenuItem
import com.example.nutribite.domain.model.Restaurant
import com.example.nutribite.presentation.components.getCustomerInfo
import com.example.nutribite.presentation.components.getTimeInMins
import com.example.nutribite.presentation.util.Screen

@Composable
fun RestaurantDetail(
    navController: NavHostController,
    viewModel: RestaurantDetailViewModel = hiltViewModel()
) {

    val detailScreenState by viewModel.detailScreenState


    val rotationState by animateFloatAsState(
        targetValue = if (detailScreenState.recommendedExpandedState) 180f else 0f
    )

    val rotationStateNonVeg by animateFloatAsState(
        targetValue = if (detailScreenState.nonVegExpandedState) 180f else 0f
    )

    val rotationStateVeg by animateFloatAsState(
        targetValue = if (detailScreenState.vegExpandedState) 180f else 0f
    )


    Box(contentAlignment = Alignment.BottomEnd) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = stringResource(R.string.favourite)
                        )
                    }
                    Row {
                        IconToggleButton(
                            checked = detailScreenState.isLiked,
                            onCheckedChange = {
                                viewModel.onEvent(DetailScreenEvent.ToggleLikedStatus)
                            },
                        ) {
                            if (detailScreenState.isLiked) {
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = stringResource(R.string.favourite),
                                    tint = Color.Red
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.FavoriteBorder,
                                    contentDescription = stringResource(R.string.favourite)
                                )
                            }

                        }
                        IconButton(onClick = {

                        }) {
                            Icon(
                                imageVector = Icons.Outlined.Share,
                                contentDescription = stringResource(
                                    R.string.share
                                )
                            )
                        }

                    }
                }
            }
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    RestaurantDetailCard(
                        detailScreenState.restaurant!!
                    )

                }
            }
            val recommendedList =
                detailScreenState.recommendedList




            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp, 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center

                ) {
                    Text(
                        modifier = Modifier
                            .weight(6f),
                        text = stringResource(R.string.recommended),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    IconButton(
                        modifier = Modifier
                            .weight(1f)
                            .alpha(ContentAlpha.medium)
                            .rotate(rotationState),
                        onClick = {
                            viewModel.onEvent(DetailScreenEvent.ToggleRecommendedSectionExpandedState)
                        }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = stringResource(R.string.drop_down_arrow)
                        )
                    }

                }

            }
            if (detailScreenState.recommendedExpandedState) {

                items(recommendedList.size) {
                    MenuItemCard(
                        menuItem = recommendedList[it].menuItem,


                        )
                    if (it != (recommendedList.size - 1)) {
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp, 0.dp)
                        )
                    }
                }


            }

            val nonVegList = detailScreenState.menuList.filter {
                !it.menuItem.isVegetarian
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp, 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center

                ) {
                    Text(
                        modifier = Modifier
                            .weight(6f),
                        text = stringResource(R.string.non_vegetarian),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    IconButton(
                        modifier = Modifier
                            .weight(1f)
                            .alpha(ContentAlpha.medium)
                            .rotate(rotationStateNonVeg),
                        onClick = {
                            viewModel.onEvent(DetailScreenEvent.ToggleNonVegSectionExpandedState)
                        }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = stringResource(R.string.drop_down_arrow)
                        )
                    }

                }

            }
            if (detailScreenState.nonVegExpandedState) {

                items(nonVegList.size) {
                    MenuItemCard(
                        menuItem = nonVegList[it].menuItem,
                    )
                    if (it != (nonVegList.size - 1)) {
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp, 0.dp)
                        )
                    }
                }
            }

            val vegList = detailScreenState.menuList.filter {
                it.menuItem.isVegetarian
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp, 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center

                ) {
                    Text(
                        modifier = Modifier
                            .weight(6f),
                        text = stringResource(R.string.vegetarian),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    IconButton(
                        modifier = Modifier
                            .weight(1f)
                            .alpha(ContentAlpha.medium)
                            .rotate(rotationStateVeg),
                        onClick = {
                            viewModel.onEvent(DetailScreenEvent.ToggleVegSectionExpandedState)
                        }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = stringResource(R.string.drop_down_arrow)
                        )
                    }

                }

            }
            if (detailScreenState.vegExpandedState) {

                items(vegList.size) {
                    MenuItemCard(
                        menuItem = vegList[it].menuItem,
                    )
                    if (it != (vegList.size - 1)) {
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp, 0.dp)
                        )
                    }
                }


            }


        }

        if (detailScreenState.menuList.sumOf { it.noOfItems } != 0) {
            Column(modifier = Modifier.padding(16.dp)) {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Screen.Cart.route)

                    },
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(Icons.Outlined.ShoppingCart, stringResource(R.string.cart))
                }
            }
        }
    }
}


@Composable
fun MenuItemCard(
    menuItem: MenuItem,

    ) {
    var state by remember { mutableStateOf(0) }
    Row(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {


        Column(
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (menuItem.isVegetarian) {
                    Image(
                        modifier = Modifier.size(18.dp),
                        painter = painterResource(id = R.drawable.ic_veg),
                        contentDescription = stringResource(R.string.vegetarian)
                    )
                } else {
                    Image(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.ic_non_veg),
                        contentDescription = stringResource(R.string.non_vegetarian)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = menuItem.dish)
            }

            Text(text = "  Rs  ${menuItem.price}", overflow = TextOverflow.Ellipsis)
            Row {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = stringResource(id = R.string.rating),
                    tint = Color(0xFFFF7A00)
                )
                Text(text = menuItem.rating.toString())
                Text(
                    text = " · ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "${menuItem.noOfRatings} ratings",
                )
            }
        }

        if (
            state == 0
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Row {
                    Text(
                        text = stringResource(R.string.add),
                        modifier = Modifier.clickable {
                            state++

                        },
                        color = MaterialTheme.colors.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
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
                                    state--
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
        }
    }
}


@Composable
fun RestaurantDetailCard(
    restaurant: Restaurant
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Color(0xFFC6FDB3)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
        ) {
            Text(
                text = restaurant.name,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = stringResource(R.string.rating),
                    modifier = Modifier
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.width(2.dp))

                Text(text = "${restaurant.rating}(${getCustomerInfo(restaurant.noOfRatings)}) ")
                Text(
                    text = "·",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(text = " ${getTimeInMins(restaurant.timeInMillis)} ")
                Text(
                    text = "·",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(text = " $${restaurant.averagePrice} ")

            }
            Text(
                text = restaurant.variety,
                modifier = Modifier.alpha(0.5f),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = restaurant.place,
                modifier = Modifier.alpha(0.5f),
            )

            Row(
                modifier = Modifier.padding(0.dp, 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.size(50.dp),
                    painter = painterResource(id = R.drawable.outlet_card),
                    contentDescription = stringResource(R.string.outlet),
                )
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Text(
                            text = stringResource(R.string.outlet_2),
                            fontSize = 16.sp
                        )
                    }
                    Row {
                        Text(text = getTimeInMins(restaurant.timeInMillis))
                    }
                }
                Spacer(modifier = Modifier.width(24.dp))
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start
                ) {
                    Row {
                        Text(
                            text = restaurant.place, modifier = Modifier.alpha(0.5f),
                        )
                    }
                    Row {
                        Text(
                            text = stringResource(R.string.your_location),
                            modifier = Modifier.alpha(0.5f),
                        )
                    }
                }
            }
        }
    }
}
