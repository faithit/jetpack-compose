package com.faith.firstapplication.ui.theme.screens.Onboarding

import androidx.compose.foundation.Image
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
import androidx.compose.material.Button

import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.faith.firstapplication.data.onboardingItems
import com.faith.firstapplication.navigation.ROUTE_LOGIN
import com.faith.firstapplication.navigation.ROUTE_ONBOARDING
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(navController: NavHostController) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ✅ Show Skip if not last page
        if (pagerState.currentPage != onboardingItems.lastIndex) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = {
                        navController.navigate(ROUTE_LOGIN) {
                            popUpTo(ROUTE_ONBOARDING) { inclusive = true }
                        }
                    }
                ) {
                    Text("Skip")
                }
            }
        } else {
            Spacer(modifier = Modifier.height(48.dp))
        }

        // ✅ Slides
        HorizontalPager(
            count = onboardingItems.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            val item = onboardingItems[page]
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = item.title, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = item.description, style = MaterialTheme.typography.bodyLarge)
            }
        }

        Spacer(modifier = Modifier.height(24.dp)) // ✅ add some breathing space above indicators

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // ✅ Next / Get Started (moved a bit higher)
        Button(
            onClick = {
                if (pagerState.currentPage == onboardingItems.lastIndex) {
                    navController.navigate(ROUTE_LOGIN) {
                        popUpTo(ROUTE_ONBOARDING) { inclusive = true }
                    }
                } else {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp) // narrower than full width
        ) {
            Text(
                text = if (pagerState.currentPage == onboardingItems.lastIndex) "Get Started" else "Next"
            )
        }

        Spacer(modifier = Modifier.height(60.dp)) // ✅ keeps button above very bottom
    }
}
