package com.m.tripcard.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

public class FlipperLayout extends ViewGroup {
	private final String TAG = "FlipperLayout";

	private final int LEFT_WIDTH = 82;
	private Scroller mScroller;
	private VelocityTracker mVelocityTracker;
	private int mWidth;

	public static final int SCREEN_STATE_CLOSE = 0;
	public static final int SCREEN_STATE_LEFT_OPEN = 1;
	public static final int SCREEN_STATE_RIGHT_OPEN = 2;

	public static final int SCREEN_STATE_LEFT_CLOSE = 3;
	public static final int SCREEN_STATE_RIGHT_CLOSE = 4;

	public static final int TOUCH_STATE_RESTART = 0;
	public static final int TOUCH_STATE_SCROLLING = 1;

	public static final int SCROLL_STATE_NO_ALLOW = 0;
	public static final int SCROLL_STATE_ALLOW = 1;

	private int mScreenState = SCREEN_STATE_CLOSE;

	private int mTouchState = TOUCH_STATE_RESTART;

	private int mScrollState = SCROLL_STATE_NO_ALLOW;

	private int mWillState = SCREEN_STATE_CLOSE;

	private int mVelocityValue = 0;

	private boolean mOnClick = false;

	private View mLeftView, mRightView, mMasterView;

	private float mLastMotionX, mLastMotionY;

	private int mTouchSlop;

	public FlipperLayout(Context context) {
		super(context);
		initData(context);
	}

