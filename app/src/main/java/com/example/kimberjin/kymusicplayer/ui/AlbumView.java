package com.example.kimberjin.kymusicplayer.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.kimberjin.kymusicplayer.R;

/**
 * Created by ky4910 on 2019/10/29
 */
public class AlbumView extends View {

    public static final String TAG = "Album_View";

    private static final int MSG_RUN = 0x00000100;
    private static final int TIME_UPDATE = 50;

    private Bitmap mCircleBitmap;
    private Bitmap mClipBitmap; // cd图片

    private float mRotation = 0.0f;

    private Matrix mMatrix;

    private volatile boolean isRunning;

    public AlbumView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AlbumView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mCircleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cd_center);
        mMatrix = new Matrix();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isRunning = false;
    }

    /* 先于onDraw方法执行 */

    /*
        MeasureSpec封装了父布局传递给子布局的布局要求，每个MeasureSpec代表了一组宽度和高度的要求。
        MeasureSpec由大小和模式组成，它有三种模式：
            UNSPECIFIED(未指定),父元素部队自元素施加任何束缚，子元素可以得到任意想要的大小；
            EXACTLY(完全)，父元素决定自元素的确切大小，子元素将被限定在给定的边界里而忽略它本身大小；
            AT_MOST(至多)，子元素至多达到指定大小的值。
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mClipBitmap == null){
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }

        int width = 0;
        int height = 0;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        /*
            测量模式	            表示意思
           UNSPECIFIED	父容器没有对当前View有任何限制，当前View可以任意取尺寸
            EXACTLY	    当前的尺寸就是当前View应该取的尺寸 (match_parent)
            AT_MOST	    当前尺寸是当前View能取的最大尺寸 (wrap_content)
         */

        // parent assign the size
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            // child compute the size of itself
            width = mClipBitmap.getWidth();
            // parent assign the max size, child should <= widthSize
            if (widthMode == MeasureSpec.AT_MOST) {
                width = Math.min(width, widthSize);
            }
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = mClipBitmap.getHeight();
            if(heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(height, heightSize);
            }
        }

        Log.e(TAG, "setMeasuredDimension: " + width + ", " + height);
        setMeasuredDimension(width, height);
    }

    /*
        save()和restore()方法分别是保存和恢复Canvas状态的，都没有参数
        save():用来保存Canvas的状态。save之后，可以调用Canvas的平移、放缩、旋转、错切、裁剪等操作。
        restore():用来恢复Canvas之前保存的状态。防止save后对Canvas执行的操作对后续的绘制有影响。
        save和restore要配对使用，restore可以比save少，但不能多。Otherwise Error...
     */
    @Override
    protected void onDraw(Canvas canvas) {
        if (mClipBitmap == null)
            return;
        canvas.save();
        mMatrix.setRotate(mRotation, getMeasuredWidth()/2, getMeasuredHeight()/2);
        canvas.drawBitmap(mClipBitmap, mMatrix, null);
        canvas.drawBitmap(mCircleBitmap, (getMeasuredWidth() - mCircleBitmap.getWidth()/2),
                (getMeasuredHeight() - mCircleBitmap.getHeight()/2), null);
        canvas.restore();
    }

    /*
        getMeasuredWidth()获取的是view原始的大小，也就是这个view在XML文件中配置或者是代码中设置的大小。
        getWidth（）获取的是这个view最终显示的大小，这个大小有可能等于原始的大小也有可能不等于原始大小。
     */
    // 创建圆形剪切图
    private Bitmap createCircleBitmap(Bitmap src) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setARGB(255, 241, 239, 229);

        /*
            创建一个getMeasuredWidth()*getMeasuredHeight()大小的Bitmap，使用它作为Canvas的操作对象
         */
        Bitmap target = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);

        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredWidth() / 2,
                getMeasuredWidth() / 2, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(src, 0, 0, paint);

        return target;
    }

    // 设置Album图片
    public void setImage(Bitmap bmp) {
        int widthSize = bmp.getWidth();
        int heightSize = bmp.getHeight();
        int widthSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.AT_MOST);
        int heightSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.AT_MOST);

        Log.e(TAG, "widthSize:" + widthSize + " heightSize:" + heightSize
            + " widthSpec:" + widthSpec + " heightSpec:" + heightSpec);

        measure(widthSpec, heightSpec);

        mClipBitmap = createCircleBitmap(bmp);
        Log.e(TAG, "width:" + bmp.getWidth() + " height:" + bmp.getHeight());
        requestLayout();
        invalidate();
    }

    /**
     * 开始旋转
     */
    public void start() {
        if(isRunning) return;
        isRunning = true;
        mHandler.sendEmptyMessageDelayed(MSG_RUN, TIME_UPDATE);
    }

    /**
     * 暂停旋转
     */
    public void pause() {
        if(!isRunning) return;
        isRunning = false;
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if(message.what == MSG_RUN) {
                if(!isRunning) return false;

                mRotation += 0.1f;
                if(mRotation >= 360) mRotation = 0;
                // 调用invalidate(), 会触发onDraw()和computeScroll()
                invalidate();
                mHandler.sendEmptyMessageDelayed(MSG_RUN, TIME_UPDATE);
            }
            return false;
        }
    });
}
