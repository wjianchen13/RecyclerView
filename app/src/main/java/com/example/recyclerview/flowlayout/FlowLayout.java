package com.example.recyclerview.flowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * flFlow	boolean	true to allow flow. false to restrict all child views in one row. The default is true.
 * flChildSpacing	auto/dimension	The horizontal spacing between child views. Either auto, or a fixed size. The default is 0dp.
 * flChildSpacingForLastRow	auto/align/
 * dimension	The horizontal spacing between child views of the last row. Either auto, align or a fixed size. If not set, childSpacing will be used instead.
 * flRowSpacing	行间距，可以填auto或者具体的值 默认是 0dp.
 * flRtl	boolean	true to layout child views from right to left. false to layout from left to right. The default is false.
 * flMaxRows	integer	The maximum height of FlowLayout in terms of number of rows.
 */
public class FlowLayout extends ViewGroup {
    private static final String LOG_TAG = FlowLayout.class.getSimpleName();

    /**
     * Special value for the child view spacing.
     * SPACING_AUTO means that the actual spacing is calculated according to the size of the
     * container and the number of the child views, so that the child views are placed evenly in
     * the container.
     */
    public static final int SPACING_AUTO = -65536;

    /**
     * Special value for the horizontal spacing of the child views in the last row
     * SPACING_ALIGN means that the horizontal spacing of the child views in the last row keeps
     * the same with the spacing used in the row above. If there is only one row, this value is
     * ignored and the spacing will be calculated according to childSpacing.
     */
    public static final int SPACING_ALIGN = -65537;

    private static final int SPACING_UNDEFINED = -65538;

    private static final int UNSPECIFIED_GRAVITY = -1;

    private static final int ROW_VERTICAL_GRAVITY_AUTO = -65536;

    private static final boolean DEFAULT_FLOW = true;
    private static final int DEFAULT_CHILD_SPACING = 0;
    private static final int DEFAULT_CHILD_SPACING_FOR_LAST_ROW = SPACING_UNDEFINED;
    private static final float DEFAULT_ROW_SPACING = 0;
    private static final boolean DEFAULT_RTL = false;
    private static final int DEFAULT_MAX_ROWS = Integer.MAX_VALUE;

    private boolean mFlow = DEFAULT_FLOW;
    private int mChildSpacing = DEFAULT_CHILD_SPACING;
    private int mMinChildSpacing = DEFAULT_CHILD_SPACING;
    private int mChildSpacingForLastRow = DEFAULT_CHILD_SPACING_FOR_LAST_ROW;
    private float mRowSpacing = DEFAULT_ROW_SPACING;
    private float mAdjustedRowSpacing = DEFAULT_ROW_SPACING;
    private boolean mRtl = DEFAULT_RTL;
    private int mMaxRows = DEFAULT_MAX_ROWS;
    private int mGravity = UNSPECIFIED_GRAVITY;
    private int mRowVerticalGravity = ROW_VERTICAL_GRAVITY_AUTO;
    private int mExactMeasuredHeight;

    /**
     * 水平行间距
     */
    private List<Float> mHorizontalSpacingForRow = new ArrayList<>();

    /**
     * 这一行中的高度值
     */
    private List<Integer> mHeightForRow = new ArrayList<>();

    /**
     * 这一行的宽度，不包含左右间距，中间间距包含
     */
    private List<Integer> mWidthForRow = new ArrayList<>();

