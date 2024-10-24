package com.cctegitc.ai.function.modules.intelligentduty.controller;

import com.cctegitc.ai.function.db.pojo.Notification;
import com.cctegitc.ai.function.modules.intelligentduty.service.impl.NotificationTaskServiceImpl;
import com.cctegitc.ai.function.modules.intelligentduty.service.impl.NotificationsServiceImpl;
import com.cctegitc.ai.function.util.Constants;
import com.cctegitc.ai.function.util.common.res.ResultResponse;
import com.cctegitc.ai.function.vo.pagefind.NotificationPageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@Api(tags = "语音通知模块")
@RequestMapping("/notification")
@Slf4j
public class NotificationController {

    @Autowired
    private NotificationsServiceImpl notificationsService;

    @Autowired
    private NotificationTaskServiceImpl notificationTaskService;

    @ApiOperation(value = "所有表单")
    @GetMapping("/findAll")
    public ResultResponse findAll() {
        try {
            List<Notification> notificationList = notificationsService.findAll();
            if (notificationList != null && !notificationList.isEmpty()) {
                return new ResultResponse(Constants.STATUS_OK, Constants.MESSAGE_OK, notificationList);
            } else {
                return new ResultResponse(Constants.STATUS_FAIL, Constants.MESSAGE_FAIL, Constants.FAIL_TO_GET);
            }
        } catch (Exception e) {
            return new ResultResponse(Constants.STATUS_FAIL, Constants.MESSAGE_FAIL, e.getMessage() + "," +  Constants.FAIL_TO_GET);
        }
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("/findByPage")
    public ResultResponse findByPage(Integer page, Integer limit) {
        try {
            NotificationPageVO notificationPageVO = notificationsService.findByPage(page, limit);
            if (Objects.nonNull(notificationPageVO)) {
                return new ResultResponse(Constants.STATUS_OK, Constants.MESSAGE_OK, notificationPageVO);
            } else {
                return new ResultResponse(Constants.STATUS_FAIL, Constants.MESSAGE_FAIL, Constants.FAIL_TO_GET);
            }
        } catch (Exception e) {
            return new ResultResponse(Constants.STATUS_FAIL, Constants.MESSAGE_FAIL, e.getMessage() + "，" + Constants.FAIL_TO_GET);
        }
    }

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public ResultResponse add(@RequestBody Notification notification) {
        try {
            int i = notificationsService.add(notification);
            if (i > 0) {
                try {
                    notificationTaskService.createTask(notification);
                } catch (Exception e) {
                    log.error("创建语音通知任务失败", e);
                }
                return new ResultResponse(Constants.STATUS_OK, Constants.MESSAGE_OK, Constants.SAVE_SUCESS);
            } else {
                return new ResultResponse(Constants.STATUS_FAIL, Constants.MESSAGE_FAIL, Constants.SAVE_FAILED);
            }
        } catch (Exception e) {
            return new ResultResponse(Constants.STATUS_FAIL, Constants.MESSAGE_FAIL, e + "," + Constants.SAVE_FAILED);
        }
    }

    @ApiOperation(value = "更新")
    @PostMapping("/update")
    public ResultResponse update(@RequestBody Notification notification) {
        ResultResponse resultResponse = new ResultResponse();
        try {
            int i = notificationsService.updateData(notification);
            if (i > 1) {
                resultResponse.setCode(Constants.STATUS_OK);
                resultResponse.setMessage(Constants.MESSAGE_OK);
                resultResponse.setData(Constants.UPDATE_SUCESS);
                try {
                    notificationTaskService.updateTask(notification);
                } catch (Exception e) {
                    log.error("更新语音通知任务失败", e);
                }
            } else {
                resultResponse.setCode(Constants.STATUS_FAIL);
                resultResponse.setMessage(Constants.MESSAGE_FAIL);
                resultResponse.setData(Constants.UPDATE_FAILED);
            }
        } catch (Exception e) {
            resultResponse.setCode(Constants.STATUS_FAIL);
            resultResponse.setMessage(Constants.MESSAGE_FAIL);
            resultResponse.setData(e.getMessage() + "，" + Constants.UPDATE_FAILED);
        }
        return resultResponse;
    }

    @ApiOperation(value = "删除")
    @PostMapping("delete/{id}")
    public ResultResponse delete(@PathVariable(value = "id") Long id) {
        ResultResponse res = new ResultResponse();
        try {
            int i = notificationsService.delete(id);
            if (i == 1) {
                res.setCode(Constants.STATUS_OK);
                res.setMessage(Constants.MESSAGE_OK);
                res.setData(Constants.DELETE_SUCESS);
                try {
                    notificationTaskService.deleteTask(id);
                } catch (Exception e) {
                    log.error("更新语音通知任务失败", e);
                }
            } else {
                res.setCode(Constants.STATUS_FAIL);
                res.setMessage(Constants.MESSAGE_FAIL);
                res.setData(Constants.OPT_FAILED);
            }
        } catch (Exception e) {
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData(e.getMessage() + "，" + Constants.DELETE_FAILED);
        }
        return res;
    }

    @ApiOperation(value = "id查询")
    @GetMapping("/findById/{id}")
    public ResultResponse findById(@PathVariable(value = "id") Long id) {
        ResultResponse res = new ResultResponse();
        try {
            Notification notification = notificationsService.findById(id);
            if (Objects.nonNull(notification)) {
                res.setCode(Constants.STATUS_OK);
                res.setMessage(Constants.MESSAGE_OK);
                res.setData(notification);
            } else {
                res.setCode(Constants.STATUS_FAIL);
                res.setMessage(Constants.MESSAGE_FAIL);
                res.setData(Constants.FAIL_TO_GET);
            }
        } catch (Exception e) {
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData(e.getMessage() + "，" + Constants.FAIL_TO_GET);
        }
        return res;
    }
}
