package com.cctegitc.ai.function.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: wgd
 * @Date: 2022-08-22
 * @Time: 9:51
 */
@Configuration
public class ElasticSerachConfig {


    @Bean
    public RestHighLevelClient restHighLevelClient() {

        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
                new HttpHost("127.0.0.1", 9200, "http")
        ));

        return client;
    }
}
