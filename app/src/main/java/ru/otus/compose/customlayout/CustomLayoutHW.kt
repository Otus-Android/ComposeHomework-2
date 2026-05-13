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
        val totalItems = measurables.size
        if (totalItems == 0) {
            return@Layout layout(0, 0) {}
        }

        val measureConstraints = constraints.copy(
            minWidth = 0,
            maxWidth = Constraints.Infinity,
            minHeight = 0
        )
        val placeables = measurables.map { it.measure(measureConstraints) }

        val rows = (totalItems + columns - 1) / columns

        val rowWidths = IntArray(rows) { 0 }
        val rowHeights = IntArray(rows) { 0 }

        for (row in 0 until rows) {
            val start = row * columns
            val end = minOf(start + columns, totalItems)
            var totalWidth = 0
            var maxHeight = 0
            for (i in start until end) {
                totalWidth += placeables[i].width
                maxHeight = maxOf(maxHeight, placeables[i].height)
            }
            rowWidths[row] = totalWidth
            rowHeights[row] = maxHeight
        }

        val containerWidth = maxOf(rowWidths.maxOrNull() ?: 0, constraints.minWidth)
            .coerceIn(constraints.minWidth, constraints.maxWidth)
        val containerHeight = rowHeights.sum()

        layout(containerWidth, containerHeight) {
            var y = 0
            for (row in 0 until rows) {
                val start = row * columns
                val end = minOf(start + columns, totalItems)
                var x = 0
                for (i in start until end) {
                    placeables[i].placeRelative(x, y)
                    x += placeables[i].width
                }
                y += rowHeights[row]
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
                Modifier.size(100.dp).padding(4.dp).border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier.size(110.dp).padding(4.dp).border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier.size(90.dp).padding(4.dp).border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier.size(120.dp).padding(4.dp).border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier.size(100.dp).padding(4.dp).border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier.size(80.dp).padding(4.dp).border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier.size(100.dp).padding(4.dp).border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier.size(120.dp).padding(4.dp).border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier.size(100.dp).padding(4.dp).border(2.dp, color = Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.catanddot),
                contentDescription = null,
                Modifier.size(90.dp).padding(4.dp).border(2.dp, color = Color.Black)
            )
        }
    }
}