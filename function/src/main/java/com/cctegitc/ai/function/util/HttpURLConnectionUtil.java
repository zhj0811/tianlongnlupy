package com.cctegitc.ai.function.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class HttpURLConnectionUtil {

    @Autowired
    private RestTemplate restTemplate;


    public String doPost(String url, String json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String res = "";

        try {
            HttpPost post = new HttpPost(url);
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            post.setEntity(entity);
            response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                res = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            } else {
                log.error("HTTP请求失败，状态码：{}", response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            log.error("请求语义接口失败！错误信息：{}", e.getMessage(), e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                log.error("关闭HTTP响应时出错：{}", e.getMessage());
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                log.error("关闭HTTP客户端时出错：{}", e.getMessage());
            }
        }
        return res;
    }

    /**
     * Http get请求
     *
     * @param httpUrl 连接
     * @param param   参数
     * @return 响应数据
     */
    public String doGetAnswerOld(String httpUrl, String param, String userId, String controlFlag) {
        long startTime = System.currentTimeMillis();
        String result = null;
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
                    .path("/qa/{param}")
                    .queryParam("control", controlFlag)
                    .queryParam("createPcode", userId);
            String url = builder.buildAndExpand(param).toUriString();
            log.debug("请求url: {}", url);
            result = restTemplate.getForObject(url, String.class);
        } catch (RestClientException e) {
            log.error("请求Rest服务失败！错误信息：{}", e.getMessage(), e);
        } catch (Exception e) {
            log.error("请求发生未知错误！错误信息：{}", e.getMessage(), e);
        } finally {
            long endTime = System.currentTimeMillis();
            log.debug("请求url: {}, 耗时：毫秒", httpUrl, endTime - startTime);
        }
        return result;
    }

    public String doGetAnswer(String httpUrl, String param, String userId, String controlFlag) {
        log.debug("请求url: {}, param :{}", httpUrl, param);
        String result = null;
        try {
            // 构建URL，并确保参数被正确编码
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
                    .path("/qa/{param}")
                    .queryParam("control", controlFlag)
                    .queryParam("createPcode", userId);
            String url = builder.buildAndExpand(param).toUriString();
            log.debug("请求URL: {}", url);
            // 使用exchange方法以获取完整的HTTP响应
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                result = response.getBody();
                log.debug("请求成功, 返回内容: {}", result);
            } else {
                log.debug("请求失败, HTTP状态码: {}", response.getStatusCode());
            }
        } catch (RestClientException e) {
            log.error("请求Rest服务失败，错误信息：{}", e.getMessage(), e);
        } catch (Exception e) {
            log.error("请求发生未知错误，错误信息：{}", e.getMessage(), e);
        } finally {
            log.debug("请求URL: {}", httpUrl);
        }
        return result;
    }


    public String doGetNoParam(String httpUrl, String path) {
        String result = null;
        try {
            // 构建URL，并确保参数被正确编码
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
                    .path(path)
                    .queryParam("control", 1)
                    .queryParam("createPcode", 1);
            String url = builder.buildAndExpand().toUriString();
            log.debug("请求URL: {}", url);
            // 使用exchange方法以获取完整的HTTP响应
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                result = response.getBody();
                if (result == null || result.isEmpty()) {
                    log.error("请求成功，但返回内容为空");
                    return "-1";
                }
                log.debug("请求成功, 返回内容: {}", result);
            } else {
                log.debug("请求失败, HTTP状态码: {}", response.getStatusCode());
            }
        } catch (RestClientException e) {
            log.error("请求Rest服务失败，错误信息：{}", e.getMessage(), e);
        } catch (Exception e) {
            log.error("请求发生未知错误，错误信息：{}", e.getMessage(), e);
        } finally {
            log.debug("请求URL: {} is failed", httpUrl + path);
        }
        return result;
    }


    public String doPostAnswer(String httpUrl, String param, String userId, String controlFlag) {
        String result = null;
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(httpUrl).path("/qa/{param}");
            String url = builder.buildAndExpand(param).toUriString();
            log.debug("请求url: {}", url);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // 构造请求体（根据实际情况，这里使用Map代表JSON对象，也可以创建一个具体的DTO类）
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("control", controlFlag);
            requestBody.put("createPcode", userId);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

            result = restTemplate.postForObject(url, request, String.class);
        } catch (RestClientException e) {
            log.error("请求Rest服务失败！错误信息：{}", e.getMessage(), e);
        } catch (Exception e) {
            log.error("请求发生未知错误！错误信息：{}", e.getMessage(), e);
        } finally {
            log.debug("请求url: {}", httpUrl);
        }
        return result;
    }
}
