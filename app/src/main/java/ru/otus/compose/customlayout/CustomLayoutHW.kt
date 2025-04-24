package ru.otus.compose.customlayout

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import kotlin.math.max

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
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val constraintColumnMaxWidth = constraints.maxWidth / columns
        val placeables = measurables.map { measurable ->
            measurable.measure(
                Constraints(
                    maxWidth = constraintColumnMaxWidth,
                    maxHeight = constraints.maxHeight,
                )
            )
        }
        val maxWs = Array<Int>(size = columns) { 0 }
        val maxHs = Array<Int>(size = measurables.size / columns + 1) { 0 }
        placeables.forEachIndexed { index, placeable ->
            val column = index % columns
            maxWs[column] = max(maxWs[column], placeable.width)


            val row = index / columns
            maxHs[row] = max(maxHs[row], placeable.height)
        }

        layout(
            maxWs.fold(0) { acc, n -> acc + n },
            maxHs.fold(0) { acc, n -> acc + n },
        ) {
            var x = 0
            var y = 0

            placeables.forEachIndexed { index, placeable ->
                placeable.place(x, y)
                val isNewRow = ((index + 1) % columns) == 0
                if (isNewRow) {
                    x = 0
                    y += maxHs[index / columns]
                } else {
                    x += maxWs[index % columns]
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
}
