package com.example.customview_training.custom_view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import androidx.core.graphics.withSave
import androidx.lifecycle.MutableLiveData

class CustomColorPicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = 310.dp().toInt()
        val desiredHeight = 95.dp().toInt()

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        //Measure Width
        val width: Int = when (widthMode) {
            MeasureSpec.EXACTLY -> {
                //Must be this size
                widthSize
            }
            MeasureSpec.AT_MOST -> {
                //Can't be bigger than...
                desiredWidth.coerceAtMost(widthSize)
            }
            else -> {
                //Be whatever you want
                desiredWidth
            }
        }

        //Measure Height
        val height: Int = when (heightMode) {
            MeasureSpec.EXACTLY -> {
                //Must be this size
                heightSize
            }
            MeasureSpec.AT_MOST -> {
                //Can't be bigger than...
                desiredHeight.coerceAtMost(heightSize)
            }
            else -> {
                //Be whatever you want
                desiredHeight
            }
        }


        //MUST CALL THIS
        setMeasuredDimension(width, height)
    }

    val color = MutableLiveData(Color.WHITE)
    private val paint = Paint().apply {
        isAntiAlias = true
        strokeWidth = 4.dp()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas!!)
        drawBack(canvas)
        drawColorsToChoose(canvas)
        drawChoosenColor(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            when(it.action){
                MotionEvent.ACTION_DOWN -> {
                    checkOrChangeColor(it)
                    invalidate()
                }
            }
        }
        return true
    }

    private fun checkOrChangeColor(event: MotionEvent) {
        when {
            event.x >= 20f &&
                    event.x <= 20f + 110f &&
                    event.y >= 20f &&
                    event.y <= 20f + 110f -> {
                color.value = Color.YELLOW
            }
            event.x >= 20f &&
                    event.x <= 20f + 110f &&
                    event.y >= 20f &&
                    event.y <= 20f + 20f + 110f + 110f-> {
                color.value = Color.GREEN
            }
            event.x >= 20f + (20f + 110f) &&
                    event.x <= 20f + (20f + 110f) + 110f &&
                    event.y >= 20f &&
                    event.y <= 20f + 110f -> {
                color.value = Color.BLUE
            }
            event.x >= 20f + (20f + 110f) &&
                    event.x <= 20f + (20f + 110f) + 110f &&
                    event.y >= 20f &&
                    event.y <= 20f + 20f + 110f + 110f -> {
                color.value = Color.RED
            }
            event.x >= 20f + 2 * (20f + 110f) &&
                    event.x <= 20f + 2 * (20f + 110f) + 110f &&
                    event.y >= 20f &&
                    event.y <= 20f + 110f -> {
                color.value = Color.BLACK
            }
            event.x >= 20f + 2 * (20f + 110f) &&
                    event.x <= 20f + 2 * (20f + 110f) + 110f &&
                    event.y >= 20f &&
                    event.y <= 20f + 20f + 110f + 110f -> {
                color.value = Color.WHITE
            }
            event.x >= 20f + 3 * (20f + 110f) &&
                    event.x <= 20f + 3 * (20f + 110f) + 110f &&
                    event.y >= 20f &&
                    event.y <= 20f + 110f -> {
                color.value = Color.CYAN
            }
            event.x >= 20f + 3 * (20f + 110f) &&
                    event.x <= 20f + 3 * (20f + 110f) + 110f &&
                    event.y >= 20f &&
                    event.y <= 20f + 20f + 110f + 110f -> {
                color.value = Color.DKGRAY
            }
            event.x >= 20f + 4 * (20f + 110f) &&
                    event.x <= 20f + 4 * (20f + 110f) + 110f &&
                    event.y >= 20f &&
                    event.y <= 20f + 110f -> {
                color.value = Color.MAGENTA
            }
            event.x >= 20f + 4 * (20f + 110f) &&
                    event.x <= 20f + 4 * (20f + 110f) + 110f &&
                    event.y >= 20f &&
                    event.y <= 20f + 20f + 110f + 110f -> {
                color.value = Color.GRAY
            }

        }
    }

    private fun drawChoosenColor(canvas: Canvas) {
        val xOffset = 60f + 5 * (20f + 110f)
        canvas.withSave {
            translate(xOffset, 60f)
            drawRoundRect(0f, 0f, 160f, 160f, 12f, 12f,
                Paint().apply {
                    this.color = Color.BLACK
                    isDither = true
                    style = Paint.Style.STROKE
                    strokeJoin = Paint.Join.ROUND
                    strokeCap = Paint.Cap.ROUND
                    isAntiAlias = true
                    strokeWidth = 2.dp()
                }
            )
            paint.color = color.value!!
            drawRoundRect(0f, 0f, 160f, 160f, 12f, 12f, paint)
        }
    }

    private fun drawColorsToChoose(canvas: Canvas) {
        drawRoundColor(canvas, Color.YELLOW, 20f, 20f)
        drawRoundColor(canvas, Color.GREEN, 20f, 20f + 20f + 110f)

        drawRoundColor(canvas, Color.BLUE, 20f + (20f + 110f), 20f)
        drawRoundColor(canvas, Color.RED, 20f + (20f + 110f), 20f + 20f + 110f)

        drawRoundColor(canvas, Color.BLACK, 20f + 2 * (20f + 110f), 20f)
        drawRoundColor(canvas, Color.WHITE, 20f + 2 * (20f + 110f), 20f + 20f + 110f)

        drawRoundColor(canvas, Color.CYAN, 20f + 3 * (20f + 110f), 20f)
        drawRoundColor(canvas, Color.DKGRAY, 20f + 3 * (20f + 110f), 20f + 20f + 110f)

        drawRoundColor(canvas, Color.MAGENTA, 20f + 4 * (20f + 110f), 20f)
        drawRoundColor(canvas, Color.GRAY, 20f + 4 * (20f + 110f), 20f + 20f + 110f)
    }

    private fun drawRoundColor(canvas: Canvas, color: Int, x: Float, y: Float) {
        canvas.withSave {
            translate(x, y)
            drawRoundRect(0f, 0f, 110f, 110f, 12f, 12f,
                Paint().apply {
                    this.color = Color.BLACK
                    isDither = true
                    style = Paint.Style.STROKE
                    strokeJoin = Paint.Join.ROUND
                    strokeCap = Paint.Cap.ROUND
                    isAntiAlias = true
                    strokeWidth = 2.dp()
                }
            )
        }
        canvas.withSave {
            translate(x, y)
            paint.color = color
            drawRoundRect(0f, 0f, 110f, 110f, 12f, 12f, paint)
        }
    }

    private fun drawBack(canvas: Canvas) {
        canvas.withSave {
            paint.color = Color.parseColor("#FB8C00")
            drawRoundRect(0f, 0f, 310f.dp(), 95f.dp(), 25f, 25f, paint)
        }
    }

    private fun Number.dp(): Float = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        resources.displayMetrics
    )

}