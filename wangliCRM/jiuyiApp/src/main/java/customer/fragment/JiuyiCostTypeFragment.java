package customer.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wanglicrm.android.R;

import customer.anim.Anim;
import customer.listener.HistogramData;
import customer.view.Histogram;


public class JiuyiCostTypeFragment extends Fragment {
    private static final String LAYOUT_RES = "LAYOUT_RES";
    private int layoutRes;
    float[] ydata = new float[7];
    private int[] rectColor=new int[7];
    public View mRootView;//

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param layoutRes layout resource.
     * @return A new instance of fragment JiuyiCostTypeFragment.
     */
    public static JiuyiCostTypeFragment newInstance(int layoutRes) {
        JiuyiCostTypeFragment fragment = new JiuyiCostTypeFragment();
        Bundle args = new Bundle();
        args.putInt(LAYOUT_RES, layoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            layoutRes=getArguments().getInt(LAYOUT_RES, R.layout.jiuyi_customerfragment_costtype);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(layoutRes, container, false);
        onInit();
        return mRootView;
    }
    public void onInit() {
        for (int i=0;i<7;i++){
            ydata[i] = (int) (Math.random() * 50.0f);
            rectColor[i]=R.color.jiuyi_cost_color;
        }
        final String[] xdata = new String[]{"差旅","招待","礼品","服务","其他"};
        final Histogram histogramChart = (Histogram)mRootView.findViewById(R.id.histogramchart);
        final HistogramData histogramData = HistogramData.builder()
                .setXdata(xdata)
                .setYdata(ydata)
                .setXpCount(5)
                .setYpCount(2)
                .setRectColor(rectColor)
                .setCoordinatesColor(R.color.jiuyi_cost_color)
                .setAnimType(Anim.ANIM_ALPHA)
                .build();
        histogramChart.setChartData(histogramData);
    }

}
