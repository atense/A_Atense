package com.atense.widget;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * @author <a href="#" target="_black">Kyle</a> 2015-11-04
 * 
 */
public class FlowView extends ViewGroup {

	// 存储所有子View
	private List<List<View>> mAllChildViews = new ArrayList<List<View>>();
	// 每一行的高度
	private List<Integer> mLineHeight = new ArrayList<Integer>();

	// 最多的列数
	private int lineMaxColumn = 4;

	public FlowView(Context context) {
		this(context, null);
	}

	public FlowView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FlowView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * 设置最大的列数
	 * <p>
	 * 最大列数的取值范围为1-4，如果设置的值不再该区间中，默认赋值为4.
	 * </p>
	 * 
	 * @param count
	 */
	public void setLineMaxColumn(int count) {
		if (count > 4 || count < 1) {
			this.lineMaxColumn = 4;
		} else {
			this.lineMaxColumn = count;
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// 父控件传进来的宽度和高度以及对应的测量模式
		int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
		int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
		int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
		int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

		// 如果当前ViewGroup的宽高为wrap_content的情况
		int width = getPaddingLeft() + getPaddingRight();// 自己测量的 宽度
		int height = getPaddingTop() + getPaddingBottom();// 自己测量的高度
		// 记录每一行的宽度和高度
		int lineWidth = 0;
		int lineHeight = 0;
		// 每行中的View的个数
		int lineViewCount = 0;
		// 每行中View的宽度的最大值
		int lineViewMaxWidth = 0;

		// 获取子view的个数
		int childCount = getChildCount();

		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			boolean lastChild = i == childCount - 1;
			// 不占控件的View跳过
			if (child.getVisibility() == View.GONE) {
				if (lastChild) {
					width = Math.max(width, lineWidth);
					height += lineHeight;
				}
				continue;
			}
			// 测量子View的宽和高
			measureChild(child, widthMeasureSpec, heightMeasureSpec);

			MarginLayoutParams lp = (MarginLayoutParams) child
					.getLayoutParams();

			int childWidthMode = MeasureSpec.AT_MOST;
			// 子View占据的宽度
			int childWidthSize = child.getMeasuredWidth();

			int childHeightMode = MeasureSpec.AT_MOST;
			// 子View占据的高度
			int childHeightSize = child.getMeasuredHeight();

			if (lp.height >= 0) {
				childHeightMode = MeasureSpec.EXACTLY;
				// childHeightSize = lp.height;
			} else if (modeHeight == MeasureSpec.UNSPECIFIED) {
				childHeightMode = MeasureSpec.UNSPECIFIED;
				childHeightSize = 0;
			}
			// 测量子View的宽和高
			child.measure(MeasureSpec.makeMeasureSpec(childWidthSize,
					childWidthMode), MeasureSpec.makeMeasureSpec(
					childHeightSize, childHeightMode));

			int childWidth = child.getMeasuredWidth() + lp.leftMargin
					+ lp.rightMargin;
			lineViewMaxWidth = lineViewMaxWidth == 0 ? childWidth : Math.max(
					lineViewMaxWidth, childWidth);

			// 换行时候
			if (lineWidth + childWidth > sizeWidth
					|| (lineMaxColumn > 0 && lineViewCount >= lineMaxColumn)
					|| (!checkAddCurLineViews(sizeWidth, lineViewMaxWidth,
							lineViewCount))) {
				// 对比得到最大的宽度
				width = Math.max(width, lineWidth);
				// 重置lineWidth
				lineWidth = childWidth;
				// 记录行高
				height += lineHeight;
				lineHeight = child.getMeasuredHeight() + lp.topMargin
						+ lp.bottomMargin;
				lineViewCount = 1;
				lineViewMaxWidth = childWidth;
			} else {// 不换行情况
				// 叠加行宽
				lineWidth += childWidth;
				// 得到最大行高
				lineHeight = Math.max(lineHeight, child.getMeasuredHeight()
						+ lp.topMargin + lp.bottomMargin);
				lineViewCount++;
			}
			// 处理最后一个子View的情况
			if (lastChild) {
				width = Math.max(width, lineWidth);
				height += lineHeight;
			}

		}
		// wrap_content
		width += getPaddingLeft() + getPaddingRight();

		setMeasuredDimension((modeWidth == MeasureSpec.EXACTLY) ? sizeWidth
				: width, (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight
				: height);
		// 显示的大小
	}

	/**
	 * 重新计算当前行中所有的View的宽度，宽度为所有View平分当前的FlowView的宽度
	 * 
	 * @param lineViews
	 *            当前行中的View列表
	 */
	private void reMeasureLineViews(List<View> lineViews) {
		// 当前行的View的个数
		int count = lineViews.size();
		if (count == 0) {
			return;
		}
		// 当前行中每个View占用的宽度
		int itemWidth = (this.getMeasuredWidth() - this.getLeftPaddingOffset() - this
				.getRightPaddingOffset()) / count;
		for (int i = 0; i < count; i++) {
			View child = lineViews.get(i);
			MarginLayoutParams lp = (MarginLayoutParams) child
					.getLayoutParams();
			int childWidthSize = itemWidth - lp.leftMargin - lp.rightMargin;
			int childHeightSize = child.getMeasuredHeight();
			child.measure(MeasureSpec.makeMeasureSpec(childWidthSize,
					MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(
					childHeightSize, MeasureSpec.EXACTLY));
		}
	}

	/**
	 * 重新测量最后一行
	 * 
	 * @param lineViews
	 *            最后一行的View列表
	 * @param prveLineViewCount
	 *            上一行中View的个数
	 */
	private void reMeasureLineViews(List<View> lineViews, int prveLineViewCount) {
		// 当前行的View的个数
		int count = lineViews.size();
		if (count == 0) {
			return;
		}
		// 只有一行时，设置上一行的个数为最大个数
		prveLineViewCount = prveLineViewCount == 0 ? lineMaxColumn
				: prveLineViewCount;
		// 如果当前行中View的个数大于等于上一行中View的个数时，所有View的宽度为总宽度的平均值
		if (prveLineViewCount <= count) {
			reMeasureLineViews(lineViews);
			return;
		}
		// 上一行中View的个数为基数并且当前行中View的个数大于上一行中View的个数的一半时，所有View的宽度为总宽度的平均值
		else if (prveLineViewCount % 2 == 1
				&& count > (int) (prveLineViewCount * 1.0 / 2)) {
			reMeasureLineViews(lineViews);
			return;
		}
		// 上一行中每个View占用的宽度
		int prveItemWidth = (this.getMeasuredWidth()
				- this.getLeftPaddingOffset() - this.getRightPaddingOffset())
				/ prveLineViewCount;

		// 当前行中的最大宽度
		int lineViewMaxWidth = 0;
		for (int i = 0; i < count; i++) {
			View child = lineViews.get(i);
			MarginLayoutParams lp = (MarginLayoutParams) child
					.getLayoutParams();
			lineViewMaxWidth = Math.max(lineViewMaxWidth,
					child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin);
		}
		// 如果当前行中View的最大宽度大于上一行的平均值时，当前行中每个View的宽度为上一行平均值的2倍，否则为上一行的平均值。

		int itemWidth = prveItemWidth;
		if (prveItemWidth < lineViewMaxWidth) {
			if (prveLineViewCount % 2 == 0
					|| count > (int) (prveLineViewCount * 1.0 / 2)) {
				reMeasureLineViews(lineViews);
				return;
			}
			itemWidth = 2 * prveItemWidth;
		}

		for (int i = 0; i < count; i++) {
			View child = lineViews.get(i);
			MarginLayoutParams lp = (MarginLayoutParams) child
					.getLayoutParams();
			int childWidthSize = itemWidth - lp.leftMargin - lp.rightMargin;
			int childHeightSize = child.getMeasuredHeight();
			child.measure(MeasureSpec.makeMeasureSpec(childWidthSize,
					MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(
					childHeightSize, MeasureSpec.EXACTLY));
		}
	}

	/**
	 * 检查是否能够添加到当前行中
	 * 
	 * @param totalWidth
	 *            行的总宽度
	 * @param maxWidth
	 *            行中View的最大宽度值
	 * @param curCount
	 *            行中的View个数 - 1
	 * @return 如果当前行中的平均值大于宽度最大值时返回true，否则返回false
	 */
	private boolean checkAddCurLineViews(int totalWidth, int maxWidth,
			int curCount) {
		if (totalWidth / (curCount + 1) > maxWidth) {
			return true;
		}
		return false;
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		mAllChildViews.clear();
		mLineHeight.clear();
		// 获取当前ViewGroup的宽度
		int width = getWidth();

		int lineWidth = 0;
		int lineHeight = 0;
		//
		int lineItemMaxWidth = 0;
		//
		int prevLineViewCount = 0;
		// 记录当前行的view
		List<View> lineViews = new ArrayList<View>();
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			boolean lastChild = i == childCount - 1;
			MarginLayoutParams lp = (MarginLayoutParams) child
					.getLayoutParams();
			int childWidth = child.getMeasuredWidth();
			int childHeight = child.getMeasuredHeight();

			// 如果需要换行
			if ((lineViews.size() != 0 && childWidth + lineWidth
					+ lp.leftMargin + lp.rightMargin > width)
					|| (lineMaxColumn > 0 && lineViews.size() >= lineMaxColumn)) {
				mAllChildViews.add(lineViews);
				// 记录LineHeight
				mLineHeight.add(lineHeight);
				// 记录当前行的Views
				reMeasureLineViews(lineViews);
				prevLineViewCount = lineViews.size();
				// 重置行的宽高
				lineWidth = 0;
				lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
				// 重置view的集合
				lineViews = new ArrayList<View>();
				lineItemMaxWidth = 0;
			}
			lineItemMaxWidth = lineItemMaxWidth == 0 ? childWidth
					+ lp.leftMargin + lp.rightMargin : Math.max(
					lineItemMaxWidth, childWidth + lp.leftMargin
							+ lp.rightMargin);
			if (!checkAddCurLineViews(width, lineItemMaxWidth, lineViews.size())) {
				if (lineViews.size() != 0) {
					mAllChildViews.add(lineViews);
				}
				// 记录LineHeight
				mLineHeight.add(lineHeight);
				// 记录当前行的Views
				reMeasureLineViews(lineViews);
				prevLineViewCount = lineViews.size();
				// 重置行的宽高
				lineWidth = 0;
				lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
				// 重置view的集合
				lineViews = new ArrayList<View>();
				lineItemMaxWidth = childWidth + lp.leftMargin + lp.rightMargin;
			}

			lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
			lineHeight = Math.max(lineHeight, childHeight + lp.topMargin
					+ lp.bottomMargin);
			lineViews.add(child);

			if (lastChild) {
				// 处理最后一行
				mLineHeight.add(lineHeight);
				mAllChildViews.add(lineViews);
				// 记录当前行的Views
				reMeasureLineViews(lineViews, prevLineViewCount);
			}
		}

		// 设置子View的位置
		int left = 0;
		int top = 0;
		// 获取行数
		int lineCount = mAllChildViews.size();
		for (int i = 0; i < lineCount; i++) {
			// 当前行的views和高度
			lineViews = mAllChildViews.get(i);
			lineHeight = mLineHeight.get(i);
			for (int j = 0; j < lineViews.size(); j++) {
				View child = lineViews.get(j);
				// 判断是否显示
				if (child.getVisibility() == View.GONE) {
					continue;
				}
				MarginLayoutParams lp = (MarginLayoutParams) child
						.getLayoutParams();
				int cLeft = left + lp.leftMargin;
				int cTop = top + lp.topMargin;
				int cRight = cLeft + child.getMeasuredWidth();
				int cBottom = cTop + child.getMeasuredHeight();
				// 进行子View进行布局
				child.layout(cLeft, cTop, cRight, cBottom);
				left += child.getMeasuredWidth() + lp.leftMargin
						+ lp.rightMargin;
			}
			left = 0;
			top += lineHeight;
		}

	}

	/**
	 * 与当前ViewGroup对应的LayoutParams
	 */
	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new MarginLayoutParams(getContext(), attrs);
	}

}
