package com.cctegitc.ai.function.util;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.cctegitc.ai.authority.common.annotation.ExcelColum;
import com.cctegitc.ai.function.constant.Constant;
import com.cctegitc.ai.function.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import cn.hutool.core.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: ImportExportUtil
 * @Description:
 * @Author: RenYi
 * @Data:2022/3/2 15:33
 * @Version:1.0
 */
@Slf4j
public class ImportExportUtil {

    private static final ThreadLocal<SimpleDateFormat> DATE_FORMAT =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    private static final ThreadLocal<SimpleDateFormat> YEAR_MONTH_DAY_FORMAT =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    /**
     * 为单元格设置内容
     *
     * @param cell
     * @param value
     * @return void
     * @author jiangyang
     * @date 2022-04-14
     */
    public static void setCellValue(Cell cell, String value) {
        if (value == null) {
            cell.setCellValue("");
        } else {
            cell.setCellValue(value);
        }
    }

    /**
     * 将时间字符串转换成Date对象
     * 当formatPattern 为空时使用默认格式： yyyy-MM-dd HH:mm:ss
     *
     * @param src           要转换的字符串
     * @param formatPattern 日期格式
     * @return Date 转换结果
     * @author jiangyang
     * @date 2022-04-14
     */
    public static Date parseDate(String src, String formatPattern) {
        try {
            SimpleDateFormat simpleDateFormat;
            // 当传入的日期格式为空时，采用默认的格式
            if (StringUtils.isBlank(formatPattern)) {
                simpleDateFormat = DATE_FORMAT.get();
            } else {
                simpleDateFormat = new SimpleDateFormat(formatPattern);
            }
            return simpleDateFormat.parse(src);
        } catch (Exception e) {
            log.error("format data {} error", src);
        }
        return null;
    }

    /**
     * @Description:获取每一行单元格的数据，判断是否为空，
     * @Author: jiangyang
     * @Data: 2022/4/18 17:16
     * @Param: [row, colIndex]
     * @Return: org.apache.poi.ss.usermodel.Cell
     * Throws:
     */
    public static Cell getCell(Row row, int colIndex) {
        return Optional.ofNullable(row.getCell(colIndex)).orElseGet(() -> row.createCell(colIndex));
    }

