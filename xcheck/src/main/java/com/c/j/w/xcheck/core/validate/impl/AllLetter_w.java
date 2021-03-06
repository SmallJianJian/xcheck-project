package com.c.j.w.xcheck.core.validate.impl;

import com.c.j.w.xcheck.core.XResult;
import com.c.j.w.xcheck.core.util.StringUtil;
import com.c.j.w.xcheck.core.validate.AbstractValidateMethod;
import com.c.j.w.xcheck.core.validate.ValidateParam;
import org.springframework.stereotype.Component;

/**
 * 全字母
 * @Author chenjw
 * @Date 2016年12月08日
 */
@Component("w")
public class AllLetter_w extends AbstractValidateMethod {

    @Override
    public XResult validate(ValidateParam validateParam) {
        if (StringUtil.isAllLetter(validateParam.getMainFieldVal())) {
            return XResult.success();
        } else {
            return XResult.failure(getFieldAlias(validateParam) + "必须为字母");
        }
    }

}
