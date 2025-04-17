package ru.otus.compose.customlayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
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
    val columnCount = if (columns <= 0) 1 else columns

    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }
        val rows = placeables.chunked(columnCount)
        val columnWidths = IntArray(columnCount) { col ->
            rows.maxOfOrNull { row -> row.getOrNull(col)?.width ?: 0 } ?: 0
        }
        val rowHeights = rows.map { row -> row.maxOfOrNull { it.height } ?: 0 }

        layout(columnWidths.sum(), rowHeights.sum()) {
            var yOffset = 0
            rows.forEachIndexed { rowIndex, row ->
                val rowHeight = rowHeights[rowIndex]
                var xOffset = 0
                row.forEachIndexed { columnIndex, placeable ->
                    placeable.placeRelative(x = xOffset, y = yOffset)
                    xOffset += columnWidths[columnIndex]
                }
                yOffset += rowHeight
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