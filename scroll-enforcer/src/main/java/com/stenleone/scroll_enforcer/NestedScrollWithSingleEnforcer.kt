package com.stenleone.scrollenforcer

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.core.widget.NestedScrollView
import kotlin.math.abs

class NestedScrollWithSingleEnforcer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : NestedScrollView(context, attrs, defStyleAttr) {

    private var slop = 0

    init {
        val config = ViewConfiguration.get(context)
        slop = config.scaledEdgeSlop
    }

    private var xDistance = 0f
    private var yDistance: Float = 0f
    private var lastX: Float = 0f
    private var lastY: Float = 0f

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                run {
                    yDistance = 0f
                    xDistance = yDistance
                }
                lastX = ev.x
                lastY = ev.y

                computeScroll()
            }
            MotionEvent.ACTION_MOVE -> {
                val curX = ev.x
                val curY = ev.y
                xDistance += abs(curX - lastX)
                yDistance += abs(curY - lastY)
                lastX = curX
                lastY = curY
                if (xDistance > yDistance) {
                    return false
                }
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

}