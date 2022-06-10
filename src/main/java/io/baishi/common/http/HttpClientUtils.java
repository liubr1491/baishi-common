package io.baishi.common.http;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 使用HttpClient访问url工具类
 *
 * @author nick
 * @date 2018/2/24
 */
public class HttpClientUtils {

    private static final int HTTP_STATUS_CODE_SUCCESS = 200;

    private static final CloseableHttpClient CLOSEABLE_HTTP_CLIENT = HttpClients.createDefault();

    private static final int CONNECTION_REQUEST_TIMEOUT = 1000;
    private static final int SOCKET_TIMEOUT = 1000;
    private static final int CONNECT_TIMEOUT = 1000;
    private static final String CONTENT_TYPE_STREAM = "application/octet-stream";
    private static final boolean CHECK_CONTENT_LENGTH = false;

    /**
     * post访问
     *
     * @param url
     * @param requestBytes
     * @return
     */
    public static byte[] sendPost(String url, byte[] requestBytes) throws IOException {
        return sendPost(url,
                requestBytes,
                CONTENT_TYPE_STREAM,
                CHECK_CONTENT_LENGTH,
                CONNECTION_REQUEST_TIMEOUT,
                SOCKET_TIMEOUT,
                CONNECT_TIMEOUT);
    }

    /**
     * post访问
     *
     * @param url
     * @param requestBytes
     * @param contentType
     * @return
     */
    public static byte[] sendPost(String url, byte[] requestBytes, String contentType) throws IOException {
        return sendPost(url,
                requestBytes,
                contentType,
                CHECK_CONTENT_LENGTH,
                CONNECTION_REQUEST_TIMEOUT,
                SOCKET_TIMEOUT,
                CONNECT_TIMEOUT);
    }

    public static byte[] sendPost(String url, byte[] requestBytes, int connectTimeout) throws IOException {
        return sendPost(url,
                requestBytes,
                CONTENT_TYPE_STREAM,
                CHECK_CONTENT_LENGTH,
                CONNECTION_REQUEST_TIMEOUT,
                SOCKET_TIMEOUT,
                connectTimeout);
    }

    public static byte[] sendPost(String url,
                                  byte[] requestBytes,
                                  int connectionRequestTimeout,
                                  int socketTimeout,
                                  int connectTimeout) throws IOException {
        return sendPost(url,
                requestBytes,
                CONTENT_TYPE_STREAM,
                CHECK_CONTENT_LENGTH,
                connectionRequestTimeout,
                socketTimeout,
                connectTimeout);
    }

    /**
     * 通过POST方式访问
     *
     * @param url
     * @param bytes
     * @param contentType
     * @param checkContentLength
     * @param connectionRequestTimeout
     * @param socketTimeout
     * @param connectTimeout
     * @return
     * @throws IOException
     */
    public static byte[] sendPost(String url,
                                  byte[] bytes,
                                  String contentType,
                                  boolean checkContentLength,
                                  int connectionRequestTimeout,
                                  int socketTimeout,
                                  int connectTimeout) throws IOException, CustomHttpException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new ByteArrayEntity(bytes));
        if (!StringUtils.isBlank(contentType)) {
            httpPost.setHeader("Content-type", contentType);
        }
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .build();
        httpPost.setConfig(requestConfig);
        try (CloseableHttpResponse httpResponse = CLOSEABLE_HTTP_CLIENT.execute(httpPost)) {
            if (httpResponse.getStatusLine().getStatusCode() != HTTP_STATUS_CODE_SUCCESS) {
                throw new CustomHttpException(httpResponse.getStatusLine() + ". url is " + url);
            }
            HttpEntity entityResponse = httpResponse.getEntity();
            if (checkContentLength) {
                long contentLength = entityResponse.getContentLength();
                if (contentLength <= 0) {
                    throw new CustomHttpException("has response, but response's contentLength is zero. url is " + url);
                }
            }
            return EntityUtils.toByteArray(entityResponse);
        }
    }
}
