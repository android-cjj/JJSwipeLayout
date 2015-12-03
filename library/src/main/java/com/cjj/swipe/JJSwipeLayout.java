package com.cjj.swipe;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by cjj on 2015/12/3.
 */
public class JJSwipeLayout extends LinearLayout {

    /**打印log的tag*/
    private final static String Tag = JJSwipeLayout.class.getSimpleName();

    /**内容界面*/
    private ViewGroup mContentLayout;

    /**遮盖界面*/
    private ViewGroup mBehindLayout;

    /**这个我不知道怎么注释*/
    private ViewDragHelper mViewDragHelper;

    /**手势事件类*/
    private GestureDetectorCompat mGestureDetectorCompat;

    /**左滑距离*/
    private int mSwipeLeft;

    /**左滑最大距离*/
    private int mSwipeWidth;

    /**宽度*/
    private int mWidth;

    /**高度*/
    private int mHeight;

    /**滑动监听*/
    private SwipeListener mSwipeBackListener;

    private boolean isAnim = false;

    private boolean isOpen = false;


    public JJSwipeLayout(Context context) {
        super(context);
        init();
    }

    public JJSwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public JJSwipeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCallback());//创建ViewDragHelper的实例，第一个参数是ViewGroup，传自己，第二个灵敏度，一般正常是1.0，不正常你自己写，第三个是回调，看下面...
        mGestureDetectorCompat = new GestureDetectorCompat(getContext(), new XScrollDetector());//手势操作，第二参数什么意思看下面
    }

    /**
     * View 中所有的子控件均被映射成xml后触发
     */
    @Override
    protected void onFinishInflate() {
        mContentLayout = (ViewGroup) getChildAt(0);
        mBehindLayout = (ViewGroup) getChildAt(1);
        mBehindLayout.setClickable(true);
        mContentLayout.setClickable(true);
        super.onFinishInflate();
    }

    /**
     * 手势监听回调
     */
    class XScrollDetector extends GestureDetector.SimpleOnGestureListener {//SimpleOnGestureListener为了不用重写那么多监听的帮助类
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            return Math.abs(distanceY) <= Math.abs(distanceX);//判断是否是滑动的x距离>y距离
        }


    }

    public boolean isOpen() {
        return isOpen;
    }

    public interface SwipeListener {
        /**打开*/
        public void onOpen();

        /**关闭*/
        public void onClose();

    }


    /**
     * 事件拦截
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev) && mGestureDetectorCompat.onTouchEvent(ev);
    }

    /**
     * 事件监听，交给ViewDragHelper处理
     * @param e
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction())
        {

            case MotionEvent.ACTION_MOVE:

                getParent().requestDisallowInterceptTouchEvent(true);

                break;


        }
        try {
            mViewDragHelper.processTouchEvent(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * 当view的大小发生变化时触发
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = mContentLayout.getMeasuredWidth();
        mHeight = mContentLayout.getMeasuredHeight();
        mSwipeWidth = mBehindLayout.getMeasuredWidth();

    }

    /**
     * 设置监听
     * @param swipeBackListener
     */
    public void setOnSwipeBackListener(SwipeListener swipeBackListener) {
        this.mSwipeBackListener = swipeBackListener;
    }


    /**
     * 打开
     */
    public void open() {
        if (mViewDragHelper.smoothSlideViewTo(mContentLayout, -mSwipeWidth, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        if (null != mSwipeBackListener) mSwipeBackListener.onOpen();
        isOpen = true;
    }


    /**
     * 关闭
     */
    public void close() {
        if (mViewDragHelper.smoothSlideViewTo(mContentLayout, 0, 0)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        if (null != mSwipeBackListener) mSwipeBackListener.onClose();
        isOpen = false;
    }


    /**
     *draw() 过程 调用 该 方法
     */
    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setAlphaAnim(boolean isAnim){
        this.isAnim = isAnim;
    }


    private class DragHelperCallback extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return mWidth;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (child == mContentLayout) {
                int newLeft = Math.min(Math.max((-getPaddingLeft()-mSwipeWidth), left), 0);
                return newLeft;
            } else{
                int newLeft = Math.min(Math.max(left, getPaddingLeft()+mWidth-mSwipeWidth), getPaddingLeft()+mWidth+getPaddingRight());
                return newLeft;
            }

        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            Log.i(Tag, "onViewReleased");
            if (xvel > 0) {
                close();
            } else if (xvel < 0) {
                open();
            } else if (-mSwipeLeft <= mSwipeWidth * 0.5) {
                open();
            } else if (-mSwipeLeft > mSwipeWidth * 0.5) {
                close();
            } else {
                close();
            }
        }



        @Override
        public void onViewPositionChanged(View changedView, int left, int top,
                                          int dx, int dy) {
            Log.i(Tag,"onViewPositionChanged");
            mSwipeLeft = left;
            float per = Math.abs((float)left/(float)mSwipeWidth);
            if (changedView == mContentLayout) {
                mBehindLayout.offsetLeftAndRight(dx);
                if(isAnim)
                mBehindLayout.setAlpha(per);
            } else {
                mContentLayout.offsetLeftAndRight(dx);
            }
            invalidate();
        }
    }

}
