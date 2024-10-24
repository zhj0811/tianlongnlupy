package com.cctegitc.ai.function.scheduleTask;//package com.cctegitc.coalding.scheduleTask;

import com.alibaba.fastjson.JSONObject;
import com.cctegitc.ai.function.util.HttpUtils;

import java.util.*;


public class ScheduledExceutor  extends Thread {

    private String loginUrl="http://178.68.140.224:8089/nuas/api/v1/sessions";

    private String noticeUrl="http://178.68.140.224:8089/nuas/api/v1/recordnotice";

    private String content = "";

    private String phone = "";

    public ScheduledExceutor(String content,String phone) {
        super();
        this.content = content;
        this.phone = phone;
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        String token="";
        try{
            Map<String,Object> map = new HashMap<>();
            JSONObject json= HttpUtils.httpPostLoginRequest(loginUrl);
        json= JSONObject.parseObject(json.get("data").toString());
        token=json.get("token").toString();
        map.clear();
        map.put("groupId","601000100016");
        map.put("message",content);
        Map<String,Object> map1 = new HashMap<>();
        List<JSONObject> list=new ArrayList<>();
        map1.put("id","");
        String[] arg=new String[1];
        arg[0]=phone;
        map1.put("phoneNums",arg);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id","");
            jsonObject.put("phoneNums",arg);
            list.add(jsonObject);
        map.put("contact2NumberInfos",list);
        json=HttpUtils.sendPost(noticeUrl,map,token);
        System.out.println("请求录音通知接口结束+++++++++++++++++++"+json.toString());
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("token:"+token);
            System.out.println("ERROR--Date = " + new Date() + ", content :" + content + ", phone :" + phone);
        }
    }



}
