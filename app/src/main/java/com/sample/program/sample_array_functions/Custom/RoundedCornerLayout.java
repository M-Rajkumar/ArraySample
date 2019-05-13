package com.sample.program.sample_array_functions.Custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class RoundedCornerLayout extends RelativeLayout {
    private Bitmap maskBitmap;
    private Paint paint;
    private float cornerRadius;

    public RoundedCornerLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public RoundedCornerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public RoundedCornerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        setWillNotDraw(false);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (maskBitmap == null) {
            // This corner radius assumes the image width == height and you want it to be circular
            // Otherwise, customize the radius as needed
            cornerRadius = canvas.getHeight()/2;
            maskBitmap = createMask(canvas.getWidth(), canvas.getHeight());
        }

        canvas.drawBitmap(maskBitmap, 0f, 0f, paint);
    }

    private Bitmap createMask(int width, int height) {
        Bitmap mask = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mask);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE); // TODO set your background color as needed

        canvas.drawRect(0, 0, width, height, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
//        canvas.drawRoundRect(new RectF(0, 0, width, height), cornerRadius, cornerRadius, paint);
//        drawRoundedRect2(canvas,10, 10, 10, 10, paint);

        int offset = 0;

        RectF rectF = new RectF(
                offset, // left
                offset, // top
                canvas.getWidth() - offset, // right
                canvas.getHeight() - offset // bottom
        );


        int cornersRadius = 25;

        // Finally, draw the rounded corners rectangle object on the canvas
        canvas.drawRoundRect(
                rectF, // rect
                cornersRadius, // rx
                cornersRadius, // ry
                paint // Paint
        );

        return mask;
    }

    private void drawRoundedRect2(Canvas canvas, float left, float top, float right, float bottom, Paint paint) {
        float radius = getHeight()/2;
        canvas.drawCircle(radius, radius, radius, paint);
        canvas.drawCircle(right - radius, radius, radius, paint);
        canvas.drawRect(left + radius, top, right - radius, bottom, paint);
    }
}

