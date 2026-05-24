package ru.otus.compose.customlayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
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
    content: @Composable () -> Unit = { },
) {
    // Гарантируем, что количество колонок не меньше 1
    val safeColumns = remember(columns) { columns.coerceAtLeast(1) }

    // Кешируем политику измерения, чтобы не создавать её заново при каждой рекомпозиции
    val measurePolicy = remember(safeColumns) {
        MeasurePolicy { measurables, constraints ->
            // Измеряем все дочерние элементы с мягкими ограничениями (без минимальной ширины/высоты)
            @Suppress("DerivedStateOfCandidate")
            val placeableList =
                measurables.map {
                    // принудительно сбрасывает минимальные требования к размеру в ноль.
                    it.measure(constraints.copy(minWidth = 0, minHeight = 0))
                }

            // Вычисляем необходимое количество строк
            val rows = (placeableList.size + safeColumns - 1) / safeColumns
            // Массивы для хранения максимальной ширины каждой колонки и высоты каждой строки
            val columnWidths = IntArray(safeColumns)
            val rowHeights = IntArray(rows)

            // Проходим по всем элементам, чтобы определить размеры сетки
            placeableList.forEachIndexed { index, placeable ->
                val column = index % safeColumns
                val row = index / safeColumns
                columnWidths[column] = maxOf(columnWidths[column], placeable.width)
                rowHeights[row] = maxOf(rowHeights[row], placeable.height)
            }

            // Итоговая ширина и высота макета (сумма всех колонок и строк с учетом ограничений)
            val width = columnWidths.sum().coerceIn(constraints.minWidth, constraints.maxWidth)
            val height = rowHeights.sum().coerceIn(constraints.minHeight, constraints.maxHeight)

            // Размещаем элементы
            layout(width, height) {
                var y = 0
                for (row in 0 until rows) {
                    var x = 0
                    for (column in 0 until safeColumns) {
                        val index = row * safeColumns + column
                        if (index < placeableList.size) {
                            // Размещаем элемент с учетом текущих координат x и y
                            placeableList[index].placeRelative(x, y)
                            // Сдвигаем x на ширину текущей колонки
                            x += columnWidths[column]
                        }
                    }
                    // После завершения строки сдвигаем y на высоту текущей строки
                    y += rowHeights[row]
                }
            }
        }
    }

    Layout(
        content = content,
        modifier = modifier,
        measurePolicy = measurePolicy
    )
}

@Preview
@Composable
private fun CustomLayoutHWPreview() {
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