package com.cctegitc.ai.function.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cctegitc.ai.function.db.dao.OutCallnotificationsMapper;
import com.cctegitc.ai.function.db.pojo.OutCallnotifications;
import com.cctegitc.ai.function.scheduleTask.ScheduledExceutor;
import com.cctegitc.ai.function.modules.intelligentduty.service.IOutCallnotificationsService;
import com.cctegitc.ai.function.util.TimeUtils;
import com.cctegitc.ai.function.vo.pagefind.OutCallnotificationsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class OutCallnotificationsServiceImpl implements IOutCallnotificationsService {

    @Resource
    private OutCallnotificationsMapper outCallnotificationsMapper;

    //定时外呼线程池
    private ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

    /**
     * 查看所有测库信息
     *
     * @return
     */
    @Override
    public List<OutCallnotifications> findAll() {
        QueryWrapper<OutCallnotifications> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("on_off", "1");
        return outCallnotificationsMapper.selectList(queryWrapper);
    }

    /**
     * 分页查看所有外呼信息
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public OutCallnotificationsVo findByPage(OutCallnotifications outCallnotifications, Integer page, Integer limit) {
        OutCallnotificationsVo outCallnotificationsVo = new OutCallnotificationsVo();
        QueryWrapper<OutCallnotifications> queryWrapper = new QueryWrapper<>();
        if (!"".equals(outCallnotifications.getCallContent())) {
            queryWrapper.eq("call_content", outCallnotifications.getCallContent());
        }
        if (!"".equals(outCallnotifications.getModel())) {
            queryWrapper.eq("model", outCallnotifications.getModel());
        }
        if (!"".equals(outCallnotifications.getOnOff())) {
            queryWrapper.eq("on_off", outCallnotifications.getOnOff());
        }
        if (!"".equals(outCallnotifications.getCallDate())) {
            queryWrapper.eq("call_date", outCallnotifications.getCallDate());
        }
        if (!"".equals(outCallnotifications.getUserId())) {
            queryWrapper.eq("user_id", outCallnotifications.getUserId());
        }
        Page<OutCallnotifications> objectPage = new Page<>(page, limit);
        Page<OutCallnotifications> outCallnotificationsPage = outCallnotificationsMapper.selectPage(objectPage, queryWrapper);
        outCallnotificationsVo.setPage(page);
        outCallnotificationsVo.setLimit(limit);
        if (outCallnotificationsPage.getSize() == 0) {
            outCallnotificationsVo.setTotal(Long.parseLong("0"));
            outCallnotificationsVo.setItems(null);
        }
        outCallnotificationsVo.setTotal(outCallnotificationsPage.getTotal());
        outCallnotificationsVo.setItems(outCallnotificationsPage.getRecords());
        return outCallnotificationsVo;
    }

    /**
     * 添加外呼
     *
     * @param outCallnotifications
     * @return
     */
    @Override
    public int add(OutCallnotifications outCallnotifications) {
        service.shutdown();
        service = Executors.newScheduledThreadPool(10);
        Calendar currentDate = Calendar.getInstance();
        Date callTime = outCallnotifications.getCallTime();
        //设置定时 任务，指定时间后执行（需计算当前时间与业务时间差），
        currentDate.clear();
        currentDate.setTime(callTime);
        //提醒时间对应毫秒
        long timeMillis = currentDate.getTimeInMillis();
        currentDate.clear();
        try {
            currentDate.setTime(TimeUtils.getTimeWithDate(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long weekMill = 7 * 24 * 60 * 60 * 1000;
        //当前时间对应毫秒
        long currentTimeMillis = currentDate.getTimeInMillis();
        long s = timeMillis - currentTimeMillis;
        long dayMill = 24 * 60 * 60 * 1000;
        String xingqi = TimeUtils.getXingQi(new Date());
        int currentDayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH);
        ScheduledExceutor job = new ScheduledExceutor(outCallnotifications.getCallContent(), outCallnotifications.getUserId());
        if (!"".equals(outCallnotifications.getModel()) && "一次".equals(outCallnotifications.getModel()) && "1".equals(outCallnotifications.getOnOff())) {
            if (s > 0) {
                System.out.println("定时设置成功：" + s + "毫秒后触发");
                //当天触发
                service.schedule(job, s, TimeUnit.MILLISECONDS);
                //关闭
                outCallnotifications.setOnOff("0");
                outCallnotificationsMapper.updateById(outCallnotifications);
            }
        } else if (!"".equals(outCallnotifications.getModel()) && "每天".equals(outCallnotifications.getModel()) && "1".equals(outCallnotifications.getOnOff())) {
            if (s > 0) {
                System.out.println("定时设置成功：" + s + "毫秒后触发");
                //当天触发
                service.scheduleAtFixedRate(job, s, dayMill, TimeUnit.MILLISECONDS);
            }
        } else if (!"".equals(outCallnotifications.getModel()) && "每周".equals(outCallnotifications.getModel()) && "1".equals(outCallnotifications.getOnOff())) {
            if (!"".equals(outCallnotifications.getCallDate()) && xingqi.equals(outCallnotifications.getCallDate())) {
                if (s > 0) {
                    service.scheduleAtFixedRate(job, s, weekMill, TimeUnit.MILLISECONDS);
                }
            }
        } else if (!"".equals(outCallnotifications.getModel()) && "每月".equals(outCallnotifications.getModel()) && "1".equals(outCallnotifications.getOnOff())) {
            //因定时每日0时刷新 所以只需要判断是不是当天定时
            if (Integer.parseInt(outCallnotifications.getCallDate()) == currentDayOfMonth) {
                if (s > 0) {
                    service.scheduleAtFixedRate(job, s, weekMill, TimeUnit.MILLISECONDS);
                }
            }
        }
        return outCallnotificationsMapper.insert(outCallnotifications);
    }

    /**
     * 更新外呼信息
     *
     * @param outCallnotifications
     * @return
     */
    @Override
    public int updateData(OutCallnotifications outCallnotifications) {
        service.shutdown();
        service = Executors.newScheduledThreadPool(10);
        Calendar currentDate = Calendar.getInstance();
        Date callTime = outCallnotifications.getCallTime();
        //设置定时 任务，指定时间后执行（需计算当前时间与业务时间差），
        currentDate.clear();
        currentDate.setTime(callTime);
        //提醒时间对应毫秒
        long timeMillis = currentDate.getTimeInMillis();
        currentDate.clear();
        try {
            currentDate.setTime(TimeUtils.getTimeWithDate(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long weekMill = 7 * 24 * 60 * 60 * 1000;
        //当前时间对应毫秒
        long currentTimeMillis = currentDate.getTimeInMillis();
        long s = timeMillis - currentTimeMillis;
        long dayMill = 24 * 60 * 60 * 1000;
        String xingqi = TimeUtils.getXingQi(new Date());
        int currentDayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH);
        ScheduledExceutor job = new ScheduledExceutor(outCallnotifications.getCallContent(), outCallnotifications.getUserId());
        if (!"".equals(outCallnotifications.getModel()) && "一次".equals(outCallnotifications.getModel()) && "1".equals(outCallnotifications.getOnOff())) {
            if (s > 0) {
                System.out.println("定时设置成功：" + s + "毫秒后触发");
                //当天触发
                service.schedule(job, s, TimeUnit.MILLISECONDS);
                //关闭
                outCallnotifications.setOnOff("0");
                outCallnotificationsMapper.updateById(outCallnotifications);
            }
        } else if (!"".equals(outCallnotifications.getModel()) && "每天".equals(outCallnotifications.getModel()) && "1".equals(outCallnotifications.getOnOff())) {
            if (s > 0) {
                System.out.println("定时设置成功：" + s + "毫秒后触发");
                //当天触发
                service.scheduleAtFixedRate(job, s, dayMill, TimeUnit.MILLISECONDS);
            }
        } else if (!"".equals(outCallnotifications.getModel()) && "每周".equals(outCallnotifications.getModel()) && "1".equals(outCallnotifications.getOnOff())) {
            if (!"".equals(outCallnotifications.getCallDate()) && xingqi.equals(outCallnotifications.getCallDate())) {
                if (s > 0) {
                    service.scheduleAtFixedRate(job, s, weekMill, TimeUnit.MILLISECONDS);
                }
            }
        } else if (!"".equals(outCallnotifications.getModel()) && "每月".equals(outCallnotifications.getModel()) && "1".equals(outCallnotifications.getOnOff())) {
            //因定时每日0时刷新 所以只需要判断是不是当天定时
            if (Integer.parseInt(outCallnotifications.getCallDate()) == currentDayOfMonth) {
                if (s > 0) {
                    service.scheduleAtFixedRate(job, s, weekMill, TimeUnit.MILLISECONDS);
                }
            }
        }
        return outCallnotificationsMapper.updateById(outCallnotifications);
    }

    /**
     * 删除当前外呼信息
     *
     * @param id
     * @return
     */
    @Override
    public int delete(Long id) {
        return outCallnotificationsMapper.deleteById(id);
    }

    /**
     * 通过id查询外呼信息
     *
     * @param id
     * @return
     */
    @Override
    public OutCallnotifications findById(Long id) {
        return outCallnotificationsMapper.selectById(id);
    }

    /**
     * 定时外呼功能
     *
     * @return
     */
    @Override
    public void outCallScheduled() {
        service.shutdown();
        service = Executors.newScheduledThreadPool(10);
        QueryWrapper<OutCallnotifications> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("on_off", "1");
        List<OutCallnotifications> list = outCallnotificationsMapper.selectList(queryWrapper);
        if (list.size() <= 0) {
            return;
        }
        long dayMill = 24 * 60 * 60 * 1000;
        long weekMill = 7 * 24 * 60 * 60 * 1000;
        Calendar currentDate = Calendar.getInstance();
        int currentDayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < list.size(); i++) {
            String model = list.get(i).getModel();
            ScheduledExceutor job = new ScheduledExceutor(list.get(i).getCallContent(), list.get(i).getPhone());

            Date callTime = list.get(i).getCallTime();
            //设置定时 任务，指定时间后执行（需计算当前时间与业务时间差），
            currentDate.clear();
            currentDate.setTime(callTime);
            //数据库时间对应毫秒
            long timeMillis = currentDate.getTimeInMillis();
            currentDate.clear();
            try {
                currentDate.setTime(TimeUtils.getTimeWithDate(new Date()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //当前时间对应毫秒
            long currentTimeMillis = currentDate.getTimeInMillis();
            long s = timeMillis - currentTimeMillis;
            OutCallnotifications outCallnotifications = list.get(i);
            String xingqi = TimeUtils.getXingQi(new Date());
            if ("一次".equals(model) && !"".equals(model)) {
                if (s > 0) {
                    System.out.println("定时设置成功：" + s + "毫秒后触发");
                    //当天触发
                    service.schedule(job, s, TimeUnit.MILLISECONDS);
                    //关闭
                    outCallnotifications.setOnOff("0");
                    outCallnotificationsMapper.updateById(outCallnotifications);
                } else {
                    System.out.println("定时设置成功：" + s + "毫秒后触发");
                    //隔天触发
                    s = s + 24 * 60 * 60 * 1000;
                    service.schedule(job, s, TimeUnit.MILLISECONDS);
                    //关闭
                    outCallnotifications.setOnOff("0");
                    outCallnotificationsMapper.updateById(outCallnotifications);
                }
            } else if ("每天".equals(model) && !"".equals(model)) {
                if (s > 0) {
                    System.out.println("定时设置成功：" + s + "毫秒后触发");
                    //当天触发
                    service.scheduleAtFixedRate(job, s, dayMill, TimeUnit.MILLISECONDS);
                } else {
                    s = 24 * 60 * 60 * 1000 + (-s);
                    System.out.println("定时设置成功：" + s + "毫秒后触发");
                    //隔天触发
                    service.scheduleAtFixedRate(job, s, dayMill, TimeUnit.MILLISECONDS);
                }
            } else if ("每周".equals(model) && !"".equals(model)) {
                if (!"".equals(list.get(i).getCallDate()) && "星期一".equals(list.get(i).getCallDate()) && "星期一".equals(xingqi)) {
                    if (s > 0) {
                        service.scheduleAtFixedRate(job, s, weekMill, TimeUnit.MILLISECONDS);
                    } else {
                        s = weekMill + (-s);
                        service.scheduleAtFixedRate(job, s, weekMill, TimeUnit.MILLISECONDS);
                    }
                } else if (!"".equals(list.get(i).getCallDate()) && "星期二".equals(list.get(i).getCallDate()) && "星期二".equals(xingqi)) {
                    if (s > 0) {
                        service.scheduleAtFixedRate(job, s, weekMill, TimeUnit.MILLISECONDS);
                    } else {
                        s = weekMill + (-s);
                        service.scheduleAtFixedRate(job, s, weekMill, TimeUnit.MILLISECONDS);
                    }
                } else if (!"".equals(list.get(i).getCallDate()) && "星期三".equals(list.get(i).getCallDate()) && "星期三".equals(xingqi)) {
                    if (s > 0) {
                        service.scheduleAtFixedRate(job, s, weekMill, TimeUnit.MILLISECONDS);
                    } else {
                        s = weekMill + (-s);
                        service.scheduleAtFixedRate(job, s, weekMill, TimeUnit.MILLISECONDS);
                    }
                } else if (!"".equals(list.get(i).getCallDate()) && "星期四".equals(list.get(i).getCallDate()) && "星期四".equals(xingqi)) {
                    if (s > 0) {
                        service.scheduleAtFixedRate(job, s, weekMill, TimeUnit.MILLISECONDS);
                    } else {
                        s = weekMill + (-s);
                        service.scheduleAtFixedRate(job, s, weekMill, TimeUnit.MILLISECONDS);
                    }
                } else if (!"".equals(list.get(i).getCallDate()) && "星期五".equals(list.get(i).getCallDate()) && "星期五".equals(xingqi)) {
                    if (s > 0) {
                        service.scheduleAtFixedRate(job, s, weekMill, TimeUnit.MILLISECONDS);
                    } else {
                        s = weekMill + (-s);
                        service.scheduleAtFixedRate(job, s, weekMill, TimeUnit.MILLISECONDS);
                    }
                } else if (!"".equals(list.get(i).getCallDate()) && "星期六".equals(list.get(i).getCallDate()) && "星期六".equals(xingqi)) {
                    if (s > 0) {
                        service.scheduleAtFixedRate(job, s, weekMill, TimeUnit.MILLISECONDS);
                    } else {
                        s = weekMill + (-s);
                        service.scheduleAtFixedRate(job, s, weekMill, TimeUnit.MILLISECONDS);
                    }
                } else {
                    if (s > 0) {
                        service.scheduleAtFixedRate(job, s, weekMill, TimeUnit.MILLISECONDS);
                    } else {
                        s = weekMill + (-s);
                        service.scheduleAtFixedRate(job, s, weekMill, TimeUnit.MILLISECONDS);
                    }
                }
            } else if ("每月".equals(model) && !"".equals(model)) {
                //因定时每日0时刷新 所以只需要判断是不是当天定时
                if (Integer.parseInt(list.get(i).getCallDate()) == currentDayOfMonth) {
                    if (s > 0) {
                        service.scheduleAtFixedRate(job, s, weekMill, TimeUnit.MILLISECONDS);
                    }
                }
            }
        }

    }
}
