/**
 * 
 */
package com.springcloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.springcloud.zuul.filter.SimpleFilter;

/**
 * @author <a href="mailto:ken.wu@welab-inc.com">ken.wu</a>
 * @date 2019-11-08
 */
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class GatewayApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public SimpleFilter simpleFilter() {
        return new SimpleFilter();
    }
}
