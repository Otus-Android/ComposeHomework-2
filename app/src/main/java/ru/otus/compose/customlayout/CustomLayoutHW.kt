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
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp

@Composable
fun CustomLayoutHW(
    columns: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { }
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        require(columns > 0) { "Columns must be > 0" }
        check(constraints.hasBoundedWidth) { "CustomLayoutHW requires bounded width" }
        val placeables = measurables.map { it.measure(constraints) }
        val columnWidths = IntArray(columns)
        placeables.forEachIndexed { index, placeable ->
            val columnIndex = index % columns
            columnWidths[columnIndex] = maxOf(columnWidths[columnIndex], placeable.width)
        }
        val rowCount = (measurables.size + columns - 1) / columns
        val rowHeights = IntArray(rowCount)
        placeables.forEachIndexed { index, placeable ->
            val row = index / columns
            rowHeights[row] = maxOf(rowHeights[row], placeable.height)
        }
        val totalWidth = constraints.constrainWidth(columnWidths.sum())
        val totalHeight = constraints.constrainHeight(rowHeights.sum())
        layout(totalWidth, totalHeight) {
            placeables.forEachIndexed { index, placeable ->
                val columnIndex = index % columns
                val rowIndex = index / columns
                val xPosition = columnWidths.take(columnIndex).sum()
                val yPosition = rowHeights.take(rowIndex).sum()
                placeable.placeRelative(xPosition, yPosition)
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