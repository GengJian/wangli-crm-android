package customer.listener;



import java.util.List;

import customer.view.FlowTagLayout;

/**
 * 标签流
 */
public interface OnTagSelectListener {
    void onItemSelect(FlowTagLayout parent, List<Integer> selectedList);
}
