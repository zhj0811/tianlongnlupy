package com.cctegitc.ai.function.util;


import com.cctegitc.ai.authority.common.res.ResultResponse;

import java.util.List;

public  class ResponseUtils {

    public static <T> void buildResponsePutList(ResultResponse res, List<T> dataList, String errorMessage) {
        if (dataList != null && !dataList.isEmpty()) {
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData(dataList);
        } else {
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            // 使用传入的错误消息
            res.setData(errorMessage);
        }
    }

    public static <T> void buildResponsePutObj(ResultResponse res, T Object, String errorMessage) {
        if (Object != null) {
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData(Object);
        } else {
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            // 使用传入的错误消息
            res.setData(errorMessage);
        }
    }

    public static void buildExceptionResp(Exception e, ResultResponse res) {
        res.setCode(Constants.STATUS_FAIL);
        res.setMessage("系统繁忙，请稍后再试");
        res.setData(e.getMessage());
    }

}
