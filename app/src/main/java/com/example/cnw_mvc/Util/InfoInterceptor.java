package com.example.cnw_mvc.Util;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class InfoInterceptor implements Interceptor {
    private static final String TAG = "TestInterceptor";

    // 请求body转String
    private static String requestBodyToString(RequestBody requestBody) throws IOException {
        if (requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            return buffer.readUtf8();
        }
        return "";
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Charset UTF8 = Charset.forName("UTF-8");
        // 打印请求报文
        Request request = chain.request();
        HttpUrl url = request.url();
        String scheme = url.scheme();
        String host = url.host();
        int port = url.port();
        String path = url.encodedPath();
        String query = url.encodedQuery();
//        Log.d(TAG, "scheme: " + scheme);
//        Log.d(TAG, "host: " + host);
//        Log.d(TAG, "port: " + port);
//        Log.d(TAG, "path: " + path);
//        Log.d(TAG, "query: " + query);
        RequestBody requestBody = request.body();
        String bodyToString = null;
        if (requestBody != null) {
            bodyToString = requestBodyToString(requestBody);
        }
//        Log.d(TAG, String.format("发送请求:\nmethod: %s\nheaders: %surl: %s\nbody: %s",
//                request.method(), request.headers(), url, bodyToString));
        // 打印返回报文
        // 先执行请求，才能获取报文
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
        String bodyString = "";
        String bodySize = "";
        if (responseBody != null) {
            //不能使用responseBody.string()，否则response会close，在onResponse()回调获取不到response
//            bodyString = responseBody.string();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();
            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();// text/plain;charset=UTF-8
            if (contentType != null) {
                charset = contentType.charset(UTF8);// UTF-8, charset might be null
            }
            bodyString = buffer.clone().readString(charset == null ? UTF8 : charset);
            long contentLength = responseBody.contentLength();
            bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
        }
        Util.d(TAG, String.format("收到响应:\n%s %s\nheaders: %s请求url: %s\n请求body: %s\n" +
                        "响应body: %s\n响应bodySize: %s",
                response.code(), response.message(), response.headers(), response.request().url(),
                requestBodyToString(response.request().body()), bodyString, bodySize));
        return response;
    }
}