package com.android.finance.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.android.finance.R;
import com.android.finance.bean.credit.CreditModel;
import com.android.finance.bean.event.CreditLevelEvent;
import com.android.finance.enums.CreditLevelEnum;
import com.android.finance.ui.activity.credit.CreditDetailActivity;
import com.android.finance.ui.adapter.CreditAdapter;
import com.android.finance.ui.fragment.common.BaseFragment;
import com.android.finance.ui.widget.CommonTitleBar;
import com.android.finance.ui.widget.filter.FilterCreditAdapter;
import com.android.finance.ui.widget.filter.FilterManager;
import com.android.finance.ui.widget.filter.FilterView;
import com.android.finance.ui.widget.load.BottomRefreshListView;
import com.android.finance.ui.widget.load.RefreshLayout;
import com.finance.framework.util.GeneratedClassUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by yanxin on 16/2/23.
 */
@EFragment(R.layout.fragment_credit)
public class CreditFragment extends BaseFragment {

    View mView;

    @ViewById(R.id.titleBar)
    CommonTitleBar mCommonTitleBar;

    @ViewById(R.id.filterView)
    FilterView mFilterView;

    @ViewById(R.id.refreshLayout)
    RefreshLayout mSwipeRefreshLayout;

    @ViewById(R.id.listview)
    BottomRefreshListView mBottomRefreshListView;

    CreditAdapter mCreditAdapter;
    FilterManager mFilterManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mView == null) mView = inflater.inflate(R.layout.fragment_credit, container, false);
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }
        return mView;
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected void initView() {
        if(isInit) return;
        super.initView();

        mCommonTitleBar.hideLeft();
        mCommonTitleBar.setTitle(getString(R.string.credit_title));
        mSwipeRefreshLayout.setListView(mBottomRefreshListView);

        mBottomRefreshListView.addHeaderView(new View(getActivity()));
        mCreditAdapter = new CreditAdapter(getActivity());
        mBottomRefreshListView.setAdapter(mCreditAdapter);

        mBottomRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), GeneratedClassUtils.get(CreditDetailActivity.class));
                startActivity(intent);
            }
        });

        //设置筛选
        mFilterManager = new FilterManager();
        FilterCreditAdapter filterAdapter = new FilterCreditAdapter(getContext(),mFilterManager);
        int[] mFilter123Selected = {0,0,0};
        filterAdapter.setData(getResources().getStringArray(R.array.credit_bank),
                getResources().getStringArray(R.array.credit_type),
                getResources().getStringArray(R.array.credit_level),
                mFilter123Selected);
        mFilterManager.setComponents(mFilterView, filterAdapter);

        requestLoans();
    }

    private void requestLoans() {
        List<CreditModel> lists = new ArrayList<>();

        CreditModel model = new CreditModel();
        model.setId("123");
        model.setScore(3.5f);
        model.setInterest(2321);
        model.setName("平安银行-新一贷");
        model.setMonthPay(8765);
        model.setType("平安银行");
        lists.add(model);

        model = new CreditModel();
        model.setId("123");
        model.setScore(2.5f);
        model.setInterest(2321);
        model.setName("平安银行-新一贷");
        model.setMonthPay(8765);
        model.setType("平安银行");
        lists.add(model);

        model = new CreditModel();
        model.setId("123");
        model.setScore(3f);
        model.setInterest(2321);
        model.setName("平安银行-新一贷");
        model.setMonthPay(8765);
        model.setType("平安银行");
        lists.add(model);

        model = new CreditModel();
        model.setId("123");
        model.setScore(5f);
        model.setInterest(2321);
        model.setName("平安银行-新一贷");
        model.setMonthPay(8765);
        model.setType("平安银行");
        lists.add(model);

        model = new CreditModel();
        model.setId("123");
        model.setScore(4f);
        model.setInterest(2321);
        model.setName("平安银行-新一贷");
        model.setMonthPay(8765);
        model.setType("平安银行");
        lists.add(model);

        model = new CreditModel();
        model.setId("123");
        model.setScore(4f);
        model.setInterest(2321);
        model.setName("平安银行-新一贷");
        model.setMonthPay(8765);
        model.setType("平安银行");
        lists.add(model);

        model = new CreditModel();
        model.setId("123");
        model.setScore(1f);
        model.setInterest(2321);
        model.setName("平安银行-新一贷");
        model.setMonthPay(8765);
        model.setType("平安银行");
        lists.add(model);

        model = new CreditModel();
        model.setId("123");
        model.setScore(0.5f);
        model.setInterest(2321);
        model.setName("平安银行-新一贷");
        model.setMonthPay(8765);
        model.setType("平安银行");
        lists.add(model);

        mCreditAdapter.add(lists);
    }

    public void onEventMainThread(CreditLevelEvent event) {
        CreditLevelEnum levelEnum = event.getLevel();
        if(levelEnum == null) levelEnum = CreditLevelEnum.不限;
        mFilterManager.setSelect(3,levelEnum.getCode(),"");
    }

}