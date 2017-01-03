package zl.com.myapplication;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by Mon on 2017/1/3.
 */
public class TBehavior extends CoordinatorLayout.Behavior<ViewGroup> implements AbsListView.OnScrollListener {

    private float lastV = 0;
    private ViewGroup child;
    private View dependency;
    private int height;
    private ImageView imageView;

    public TBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ViewGroup child, View dependency) {

        return dependency instanceof ListView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ViewGroup child, View dependency) {

        if (dependency instanceof ListView) {
            ((ListView) dependency).setOnScrollListener(this);
            this.child = child;
            this.dependency = dependency;

            imageView = ((ImageView) child.getChildAt(0));

            height = dependency.getHeight();
//            int height = child.getHeight();
//            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) dependency.getLayoutParams();
//            layoutParams.setMargins(0, height, 0, 0);
        }

        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int j, int i1, int i2) {
        if (dependency != null && child != null) {
            int scrollY = getScrollY();
            int height = 0;
            View childAt = ((ListView) dependency).getChildAt(0);
            childAt.measure(0, 0);
            int measuredHeight = childAt.getMeasuredHeight();
            height = measuredHeight * ((ListView) dependency).getAdapter().getCount();

            float v = (float) scrollY / (height - this.height);

//            ObjectAnimator animator = ObjectAnimator.ofFloat(child, View.ALPHA, lastV, v).setDuration(0);
//            animator.start();

            if (v >= 1)
                v = 1;

            child.setBackgroundColor(Color.rgb((int) (255 * (1 - v)), (int) (255 * (1 - v)), (int) (255 * (1 - v))));

//            lastV = v;

            if (v != 0) {
                imageView.setImageResource(R.drawable.goods_select);
            } else {
                imageView.setImageResource(R.drawable.goods);
            }
        }
    }

    public int getScrollY() {
        View c = ((ListView) dependency).getChildAt(0);
        if (c == null) {
            return 0;
        }
        int firstVisiblePosition = ((ListView) dependency).getFirstVisiblePosition();
        int top = c.getTop();
        return -top + firstVisiblePosition * c.getHeight();
    }
}
