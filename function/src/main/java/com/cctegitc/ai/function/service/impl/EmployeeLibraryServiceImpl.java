package com.cctegitc.ai.function.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cctegitc.ai.function.db.dao.EmployeeLibraryMapper;
import com.cctegitc.ai.function.db.dao.OrganizationMapper;
import com.cctegitc.ai.function.db.pojo.EmployeeLibrary;
import com.cctegitc.ai.function.db.pojo.Organization;
import com.cctegitc.ai.function.modules.contactsmodule.service.IEmployeeLibraryService;
import com.cctegitc.ai.function.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author peip
 * @since 2022-12-02
 */
@Service
@Slf4j
public class EmployeeLibraryServiceImpl implements IEmployeeLibraryService {

    @Resource
    private EmployeeLibraryMapper baseMapper;

    @Resource
    private OrganizationMapper organizationMapper;

    @Value("${dispatch.audio-login-url}")
    private String loginUrl;

    @Value("${dispatch.contacts-url}")
    private String contactsUrl;

    @Value("${dispatch.accounts-url}")
    private String accountsUrl;

    @Value("${dispatch.organization_url}")
    private String organizationUrl;

    @Value("${dispatch.nuas-http-url}")
    private String nuasUrl;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 查询分页数据
     *
     * @param employeeLibrary 条件
     * @param
     * @param
     * @return
     */
    @Override
    public JSONObject findByPage(EmployeeLibrary employeeLibrary) {
        try {
            JSONObject json = HttpUtils.httpPostLoginRequest(loginUrl);
            json = JSONObject.parseObject(json.get("data").toString());
            String token = json.get("token").toString();
            Map<String, Object> map = new HashMap<>();
            map.put("keyword", employeeLibrary.getKeyword());
            map.put("per_page", employeeLibrary.getLimit());
            map.put("page", employeeLibrary.getPage());
            json = HttpUtils.sendGet(accountsUrl, map, token);
            return json;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 添加
     *
     * @param json
     * @return int
     */
    @Override
    public JSONObject add(JSONObject json) {
        try {
            JSONObject jsonObject = HttpUtils.httpPostLoginRequest(loginUrl);
            jsonObject = JSONObject.parseObject(jsonObject.get("data").toString());
            String token = jsonObject.get("token").toString();
            System.out.println("token======" + token);
            System.out.println("请求联系人新增接口开始-----------------");
            jsonObject = HttpUtils.sendJSONPost(json, accountsUrl, token);
            System.out.println("新增结果" + jsonObject);
            return jsonObject;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    @Override
    public JSONObject delete(Long id) {
        try {
            JSONObject jsonObject = HttpUtils.httpPostLoginRequest(loginUrl);
            jsonObject = JSONObject.parseObject(jsonObject.get("data").toString());
            String token = jsonObject.get("token").toString();
            System.out.println("token======" + token);
            System.out.println("请求联系人修改接口开始-----------------");
            jsonObject = HttpUtils.sendDelete(accountsUrl + "/" + id, token);
            System.out.println("修改结果" + jsonObject);
            return jsonObject;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 修改
     *
     * @param json
     * @return int
     */
    @Override
    public JSONObject updateData(JSONObject json) {
        try {
            JSONObject jsonObject = HttpUtils.httpPostLoginRequest(loginUrl);
            jsonObject = JSONObject.parseObject(jsonObject.get("data").toString());
            String token = jsonObject.get("token").toString();
            System.out.println("token======" + token);
            System.out.println("请求联系人修改接口开始-----------------");
            String url = accountsUrl + "/" + json.getString("id");
            System.out.println("修改请求url：" + url);
            jsonObject = HttpUtils.sendJSONPut(json, url, token);
            System.out.println("修改结果" + jsonObject);
            return jsonObject;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * id查询数据
     *
     * @param id id
     * @return EmployeeLibrary
     */
    @Override
    public EmployeeLibrary findById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 查询所有数据
     *
     * @return List<EmployeeLibrary>
     */
    @Override
    public List<EmployeeLibrary> findAll() {
        return baseMapper.selectList(null);
    }

    @Override
    public int updateAll() {
        String token = "";
        try {
            Map<String, Object> map = new HashMap<>();
            JSONObject json = HttpUtils.httpPostLoginRequest(loginUrl);
            json = JSONObject.parseObject(json.get("data").toString());
            token = json.get("token").toString();
            System.out.println("token======" + token);
            System.out.println("请求联系人信息接口开始-----------------");
            for (int i = 0; i < 5; i++) {
                map.put("page", i + "");
                map.put("per_page", "200");
                json = HttpUtils.sendGet(contactsUrl, map, token);
                System.out.println("code-----" + JSONObject.parseObject(json.get("status").toString()).get("code"));
                JSONArray data = JSONObject.parseArray(JSONObject.parseObject(json.getString("data")).toString());
                System.out.println("共有：" + data.size() + "条员工数据");
                if ((int) (JSONObject.parseObject(json.get("status").toString()).get("code")) == -1) {
                    break;
                } else {
                    System.out.println("开始循环------------");
                    Long id = null;
                    String name = "";
                    String displayName = "";
                    String organizationIds = "";
                    String phone = "";
                    String tel = "";
                    String job = "";
                    for (int i1 = 0; i1 < data.size(); i1++) {
                        id = Long.parseLong(JSONObject.parseObject(data.get(i1).toString()).getString("id"));
                        name = JSONObject.parseObject(data.get(i1).toString()).getString("name");
                        displayName = JSONObject.parseObject(data.get(i1).toString()).getString("displayName");
                        organizationIds = JSONObject.parseObject(data.get(i1).toString()).getString("organizationId");
                        job = JSONObject.parseObject(JSONObject.parseObject(data.get(i1).toString()).getString("extension")).getString("JobNumber");
                        //organizationIds=JSONObject.parseArray(JSONObject.parseObject(data.get(i).toString()).get("organizationIds").toString()).get(0).toString();
                        JSONArray phoneNumbers = JSONObject.parseArray(JSONObject.parseObject(data.get(i1).toString()).get("phoneNumbers").toString());
                        for (int j = 0; j < phoneNumbers.size(); j++) {
                            if ("PRE-MobilePhone".equals(JSONObject.parseObject(phoneNumbers.get(j).toString()).get("type").toString())) {
                                phone = JSONObject.parseObject(phoneNumbers.get(j).toString()).get("number").toString();
                            } else if ("PRE-Work".equals(JSONObject.parseObject(phoneNumbers.get(j).toString()).get("type").toString())) {
                                tel = JSONObject.parseObject(phoneNumbers.get(j).toString()).get("number").toString();
                            }
                        }
                        EmployeeLibrary employeeLibrary = baseMapper.selectById(id);
                        if (employeeLibrary != null) {
                            employeeLibrary.setDept(organizationIds);
                            employeeLibrary.setDisplayName(displayName);
                            employeeLibrary.setName(name);
                            employeeLibrary.setPhone(phone);
                            employeeLibrary.setTel(tel);
                            employeeLibrary.setJob(job);
                            QueryWrapper<EmployeeLibrary> queryWrapper = new QueryWrapper<>();
                            queryWrapper.isNotNull(true, "dept");
                            queryWrapper.isNotNull(true, "display_name");
                            queryWrapper.isNotNull(true, "name");
                            queryWrapper.isNotNull(true, "phone");
                            queryWrapper.isNotNull(true, "tel");
                            baseMapper.update(employeeLibrary, queryWrapper);
                        } else {
                            EmployeeLibrary emp = new EmployeeLibrary();
                            emp.setId(id);
                            emp.setDept(organizationIds);
                            emp.setDisplayName(displayName);
                            emp.setName(name);
                            emp.setPhone(phone);
                            emp.setTel(tel);
                            emp.setJob(job);
                            System.out.println("开始插入员工数据" + emp.toString());
                            baseMapper.insert(emp);
                        }
                    }
                }
            }
            System.out.println("请求联系人信息接口结束+++++++++++++++++++" + json.toString());
            System.out.println("请求部门信息接口开始-----------------");
            map.put("page", "0");
            map.put("per_page", "500");
            json = HttpUtils.sendGet(organizationUrl, map, token);
            System.out.println("部门信息====" + json);
            JSONObject jsonObject = JSONObject.parseObject(json.get("status").toString());
            if (jsonObject.getInteger("code") == 0) {
                JSONArray jsonArray = JSONObject.parseArray(json.get("data").toString());
                Long id = null;
                String name = "";
                String type = "";
                String state = "";
                String parentId = "";
                for (int i = 0; i < jsonArray.size(); i++) {
                    id = Long.parseLong(JSONObject.parseObject(jsonArray.get(i).toString()).getString("id"));
                    name = JSONObject.parseObject(jsonArray.get(i).toString()).getString("name");
                    type = JSONObject.parseObject(jsonArray.get(i).toString()).getString("type");
                    state = JSONObject.parseObject(jsonArray.get(i).toString()).getString("state");
                    parentId = JSONObject.parseObject(jsonArray.get(i).toString()).getString("parentId");

                    Organization organization = organizationMapper.selectById(id);
                    if (organization != null) {
                        organization.setName(name);
                        organization.setType(type);
                        organization.setState(state);
                        organization.setParentId(parentId);
                        QueryWrapper<Organization> queryWrapper = new QueryWrapper<>();
                        queryWrapper.isNotNull(true, "name");
                        queryWrapper.isNotNull(true, "type");
                        queryWrapper.isNotNull(true, "state");
                        queryWrapper.isNotNull(true, "parent_id");
                        organizationMapper.update(organization, queryWrapper);
                    } else {
                        Organization org = new Organization();
                        org.setId(id);
                        org.setName(name);
                        org.setType(type);
                        org.setState(state);
                        org.setParentId(parentId);
                        organizationMapper.insert(org);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("token:" + token);
            return -1;
        }
        return 0;
    }

    @Override
    public String phoneAdd(String number) {
        String token = "";
        try {
            JSONObject json = HttpUtils.httpPostLoginRequest(loginUrl);
            json = JSONObject.parseObject(json.get("data").toString());
            token = json.get("token").toString();
            Map<String, Object> map = new HashMap<>();
            map.put("called", number);
            map.put("caller", 8000);
            json = HttpUtils.sendPost(nuasUrl, map, token);
            JSONObject jsonObject = JSONObject.parseObject(json.get("status").toString());
            if ((int) jsonObject.get("code") != 0) {
                map.put("caller", 8001);
                json = HttpUtils.sendPost(nuasUrl, map, token);
                jsonObject = JSONObject.parseObject(json.get("status").toString());
                if ((int) jsonObject.get("code") != 0) {
                    return "话机占用,请稍后再试";
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("token:" + token);
            return "系统错误，请联系管理员！";
        }
        return "外呼成功";
    }

    @Override
    public JSONObject getDeptData() {
        try {
            JSONObject json = HttpUtils.httpPostLoginRequest(loginUrl);
            json = JSONObject.parseObject(json.get("data").toString());
            String token = json.get("token").toString();
            Map<String, Object> map = new HashMap<>();
            map.put("per_page", 500);
            map.put("page", 0);
            json = HttpUtils.sendGet(organizationUrl, map, token);
            return json;
        } catch (Exception e) {
            return null;
        }
    }

    public String getToken() {
        String url = "https://n2oapi.woqu365.com/token/get";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // jsonString作为请求参数
        JSONObject object1 = new JSONObject();
        object1.put("corpId", "811dc7ed82bb43de82a2febf74381842");
        object1.put("appKey", "c0eb2cc0f31d438295f740f218119123");
        object1.put("appSecret", "5b7a616ce855480ca3bc0e9743355432");
        String jsonString = JSONObject.toJSONString(object1);

        HttpEntity<String> request = new HttpEntity<String>(jsonString, headers);
        String result = restTemplate.postForObject(url, request, String.class);

        // JsonObject
        JSONObject jsonObject = JSONObject.parseObject(result);
        String token = jsonObject.getJSONObject("data").getString("token");

        return token;
    }


    @Override
    public  String getPhoneByName(String name) {
        String token = getToken();
        String url = "https://n2oapi.woqu365.com/v2/api/wrap/employee/findEmployeePage?token=" + token;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject jsonobject = new JSONObject();
        String[] names = {"PERSONAL_PROFILE.employee_code", "PERSONAL_PROFILE.full_name", "CONTACT.mobile_number"};
        jsonobject.put("names", names);
        jsonobject.put("pageSize", 500);
        String jsonString = JSONObject.toJSONString(jsonobject);

        HttpEntity<String> request = new HttpEntity<>(jsonString, headers);

        try {
            String result = restTemplate.postForObject(url, request, String.class);
            log.debug("API result: {}", result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONArray employeeList = jsonObject.getJSONObject("data").getJSONArray("list");
            for (int i = 0; i < employeeList.size(); i++) {
                JSONObject employeeObj = employeeList.getJSONObject(i);
                if (name.equals(employeeObj.getString("full_name"))) {
                    String phone = employeeObj.getString("mobile_number");
                    String res = name + "的电话号码是" + phone;
                    log.debug("Result: {}", res);
                    return res;
                }
            }
        } catch (Exception e) {
            log.error("An error occurred during API call or processing result", e);
        }
        return name + "的电话号码未找到";
    }
}
