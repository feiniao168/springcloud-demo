/**
 * Copyright 2019 Welab, Inc. All rights reserved.
 * WELAB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.springcloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author <a href="mailto:ken.wu@welab-inc.com">ken.wu</a>
 * @date 2019-12-02
 */
@EnableEurekaClient
@SpringBootApplication
public class BookApplication {
    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }
}