	public FlipperLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initData(context);
	}

	public FlipperLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initData(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(width, height);

		for (int i = 0, j = getChildCount(); i < j; i++) {
			getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
		}

	}

	public void setLeftView(View leftView, LayoutParams params) {
		if (mLeftView == null) {
			this.mLeftView = leftView;
			addView(mLeftView, 0, params);
		}
	}

	public void setRightView(View rightView, LayoutParams params) {
		if (mRightView == null) {
			this.mRightView = rightView;
			addView(mRightView, 1, params);
		}
	}

	public void setMasterView(View masterView, LayoutParams params) {
		if (mMasterView == null) {
			this.mMasterView = masterView;
			addView(mMasterView, 2, params);
		}
	}

	private void initData(Context context) {
		mScroller = new Scroller(context);
		mWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				LEFT_WIDTH, getResources().getDisplayMetrics());
		mTouchSlop = ViewConfiguration.get(getContext()).getScaledEdgeSlop();
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		mLeftView.layout(0, 0, mLeftView.getMeasuredWidth(),
				mLeftView.getMeasuredHeight());
		mMasterView.layout(0, 0, mMasterView.getMeasuredWidth(),
				mMasterView.getMeasuredHeight());
		mRightView.layout(0, 0, mRightView.getMeasuredWidth(),
				mRightView.getMeasuredHeight());
	}

	@Override
	public void computeScroll() {
		super.computeScroll();
		if (mScroller.computeScrollOffset()) {
			mMasterView.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			postInvalidate();
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		obtainVelocityTracker(ev);
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mTouchState = mScroller.isFinished() ? TOUCH_STATE_RESTART
					: TOUCH_STATE_SCROLLING;
			if (mTouchState == TOUCH_STATE_RESTART) {
				int x = (int) ev.getX();
				int screenWidth = getWidth();
				mScrollState = SCROLL_STATE_NO_ALLOW;

				if (mScreenState == SCREEN_STATE_LEFT_OPEN
						&& x >= screenWidth - mWidth) {
					mScrollState = SCROLL_STATE_ALLOW;
					mOnClick = true;
				}
				if (mScreenState == SCREEN_STATE_RIGHT_OPEN && x <= mWidth) {
					mScrollState = SCROLL_STATE_ALLOW;
					mOnClick = true;
				}
			} else
				return false;
			break;
		case MotionEvent.ACTION_MOVE:
			// to close left
			if (mScrollState == SCROLL_STATE_ALLOW
					&& getWidth() - (int) ev.getX() < mWidth
					&& mScreenState == SCREEN_STATE_LEFT_OPEN) {
				mWillState = SCREEN_STATE_LEFT_CLOSE;
				return true;
			}
			// to close right
			if (mScrollState == SCROLL_STATE_ALLOW && ev.getX() < mWidth
					&& mScreenState == SCREEN_STATE_RIGHT_OPEN) {
				mWillState = SCREEN_STATE_RIGHT_CLOSE;
				return true;
			}

			break;

		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			releaseVelocityTracker();
			break;

		default:
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		final float x = ev.getX();
		final float y = ev.getY();
		obtainVelocityTracker(ev);
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mLastMotionX = x;
			mLastMotionY = y;
			mTouchState = mScroller.isFinished() ? TOUCH_STATE_RESTART
					: TOUCH_STATE_SCROLLING;
			if (mTouchState == TOUCH_STATE_SCROLLING) {
				return false;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			final float dx = x - mLastMotionX;
			if (mScrollState == SCROLL_STATE_NO_ALLOW) {
				final float xDiff = Math.abs(dx);
				final float yDiff = Math.abs(y - mLastMotionY);
				mLastMotionX = x;
				mLastMotionY = y;
				if (xDiff > mTouchSlop && xDiff > yDiff) {
					if (mScrollState == SCROLL_STATE_NO_ALLOW)
						if (mScreenState == SCREEN_STATE_CLOSE) {
							mScrollState = SCROLL_STATE_ALLOW;
							Log.i(TAG, "scroll state allow");
							return false;
						}
				}
			}

			mOnClick = false;
			mVelocityTracker.computeCurrentVelocity(1000,
					ViewConfiguration.getMaximumFlingVelocity());
			if (mScrollState == SCROLL_STATE_ALLOW
					&& Math.abs(mVelocityTracker.getXVelocity()) > 200) {
				Log.i(TAG, "scroll state allow 2");
				if (dx > 0 && mScreenState == SCREEN_STATE_CLOSE) {
					if (mRightView.getVisibility() == View.VISIBLE)
						mRightView.setVisibility(View.INVISIBLE);
					if (mLeftView.getVisibility() == View.INVISIBLE)
						mLeftView.setVisibility(View.VISIBLE);

					mWillState = SCREEN_STATE_LEFT_OPEN;
				} else if (dx < 0 && mScreenState == SCREEN_STATE_CLOSE) { // �����ƣ�������rightView
					if (mLeftView.getVisibility() == View.VISIBLE)
						mLeftView.setVisibility(View.INVISIBLE);
					if (mRightView.getVisibility() == View.INVISIBLE)
						mRightView.setVisibility(View.VISIBLE);
					mWillState = SCREEN_STATE_RIGHT_OPEN;
				}

				return true;
			}
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			releaseVelocityTracker();
			break;
		default:
			break;
		}

		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		final float x = ev.getX();
		obtainVelocityTracker(ev);
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mLastMotionX = x;
			mTouchState = mScroller.isFinished() ? TOUCH_STATE_RESTART
					: TOUCH_STATE_SCROLLING;
			if (mTouchState == TOUCH_STATE_SCROLLING) {
				return false;
			}

			break;
		case MotionEvent.ACTION_MOVE:
			if (!mOnClick) {
				final float deltaX = x - mLastMotionX;
				mVelocityTracker.computeCurrentVelocity(1000,
						ViewConfiguration.getMaximumFlingVelocity());
				mVelocityValue = (int) mVelocityTracker.getXVelocity();
				mMasterView.scrollBy(-(int) deltaX, 0); // ͨ����仰����View������ָ�ƶ�
				mLastMotionX = x;
			}
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			if (mOnClick) {
				if (mScreenState == SCREEN_STATE_LEFT_OPEN)
					closeLeftView();
				else if (mScreenState == SCREEN_STATE_RIGHT_OPEN)
					closeRightView();
				mOnClick = false;
				releaseVelocityTracker();
				return super.onTouchEvent(ev);
			}
			if (mScrollState == SCROLL_STATE_ALLOW) {
				if (mWillState == SCREEN_STATE_LEFT_OPEN
						&& mScreenState == SCREEN_STATE_CLOSE) {
					if (mVelocityValue > 2000)
						openLeftView(200);
					else if (ev.getX() > getWidth() / 2)
						openLeftView();
					else
						closeLeftView();
				} else if (mWillState == SCREEN_STATE_RIGHT_OPEN
						&& mScreenState == SCREEN_STATE_CLOSE) {
					if (mVelocityValue < -2000)
						openRightView(200);
					else if (ev.getX() < getWidth() / 2)
						openRightView();
					else
						closeRightView();
				} else if (mWillState == SCREEN_STATE_LEFT_CLOSE
						&& mScreenState == SCREEN_STATE_LEFT_OPEN) {
					if (mVelocityValue < -2000)
						closeLeftView(200);
					else if (ev.getX() < getWidth() / 2)
						closeLeftView();
					else
						openLeftView();
				} else if (mWillState == SCREEN_STATE_RIGHT_CLOSE
						&& mScreenState == SCREEN_STATE_RIGHT_OPEN) {
					if (mVelocityValue > 2000)
						closeRightView(200);
					else if (ev.getX() > getWidth() / 2)
						closeRightView();
					else
						openRightView();
				} else {
					Log.i(TAG, "up other ");
					if (mMasterView.getScrollX() < 0)
						closeLeftView();
					else
						closeRightView();
				}
			}
			releaseVelocityTracker();
			break;
		default:
			break;
		}

		return super.onTouchEvent(ev);
	}

	private void obtainVelocityTracker(MotionEvent event) {
		if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(event);
	}

	private void releaseVelocityTracker() {
		if (mVelocityTracker != null) {
			mVelocityTracker.recycle();
			mVelocityTracker = null;
		}
	}

	private void openLeftView(int duration) {
		mLeftView.setVisibility(View.VISIBLE);
		mRightView.setVisibility(View.INVISIBLE);
		mTouchState = mScroller.isFinished() ? TOUCH_STATE_RESTART
				: TOUCH_STATE_SCROLLING;
		if (mTouchState == TOUCH_STATE_RESTART) {
			mScreenState = SCREEN_STATE_LEFT_OPEN;
			Log.i(TAG, "open left view");
			mScroller
					.startScroll(mMasterView.getScrollX(), 0, -(getWidth()
							- mWidth - Math.abs(mMasterView.getScrollX())), 0,
							duration);
			invalidate();
		}
	}

	public void openLeftView() {
		openLeftView(800);
	}

	private void closeLeftView(int duration) {
		Log.i(TAG, "close left view");
		mScreenState = SCREEN_STATE_CLOSE;
		mScroller.startScroll(mMasterView.getScrollX(), 0,
				-mMasterView.getScrollX(), 0, duration);
		invalidate();
	}

	public void closeLeftView() {
		closeLeftView(800);
	}

	public void openRightView(int duration) {
		System.out.println(mLeftView == null);
		System.out.println(mRightView == null);
		mLeftView.setVisibility(View.INVISIBLE);
		mRightView.setVisibility(View.VISIBLE);
		mTouchState = mScroller.isFinished() ? TOUCH_STATE_RESTART
				: TOUCH_STATE_SCROLLING;
		if (mTouchState == TOUCH_STATE_RESTART) {
			Log.i(TAG, "open right view");
			mScreenState = SCREEN_STATE_RIGHT_OPEN;
			mScroller.startScroll(mMasterView.getScrollX(), 0, (getWidth()
					- mMasterView.getScrollX() - mWidth), 0, duration);
			invalidate();
		}
	}

	public void openRightView() {
		openRightView(800);
	}

	public void closeRightView(int duration) {
		Log.i(TAG, "close right view");
		mScreenState = SCREEN_STATE_CLOSE;
		mScroller.startScroll(mMasterView.getScrollX(), 0,
				-mMasterView.getScrollX(), 0, duration);
		invalidate();
	}

	public void closeRightView() {
		closeRightView(800);
	}

	public int getLeftWidth() {
		return mWidth;
	}
}
