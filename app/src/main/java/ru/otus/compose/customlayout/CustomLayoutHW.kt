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
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp

fun Boolean.toInt() = if (this) 1 else 0

/**
 * Task: Make custom grid layout
 */
@Composable
fun CustomLayoutHW(
    columns: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { }
) {
    // Replace Column with your custom layout
    Layout(
        modifier = modifier,
        content = content,
    ) { measurables, constraints ->
        val itemsCount = measurables.size
        val rows = itemsCount / columns +
                (itemsCount % columns > 0).toInt()
        val maxWidth = constraints.maxWidth / columns
        val maxHeight = constraints.maxHeight / rows
        val placeables = measurables.map { measurable ->
            measurable.measure(
                Constraints(
                    maxWidth = maxWidth,
                    maxHeight = maxHeight,
                )
            )
        }
        val heigh = placeables.maxOf { it.height }
        val width = placeables.maxOf { it.width }

        layout(
            width * columns,
            heigh * rows,
        ) {
            for (i in 0 until rows) {
                for (j in 0 until columns) {
                    val currentPlaceable = i * columns + j
                    if (currentPlaceable >= itemsCount) {
                        break
                    }
                    placeables[currentPlaceable].place(
                        x = heigh * j,
                        y = width * i
                    )
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