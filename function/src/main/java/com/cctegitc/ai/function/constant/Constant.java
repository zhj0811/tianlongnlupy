package com.cctegitc.ai.function.constant;

import java.util.Arrays;
import java.util.List;

/**
 * 常量定义类
 *
 * @author jiangyang
 * @date 2021-12-28 14:24:10
 */
public class Constant {


    public static final String API_ACTIVITI_NAME = "activiti";

    public static final String API_AUTHORITY_NAME = "authority";

    public static final String SPECIAL_TASK = "特殊作业";

    public static final String UPPER_AIR_TASK = "高空作业";

    public static final String APPROVE_STATUS = "approveStatus";

    public static final String APPROVE_PASS_TEXT = "同意";

    public static final String APPROVE_REJECT_TEXT = "驳回";

    public static final String PROCESS_IN_APPROVING = "审批中";

    public static final String PROCESS_ENDED = "流程结束";

    public static final String ACTIVITY_TYPE_END = "endEvent";

    public static final String BLANK_STR = "";

    public static final String TODO_TASK = "todo";

    public static final String DONE_TASK = "done";

    public static final String REQUESTED_TASK = "requested";

    public static final String DASH_LINE = "-";

    public static final String POINT = ".";

    public static final String SUBMIT_STATE = "已提交";

    public static final String REJECT_STATE = "已驳回";

    public static final String ROLL_BACK_STATE = "已撤销";

    public static final String ROLL_BACK = "撤销";

    public static final String SEMICOLON_DELIMITER = ";";

    public static final String EQUIPMENT_TYPE_AUXILIARY = "0";

    public static final String EQUIPMENT_TYPE_POWER = "1";

    public static final List<String> EQUIPMENT_REPAIRS = Arrays.asList("配电设备维修", "辅助设备维修", "生产设备维修");

    /**
     * 体检表内容提取正则
     */
    public static final String HEALTH_EXAM_NUMBER_REGX = "登记流水号\\s?[:：]\\s?(.*?)\\s";
    public static final String HEALTH_EXAM_NAME_REGX = "姓名\\s?[:：\\s?]\\s?(.*?)\\s";
    public static final String HEALTH_EXAM_AGE_REGX = "年龄\\s?[:：]\\s?(\\d+)";
    public static final String HEALTH_EXAM_UNIT_REGX = "单位名称\\s?[:：]\\s?(\\S+)";
    public static final String HEALTH_EXAM_DATE_REGX = "日期\\s?[:：]\\s?(\\S+)";
    public static final String HEALTH_EXAM_DOCTOR_REGX = "医生\\s?[:：]\\s?(\\S+)";
    public static final String HEALTH_EXAM_GENDER_REGX = "性别\\s?[:：]\\s?(\\S+)";

    public static final List<String> HEALTH_EXAM_DEPT = Arrays.asList("内科", "外科", "检验科", "放射科", "心电图", "彩超", "肺功能室", "五官科", "总检结论");

    public static final int FIRST_COLUMN = 0;
    public static final int SECOND_COLUMN = 1;
    public static final int FIFTH_COLUMN = 4;

    public static final String SHEET_NAME_PATTERN = "\\d{1,2}\\.\\d{1,2}";

    public static final short CELL_HEIGHT_MULTIPLE = 20;

    public static final List<String> LOADING_STATISTIC_TITLE_LIST = Arrays.asList("日期", "列数", "开装\n时间", "结束\n时间", "车次", "流向", "煤种", "装车提交是否及时",
            "分煤种\n（两个分煤种不同的就是需要改的）", "", "快检", "快检", "节数", "吨位", "配煤方式", "装车员", "带班\n主任", "调度员", "值班\n领导", "备注");

    public static final List<String> PRODUCTIVITY_LIST = Arrays.asList("日期", "当日原煤处理量", "当日末煤处理量", "当日块煤处理量", "当日运行时间", "时间换算", "检修影响时间", "生产核定生产时间",
            "时间换算", "生产车间效率", "生产全天小时量", "生产影响时间", "检修车间核定时间", "时间换算", "检修车间效率", "检修全天小时量", "调度室全天小时量", "备注");

    /**
     * 一小时对应的毫秒数
     */
    public static final int HOUR_MILLISECOND = 1000 * 60 * 60;

    /**
     * 一分钟对应的毫秒数
     */
    public static final int MINUTE_MILLISECOND = 1000 * 60;

    /**
     * 1899-12-31 00:00:00 对应的时间戳毫秒数
     */
    public static final long TIMESTAMP_OF_1899_12_31 = -2209104000000L;

    /**
     * 逗号分隔符
     */
    public static final CharSequence COMMA_SEPARATER = ",";

    /**
     * 部门正常状态
     */
    public static final String DEPT_NORMAL = "0";

    /**
     * 部门停用状态
     */
    public static final String DEPT_DISABLE = "1";
    /**
     * 未删除状态
     */
    public static final Object NOT_DELETE = '0';

    /**
     * 删除状态
     */
    public static final Object DELETE_FLAG = '2';
    /**
     * 年月日日期格式
     */
    public static final String YEAR_MONTH_DAY_PATTERN = "yyyy-MM-dd";

    /**
     * 年月日日期格式
     */
    public static final String MONTH_DAY_PATTERN = "M月d日";
}
