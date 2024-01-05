package com.example.androidscholar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.sqrt

class CustomDrawingView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint()

    // Define the coordinates of pointA and pointB
    private val pointA = Pair(300f, 800f)
    private val pointB = Pair(100f, 1200f)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = Color.BLACK
        paint.strokeWidth = 5f

        // Draw a line between pointA and pointB
        canvas.drawLine(pointA.first, pointA.second, pointB.first, pointB.second, paint)

        // Calculate the perpendicular line's endpoint
        val perpendicularEndPoint = calculatePerpendicularEndPoint()

        // canvas.drawText("pointC=$perpendicularEndPoint", pointA.first, pointA.second, paint.apply { textSize = 50f })
        // Draw the perpendicular line starting from pointA
        canvas.drawLine(pointA.first, pointA.second, perpendicularEndPoint.first, perpendicularEndPoint.second, paint.apply { color = Color.RED })
    }

    private fun calculatePerpendicularEndPoint(): Pair<Float, Float> {
        // Calculate the slope of the line between pointA and pointB
        val deltaX = pointB.first - pointA.first
        val deltaY = pointB.second - pointA.second
        val distance = sqrt((deltaX * deltaX + deltaY * deltaY))
        if (deltaX == 0f && deltaY == 0f) return Pair(pointA.first, pointA.second)
        else if (deltaX == 0f) return Pair(pointA.first+distance, pointA.second)
        else if (deltaY == 0f) return Pair(pointA.first , pointA.second-distance)
        val slopeAB = deltaY / deltaX
        // Calculate the negative reciprocal slope for the perpendicular line
        val slopePerpendicular = -1 / slopeAB

        // Calculate the endpoint coordinates for the perpendicular line
        val perpendicularX = pointA.first+distance  // Adjust the length of the perpendicular line
        val perpendicularY = pointA.second + slopePerpendicular * (perpendicularX - pointA.first)

        return Pair(perpendicularX, perpendicularY)
    }
}
