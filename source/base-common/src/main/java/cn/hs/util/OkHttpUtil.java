package cn.hs.util;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OkHttpUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(OkHttpUtil.class);

    //优化Client
    public static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(2, TimeUnit.SECONDS)
            .build();

    public static String post(String url, RequestBody body) {
        LOGGER.info("url=" + url);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            LOGGER.error("请求异常", e);
        }
        return null;
    }

    public static String get(String url) {
        LOGGER.info("url=" + url);
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            LOGGER.error("请求异常", e);
        }
        return null;
    }
}
