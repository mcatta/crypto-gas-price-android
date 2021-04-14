package dev.marcocattaneo.cryptogasprice.ui.widgets

import android.graphics.Color
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import dev.marcocattaneo.cryptogasprice.R
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ChartWidget(modifier: Modifier, datasets: List<ChartDataSet>) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.primaryVariant)
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .then(modifier)
    ) {
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                val simpleDateFormatter = SimpleDateFormat("H:mm", Locale.getDefault())
                LineChart(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        context.resources.getDimensionPixelSize(
                            R.dimen.chart_height
                        )
                    )

                    // Chart setup
                    animateXY(200, 200)
                    axisLeft.apply {
                        isEnabled = true
                        textColor = Color.WHITE
                    }
                    axisRight.isEnabled = false
                    xAxis.apply {
                        isEnabled = true
                        position = XAxis.XAxisPosition.BOTTOM
                        textColor = Color.WHITE

                        valueFormatter = object: ValueFormatter() {
                            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                                return simpleDateFormatter.format(datasets[0].dataSet[value.toInt()].second)
                            }
                        }
                    }
                    isDoubleTapToZoomEnabled = false
                    setPinchZoom(false)
                    setScaleEnabled(false)
                    legend.isEnabled = false
                    description.isEnabled = false
                    isHighlightPerDragEnabled = false
                    isHighlightPerTapEnabled = false


                    data = LineData().apply {
                        datasets.forEach { set ->
                            addDataSet(
                                CustomLineDataSet(
                                    ContextCompat.getColor(
                                        context,
                                        set.lineColor
                                    ), set.dataSet
                                )
                            )
                        }
                    }
                }
            }
        )
    }
}

@Composable
@Preview
fun ChartWidgetPreview() {
    ChartWidget(
        Modifier, listOf(
            ChartDataSet(Color.RED, listOf())
        )
    )
}

class CustomLineDataSet(lineColor: Int, data: List<Pair<Float, Date>>) :
    LineDataSet(
        data.mapIndexed { index, item -> Entry(index.toFloat(), item.first, item) },
        ""
    ) {

    init {
        this.setDrawHorizontalHighlightIndicator(false)
        this.setDrawVerticalHighlightIndicator(false)
        this.highlightLineWidth = 1f
        this.mode = Mode.CUBIC_BEZIER
        this.cubicIntensity = 0.2f
        this.setDrawCircles(false)
        this.setCircleColor(lineColor)
        this.setDrawCircleHole(false)
        this.circleRadius = 0f
        this.isHighlightEnabled = false
        this.setDrawValues(false)
        this.setDrawFilled(false)
        this.color = lineColor
        this.lineWidth = 1.5f
    }
}

data class ChartDataSet(
    val lineColor: Int,
    val dataSet: List<Pair<Float, Date>>
)