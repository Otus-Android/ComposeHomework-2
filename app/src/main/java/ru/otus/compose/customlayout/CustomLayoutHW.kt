package ru.otus.compose.customlayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Task: Make custom grid layout
 */
@Composable
fun CustomLayoutHW(
    columns: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { }
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }

        var maxHeight = 0
        placeables.forEach { placeable ->
            if (placeable.height > maxHeight) maxHeight = placeable.height
        }

        layout(
            constraints.maxWidth,
            constraints.maxHeight
        ) {
            val customVerticalSpacer = 8
            var columnCount = 0
            var x = 0
            var y = 0

            placeables.forEach { placeable ->
                placeable.place(x, y)

                columnCount++

                if (columnCount < columns) {
                    x += placeable.width
                } else {
                    x = 0
                    y += maxHeight + customVerticalSpacer
                    columnCount = 0
                }
            }
        }
    }
}

@Preview
@Composable
fun CustomLayoutHWPreview() {
    Surface {
        CustomLayoutHW(
            columns = 3,
            modifier = Modifier
                .padding(4.dp)
                .border(2.dp, color = Color.Black)
                .padding(4.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(100.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(110.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(90.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(120.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(100.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(80.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(100.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(120.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(100.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier
                    .size(90.dp)
                    .padding(4.dp)
                    .border(2.dp, color = Color.Black)
            )
        }
    }
}