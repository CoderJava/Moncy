package com.ysn.moncy.library.shimmerview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.os.Build
import androidx.annotation.RequiresApi
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.animation.LinearInterpolator

/**
 * Created by root on 18/08/17.
 * Used to make effect shimmer view
 */
class ShimmerView : View, ValueAnimator.AnimatorUpdateListener {

    /**
     * @param context
     * Context
     */
    constructor(context: Context) : super(context) {
        init()
    }

    /**
     * @param context
     * Context
     * @param
     * AttributeSet
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    companion object {
        const val EDGE_ALPHA = 12
        const val SHADER_COLOR_R = 170
        const val SHADER_COLOR_G = 170
        const val SHADER_COLOR_B = 170
        const val CENTER_ALPHA = 100
        const val ITEM_BG_COLOR = Color.WHITE

        val EDGE_COLOR = Color.argb(EDGE_ALPHA, SHADER_COLOR_R, SHADER_COLOR_G, SHADER_COLOR_B)
        val CENTER_COLOR = Color.argb(CENTER_ALPHA, SHADER_COLOR_R, SHADER_COLOR_G, SHADER_COLOR_B)

        const val LIST_ITEM_LINES = 3
        const val CORNER_RADIUS = 2
        const val LINE_HEIGHT = 15
        /*const val H_SPACING = 12*/
        const val H_SPACING = 0
        /*const val W_SPACING = 16*/
        const val W_SPACING = 0
        const val IMAGE_SIZE = 50
        const val ANIMATION_DURATION = 1500L
    }

    private var listItems: Bitmap? = null
    private var animator: ValueAnimator? = null
    private var paint: Paint? = null
    private var shaderPaint: Paint? = null
    private var shaderColors: IntArray? = null

    private var lineHeight: Float = 0F
    private var hSpacing: Float = 0F
    private var wSpacing: Float = 0F
    private var imageSize: Float = 0F
    private var cornerRadius: Float = 0F

    /**
     * Init constructor
     */
    fun init() {
        val metric = context.resources.displayMetrics
        cornerRadius = dpToPixels(metric, CORNER_RADIUS)
        hSpacing = dpToPixels(metric, H_SPACING)
        wSpacing = dpToPixels(metric, W_SPACING)
        lineHeight = spToPixels(metric, LINE_HEIGHT)
        imageSize = dpToPixels(metric, IMAGE_SIZE)

        animator = ValueAnimator.ofFloat(-1F, 2F)
        animator?.duration = ANIMATION_DURATION
        animator?.interpolator = LinearInterpolator()
        animator?.repeatCount = ValueAnimator.INFINITE
        animator?.addUpdateListener(this)

        paint = Paint()

        shaderPaint = Paint()
        shaderPaint?.isAntiAlias = true
        shaderColors = intArrayOf(EDGE_COLOR, CENTER_COLOR, EDGE_COLOR)
    }

    /**
     * @param changedView
     * View
     * @param visibility
     * Visibility
     */
    override fun onVisibilityChanged(changedView: View?, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        when (visibility) {
            VISIBLE -> animator?.start()
            INVISIBLE, GONE -> animator?.cancel()
        }
    }

    /**
     * @param valueAnimator
     * ValueAnimator
     */
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onAnimationUpdate(valueAnimator: ValueAnimator) {
        if (isAttachedToWindow) {
            val factor: Float = valueAnimator.animatedValue as Float
            updateShader(width = width.toFloat(), factor = factor)
            invalidate()
        }
    }

    /**
     * @param w
     * Width
     * @param h
     * Height
     * @param oldw
     * Old Width
     * @param oldh
     * Old Height
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateShader(width = w.toFloat())
        if (h > 0 && w > 0) {
            drawListItems(w, h)
        } else {
            listItems = null
            animator?.cancel()
        }
    }

    /**
     * @param width
     * Width
     * @param factor
     * Factor with default value -1F
     */
    private fun updateShader(width: Float, factor: Float = -1F) {
        val left = width * factor
        val shader = LinearGradient(
                left,
                0F,
                left + width,
                0F,
                shaderColors,
                floatArrayOf(0f, 0.5f, 1f),
                Shader.TileMode.CLAMP
        )
        shaderPaint?.shader = shader
    }

    /**
     * @param canvas
     * Canvas
     */
    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(EDGE_COLOR)
        canvas.drawRect(
                0F,
                0F,
                canvas.width.toFloat(),
                canvas.height.toFloat(),
                shaderPaint
        )
        if (listItems != null) {
            canvas.drawBitmap(listItems, 0F, 0F, paint)
        }
    }

    /**
     * @param w
     * Width item
     * @param h
     * Height item
     */
    private fun drawListItems(w: Int, h: Int) {
        listItems = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(listItems)
        val item = getItemBitmap(w)
        var top = 0
        do {
            canvas.drawBitmap(item, 0F, top.toFloat(), paint)
            top += item.height
        } while (top < canvas.height)
        canvas.drawColor(ITEM_BG_COLOR, PorterDuff.Mode.SRC_IN)
    }

    /**
     * @param w
     * Width bitmap
     */
    private fun getItemBitmap(w: Int): Bitmap {
        val h = calculateListItemHeight(LIST_ITEM_LINES)
        val item = Bitmap.createBitmap(w, h, Bitmap.Config.ALPHA_8)

        val canvas = Canvas(item)
        canvas.drawColor(Color.argb(255, 0, 0, 0))

        val itemPaint = Paint()
        itemPaint.isAntiAlias = true
        itemPaint.color = Color.argb(0, 0, 0, 0)
        itemPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)

        // Avatar
        val rectF = RectF(wSpacing, hSpacing, wSpacing + imageSize, hSpacing + imageSize)
        /*canvas.drawOval(rectF, itemPaint)*/
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, itemPaint)

        val textLeft = rectF.right + hSpacing
        val textRight = canvas.width - wSpacing

        // Title line
        val titleWidth = (textRight - textLeft) * 0.5F
        rectF.set(textLeft, hSpacing, textLeft + titleWidth, hSpacing + lineHeight)
        /*canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, itemPaint)*/

        // Timestamp
        val timeWidth = (textRight - textLeft) * 0.2F
        rectF.set(textRight - timeWidth, hSpacing, textRight, hSpacing + lineHeight)
        /*canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, itemPaint)*/

        // Text lines
        for (a in 0 until LIST_ITEM_LINES - 1) {
            val lineTop = rectF.bottom + hSpacing
            rectF.set(textLeft, lineTop, textRight, lineTop + lineHeight)
            /*canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, itemPaint)*/
        }
        return item
    }

    /**
     * @param lines
     * Height item lines
     */
    private fun calculateListItemHeight(lines: Int): Int =
            ((lines * lineHeight) + (hSpacing * (lines + 1))).toInt()

    /**
     * @param metrics
     * DisplayMetrics
     * @param dp
     * dp value
     */
    private fun dpToPixels(metrics: DisplayMetrics, dp: Int): Float =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), metrics)

    /**
     * @param metrics
     * DisplayMetrics
     * @param sp
     * sp value
     */
    private fun spToPixels(metrics: DisplayMetrics, sp: Int): Float =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp.toFloat(), metrics)

    /**
     * Detach it from window
     */
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator?.removeAllUpdateListeners()
        animator = null
        listItems = null
    }
}