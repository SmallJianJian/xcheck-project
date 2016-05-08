package com.cmy.xcheck.util;

import com.cmy.xcheck.config.XMessageBuilder;
import com.cmy.xcheck.support.XBean;
import com.cmy.xcheck.support.annotation.Check;
import com.cmy.xcheck.support.annotation.XAnnotationConfigApplicationContext;
import com.cmy.xcheck.util.analyze.ConditionExpressionAnalyzer;
import com.cmy.xcheck.util.analyze.LogicExpressionAnalyzer;
import com.cmy.xcheck.util.analyze.SimpleExpressionAnalyzer;
import com.cmy.xcheck.util.item.XCheckItem;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 表达式解析
 * Created by Kevin72c on 2016/4/29.
 */
public class XExpressionParser {

    /**
     * 扫描解析校验对象
     * @param classes
     */
    public static void parseXbean(Set<Class<?>> classes) {

        for (Class<?> clz : classes) {
            Method[] methods = clz.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Check.class)) {
                    parseXbean_(method);
                }
            }
        }
    }

    /**
     * 解析校验对象
     * @param method
     */
    public static void parseXbean_(Method method) {
        Check check = method.getAnnotation(Check.class);
        String[] values = check.value();
        Map<String, String> fieldAlias = parseFieldAliasToMap(check.fieldAlias());
        boolean hint = check.hint();
        boolean required = check.required();
        List<XCheckItem> checkItems = new ArrayList<XCheckItem>();
        for (int i = 0; i < values.length; i++) {
            if (Validator.isNotEmpty(values[i])) {
                checkItems.add(parseExpression(values[i]));
            }
        }
        XBean xBean = new XBean(fieldAlias, checkItems, required, hint);
        // 注册校验对象
        XAnnotationConfigApplicationContext.register(check, xBean);
    }

    /**
     * 字段别名转map
     * @param fieldAlias
     * @return
     */
    private static Map<String, String> parseFieldAliasToMap(String[] fieldAlias) {
        Map<String, String> m = new HashMap<String, String>();
        for (String alias : fieldAlias) {
            String[] split = alias.replaceAll("\\s", "").split(",");
            for (String sp :split) {
                if(Validator.isEmpty(sp)) {
                    continue;
                }
                String[] fieldAndName = sp.split("=");
                int fieldNameLen = fieldAndName.length;
                if (fieldNameLen == 2) {
                    m.put(fieldAndName[0], fieldAndName[1]);
                } else {
                    throw new IllegalArgumentException(XMessageBuilder.getProperty("fieldAliasError"));
                }
            }
        }
        return m;
    }

    /**
     * 解析表达式类型
     * @param expression
     * @return
     */
    private static XCheckItem parseExpression(String expression) {
        XCheckItem checkItem;
        if (expression.startsWith("if")) {
            // if表达式
            checkItem = ConditionExpressionAnalyzer.analyze(expression);
        } else if (expression.matches("(.*?)(<=|<|>=|>|==|!=)(.*)")) {
            // 逻辑比较表达式
            checkItem = LogicExpressionAnalyzer.analyze(expression);
        } else {
            // 普通表达式
            checkItem = SimpleExpressionAnalyzer.analyze(expression);
        }
        return checkItem;
    }

}