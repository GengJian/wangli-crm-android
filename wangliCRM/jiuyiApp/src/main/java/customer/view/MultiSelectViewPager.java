package customer.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * 多级选择Viewpage
 */
public class MultiSelectViewPager extends ViewPager {

    public MultiSelectViewPager(Context context) {
        super(context);
    }

    public MultiSelectViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    

    @Override
        public void scrollTo(int x, int y) {
         if (getAdapter()==null || x>getWidth()*(getAdapter().getCount()-2)){
            return;
        }
        super.scrollTo(x, y);
    }
}
