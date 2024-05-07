
package com.example.nutribite.presentation.history

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.nutribite.R
import com.example.nutribite.domain.model.Restaurant
import com.example.nutribite.presentation.components.RestaurantCard
import com.example.nutribite.presentation.components.SearchBar
import com.example.nutribite.presentation.util.Screen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun History(
    viewModel: HistoryViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    val state by viewModel.likedRestaurants


    val context = LocalContext.current as Activity

    val pagerState = rememberPagerState()


    context.window.statusBarColor = Color.Gray.toArgb()
    context.window.navigationBarColor = Color.White.toArgb()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            SearchBar()
            Spacer(modifier = Modifier.height(8.dp))

            Tabs(pagerState = pagerState)
        }
        TabsContent(
            pagerState = pagerState, state.likedRestaurantList,
            onClick = {
                viewModel.onEvent(HistoryEvent.SelectRestaurant(it){
                    navHostController.navigate(Screen.RestaurantDetails.route)
                })
            }
        )

    }
}


@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    val list = listOf("History", "Favourites")
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier.padding(0.dp, 0.dp, 75.dp, 0.dp),
        backgroundColor = Color.White,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 0.dp,
                color = Color.White
            )
        },

        ) {
        list.forEachIndexed { index, _ ->
            Tab(
                text = {
                    Text(
                        text = list[index],
                        fontSize = 16.sp,
                        fontWeight = if (pagerState.currentPage == index) FontWeight.Bold else FontWeight.Light,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Left
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }


            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(
    pagerState: PagerState,
    list: List<Restaurant>,
    onClick: (restaurant: Restaurant) -> Unit
) {

    HorizontalPager(count = 2, state = pagerState) { page ->
        when (page) {
            0 -> HistorySection()
            1 -> FavouritesSection(list = list, onClick)
        }
    }

}

@Composable
fun FavouritesSection(
    list: List<Restaurant>,
    onClick: (restaurant: Restaurant) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)

    ) {
        if (list.isNotEmpty()) {
            LazyColumn {

                items(list.size) {
                    RestaurantCard(
                        restaurant = list[it],
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                onClick(list[it])
                            }
                    )
                }

            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Text(text = stringResource(R.string.empty))
            }
        }
    }
}

@Composable
fun HistorySection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            elevation = 16.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column() {
                        Text(text = "Fish n Rolls", fontWeight = FontWeight.Bold)
                        Text(
                            text = "Bhopal",
                            modifier = Modifier.alpha(0.5f),
                        )

                        Text(
                            text = "13 Aug 2022, 11:12 PM",
                            modifier = Modifier.alpha(0.5f),
                        )

                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                    ) {
                        Text(
                            text = "Rs 240",
                        )
                    }
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 8.dp),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(id = R.drawable.ic_non_veg),
                            contentDescription = "Non-Vegetarian"
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        Text(text = "Chinese Shawarma\nCombo (1)", maxLines = 2)
                    }
                    Row {
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Reorder")
                        }
                    }

                }

                Spacer(modifier = Modifier.height(8.dp))


                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Your rating for delivery",
                                modifier = Modifier.alpha(0.5f),
                            )
                            Text(
                                text = "Your rating for food",
                                modifier = Modifier.alpha(0.5f),
                            )

                        }

                        Column {
                            Row {
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = "Rating",
                                    tint = Color(0xFFFF7A00)
                                )
                                Text(text = "5")
                            }
                            Row {
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = "Rating",
                                    tint = Color(0xFFFF7A00)
                                )
                                Text(text = "5")
                            }
                        }

                    }




                    Spacer(modifier = Modifier.width(16.dp))


                    Row() {
                        Text(
                            text = "Delivered",
                            modifier = Modifier.alpha(0.5f),
                        )
                        Icon(
                            imageVector = Icons.Filled.Circle,
                            contentDescription = "Rating",
                            tint = MaterialTheme.colors.primary
                        )

                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            elevation = 16.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column() {
                        Text(text = "Fish n Rolls", fontWeight = FontWeight.Bold)
                        Text(
                            text = "Bhopal",
                            modifier = Modifier.alpha(0.5f),
                        )

                        Text(
                            text = "13 Aug 2022, 11:12 PM",
                            modifier = Modifier.alpha(0.5f),
                        )

                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                    ) {
                        Text(
                            text = "RS 240",
                        )
                    }
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 8.dp),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(id = R.drawable.ic_non_veg),
                            contentDescription = "Non-Vegetarian"
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        Text(text = "Chinese Shawarma\nCombo (1)", maxLines = 2)
                    }
                    Row {
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Reorder")
                        }
                    }

                }

                Spacer(modifier = Modifier.height(8.dp))


                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row {
                        Text(
                            text = "Rate Order",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.primary,
                            fontSize = 16.sp,
                            modifier = Modifier.clickable { }
                        )
                    }




                    Spacer(modifier = Modifier.width(16.dp))


                    Row() {
                        Text(
                            text = "Delivered",
                            modifier = Modifier.alpha(0.5f),
                        )
                        Icon(
                            imageVector = Icons.Filled.Circle,
                            contentDescription = "Rating",
                            tint = MaterialTheme.colors.primary
                        )

                    }
                }
            }

        }

    }
}
