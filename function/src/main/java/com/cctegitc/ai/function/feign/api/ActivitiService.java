package com.cctegitc.ai.function.feign.api;

import java.util.List;
import java.util.Map;

import com.cctegitc.ai.function.constant.Constant;
import com.cctegitc.ai.function.feign.RestResult;
import com.cctegitc.ai.function.feign.annotation.FeignApi;
import com.cctegitc.ai.function.feign.dto.ProcessApproveDto;
import com.cctegitc.ai.function.feign.dto.ProcessDefinitionDto;
import com.cctegitc.ai.function.feign.dto.ProcessStartParamDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cctegitc.ai.function.feign.dto.BaseDto;
import com.cctegitc.ai.function.feign.dto.DataPage;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * @author jiangyang
 * @date 2021-12-28 13:44:12
 */
@FeignApi(serviceName = Constant.API_ACTIVITI_NAME)
public interface ActivitiService {

    /**
     * 启动流程
     *
     * @param dto
     * @return RestResult
     */
    @Headers({"Content-Type: application/json"})
    @RequestLine("POST /processes/start")
    RestResult<String> start(ProcessStartParamDto dto);

    /**
     * 流程定义列表
     *
     * @param baseDto
     * @return
     */
    @Headers({"Content-Type: application/json"})
    @RequestLine("POST /processes/def-list")
    RestResult<DataPage<ProcessDefinitionDto>> modelList(@RequestBody BaseDto baseDto);

    /**
     * 获取流程图审批节点assignee对应key
     *
     * @param processDefinitionDto
     * @return RestResult<List < String>>
     * @author jiangyang
     * @date 2021/12/31 15:41
     */
    @Headers({"Content-Type: application/json"})
    @RequestLine("POST /processes/approve-keys")
    RestResult<List<String>> getAssigneeKeys(@RequestBody ProcessDefinitionDto processDefinitionDto);

    /**
     * 获取当前节点信息
     *
     * @param processInsId 流程实例id
     * @return RestResult
     * @author jiangyang
     * @date 2021/12/31 15:35
     */
    @Headers({"Content-Type: application/json"})
    @RequestLine("GET /processes/myCurrentNodeInfo/{processInstanceId}")
    RestResult<Map<String, Object>> getMyCurrentNodeInfo(@Param(value = "processInstanceId") String processInsId);

    /**
     * 获取流程定义流程图
     *
     * @param processDefId
     * @return
     */
    @Headers({"Content-Type: application/json"})
    @RequestLine("GET /processes/def/img/{processDefId}")
    RestResult getProcessImage(@Param("processDefId") String processDefId);

    /**
     * 完成任务
     *
     * @param processApproveDto
     * @author jiangyang
     * @date 2022/1/7 18:55
     */
    @Headers({"Content-Type: application/json"})
    @RequestLine("POST /processes/complete")
    void complete(ProcessApproveDto processApproveDto);

    /**
     * 撤回申请
     *
     * @param processInstanceId
     * @author jiangyang
     * @date 2022/1/7 18:54
     */
    @Headers({"Content-Type: application/json"})
    @RequestLine("POST /processes/rollBack")
    void rollBack(String processInstanceId);

//    /**
//     * 删除流程定义
//     *
//     * @param form
//     * @return
//     */
//    @Headers({"Content-Type: application/json"})
//    @RequestLine("DELETE /processes/delete-def")
//    RestResult delProcessDefinitions(@RequestBody ArrayForm form);


    /**
     * 导入流程
     *
     * @param file
     * @return
     */
    @Headers({"Content-Type: multipart/form-data"})
    @RequestLine("POST /processes/importProcess")
    RestResult importProcess(@RequestParam MultipartFile file);


    /**
     * 获取流程定义信息
     *
     * @param processDefId
     * @return
     */
    @Headers({"Content-Type: application/json"})
    @RequestLine("GET /processes/def/detail/{processDefId}")
    RestResult<ProcessDefinitionDto> getProcessDefinition(@Param("processDefId") String processDefId);
//
//    /**
//     * 获取流程定义下拉列表
//     *
//     * @return RestResult
//     */
//    @Headers({"Content-Type: application/json"})
//    @RequestLine("GET /processes/def/dropdown")
//    RestResult<List<ProcessDefItem>> getProcessDefDropdownList();

}
