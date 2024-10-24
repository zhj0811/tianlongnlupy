package com.cctegitc.ai.function.util.common.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ContentTypeConstant {
    /**
     * DOCX
     */
    public static final String DOCX_CONTENTTYPE = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";

    /**
     * DOX
     */
    public static final String DOC_CONTENTTYPE = "application/msword";

    /**
     * TXT
     */
    public static final String TXT_CONTENTTYPE = "text/plain";

    /**
     * 文本类型集合
     */
    public static final List<String> TEXT_TYPES = Collections.unmodifiableList(Arrays.asList(
            DOCX_CONTENTTYPE, DOC_CONTENTTYPE, TXT_CONTENTTYPE));

    /**
     * xls
     */
    public static final String EXCEL_XLS_CONTENTTYPE = "application/vnd.ms-excel";

    /**
     * xlsx
     */
    public static final String EXCEL_XLSX_CONTENTTYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    /**
     * csv
     */
    public static final String CSV_CONTENTTYPE = "text/csv";

    /**
     * PDF
     */
    public static final String PDF_CONTENTTYPE =  "application/pdf";


    // 电子表格类型集合
    public static final List<String> SPREADSHEET_TYPES = Collections.unmodifiableList(Arrays.asList(
            EXCEL_XLS_CONTENTTYPE,
            EXCEL_XLSX_CONTENTTYPE,
            CSV_CONTENTTYPE
    ));



    // 音频类型常量
    public static final String AUDIO_MP3_CONTENTTYPE = "audio/mpeg";
    public static final String AUDIO_OGG_CONTENTTYPE = "audio/ogg";
    public static final String AUDIO_WAV_CONTENTTYPE = "audio/wav";

    public static final String AUDIO_FLAC_CONTENTTYPE = "audio/flac";
    public static final String AUDIO_AMR_CONTENTTYPE = "audio/amr";

    // 音频类型集合
    public static final List<String> AUDIO_TYPES = Collections.unmodifiableList(Arrays.asList(
            AUDIO_MP3_CONTENTTYPE,
            AUDIO_OGG_CONTENTTYPE,
            AUDIO_WAV_CONTENTTYPE,
            AUDIO_FLAC_CONTENTTYPE,
            AUDIO_AMR_CONTENTTYPE
    ));


    // 图像类型常量
    public static final String IMAGE_JPEG_CONTENTTYPE = "image/jpeg";
    public static final String IMAGE_PNG_CONTENTTYPE = "image/png";
    public static final String IMAGE_GIF_CONTENTTYPE = "image/gif";
    // 图像类型集合
    public static final List<String> IMAGE_TYPES = Collections.unmodifiableList(Arrays.asList(
            IMAGE_JPEG_CONTENTTYPE,
            IMAGE_PNG_CONTENTTYPE,
            IMAGE_GIF_CONTENTTYPE
    ));


    /**
     * mp4
     */
    public static final String VIDEO_MP4_CONTENTTYPE = "video/mp4";

    /**
     * avi
     */
    public static final String VIDEO_AVI_CONTENTTYPE = "video/x-msvideo";

    /**
     * mov
     */
    public static final String VIDEO_QUICKTIME_CONTENTTYPE = "video/quicktime";

    /**
     * flv
     */
    public static final String VIDEO_FLV_CONTENTTYPE = "video/x-flv";


    // 视频类型集合
    public static final List<String> VIDEO_TYPES = Collections.unmodifiableList(Arrays.asList(
            VIDEO_MP4_CONTENTTYPE,
            VIDEO_AVI_CONTENTTYPE,
            VIDEO_QUICKTIME_CONTENTTYPE,VIDEO_FLV_CONTENTTYPE
    ));

    /**
     * DWG - AutoCAD Drawing Database
     */
    public static final String CAD_DWG_CONTENTTYPE = "image/vnd.dwg";

    /**
     * DXF - Drawing Exchange Format
     */
    public static final String CAD_DXF_CONTENTTYPE = "image/vnd.dxf";

    /**
     * STL - Stereolithography
     */
    public static final String CAD_STL_CONTENTTYPE = "application/vnd.ms-pki.stl";

    /**
     * IGES - Initial Graphics Exchange Specification
     */
    public static final String CAD_IGES_CONTENTTYPE = "model/iges";

    /**
     * STEP - Standard for the Exchange of Product Model Data
     */
    public static final String CAD_STEP_CONTENTTYPE = "application/step";


    public static final List<String> CAD_TYPES = Collections.unmodifiableList(Arrays.asList(
            CAD_DWG_CONTENTTYPE, CAD_DXF_CONTENTTYPE, CAD_STL_CONTENTTYPE, CAD_IGES_CONTENTTYPE, CAD_STEP_CONTENTTYPE));

    public static final List<String> DOCUMENT_TYPES = Collections.unmodifiableList(Arrays.asList(
            EXCEL_XLS_CONTENTTYPE,
            EXCEL_XLSX_CONTENTTYPE,
            CSV_CONTENTTYPE,DOCX_CONTENTTYPE, DOC_CONTENTTYPE,PDF_CONTENTTYPE,
            CAD_DWG_CONTENTTYPE, CAD_DXF_CONTENTTYPE, CAD_STL_CONTENTTYPE, CAD_IGES_CONTENTTYPE, CAD_STEP_CONTENTTYPE
    ));

}
