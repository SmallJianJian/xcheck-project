package com.c.j.w.xcheck.core;

import com.alibaba.fastjson.JSONObject;
import com.c.j.w.xcheck.core.po.FailureResponse;
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
//            writer.write(JSONObject.toJSONString(new FailureResponse(failMessage)));
            writer.write("{\"status\":" + 5010 + ",\"message\":\"" + failMessage + "\"}");
        } catch (IOException var8) {
            var8.printStackTrace();
        } finally {
            if(writer != null) {
                writer.close();
            }
        }
    }


    public static void main(String[] args) {
        System.out.printf(JSONObject.toJSONString(new FailureResponse("不能为空")));
    }
}
