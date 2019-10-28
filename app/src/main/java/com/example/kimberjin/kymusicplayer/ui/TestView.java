package com.example.kimberjin.kymusicplayer.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ky4910 on 2019/10/28
 */
public class TestView extends View {

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(100, widthMeasureSpec);
        int height = getMySize(100, heightMeasureSpec);

        if (width < height) {
            height = width;
        } else {
            width = height;
        }

        setMeasuredDimension(width, height);
    }

    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            //如果没有指定大小，就设置为默认大小
            case MeasureSpec.UNSPECIFIED:
                mySize = defaultSize;
                break;
            //如果测量模式是最大取值为size
            case MeasureSpec.AT_MOST:
                //我们将大小取最大值,你也可以取其他值
                mySize = size;
                break;
            //如果是固定的大小，那就不要去改变它
            case MeasureSpec.EXACTLY:
                mySize = size;
                break;
        }
        return mySize;
    }
}