    /**
     * 保存每一行的元素的个数
     */
    private List<Integer> mChildNumForRow = new ArrayList<>();

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.FlowLayout, 0, 0);
        try {
            mFlow = a.getBoolean(R.styleable.FlowLayout_flFlow, DEFAULT_FLOW);
            mChildSpacing = getDimensionOrInt(a, R.styleable.FlowLayout_flChildSpacing, (int) dpToPx(DEFAULT_CHILD_SPACING));
            mMinChildSpacing = getDimensionOrInt(a, R.styleable.FlowLayout_flMinChildSpacing, (int) dpToPx(DEFAULT_CHILD_SPACING));
            mChildSpacingForLastRow = getDimensionOrInt(a, R.styleable.FlowLayout_flChildSpacingForLastRow, SPACING_UNDEFINED);
            mRowSpacing = getDimensionOrInt(a, R.styleable.FlowLayout_flRowSpacing, (int) dpToPx(DEFAULT_ROW_SPACING));
            mMaxRows = a.getInt(R.styleable.FlowLayout_flMaxRows, DEFAULT_MAX_ROWS);
            mRtl = a.getBoolean(R.styleable.FlowLayout_flRtl, DEFAULT_RTL);
            mGravity = a.getInt(R.styleable.FlowLayout_android_gravity, UNSPECIFIED_GRAVITY);
            mRowVerticalGravity = a.getInt(R.styleable.FlowLayout_flRowVerticalGravity, ROW_VERTICAL_GRAVITY_AUTO);
        } finally {
            a.recycle();
        }
    }

    private int getDimensionOrInt(TypedArray a, int index, int defValue) {
        TypedValue tv = new TypedValue();
        a.getValue(index, tv);
        if (tv.type == TypedValue.TYPE_DIMENSION) {
            return a.getDimensionPixelSize(index, defValue);
        } else {
            return a.getInt(index, defValue);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        mHorizontalSpacingForRow.clear();
        mHeightForRow.clear();
        mWidthForRow.clear();
        mChildNumForRow.clear();

        int measuredHeight = 0, measuredWidth = 0, childCount = getChildCount();
        int rowWidth = 0, maxChildHeightInRow = 0, childNumInRow = 0;
        final int rowSize = widthSize - getPaddingLeft() - getPaddingRight();
        int rowTotalChildWidth = 0;
        final boolean allowFlow = widthMode != MeasureSpec.UNSPECIFIED && mFlow;
        final int childSpacing = mChildSpacing == SPACING_AUTO && widthMode == MeasureSpec.UNSPECIFIED
                ? 0 : mChildSpacing;
        final float tmpSpacing = childSpacing == SPACING_AUTO ? mMinChildSpacing : childSpacing;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }

            LayoutParams childParams = child.getLayoutParams();
            int horizontalMargin = 0, verticalMargin = 0;
            if (childParams instanceof MarginLayoutParams) {
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, measuredHeight);
                MarginLayoutParams marginParams = (MarginLayoutParams) childParams;
                horizontalMargin = marginParams.leftMargin + marginParams.rightMargin;
                verticalMargin = marginParams.topMargin + marginParams.bottomMargin;
            } else {
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
            }

            int childWidth = child.getMeasuredWidth() + horizontalMargin;
            int childHeight = child.getMeasuredHeight() + verticalMargin;
            if (allowFlow && rowWidth + childWidth > rowSize) { // 换行之后第一个元素
                // Save parameters for current row
                mHorizontalSpacingForRow.add(getSpacingForRow(childSpacing, rowSize, rowTotalChildWidth, childNumInRow));
                mChildNumForRow.add(childNumInRow);
                mHeightForRow.add(maxChildHeightInRow);
                mWidthForRow.add(rowWidth - (int) tmpSpacing);
                if (mHorizontalSpacingForRow.size() <= mMaxRows) {
                    measuredHeight += maxChildHeightInRow;
                }
                measuredWidth = Math.max(measuredWidth, rowWidth);

                // Place the child view to next row
                childNumInRow = 1;
                rowWidth = childWidth + (int) tmpSpacing;
                rowTotalChildWidth = childWidth;
                maxChildHeightInRow = childHeight;
            } else {
                childNumInRow++;
                rowWidth += childWidth + tmpSpacing;
                rowTotalChildWidth += childWidth;
                maxChildHeightInRow = Math.max(maxChildHeightInRow, childHeight);
            }
        }

        // Measure remaining child views in the last row
        if (mChildSpacingForLastRow == SPACING_ALIGN) {
            // For SPACING_ALIGN, use the same spacing from the row above if there is more than one
            // row.
            if (mHorizontalSpacingForRow.size() >= 1) {
                mHorizontalSpacingForRow.add(
                        mHorizontalSpacingForRow.get(mHorizontalSpacingForRow.size() - 1));
            } else {
                mHorizontalSpacingForRow.add(
                        getSpacingForRow(childSpacing, rowSize, rowTotalChildWidth, childNumInRow));
            }
        } else if (mChildSpacingForLastRow != SPACING_UNDEFINED) {
            // For SPACING_AUTO and specific DP values, apply them to the spacing strategy.
            mHorizontalSpacingForRow.add(
                    getSpacingForRow(mChildSpacingForLastRow, rowSize, rowTotalChildWidth, childNumInRow));
        } else {
            // For SPACING_UNDEFINED, apply childSpacing to the spacing strategy for the last row.
            mHorizontalSpacingForRow.add(
                    getSpacingForRow(childSpacing, rowSize, rowTotalChildWidth, childNumInRow));
        }

        mChildNumForRow.add(childNumInRow);
        mHeightForRow.add(maxChildHeightInRow);
        mWidthForRow.add(rowWidth - (int) tmpSpacing);
        if (mHorizontalSpacingForRow.size() <= mMaxRows) {
            measuredHeight += maxChildHeightInRow;
        }
        measuredWidth = Math.max(measuredWidth, rowWidth);

        if (childSpacing == SPACING_AUTO) {
            measuredWidth = widthSize;
        } else if (widthMode == MeasureSpec.UNSPECIFIED) {
            measuredWidth = measuredWidth + getPaddingLeft() + getPaddingRight();
        } else {
            measuredWidth = Math.min(measuredWidth + getPaddingLeft() + getPaddingRight(), widthSize);
        }

        measuredHeight += getPaddingTop() + getPaddingBottom();
        int rowNum = Math.min(mHorizontalSpacingForRow.size(), mMaxRows);
        float rowSpacing = mRowSpacing == SPACING_AUTO && heightMode == MeasureSpec.UNSPECIFIED
                ? 0 : mRowSpacing;
        if (rowSpacing == SPACING_AUTO) {
            if (rowNum > 1) {
                mAdjustedRowSpacing = (heightSize - measuredHeight) / (rowNum - 1);
            } else {
                mAdjustedRowSpacing = 0;
            }
            measuredHeight = heightSize;
        } else {
            mAdjustedRowSpacing = rowSpacing;
            if (rowNum > 1) {
                measuredHeight = heightMode == MeasureSpec.UNSPECIFIED
                        ? ((int) (measuredHeight + mAdjustedRowSpacing * (rowNum - 1)))
                        : (Math.min((int) (measuredHeight + mAdjustedRowSpacing * (rowNum - 1)),
                        heightSize));
            }
        }

        mExactMeasuredHeight = measuredHeight;

        measuredWidth = widthMode == MeasureSpec.EXACTLY ? widthSize : measuredWidth;
        measuredHeight = heightMode == MeasureSpec.EXACTLY ? heightSize : measuredHeight;

        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int paddingLeft = getPaddingLeft(), paddingRight = getPaddingRight(),
                paddingTop = getPaddingTop(), paddingBottom = getPaddingBottom();

        int x = mRtl ? (getWidth() - paddingRight) : paddingLeft;
        int y = paddingTop;

        int verticalGravity = mGravity & Gravity.VERTICAL_GRAVITY_MASK;
        int horizontalGravity = mGravity & Gravity.HORIZONTAL_GRAVITY_MASK;

        switch (verticalGravity) {
            case Gravity.CENTER_VERTICAL: { // 垂直居中计算y坐标
                int offset = (bottom - top - paddingTop - paddingBottom - mExactMeasuredHeight) / 2;
                y += offset;
                break;
            }
            case Gravity.BOTTOM: { // 底部对齐计算y坐标
                int offset = bottom - top - paddingTop - paddingBottom - mExactMeasuredHeight;
                y += offset;
                break;
            }
            default:
                break;
        }

        int horizontalPadding = paddingLeft + paddingRight; // 左右padding
        int layoutWidth = right - left; // 父布局宽度
        if(mRtl) {
            x -= getHorizontalGravityOffsetForRow(horizontalGravity, layoutWidth, horizontalPadding, 0);
        } else {
            x += getHorizontalGravityOffsetForRow(horizontalGravity, layoutWidth, horizontalPadding, 0);
        }
        int verticalRowGravity = mRowVerticalGravity & Gravity.VERTICAL_GRAVITY_MASK;

        int rowCount = mChildNumForRow.size(), childIdx = 0;
        for (int row = 0; row < Math.min(rowCount, mMaxRows); row++) {
            int childNum = mChildNumForRow.get(row);
            int rowHeight = mHeightForRow.get(row);
            float spacing = mHorizontalSpacingForRow.get(row);
            for (int i = 0; i < childNum && childIdx < getChildCount(); ) {
                View child = getChildAt(childIdx++);
                if (child.getVisibility() == GONE) {
                    continue;
                } else {
                    i++;
                }

                LayoutParams childParams = child.getLayoutParams();
                int marginLeft = 0, marginTop = 0, marginBottom = 0, marginRight = 0;
                if (childParams instanceof MarginLayoutParams) {
                    MarginLayoutParams marginParams = (MarginLayoutParams) childParams;
                    marginLeft = marginParams.leftMargin;
                    marginRight = marginParams.rightMargin;
                    marginTop = marginParams.topMargin;
                    marginBottom = marginParams.bottomMargin;
                }

                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                int tTop = y + marginTop;
                if (verticalRowGravity == Gravity.BOTTOM) {
                    tTop = y + rowHeight - marginBottom - childHeight;
                } else if (verticalRowGravity == Gravity.CENTER_VERTICAL) {
                    tTop = y + marginTop + (rowHeight - marginTop - marginBottom - childHeight) / 2;
                }
                int bBottom = tTop + childHeight;
                if (mRtl) {
                    int l1 = x - marginRight - childWidth;
                    int r1 = x - marginRight;
                    child.layout(l1, tTop, r1, bBottom);
                    x -= childWidth + spacing + marginLeft + marginRight;
                } else {
                    int l2 = x + marginLeft;
                    int r2 = x + marginLeft + childWidth;
                    child.layout(l2, tTop, r2, bBottom);
                    x += childWidth + spacing + marginLeft + marginRight;
                }
            }
            x = mRtl ? (getWidth() - paddingRight) : paddingLeft;
            if(mRtl) {
                x -= getHorizontalGravityOffsetForRow(
                        horizontalGravity, layoutWidth, horizontalPadding, row + 1);
            } else {
                x += getHorizontalGravityOffsetForRow(
                        horizontalGravity, layoutWidth, horizontalPadding, row + 1);
            }

            y += rowHeight + mAdjustedRowSpacing;
        }

        for (int i = childIdx; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            child.layout(0, 0, 0, 0);
        }
    }

    /**
     * 获取水平偏移量，根据Gravity
     * @param horizontalGravity 水平偏移
     * @param parentWidth
     * @param horizontalPadding
     * @param row
     * @return
     */
    private int getHorizontalGravityOffsetForRow(int horizontalGravity, int parentWidth, int horizontalPadding, int row) {
        if (mChildSpacing == SPACING_AUTO || row >= mWidthForRow.size()
                || row >= mChildNumForRow.size() || mChildNumForRow.get(row) <= 0) {
            return 0;
        }

        int offset = 0;
        switch (horizontalGravity) {
            case Gravity.CENTER_HORIZONTAL:
                offset = (parentWidth - horizontalPadding - mWidthForRow.get(row)) / 2;
                break;
            case Gravity.RIGHT:
                offset = parentWidth - horizontalPadding - mWidthForRow.get(row);
                break;
            default:
                break;
        }
        return offset;
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    /**
     * Returns whether to allow child views flow to next row when there is no enough space.
     *
     * @return Whether to flow child views to next row when there is no enough space.
     */
    public boolean isFlow() {
        return mFlow;
    }

    /**
     * Sets whether to allow child views flow to next row when there is no enough space.
     *
     * @param flow true to allow flow. false to restrict all child views in one row.
     */
    public void setFlow(boolean flow) {
        mFlow = flow;
        requestLayout();
    }

    /**
     * Returns the horizontal spacing between child views.
     *
     * @return The spacing, either {@link FlowLayout#SPACING_AUTO}, or a fixed size in pixels.
     */
    public int getChildSpacing() {
        return mChildSpacing;
    }

    /**
     * Sets the horizontal spacing between child views.
     *
     * @param childSpacing The spacing, either {@link FlowLayout#SPACING_AUTO}, or a fixed size in
     *                     pixels.
     */
    public void setChildSpacing(int childSpacing) {
        mChildSpacing = childSpacing;
        requestLayout();
    }

    /**
     * Returns the horizontal spacing between child views of the last row.
     *
     * @return The spacing, either {@link FlowLayout#SPACING_AUTO},
     * {@link FlowLayout#SPACING_ALIGN}, or a fixed size in pixels
     */
    public int getChildSpacingForLastRow() {
        return mChildSpacingForLastRow;
    }

    /**
     * Sets the horizontal spacing between child views of the last row.
     *
     * @param childSpacingForLastRow The spacing, either {@link FlowLayout#SPACING_AUTO},
     *                               {@link FlowLayout#SPACING_ALIGN}, or a fixed size in pixels
     */
    public void setChildSpacingForLastRow(int childSpacingForLastRow) {
        mChildSpacingForLastRow = childSpacingForLastRow;
        requestLayout();
    }

    /**
     * Returns the vertical spacing between rows.
     *
     * @return The spacing, either {@link FlowLayout#SPACING_AUTO}, or a fixed size in pixels.
     */
    public float getRowSpacing() {
        return mRowSpacing;
    }

    /**
     * Sets the vertical spacing between rows in pixels. Use SPACING_AUTO to evenly place all rows
     * in vertical.
     *
     * @param rowSpacing The spacing, either {@link FlowLayout#SPACING_AUTO}, or a fixed size in
     *                   pixels.
     */
    public void setRowSpacing(float rowSpacing) {
        mRowSpacing = rowSpacing;
        requestLayout();
    }

    /**
     * Returns the maximum number of rows of the FlowLayout.
     *
     * @return The maximum number of rows.
     */
    public int getMaxRows() {
        return mMaxRows;
    }

    /**
     * Sets the height of the FlowLayout to be at most maxRows tall.
     *
     * @param maxRows The maximum number of rows.
     */
    public void setMaxRows(int maxRows) {
        mMaxRows = maxRows;
        requestLayout();
    }

    public void setGravity(int gravity) {
        if (mGravity != gravity) {
            mGravity = gravity;
            requestLayout();
        }
    }

    public void setRowVerticalGravity(int rowVerticalGravity) {
        if (mRowVerticalGravity != rowVerticalGravity) {
            mRowVerticalGravity = rowVerticalGravity;
            requestLayout();
        }
    }

    public boolean isRtl() {
        return mRtl;
    }

    public void setRtl(boolean rtl) {
        mRtl = rtl;
        requestLayout();
    }

    public int getMinChildSpacing() {
        return mMinChildSpacing;
    }

    public void setMinChildSpacing(int minChildSpacing) {
        this.mMinChildSpacing = minChildSpacing;
        requestLayout();
    }

    public int getRowsCount() {
        return mChildNumForRow.size();
    }

    private float getSpacingForRow(int spacingAttribute, int rowSize, int usedSize, int childNum) {
        float spacing;
        if (spacingAttribute == SPACING_AUTO) {
            if (childNum > 1) {
                spacing = (rowSize - usedSize) / (childNum - 1);
            } else {
                spacing = 0;
            }
        } else {
            spacing = spacingAttribute;
        }
        return spacing;
    }

    private float dpToPx(float dp) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
