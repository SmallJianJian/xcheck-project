package com.c.j.w.xcheck.core;

import com.alibaba.fastjson.JSONObject;
import com.c.j.w.xcheck.core.po.FailureResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by kevin on 2016/12/5.
 */
//@ConditionalOnMissingBean(XCheckHandlerAdapter.class)
@Configuration
public class DefaultXCheckHandler implements XCheckHandlerAdapter {

    @Autowired(required = false)
    private XCheckProperties xCheckProperties;

    /**
     * 设置校验不通过时响应处理
     * @param request
     * @param response
     * @param failMessage 校验不通过原因
     */
    @Override
    public void checkFailHandle(HttpServletRequest request, HttpServletResponse response, String failMessage) {
        PrintWriter writer = null;
        try {
            response.setContentType("application/json; charset=utf-8");
            writer = response.getWriter();
            FailureResponse failureResponse = new FailureResponse(failMessage);
            if(null!= xCheckProperties.getErrorCode()){
                failureResponse.setCode(xCheckProperties.getErrorCode());
            }
            writer.write(JSONObject.toJSONString(failureResponse));
        } catch (IOException var8) {
            var8.printStackTrace();
        } finally {
            if(writer != null) {
                writer.close();
            }
        }
    }
}
