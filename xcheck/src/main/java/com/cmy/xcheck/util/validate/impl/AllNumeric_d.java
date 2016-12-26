package com.cmy.xcheck.util.validate.impl;

import com.cmy.xcheck.support.XResult;
import com.cmy.xcheck.util.StringUtil;
import com.cmy.xcheck.util.validate.ValidateMethod;
import com.cmy.xcheck.util.validate.ValidateParam;
import org.springframework.stereotype.Component;

/**
 * 全数字
 * @Author chenjw
 * @Date 2016年12月08日
 */
@Component
public class AllNumeric_d implements ValidateMethod {

    @Override
    public XResult validate(ValidateParam validateParam) {
        if (StringUtil.isAllDigit(validateParam.getMainFieldVal())) {
            return XResult.success();
        } else {
            return XResult.failure(validateParam.getMainFieldName() + "必须为全数字");
        }
    }

    @Override
    public String getMethodAttr() {
        return "d";
    }
}
