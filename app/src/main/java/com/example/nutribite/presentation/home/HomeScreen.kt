package com.example.nutribite.presentation.home

import android.app.Activity
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.nutribite.R
import com.example.nutribite.domain.model.FoodItem
import com.example.nutribite.domain.model.Restaurant
import com.example.nutribite.presentation.components.RestaurantCard
import com.example.nutribite.presentation.components.SearchBar
import com.example.nutribite.presentation.home.components.ChipBar
import com.example.nutribite.presentation.util.Screen
import com.example.nutribite.ui.theme.LightBlue
import java.util.Calendar


@Composable
fun Home(
    scrollState: LazyListState,
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current as Activity

    context.window.statusBarColor = Color.Gray.toArgb()
    context.window.navigationBarColor = Color.White.toArgb()

    val homeScreenState by viewModel.homeScreenState


    LazyColumn(
        modifier =
        Modifier
            .fillMaxWidth(),
        state = scrollState,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            TopSection(navController = navController)
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            GreetingSection()
            Spacer(modifier = Modifier.height(24.dp))
        }
        item {
            Column(
                modifier = Modifier.padding(8.dp, 0.dp)
            ) {
                SearchBar()
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
//        item {
//            AdSection(homeScreenState.adsList)
//            Spacer(modifier = Modifier.height(16.dp))
//        }
        item {
            Card(
                modifier = Modifier.height(100.dp).width(410.dp),
                shape = RoundedCornerShape(5.dp),
                backgroundColor = LightBlue.copy(alpha = 0.6f)
            ) {
                    Column(modifier = Modifier.alpha(0.5f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Recommendations",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Get personalised recommendations>>>",
                            fontWeight = FontWeight.Light,
                            modifier = Modifier
                                .clickable { navController.navigate(Uri.parse("https://ishanpeshkar.github.io/NutrIBite")) }
                        )
                    }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }


        item {
            RecommendedSection(homeScreenState.foodList)
            Spacer(modifier = Modifier.height(16.dp))
        }
        if (homeScreenState.likedRestaurantList.isNotEmpty()) {
            item {
                FavouriteSection(homeScreenState.likedRestaurantList) {
                    viewModel.onEvent(HomeScreenEvent.SelectRestaurant(it) {
                        navController.navigate(Screen.RestaurantDetails.route)
                    })
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
            ChipBar()
        }
        item {
            MainSection()
        }
        items(homeScreenState.restaurantList.size) {
            RestaurantCard(
                restaurant = homeScreenState.restaurantList[it],
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        viewModel.onEvent(HomeScreenEvent.SelectRestaurant(homeScreenState.restaurantList[it]) {
                            navController.navigate(Screen.RestaurantDetails.route)
                        })
                    }
            )
        }
        item {
            ThankYouSection()
        }

    }

}




@Composable
fun MainSection() {
    Column(modifier = Modifier.padding(8.dp, 0.dp))
    {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.all_around_you),
            fontSize = 20.sp,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.height(8.dp))

    }
}


@Composable
fun FavouriteSection(
    list: List<Restaurant>,
    onClick: (restaurant: Restaurant) -> Unit
) {
    Column(modifier = Modifier.padding(8.dp, 0.dp))
    {
        Text(
            text = stringResource(R.string.order_from_favourites),
            fontSize = 20.sp,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.height(8.dp))

    }

    LazyRow(
        contentPadding = PaddingValues(
            start = 8.dp,
            end = 8.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(list.size) {
            FavouriteCard(restaurant = list[it], modifier = Modifier.clickable {
                onClick(list[it])
            })
        }


    }
}

@Composable
fun FavouriteCard(
    restaurant: Restaurant,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = restaurant.image), contentDescription = stringResource(R.string.restaurant),
            modifier = Modifier
                .size(100.dp, 130.dp)
                .shadow(elevation = 0.dp, shape = RoundedCornerShape(8.dp), clip = true),
            contentScale = ContentScale.Crop
        )
        Text(text = restaurant.name)

    }
}

