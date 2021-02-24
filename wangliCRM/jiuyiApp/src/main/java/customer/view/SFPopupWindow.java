package customer.view;

import android.graphics.Rect;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

import com.control.utils.Res;

public class SFPopupWindow extends PopupWindow {

    public SFPopupWindow(View contentView, int width, int height) {
        super(contentView, width,height);
    }

    public  void showAsDropDown( View anchor, int xoff, int yoff) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
            super.showAsDropDown(anchor, xoff, yoff);
        } else {
            super.showAsDropDown(anchor, xoff, yoff);
        }
    }

}