package com.wheelsinmotion.jose.wimo.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

/**
 * Created by JoseMaria on 23/11/2014.
 */
public class CustomViewFlipper extends ViewFlipper{
    private Paint paint;
    //TODO add constructor that receives an array or list of colors, then paint the selected dot whit every color from there
    //Other constructor that receives only one color.
    //TODO instead of using magic numbers for the size and positions check the best parameters relative to screen.
    //TODO boolean to hide the indicators? Or better the add them?

    public CustomViewFlipper(Context context, AttributeSet attributes){
        super(context, attributes);
        paint = new Paint();
    }

    @Override
    protected void dispatchDraw(Canvas canvas){
        super.dispatchDraw(canvas);

        float margin = 3;
        float radius = 15;
        float radiusSelected = 10;
        float positionX = getWidth() / 2 - ((radius + margin) * 2 * getChildCount() / 2);
        float positionY = getHeight() - 25;

        canvas.save();

        //Draw every dot indicator
        for (int i = 0; i < getChildCount(); i++){
            if (i == getDisplayedChild()) {
                paint.setColor(Color.WHITE);
                canvas.drawCircle(positionX, positionY, radius, paint);
                paint.setColor(Color.BLACK);
                canvas.drawCircle(positionX, positionY, radiusSelected, paint);
            }
            else{
                paint.setColor(Color.WHITE);
                canvas.drawCircle(positionX, positionY, radius, paint);
            }
            positionX += 2 * (radius + margin);
        }
        canvas.restore();
    }

}