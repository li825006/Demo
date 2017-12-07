package com.example.kaoshi1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by acer-pc on 2017/11/27.
 */

@SuppressLint("AppCompatCustomView")
public class Yuan extends ImageView {
    private BitmapShader shader;
    private Bitmap mm;
    private Paint paint;
    private Matrix matrix;

    public Yuan(Context context) {
        this(context, null);
    }

    public Yuan(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable drawable = getDrawable();
        Bitmap bitmap = getDr(drawable);
        if (bitmap != null) {
            int width = getWidth();
            int height = getHeight();
            float minSize = Math.min(width, height);
            if (shader == null || !bitmap.equals(mm)) {
                mm = bitmap;
                shader = new BitmapShader(mm, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                if (shader != null) {
                    matrix.setScale(minSize / mm.getWidth(), minSize / mm.getHeight());
                    shader.setLocalMatrix(matrix);
                }
                paint.setShader(shader);
                float radius = minSize / 2;
                canvas.drawCircle(radius, radius, radius, paint);
            } else {
                super.onDraw(canvas);
            }
        }
    }

    public Bitmap getDr(Drawable drawable) {

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof ColorDrawable) {
            Rect bounds = drawable.getBounds();
            int dWidth = bounds.width();
            int dHeidht = bounds.height();

            Bitmap bitmap = Bitmap.createBitmap(dWidth, dHeidht, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            int color = ((ColorDrawable) drawable).getColor();
            canvas.drawARGB(Color.alpha(color), Color.red(color), Color.green(color), Color.blue(color));
            return bitmap;
        }
        return null;
    }
}
