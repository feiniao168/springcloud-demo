package com.springcloud.server.web.config;

import java.io.IOException;

import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * 防范客户端xss脚本注入
 * 
 * @author <a href="mailto:ken.wu@welab-inc.com">ken.wu</a>
 * @date 2019-12-18
 */
public class XssDeserializer extends StdDeserializer<String> {

    private static final long serialVersionUID = 4762953545914037569L;

    public XssDeserializer() {
        this(null);
    }

    public XssDeserializer(Class<String> t) {
        super(t);
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        if (StringUtils.isEmpty(value)) {
            return value;
        } else {
            return HtmlUtils.htmlEscape(value, "utf-8");
        }
    }
}
