package com.ytz.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @ClassName: ElasticSearchConfiguration
 * @Description: Elasticsearch配置信息类
 * @author: yangtianzeng
 * @date: 2020/6/8 14:01
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.ytz.shop.repository.es")
@EnableJpaRepositories(basePackages = "com.ytz.shop.repository.jpa")
public class ElasticSearchConfiguration {
}
