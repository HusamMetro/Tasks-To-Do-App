package com.tuwaiq.husam.taskstodoapp.ui.screens.splash

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tuwaiq.husam.taskstodoapp.R
import com.tuwaiq.husam.taskstodoapp.ui.theme.DarkGray
import com.tuwaiq.husam.taskstodoapp.ui.theme.Purple700
import com.tuwaiq.husam.taskstodoapp.ui.theme.TasksToDoAppTheme
import com.tuwaiq.husam.taskstodoapp.util.Constants.SPLASH_SCREEN_DELAY
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToListScreen: () -> Unit
) {
    var startAnimation by remember { mutableStateOf(false) }
    val offSetState by animateDpAsState(
        targetValue = if (startAnimation) 0.dp else 100.dp,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    val colorState by animateColorAsState(
        targetValue = if (startAnimation) Purple700 else DarkGray,
        animationSpec = tween(
            durationMillis = 2000,1000
        )
    )
    val sizeState by animateSizeAsState(
        targetValue = if (startAnimation)
            Size(100f, 100f)
        else Size(0f, 0f),
        animationSpec = tween(
            durationMillis = 1500
        )
    )
    val alphaState by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    val rotateState by animateFloatAsState(
        targetValue = if (startAnimation) 0f else 360f,
        animationSpec = tween(
            durationMillis = 2000,1000
        )
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(SPLASH_SCREEN_DELAY)
        navigateToListScreen()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorState),
        contentAlignment = Alignment.Center
    ) {

        Image(
            modifier = Modifier
                .size(sizeState.width.dp, sizeState.height.dp)
                .offset(y = offSetState)
                .alpha(alpha = alphaState)
                .rotate(rotateState),
            painter = painterResource(id = getLogo()),
            contentDescription = stringResource(R.string.application_logo)
        )
    }
}

@Composable
fun getLogo(): Int {
    return if (isSystemInDarkTheme()) {
        R.drawable.ic_logo_dark
    } else {
        R.drawable.ic_logo_light
    }

}

@Composable
@Preview
private fun SplashScreenPreview() {
    SplashScreen(navigateToListScreen = {})
}

@Composable
@Preview
private fun SplashScreenPreviewDark() {
    TasksToDoAppTheme(darkTheme = true) {
        SplashScreen(navigateToListScreen = {})
    }
}