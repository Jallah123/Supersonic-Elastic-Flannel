package sidakejphes.nl.avans.edu.wherewasi;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by Jelle on 11-3-2015.
 */
public class DataProvider {

    private final static String baseUrl = "thetvdb.com/api";
    private final static String API_KEY = "983E743A757CA344";

    public static void doGet(final String path, final IResultHandler handler) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URI uri = URIUtils.createURI("http", baseUrl, -1, path, null, null);
                    HttpClient client = new DefaultHttpClient();
                    HttpGet request = new HttpGet();
                    request.setURI(uri);
                    handler.onSuccess(client.execute(request).getEntity().getContent());
                } catch (Exception e) {
                    handler.onError(e);
                }
            }
        }).start();

    }

    public static void doGet(final String path, final ArrayList<NameValuePair> params, final IResultHandler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URI uri = URIUtils.createURI("http", baseUrl, -1, path, URLEncodedUtils.format(params, "UTF-8"), null);
                    HttpClient client = new DefaultHttpClient();
                    HttpGet request = new HttpGet();
                    request.setURI(uri);
                    handler.onSuccess(client.execute(request).getEntity().getContent());
                } catch (Exception e) {
                    handler.onError(e);
                }
            }
        }).start();
    }
}
