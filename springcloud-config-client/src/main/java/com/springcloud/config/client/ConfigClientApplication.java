/**
 * Copyright 2019 Welab, Inc. All rights reserved.
 * WELAB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.springcloud.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author <a href="mailto:ken.wu@welab-inc.com">ken.wu</a>
 * @date 2019-12-03
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigClientApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

}
