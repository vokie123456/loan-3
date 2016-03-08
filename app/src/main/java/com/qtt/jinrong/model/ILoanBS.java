package com.qtt.jinrong.model;

import android.content.Context;

import com.qtt.framework.http.MCListenerObj;
import com.qtt.jinrong.bean.loan.LoanListRequest;
import com.qtt.jinrong.bean.loan.LoanProductDetailRequest;

/**
 * Created by yanxin on 16/3/7.
 */
public interface ILoanBS extends IBS {

    /**
     * 请求贷款产品列表
     * @param context
     * @param request
     * @param listenerObj
     */
    void requestList(Context context, LoanListRequest request, MCListenerObj.IObjResListener listenerObj);

    /**
     * 请求贷款产品详情
     * @param context
     * @param request
     * @param listenerObj
     */
    void requestLoanProductDetail(Context context, LoanProductDetailRequest request, MCListenerObj.IObjResListener listenerObj);

}