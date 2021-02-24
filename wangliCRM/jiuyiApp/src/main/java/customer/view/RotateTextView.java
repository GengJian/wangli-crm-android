package customer.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

import com.control.utils.Res;

/**旋转90度的TextView
 * Created by Chris on 2017/8/15.
 */

@SuppressLint("AppCompatCustomView")
public class RotateTextView extends TextView {

    private static final int DEFAULT_VALUE_ROTATE = 0;

    private int rotate = DEFAULT_VALUE_ROTATE;

    public RotateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        rotate = 90;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(Res.Dip2Pix(10),0);
        canvas.rotate(rotate);
        canvas.translate(0,-Res.Dip2Pix(10));
        super.onDraw(canvas);
    }

}
