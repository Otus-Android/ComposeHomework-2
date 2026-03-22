package ru.otus.compose.customlayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private const val ERROR_MESSAGE = "columnsCount must be > 0"

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
    Column(
        modifier = modifier,
    ) {
        content()
    }
}

@Composable
fun CustomGridLayout(
    columnsCount: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    SubcomposeLayout(
        modifier = modifier
    ) { constraints ->
        if (columnsCount == 0) throw IllegalArgumentException(ERROR_MESSAGE)

        val cellWidth = constraints.maxWidth / columnsCount

        val fixedPlaceableItems = subcompose("content_slot", content).map { measurable ->
            val width = measurable.maxIntrinsicWidth(constraints.maxHeight)
            val height = measurable.maxIntrinsicHeight(width)
            val scale = cellWidth.toFloat() / width.toFloat()

            val curHeight = (height * scale).toInt()

            measurable.measure(
                constraints.copy(
                    maxWidth = cellWidth,
                    maxHeight = curHeight,
                    minWidth = 0,
                    minHeight = 0
                )
            )
        }

        var currentMaxInRow = 0
        val ySteps = ArrayDeque<Int>()

        fixedPlaceableItems.forEachIndexed { index, placeable ->
            currentMaxInRow = maxOf(currentMaxInRow, placeable.height)

            val isLastElement = fixedPlaceableItems.lastIndex == index
            val isEndOfRow = (index + 1) % columnsCount == 0

            if (isEndOfRow || isLastElement) {
                ySteps.add(currentMaxInRow)
                currentMaxInRow = 0
            }
        }

        layout(width = constraints.maxWidth, height = constraints.maxHeight) {
            var x = 0
            var y = 0
            fixedPlaceableItems.forEachIndexed { index, placeable ->

                val isLastInRow = (index + 1) % columnsCount == 0

                placeable.placeRelative(x, y)
                if (isLastInRow) {
                    y += ySteps.removeFirst()
                    x = 0
                } else {
                    x += cellWidth
                }
            }
        }
    }
}

@Preview
@Composable
fun CustomLayoutHWPreview() {
    Surface {
        CustomGridLayout(
            columnsCount = 3,
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