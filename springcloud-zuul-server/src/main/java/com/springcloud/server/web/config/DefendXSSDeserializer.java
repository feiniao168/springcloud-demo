/**
 * Copyright 2019 Welab, Inc. All rights reserved.
 * WELAB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.springcloud.server.web.config;

import java.lang.reflect.Type;

import org.springframework.web.util.HtmlUtils;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

/**
 * @author <a href="mailto:ken.wu@welab-inc.com">ken.wu</a>
 * @date 2019-12-16
 */
public class DefendXSSDeserializer implements ObjectDeserializer {

    /** 
     * @see com.alibaba.fastjson.parser.deserializer.ObjectDeserializer#deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object)
     */
//    @SuppressWarnings("unchecked")
//    @Override
//    public String deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
//        if (clazz == String.class) {
//            final JSONLexer lexer = parser.lexer;
//            String str = lexer.stringVal();
//            return HtmlUtils.htmlEscape(str, "utf-8");
//        }
//        throw new IllegalStateException("The "+clazz+" not is java.lang.String");
//    }
//
//    /** 
//     * @see com.alibaba.fastjson.parser.deserializer.ObjectDeserializer#getFastMatchToken()
//     */
//    @Override
//    public int getFastMatchToken() {
//        // TODO Auto-generated method stub
//        return JSONToken.LITERAL_STRING;
//    }

    @SuppressWarnings("unchecked")
    public <T> T deserialze(DefaultJSONParser parser, Type clazz, Object fieldName) {
        if (clazz == StringBuffer.class) {
            final JSONLexer lexer = parser.lexer;
            if (lexer.token() == JSONToken.LITERAL_STRING) {
                String val = lexer.stringVal();
                lexer.nextToken(JSONToken.COMMA);

                return (T) new StringBuffer(HtmlUtils.htmlEscape(val, "utf-8"));
            }
            Object value = parser.parse();
            if (value == null) {
                return null;
            }
            return (T) new StringBuffer(HtmlUtils.htmlEscape(value.toString(), "utf-8"));
        }
        if (clazz == StringBuilder.class) {
            final JSONLexer lexer = parser.lexer;
            if (lexer.token() == JSONToken.LITERAL_STRING) {
                String val = lexer.stringVal();
                lexer.nextToken(JSONToken.COMMA);
                return (T) new StringBuilder(HtmlUtils.htmlEscape(val, "utf-8"));
            }
            Object value = parser.parse();
            if (value == null) {
                return null;
            }
            return (T) new StringBuilder(HtmlUtils.htmlEscape(value.toString(), "utf-8"));
        }

        return (T) deserialze(parser);
    }

    @SuppressWarnings("unchecked")
    public static <T> T deserialze(DefaultJSONParser parser) {
        final JSONLexer lexer = parser.getLexer();
        if (lexer.token() == JSONToken.LITERAL_STRING) {
            String val = lexer.stringVal();
            lexer.nextToken(JSONToken.COMMA);
            return (T) HtmlUtils.htmlEscape(val, "utf-8");
        }
        if (lexer.token() == JSONToken.LITERAL_INT) {
            String val = lexer.numberString();
            lexer.nextToken(JSONToken.COMMA);
            return (T) HtmlUtils.htmlEscape(val, "utf-8");
        }
        Object value = parser.parse();
        if (value == null) {
            return null;
        }
        return (T) HtmlUtils.htmlEscape(value.toString(), "utf-8");
    }

    public int getFastMatchToken() {
        return JSONToken.LITERAL_STRING;
    }
}
