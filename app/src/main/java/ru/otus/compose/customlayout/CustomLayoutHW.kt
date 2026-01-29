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

/**
 * Task: Make custom grid layout
 */
@Composable
fun CustomLayoutHW(
    columns: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = { }
) {
    require(columns > 0) { "columns must be > 0" }
    // Replace Column with your custom layout
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        check(constraints.hasBoundedWidth) { "CustomLayoutHW requires bounded width" }
        val childConstraints = constraints.copy(
            minWidth = 0,
            minHeight = 0
        )
        val placeables = measurables.map { measurable ->
            measurable.measure(
                constraints = childConstraints
            )
        }
        val height: Int = placeables
            .chunked(size = columns)
            .sumOf { chunk -> chunk.maxOf { it.height } }
        val layoutHeight = constraints.constrainHeight(height)
        val layoutWidth = constraints.constrainWidth(constraints.maxWidth)
        layout(
            height = layoutHeight,
            width = layoutWidth
        ) {
            var x = 0
            var y = 0
            var currentMaxHeight = 0
            placeables.forEachIndexed { index, placeable ->
                currentMaxHeight = maxOf(currentMaxHeight, placeable.height)
                placeable.place(x, y)
                if ((index + 1) % columns == 0) {
                    x = 0
                    y += currentMaxHeight
                    currentMaxHeight = 0
                } else {
                    x += placeable.width
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