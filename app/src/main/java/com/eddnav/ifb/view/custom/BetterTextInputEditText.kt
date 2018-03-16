package com.eddnav.ifb.view.custom

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.support.design.widget.TextInputEditText
import android.text.TextPaint
import android.util.AttributeSet
import com.eddnav.ifb.R


/**
 * Just adds a suffix feature on [android.support.design.widget.TextInputEditText] for now.
 *
 * @author Eduardo Naveda
 */
class BetterTextInputEditText : TextInputEditText {

    private var mLine0BaseLine: Int = 0
    private var mSuffixDrawable: Suffix = Suffix("")
    private var mTextPaint: TextPaint? = TextPaint()

    private var suffix: String = ""
        set(value) {
            field = value
            setupCompoundDrawables()
        }

    constructor(context: Context) : super(context, null)

    // For some reason, chaining constructors breaks instancing here.
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        mTextPaint!!.color = currentHintTextColor
        mTextPaint!!.textSize = textSize
        mTextPaint!!.textAlign = Paint.Align.RIGHT

        val array = context.obtainStyledAttributes(attrs, R.styleable.BetterTextInputEditText, 0, 0)

        try {
            suffix = array.getString(R.styleable.BetterTextInputEditText_suffix) ?: ""
        } finally {
            array.recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        mLine0BaseLine = getLineBounds(0, null)
        super.onDraw(canvas)
    }

    private fun setupCompoundDrawables() {
        mSuffixDrawable.text = suffix
        compoundDrawablePadding = (resources.getDimension(R.dimen.better_text_input_edit_text_suffix_margin) + mTextPaint!!.measureText(suffix)).toInt()
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, mSuffixDrawable, null)
    }

    override fun setTypeface(typeface: Typeface?) {
        super.setTypeface(typeface)
        mTextPaint?.typeface = typeface

        postInvalidate()
    }

    private inner class Suffix(suffix: String) : Drawable() {

        var text: String = suffix
            set(value) {
                field = value
                invalidateSelf()
            }

        override fun draw(canvas: Canvas?) {
            canvas?.drawText(text, 0f, mLine0BaseLine + canvas.clipBounds.top.toFloat(), mTextPaint)
        }

        override fun setAlpha(alpha: Int) {
        }

        override fun getOpacity(): Int {
            return PixelFormat.OPAQUE
        }

        override fun setColorFilter(colorFilter: ColorFilter?) {
        }
    }
}