    /**
     * 将sheet页数据读取到list
     *
     * @param sheet 要读取的sheet页
     * @return List<List < String>>
     * @author jiangyang
     * @date 2022-04-14
     */
    public static List<List<String>> readExcelSheet(Sheet sheet) {
        List<List<String>> list = new ArrayList<>();
        if (sheet == null) {
            return list;
        }
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 0; i < lastRowNum; i++) {
            Row row = sheet.getRow(i);
            List<String> rowData = new ArrayList<>();
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                String cellValue = Constant.BLANK_STR;
                Cell cell = getCell(row, j);
                if (cell.getCellTypeEnum() != CellType.STRING && isCellDateFormatted(cell)) {
                    Date dateCellValue = cell.getDateCellValue();
                    if (dateCellValue != null) {
                        cellValue = DATE_FORMAT.get().format(dateCellValue);
                    }
                } else {
                    cell.setCellType(CellType.STRING);
                    cellValue = cell.getStringCellValue();
                }
                rowData.add(cellValue);
            }
            list.add(rowData);
        }
        return list;

    }

    /**
     * 根据下标获取sheet页的一行
     *
     * @param sheet    sheet页
     * @param rowIndex 行下标
     * @return Row Row对象
     * @author jiangyang
     * @date 2022-04-14
     */
    public static Row getRow(Sheet sheet, int rowIndex) {
        return Optional.ofNullable(sheet.getRow(rowIndex)).orElseGet(() -> sheet.createRow(rowIndex));
    }

    public static void setStyleForRow(Row row, CellStyle cellStyle, int lastColIndex) {
        for (int i = 0; i < lastColIndex; i++) {
            getCell(row, i).setCellStyle(cellStyle);
        }
    }

    /**
     * @Description:字符串截取，拼接为：2022-12
     * @Author: Yrc
     * @Data: 2022/4/15 15:11
     * @Param: [day]
     * @Return: java.lang.String
     * Throws:
     */
    public static String getStr(String day) {
        String year = day.split("-")[0];
        String mouth = day.split("-")[1];

        String str = year + "-" + mouth;
        return str;
    }

    /**
     * 重写poi判断日期单元格工具类，解决当日期格式包含中文时无法识别
     *
     * @param cell
     * @return boolean
     * @author jiangyang
     * @date 2022-04-13
     */
    public static boolean isCellDateFormatted(Cell cell) {
        if (cell == null) {
            return false;
        }
        if (cell.getCellTypeEnum() == CellType.FORMULA) {
            if (cell.getCachedFormulaResultTypeEnum() != CellType.NUMERIC) {
                return false;
            }
        } else if (cell.getCellTypeEnum() != CellType.NUMERIC) {
            return false;
        }
        boolean bDate = false;

        double d = cell.getNumericCellValue();
        if (isValidExcelDate(d)) {
            CellStyle style = cell.getCellStyle();
            if (style == null) {
                return false;
            }
            int i = style.getDataFormat();
            String f = style.getDataFormatString();
            bDate = isADateFormat(i, f);
        }
        return bDate;
    }

    /**
     * 判断是否日期格式
     *
     * @param formatIndex
     * @param formatString
     * @return boolean
     * @author jiangyang
     * @date 2022-04-14
     */
    public static boolean isADateFormat(int formatIndex, String formatString) {
        if (isInternalDateFormat(formatIndex)) {
            return true;
        }

        if ((formatString == null) || (formatString.length() == 0)) {
            return false;
        }

        String fs = formatString;
        // 下面这一行是自己手动添加的 以支持汉字格式wingzing
        fs = fs.replaceAll("[\"|\']", "").replaceAll("[年|月|日|时|分|秒|毫秒|微秒]", "");
        fs = fs.replaceAll("\\\\-", "-");
        fs = fs.replaceAll("\\\\,", ",");
        fs = fs.replaceAll("\\\\.", ".");
        fs = fs.replaceAll("\\\\ ", " ");
        fs = fs.replaceAll(";@", "");
        fs = fs.replaceAll("^\\[\\$\\-.*?\\]", "");
        fs = fs.replaceAll("^\\[[a-zA-Z]+\\]", "");
        return (fs.matches("^[yYmMdDhHsS\\-/,. :]+[ampAMP/]*$"));
    }

    public static boolean isInternalDateFormat(int format) {
        switch (format) {
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 45:
            case 46:
            case 47:
            case 57:
            case 58:
                return true;
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
        }
        return false;
    }

    public static boolean isValidExcelDate(double value) {
        return (value > -4.940656458412465E-324D);
    }

    public static String formatDate(String srcDate, String formatPattern) {
        if (StringUtils.isBlank(srcDate)) {
            return Constant.BLANK_STR;
        }
        try {
            Date parse = DATE_FORMAT.get().parse(srcDate);
            SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern);
            return dateFormat.format(parse);
        } catch (ParseException e) {
            log.error("format {} to date error,", srcDate, e);
        }
        return srcDate;
    }

    /**
     * 根据传入的格式格式化日期 当formatPattern为空时默认按yyyy-MM-dd HH:mm:ss格式化
     *
     * @param date          要格式化的日期
     * @param formatPattern 日期格式
     * @return String 格式化结果
     * @author jiangyang
     * @date 2022-04-14
     */
    public static String formatDate(Date date, String formatPattern) {
        try {
            SimpleDateFormat simpleDateFormat;
            // 当传入的日期格式为空时，采用默认的格式
            if (StringUtils.isBlank(formatPattern)) {
                simpleDateFormat = DATE_FORMAT.get();
            } else {
                simpleDateFormat = new SimpleDateFormat(formatPattern);
            }
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            log.error("format data {} error", date);
        }
        return Constant.BLANK_STR;

    }

    /**
     * 将excel一行转成 clazz类型的对象
     *
     * @param row   Row对象
     * @param clazz 类型的class对象
     * @return T 封装结果对象
     * @author jiangyang
     * @date 2022-04-14
     */
    public static <T> T parseDataToObject(Row row, Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                ExcelColum annotation = field.getAnnotation(ExcelColum.class);
                if (annotation == null) {
                    continue;
                }
                field.setAccessible(true);
                Cell cell = getCell(row, annotation.col());
                if (isCellDateFormatted(cell) && !annotation.forceString()) {
                    Date dateCellValue = cell.getDateCellValue();
                    field.set(t, formatDate(dateCellValue, annotation.format()));
                } else {
                    if (isNumberCell(cell) && annotation.scale() != 0) {
                        double numericCellValue = cell.getNumericCellValue();
                        field.set(t, NumberUtil.roundStr(numericCellValue, annotation.scale()));
                    } else {
                        cell.setCellType(CellType.STRING);
                        field.set(t, cell.getStringCellValue());
                    }
                }
            }
        } catch (Exception e) {
            log.error("parseDataToObject error", e);
        }
        return t;
    }

    /**
     * 判断cell是否是数字格式
     *
     * @param cell 要判断的cell
     * @return boolean
     * @author jiangyang
     * @date 2022-04-14
     */
    public static boolean isNumberCell(Cell cell) {
        CellType cellTypeEnum = cell.getCellTypeEnum();
        if (cellTypeEnum == CellType.NUMERIC) {
            return true;
        }
        if (cell.getCellTypeEnum() == CellType.FORMULA) {
            return cell.getCachedFormulaResultTypeEnum() == CellType.NUMERIC;
        }
        return false;
    }

    /**
     * @param rowData 一行数据列表
     * @param clazz   泛型对应的类型
     * @return T 封装结果对象
     * @author jiangyang
     * @date 2022-04-14
     */
    public static <T> T parseDataToObject(List<String> rowData, Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        T t = null;
        try {
            t = clazz.newInstance();
            for (Field field : fields) {
                ExcelColum annotation = field.getAnnotation(ExcelColum.class);
                System.out.println(field.getName());
                if (annotation != null) {
                    field.setAccessible(true);
                    String format = annotation.format();
                    String value = rowData.get(annotation.col());
                    if (field.getType() == Double.class) {
                        if (!"0".equals(value)) {
                            value = CommonUtils.roundStr(rowData.get(annotation.col()), annotation.scale());
                        }
                        field.set(t, CommonUtils.convertToDouble(value));
                    } else {
                        if (StringUtils.isNotBlank(format)) {
                            value = formatDate(value, format);
                        }
                        field.set(t, value);
                    }
                }
            }
        } catch (Exception e) {
            log.error("parse excel data to object error", e);
        }
        return t;
    }

    /**
     * 将list数据写入sheet页
     *
     * @param list       数据
     * @param sheet      要写入的sheet页
     * @param startIndex 开始写入的行数下标
     * @author jiangyang
     * @date 2022-04-14
     */
    public static <T> void writeDataToSheet(List<T> list, Sheet sheet, int startIndex) {
        for (T t : list) {
            Row row = getRow(sheet, startIndex++);
            Field[] fields = t.getClass().getDeclaredFields();
            for (Field field : fields) {
                ExcelColum annotation = field.getAnnotation(ExcelColum.class);
                if (annotation != null) {
                    int col = annotation.col();
                    field.setAccessible(true);
                    try {
                        Object o = field.get(t);
                        // 为空时设置空字符串
                        if (o == null) {
                            getCell(row, col).setCellValue(Constant.BLANK_STR);
                        } else if (o instanceof Double) {
                            // double类型数据
                            getCell(row, col).setCellValue((Double) o);
                        } else if (o instanceof Date) {
                            SimpleDateFormat simpleDateFormat = YEAR_MONTH_DAY_FORMAT.get();
                            // 日期类型数据
                            getCell(row, col).setCellValue(simpleDateFormat.format((Date) o));
                        } else {
                            // 其他类型数据
                            getCell(row, col).setCellValue(String.valueOf(o));
                        }
                    } catch (IllegalAccessException e) {
                        log.error("设置字段{}值出错", field.getName());
                    }
                }
            }

        }
    }

    /**
     * 计算参数给定的日期距离1899-12-31 00:00:00 小时和分钟数
     *
     * @param dateStr   要计算的时间字符串
     * @param hasSecond 是否计算秒
     * @return String 转换后的结果 如 30:25、 45:20:00
     * @author jiangyang
     * @date 2022-04-14
     */
    public static String getSumHours(String dateStr, boolean hasSecond) {
        if (StringUtils.isBlank(dateStr)) {
            return dateStr;
        }
        String res = dateStr;
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            localDateTime = localDateTime.plusDays(1);
            int day = localDateTime.getDayOfYear();
            int hour = localDateTime.getHour();
            int minute = localDateTime.getMinute();
            int second = localDateTime.getSecond();

            res = (day - 1) * 24 + hour + ":" + (minute > 9 ? minute : "0" + minute);
            if (hasSecond) {
                res += ":" + (second > 9 ? second : "0" + second);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static Cell getCell(Sheet sheet, int rowIndex, int cellIndex) {
        Row row = getRow(sheet, rowIndex);
        return getCell(row, cellIndex);
    }

    public static void writeStringToCell(Sheet sheet, int rowIndex, int cellIndex, String v) {
        Row row = getRow(sheet, rowIndex);
        getCell(row, cellIndex).setCellValue(v);
    }

    /**
     * @Description:判断传回的时间是字符串还是Date类型
     * @Author: Yrc
     * @Data: 2022/4/21 16:29
     * @Param: [hssfRow, cellIndex]
     * @Return: java.util.Date
     * @Version: 1.0
     * Throws:
     */
    public static Date getCorrectDate(Row hssfRow, int cellIndex) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Cell cell = getCell(hssfRow, cellIndex);
            if (isCellDateFormatted(cell)) {
                return cell.getDateCellValue();
            }
            cell.setCellType(CellType.STRING);
            String stringCellValue = cell.getStringCellValue();
            if (stringCellValue != null && stringCellValue != "") {
                Date date = dateFormat.parse(stringCellValue);
                return date;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    /**
     * Excel String类型的判断
     *
     * @param row
     * @param colIndex
     * @return Author:RenYi
     */
    public static String getStringVal(Row row, int colIndex) {
        String d = null;
        Cell cell = getCell(row, colIndex);
        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            d = NumberUtil.roundStr(cell.getNumericCellValue(), 0);
        } else {
            cell.setCellType(CellType.STRING);
            d = cell.getStringCellValue();
        }
        return Optional.ofNullable(d).orElseThrow(() -> new CustomException("请输入正确的数据"));
    }

    public static String getStringVal1(Row row, int colIndex) {
        String d = null;
        Cell cell = getCell(row, colIndex);
        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            d = NumberUtil.roundStr(cell.getNumericCellValue(), 0);
        } else if (cell.getCellTypeEnum() == CellType.STRING) {
            d = cell.getStringCellValue();
        }
        return d;
    }

    /**
     * excel导入时间格式的判断和转化
     *
     * @param row
     * @param colIndex
     * @return Author:RenYi
     */
    public static Date getDateOfCell(Row row, int colIndex) throws ParseException {
        Date date = null;
        Cell cell = getCell(row, colIndex);
        if (cell.getCellTypeEnum() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            date = cell.getDateCellValue();
        } else {
            cell.setCellType(CellType.STRING);
            String stringCellValue = cell.getStringCellValue();
            // stringCellValue 格式化成date
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            formatter.setLenient(false);
            date = formatter.parse(stringCellValue);
        }

        return Optional.ofNullable(date).orElseThrow(() -> new CustomException("时间格式错误！应为 yyyy-MM-dd 或 yyyy/MM/dd"));
    }

    /**
     * Excel Int类型的判断
     *
     * @param row
     * @param colIndex
     * @return Author:RenYi
     */
    public static Integer getIntVal(Row row, int colIndex) {
        Cell cell = getCell(row, colIndex);
        String intStr;
        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            intStr = NumberUtil.roundStr(cell.getNumericCellValue(), 0);
        } else {
            cell.setCellType(CellType.STRING);
            intStr = cell.getStringCellValue();
        }
        return CommonUtils.convertStr2Integer(intStr);
    }
}
