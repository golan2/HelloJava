package http.client;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class SimpleHttpClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleHttpClient.class);

    private static final String  MIME_TYPE_JSON       = "application/json";
    private static final String  CHARSET_UTF_8        = "charset=UTF-8";
    private static final String  MIME_TYPE_JSON_UTF_8 = MIME_TYPE_JSON + ";" + CHARSET_UTF_8;
    private static final boolean NEED_PROXY           = false;
    private static final String  HOST_PORT            = "izik:9200";

    public static void main(String[] args) throws IOException {

        LOGGER.trace("AAAAAAAAAAAA");
//        final RequestConfig requestConfig = RequestConfig.custom()/*.setConnectTimeout(3000).setSocketTimeout(4000)*/.build();
        final HttpClientBuilder builder = HttpClientBuilder
                .create()
                .setConnectionManager(new BasicHttpClientConnectionManager())
                /*.setDefaultRequestConfig(requestConfig)*/;

        if (NEED_PROXY) {
            HttpHost proxy = new HttpHost("web-proxy.atl.hp.com", 8080, "http");
            builder.setProxy(proxy);
        }



        try (final CloseableHttpClient client = builder.build()) {


            for (int i = 0; i < 150; i++) {
                LOGGER.debug("============= Iteration [{}] Begin", String.format("%02d", i));
                HttpRequestBase request = getRequestMetaDataById();

                try (final CloseableHttpResponse response = client.execute(request)) {
                    final int statusCode = response.getStatusLine().getStatusCode();
                    final InputStream inputStream = response.getEntity().getContent();
                    final int available = inputStream.available();
                    final byte[] b = new byte[available];
                    final int read = inputStream.read(b);
                    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,sss").format(new Date());
                    System.out.printf("[%s] [%02d] OK: httpCode=%d available=%d read=%d \n",timeStamp,i,statusCode, available,read);
                    LOGGER.info("[{}] OK: httpCode={} available={} read={}", i, statusCode, available, read);
                    final String content = new String(b);
                    LOGGER.trace("[{}] OK: Content:\n{}", i, content);
                    Thread.sleep(2000);
                }
                catch (Exception e) {
                    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,sss").format(new Date());
                    System.out.printf("[%s] [%02d] Error: %s\n", timeStamp, i, e.getMessage());
                    LOGGER.info("[{}] Error: {}", i, e.getMessage());
                    LOGGER.trace("[{}] Error: {}", i, e.getMessage(), e);
                }
                LOGGER.debug("============= Iteration [{}] End", String.format("%02d", i));
            }

        }
    }

    private static HttpRequestBase getRequestMetaDataById() throws UnsupportedEncodingException {
        final HttpPost httpPost = new HttpPost("http://" + HOST_PORT + "/data-updates/meta-data/_search?pretty=true&size=500&izik="+ UUID.randomUUID().toString());
        httpPost.setHeader(HTTP.CONTENT_TYPE, MIME_TYPE_JSON_UTF_8);
        httpPost.setEntity(new StringEntity("{\"query\":{\"wildcard\":{\"_uid\":\"*724_sr_multi_w_dugmanlog_2017_01_4263_0000008\"}}}"));
        return httpPost;
    }

    private static HttpRequestBase getRequest9200() {
        return new HttpGet("http://"+ HOST_PORT +"/");
    }
}