@Composable
fun RecommendedSection(list: List<FoodItem>) {
    Column(modifier = Modifier.padding(8.dp, 0.dp))
    {
        Text(
            text = stringResource(R.string.recommended_for_you),
            fontSize = 20.sp,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
    LazyRow(
        contentPadding = PaddingValues(
            start = 8.dp,
            end = 8.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(list.size) {
            RecommendedCard(foodItem = list[it])
        }
    }
}

@Composable
fun RecommendedCard(
    foodItem: FoodItem
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = foodItem.image), contentDescription = stringResource(R.string.restaurant),
            modifier = Modifier
                .size(80.dp)
                .shadow(elevation = 0.dp, shape = CircleShape, clip = true),
            contentScale = ContentScale.Crop
        )
        Text(text = foodItem.name)
    }
}


@Composable
fun TopSection(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .clickable {
                    navController.navigate(Screen.Profile.route)
                },
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = stringResource(id = R.string.display_picture)
        )


        Row(modifier = Modifier.clickable { }) {
            Row {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = stringResource(R.string.location),

                    )
                Text(text = "Bhopal")
            }
        }

    }

}

@Composable
fun GreetingSection(
    userName: String = "Ayush"
) {
    val c: Calendar = Calendar.getInstance()
    val timeOfDay: Int = c.get(Calendar.HOUR_OF_DAY)
    Column(modifier = Modifier.padding(16.dp, 0.dp)) {
        Text(
            text = when (timeOfDay) {
                in 0..11 -> {
                    "Good Morning!"
                }
                in 12..15 -> {
                    "Good Afternoon!"
                }
                else -> {
                    "Good Evening!"
                }
            },
            fontSize = 28.sp,
            fontWeight = FontWeight.Light
        )
        Text(
            text = userName,
            fontSize = 28.sp,
            fontWeight = FontWeight.Light
        )
    }
}


//@Composable
//fun AdSection(
//    adsList: List<Advertisement>
//) {
//    Column()
//    {
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        LazyRow(
//            contentPadding = PaddingValues(
//                start = 8.dp,
//                end = 8.dp
//            ),
//            horizontalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            items(adsList.size) {
//                AdCard(
//                    adsList[it]
//                )
//            }
//        }
//    }
//}


//@Composable
//fun AdCard(
//    ad: Advertisement
//) {
//    Card(
//        shape = RoundedCornerShape(24.dp),
//        backgroundColor = ad.color
//    ) {
//        Row(
//            modifier = Modifier
//                .padding(16.dp)
//                .size(350.dp, 140.dp),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Column(modifier = Modifier.weight(0.5f)) {
//                Text(
//                    text = ad.title,
//                    fontSize = 24.sp,
//                    fontWeight = FontWeight.Bold,
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Text(
//                    text = ad.subTitle,
//                    fontWeight = FontWeight.Light
//                )
//            }
//            Image(
//                painter = painterResource(id = ad.image),
//                contentDescription = stringResource(R.string.ad),
//                modifier = Modifier
//                    .size(50.dp)
//                    .weight(0.5f)
//            )
//        }
//
//    }
//
//}

@Composable
fun ThankYouSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.with),
            modifier = Modifier.alpha(0.5f),
            fontFamily = FontFamily.Cursive,
            fontSize = 24.sp
        )
        Icon(
            imageVector = Icons.Filled.Favorite, contentDescription = stringResource(R.string.love),
            tint = Color.Red,
            modifier = Modifier.size(32.dp)
        )
        Text(
            text = stringResource(R.string.from_creator),
            modifier = Modifier.alpha(0.5f),
            fontFamily = FontFamily.Cursive,
            fontSize = 24.sp
        )
        Text(
            text = " , VIT Bhopal",
            modifier = Modifier.alpha(0.5f),
            fontFamily = FontFamily.Cursive,
            fontSize = 24.sp
        )
    }
}

