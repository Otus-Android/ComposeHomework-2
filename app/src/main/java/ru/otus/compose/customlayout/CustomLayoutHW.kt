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
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.max

/**
 * Task: Make custom grid layout
 */
@Composable
fun CustomLayoutHW(
    columns: Int,
    modifier: Modifier = Modifier,
    horizontalSpacing: Dp = 8.dp, // Отступ между колонками
    verticalSpacing: Dp = 8.dp,   // Отступ между строками
    content: @Composable () -> Unit = { }
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val hSpacingPx = horizontalSpacing.roundToPx()
        val vSpacingPx = verticalSpacing.roundToPx()

        // Вычисляем максимально допустимую ширину одного элемента
        val totalHorizontalSpacing = hSpacingPx * (columns - 1)
        val maxItemWidth = (constraints.maxWidth - totalHorizontalSpacing) / columns

        // Измеряем элементы.
        val itemConstraints = Constraints(maxWidth = maxItemWidth)
        val placeables = measurables.map { it.measure(itemConstraints) }

        val rows = placeables.chunked(columns)

        // Считаем высоту каждой строки на основе самого высокого элемента в ней
        val rowHeights = rows.map { row -> row.maxOfOrNull { it.height } ?: 0 }
        val totalHeight = rowHeights.sum() + (vSpacingPx * (rows.size - 1)).coerceAtLeast(0)

        layout(constraints.maxWidth, totalHeight) {
            var yPosition = 0

            rows.forEachIndexed { rowIndex, row ->
                var xPosition = 0
                row.forEach { placeable ->
                    placeable.placeRelative(
                        x = xPosition,
                        y = yPosition
                    )
                    xPosition += maxItemWidth + hSpacingPx
                }
                yPosition += rowHeights[rowIndex] + vSpacingPx
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