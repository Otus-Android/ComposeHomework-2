package ru.otus.compose.customlayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ComposeHomeworkApp() {
    CustomLayoutHW(
        columns = 3,
        modifier = Modifier.systemBarsPadding()
            .padding(14.dp)
            .verticalScroll(rememberScrollState())
    ) {
        CatAndDot(Modifier.size(100.dp))
        CatAndDot(Modifier.size(110.dp))
        CatAndDot(Modifier.size(90.dp))
        CatAndDot(Modifier.size(120.dp))
        CatAndDot(Modifier.size(100.dp))
        CatAndDot(Modifier.size(80.dp))
        CatAndDot(Modifier.size(100.dp))
        CatAndDot(Modifier.size(120.dp))
        CatAndDot(Modifier.size(100.dp))
        CatAndDot(Modifier.size(90.dp))
    }
}

@Composable
fun CatAndDot(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.catanddot),
        contentDescription = null,
        modifier = modifier
            .padding(4.dp)
            .border(2.dp, color = Color.Black)
    )
}
