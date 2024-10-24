package com.cctegitc.ai.function.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: wgd
 * @Date: 2023-07-21
 * @Time: 14:02
 */
@Slf4j
public class HttpUtils {
    public static JSONObject httpPostLoginRequest(String url) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("imei", "myimei");
        parameters.put("userName", "zndd");
        parameters.put("password", "Nucleus!");
        URL urls = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urls.openConnection();
        conn.setDoOutput(true);// 写入服务器
        conn.setDoInput(true);// 读取服务器
        conn.setUseCaches(false);// 使用缓存
        conn.setInstanceFollowRedirects(true);// 跟随重定向
        conn.setConnectTimeout(5000);// 控制socket等待建立连接的时间
        conn.setRequestMethod("POST"); // 设置请求方式
        conn.setRequestProperty("Accept-Charset", "utf-8");
        conn.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
        conn.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
        conn.setRequestProperty("Content-Length", "<calculated when request is sent>");
        conn.setRequestProperty("Host", "<calculated when request is sent>");
        conn.setRequestProperty("X-Nonce", "12345678");
        conn.setRequestProperty("X-Timestamp", "1530782756705");
        conn.setRequestProperty("X-Signature", "5288f22b25e5e52fc008df074da838b2e00ab8cc");
        conn.connect();
        // json格式的参数时
        JSONObject param = new JSONObject(parameters);
        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
        writer.write(param.toString());
        writer.flush();
        if (conn.getResponseCode() != 200) {
            System.out.println("响应码：" + conn.getResponseCode());
            System.out.println("请求失败：" + conn.getResponseMessage());
            return null;
        } else {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuffer buffer = new StringBuffer("");
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            // 返回值是json格式时
            JSONObject result = JSONObject.parseObject(buffer.toString());
            return result;
        }
    }


    public static JSONObject sendGet(String url, Map<String, Object> parameters, String token) {
        StringBuilder result = new StringBuilder();
        StringBuilder params = new StringBuilder();
        HttpURLConnection httpConn = null;

        try {
            // 编码请求参数
            URL connURL = new URL(buildFullURL(url, parameters, params));
            httpConn = (HttpURLConnection) connURL.openConnection();

            // 设置通用属性
            httpConn.setRequestProperty("X-Token", token);
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "keep-alive");

            // 建立实际的连接
            httpConn.connect();

            // 读取返回的内容
            try (BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
            }
        } catch (IOException e) {
            log.error("sendGet request error ", e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
            }
        }

        try {
            return JSONObject.parseObject(result.toString());
        } catch (Exception e) {
            log.error("parse JSON error : {}", result, e);
            return null;
        }
    }

    private static String buildFullURL(String url, Map<String, Object> parameters, StringBuilder params) throws Exception {
        if (parameters != null && !parameters.isEmpty()) {
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                if (params.length() > 0) {
                    params.append("&");
                }
                params.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.name()))
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8.name()));
            }
            url += "?" + params;
        }
        log.debug("sendGet request URL: " + url);
        return url;
    }

    /**
     * 发送POST请求
     *
     * @param url        目的地址
     * @param parameters 请求参数，Map类型。
     * @return 远程响应结果
     */
    public static JSONObject sendPostLogin(String url, Map<String, Object> parameters) throws Exception {
        StringBuilder result = new StringBuilder();
        // 处理请求参数
        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            if (params.length() > 0) {
                params.append("&");
            }
            params.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.name()))
                    .append("=")
                    .append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8.name()));
        }

        HttpURLConnection httpConn = null;
        try {
            URL connURL = new URL(url);
            httpConn = (HttpURLConnection) connURL.openConnection();

            // 设置通用属性
            setRequestProperties(httpConn);

            // 设置POST方式
            httpConn.setRequestMethod("POST");
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);

            // 发送请求参数
            try (OutputStreamWriter out = new OutputStreamWriter(httpConn.getOutputStream(), StandardCharsets.UTF_8)) {
                out.write(params.toString());
                out.flush();
            }

            // 读取返回的内容
            try (BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
            }
        } catch (Exception e) {
            // 在实际项目中应使用日志框架记录异常，例如log.error("发送POST登录请求异常", e);
            log.error("发送POST登录请求异常", e);
        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
            }
        }
        return JSONObject.parseObject(result.toString());
    }

    private static void setRequestProperties(HttpURLConnection httpConn) {
        httpConn.setRequestProperty("Accept", "*/*");
        httpConn.setRequestProperty("Connection", "Keep-Alive");
        httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
        httpConn.setRequestProperty("X-Nonce", "12345678");
        httpConn.setRequestProperty("X-Timestamp", "1530782756705");
        httpConn.setRequestProperty("X-Signature", "5288f22b25e5e52fc008df074da838b2e00ab8cc");
    }


    /**
     * 发送POST请求
     *
     * @param url        目的地址
     * @param parameters 请求参数，Map类型。
     * @return 远程响应结果
     */
    public static JSONObject sendPost(String url, Map<String, Object> parameters, String token) {
        HttpURLConnection conn = null;
        try {
            URL urls = new URL(url);
            conn = (HttpURLConnection) urls.openConnection();
            // 初始化HTTP连接的基本设置
            initializeConnection(conn, token, "POST");
            // 将Map格式的参数转换成JSON格式并发送
            sendRequest(conn, parameters);
            // 检查服务器响应码，处理服务器返回的数据
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return receiveResponse(conn);
            } else {
                // 可以改为日志记录或抛出自定义异常
                log.debug("HTTP Response fail Code：" + conn.getResponseCode());
                return null;
            }
        } catch (IOException e) {
            // 日志记录或异常处理
            log.error("HTTP Request fail", e);
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private static void initializeConnection(HttpURLConnection conn, String token, String requestMethod) throws IOException {
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        conn.setInstanceFollowRedirects(true);
        conn.setConnectTimeout(5000);
        conn.setRequestProperty("Accept-Charset", "utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setRequestProperty("X-Token", token);
        conn.setRequestMethod(requestMethod);
    }

    private static void sendRequest(HttpURLConnection conn, Map<String, Object> parameters) throws IOException {
        JSONObject param = new JSONObject(parameters);
        try (OutputStream os = conn.getOutputStream()) {
            os.write(param.toString().getBytes(StandardCharsets.UTF_8));
            os.flush();
        }
    }

    private static JSONObject receiveResponse(HttpURLConnection conn) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder buffer = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            return JSONObject.parseObject(buffer.toString());
        }
    }



    public static JSONObject sendDelete(String url, String token) {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            initializeConnection(conn, token,"DELETE");
            conn.connect();

            // 检查响应码
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return readResponse(conn);
            } else {
                log.debug("HTTP Response fail Code：" + conn.getResponseCode());
                return null;
            }
        } catch (IOException e) {
            log.error("发送DELETE请求时发生异常", e);
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private static JSONObject readResponse(HttpURLConnection conn) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder buffer = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            return JSONObject.parseObject(buffer.toString());
        }
    }


    public static JSONObject sendJSONPost(JSONObject json, String URL, String token) throws IOException {
        URL urls = new URL(URL);
        HttpURLConnection conn = (HttpURLConnection) urls.openConnection();
        conn.setDoOutput(true);// 写入服务器
        conn.setDoInput(true);// 读取服务器
        conn.setUseCaches(false);// 使用缓存
        conn.setInstanceFollowRedirects(true);// 跟随重定向
        conn.setConnectTimeout(5000);// 控制socket等待建立连接的时间
        conn.setRequestMethod("POST"); // 设置请求方式
        conn.setRequestProperty("Accept-Charset", "utf-8");
        conn.setRequestProperty("Host", "<calculated when request is sent>");
        conn.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
        conn.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
        conn.setRequestProperty("Content-Length", "<calculated when request is sent>");
        conn.setRequestProperty("X-Nonce", "12345678");
        conn.setRequestProperty("X-Timestamp", "1530782756705");
        conn.setRequestProperty("X-Signature", "5288f22b25e5e52fc008df074da838b2e00ab8cc");
        conn.setRequestProperty("X-Token", token);
        conn.connect();
        // json格式的参数时
        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
        writer.write(json.toString());
        writer.flush();
        if (conn.getResponseCode() != 200) {
            System.out.println("响应码：" + conn.getResponseCode());
            System.out.println("请求失败：" + conn.getResponseMessage());
            return null;
        } else {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuffer buffer = new StringBuffer("");
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            // 返回值是json格式时
            JSONObject result = JSONObject.parseObject(buffer.toString());
            return result;
        }
    }

    public static JSONObject sendJSONPut(JSONObject json, String URL, String token) throws IOException {
        URL urls = new URL(URL);
        HttpURLConnection conn = (HttpURLConnection) urls.openConnection();
        conn.setDoOutput(true);// 写入服务器
        conn.setDoInput(true);// 读取服务器
        conn.setUseCaches(false);// 使用缓存
        conn.setInstanceFollowRedirects(true);// 跟随重定向
        conn.setConnectTimeout(5000);// 控制socket等待建立连接的时间
        conn.setRequestMethod("PUT"); // 设置请求方式
        conn.setRequestProperty("Accept-Charset", "utf-8");
        conn.setRequestProperty("Host", "<calculated when request is sent>");
        conn.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
        conn.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
        conn.setRequestProperty("Content-Length", "<calculated when request is sent>");
        conn.setRequestProperty("X-Nonce", "12345678");
        conn.setRequestProperty("X-Timestamp", "1530782756705");
        conn.setRequestProperty("X-Signature", "5288f22b25e5e52fc008df074da838b2e00ab8cc");
        conn.setRequestProperty("X-Token", token);
        conn.connect();
        // json格式的参数时
        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
        writer.write(json.toString());
        writer.flush();
        if (conn.getResponseCode() != 200) {
            System.out.println("响应码：" + conn.getResponseCode());
            System.out.println("请求失败：" + conn.getResponseMessage());
            return null;
        } else {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuffer buffer = new StringBuffer("");
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            // 返回值是json格式时
            JSONObject result = JSONObject.parseObject(buffer.toString());
            return result;
        }
    }
}
