package customer.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wanglicrm.android.R;

import java.util.ArrayList;
import java.util.List;

import customer.entity.PieData;
import customer.view.PieChart;


public class JiuyiIronTriFragment extends Fragment {
    private static final String LAYOUT_RES = "LAYOUT_RES";
    private int layoutRes;
    public View mRootView;//

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param layoutRes layout resource.
     * @return A new instance of fragment JiuyiCostTypeFragment.
     */
    public static JiuyiIronTriFragment newInstance(int layoutRes) {
        JiuyiIronTriFragment fragment = new JiuyiIronTriFragment();
        Bundle args = new Bundle();
        args.putInt(LAYOUT_RES, layoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            layoutRes=getArguments().getInt(LAYOUT_RES, R.layout.jiuyi_customerfragment_irontri);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(layoutRes, container, false);
        onInit();
        return mRootView;
    }
    public void onInit() {
        PieChart vPie =(PieChart)mRootView.findViewById(R.id.v_pie);
        List<PieData> pieData = new ArrayList<>();
        pieData.add(new PieData("AR", (float) Math.random() * 90, vPie.getCOLORS()[0]));
        pieData.add(new PieData("FR", (float) Math.random() * 90, vPie.getCOLORS()[2]));
        pieData.add(new PieData("SR", (float) Math.random() * 90, vPie.getCOLORS()[3]));
//        for (int i = 0; i < 3; i++) {
//            pieData.add(new PieData("text" + i, (float) Math.random() * 90, vPie.getCOLORS()[i]));
//        }
        vPie.setData(pieData);
    }
}
