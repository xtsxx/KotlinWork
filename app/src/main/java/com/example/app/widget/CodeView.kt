package com.example.app.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.example.app.R
import com.example.core.utils.Utils
import com.example.core.utils.dp2px
import kotlin.random.Random

class CodeView constructor(context: Context, attrs: AttributeSet? = null) : AppCompatTextView(context, attrs) {

    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = context.getColor(R.color.colorAccent)
        strokeWidth = 6f.dp2px()
    }

    private val codeList = arrayOf(
            "kotlin",
            "android",
            "java",
            "http",
            "https",
            "okhttp",
            "retrofit",
            "tcp/ip")

    init {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        gravity = Gravity.CENTER
        setBackgroundColor(context.getColor(R.color.colorPrimary))
        setTextColor(Color.WHITE)
        updateCode()
    }

    fun updateCode() {
        val random = Random.nextInt(codeList.size)
        val code = codeList[random]
        text = code
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawLine(0f, height.toFloat(), width.toFloat(), 0f, paint)
        super.onDraw(canvas)
    }
}