package com.example.recyclerview.message1

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * 固定尺寸Layout
 */
class ApiesxKeepSizeConstraintsLayout : ConstraintLayout {
    private var rectXdd23233: Rect? = Rect()
    private var totalHeightXdd23233 = 0
    private fun initXXdd23233() {
        post { totalHeightXdd23233 = height }
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initXXdd23233()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initXXdd23233()
    }

    constructor(context: Context) : super(context) {
        initXXdd23233()
    }

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        if (rectXdd23233 == null) {
            rectXdd23233 = Rect()
        }
        getWindowVisibleDisplayFrame(rectXdd23233)
        val totalHeight = rootView.height
        val nowHeight = rectXdd23233!!.bottom - rectXdd23233!!.top
        if (totalHeight - nowHeight > totalHeight / 4) {
            // 被遮挡的范围大于1/4时，使用固定高度布局
            super.onMeasure(widthSpec, MeasureSpec.makeMeasureSpec(totalHeightXdd23233, MeasureSpec.EXACTLY))
        } else {
            // 不被明显遮挡时用默认测量
            super.onMeasure(widthSpec, heightSpec)
        }
    }

    companion object {
        private val TAG = ApiesxKeepSizeConstraintsLayout::class.java.simpleName
    }
}