package com.example.todo.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.example.baselibrary.ui.BaseActivity
import com.example.todo.R
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import kotlinx.android.synthetic.main.todo_activity_main.*


/**
 * @author Lai
 * @time 2018/8/10 10:35
 * @Description
 */
class TodoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.todo_activity_main)


        val strings = arrayListOf<PieEntry>()
        strings.add(PieEntry(2430f, "A"))
        strings.add(PieEntry(4230f, "B"))
        strings.add(PieEntry(4530f, "C"))
        strings.add(PieEntry(750f, "D"))
        strings.add(PieEntry(730f, "E"))
        strings.add(PieEntry(7220f, "F"))

        val dataSet = PieDataSet(strings, "Label")
        dataSet.setColors(Color.parseColor("#b2dae2"),
                Color.parseColor("#ceca9a"),
                Color.parseColor("#efaaaa"),
                Color.parseColor("#d5790a"),
                Color.parseColor("#ede9b5"),
                Color.parseColor("#b1b2dc"))


        dataSet.valueLinePart1Length=80f
        dataSet.setValueLinePart1Length(0.6f)
        dataSet.setValueLinePart2Length(0.4f)
        pic_chart.setHighlightPerTapEnabled(true)
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE)
        val pieData = PieData(dataSet)
        pieData.setValueTextSize(12f)
        pieData.setDrawValues(true)
        pieData.setValueFormatter(PercentFormatter())
        //中间透明圈
        pic_chart.transparentCircleRadius = 55f
        pic_chart.setUsePercentValues(true)
        //不要中间的
        pic_chart.isDrawHoleEnabled= false
        pic_chart.animateXY(500, 500)
        pic_chart.data = pieData
        pic_chart.invalidate()
    }

}