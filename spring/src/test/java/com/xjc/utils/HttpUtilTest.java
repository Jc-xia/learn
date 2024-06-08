package com.xjc.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.Test;

public class HttpUtilTest {

    @Test
    public void sendGet() throws Exception {
        CloseableHttpResponse closeableHttpResponse = HttpUtil.sendGetResponse("http://localhost:8080/httptest", null, "UTF-8");
        System.out.println(HttpUtil.parseData(closeableHttpResponse));
    }
}