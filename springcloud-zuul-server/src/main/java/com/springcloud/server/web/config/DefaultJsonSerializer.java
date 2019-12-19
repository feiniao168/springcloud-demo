package com.springcloud.server.web.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

/**
 * String类型的json序列化处理器
 */
public class DefaultJsonSerializer extends StdSerializer<String> {

    private static final long serialVersionUID = -7683006153440725447L;

    public DefaultJsonSerializer() {
        this(null);
    }

    public DefaultJsonSerializer(Class<String> t) {
        super(t);
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        // xss策略再此执行
        if (StringUtils.hasText(value)) {
            gen.writeString(HtmlUtils.htmlEscape(value, "utf-8"));
        }
    }
}
