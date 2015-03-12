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

/**
 * Created by Jelle on 11-3-2015.
 */
public class DataProvider {

    private String baseUrl;

    public DataProvider(String url) {
        setBaseUrl(url);
    }

    public void doGet(final String path, final IResultHandler handler) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URI uri = URIUtils.createURI("http", baseUrl, -1, path, null, null);
                    HttpClient client = new DefaultHttpClient();
                    HttpGet request = new HttpGet();
                    request.setURI(uri);
                    handler.onSuccess(EntityUtils.toString(client.execute(request).getEntity()));
                } catch (Exception e) {
                    handler.onError(e);
                }
            }
        }).start();
    }

    public void doGet(final String path, final ArrayList<NameValuePair> params, final IResultHandler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URI uri = URIUtils.createURI("http", baseUrl, -1, path, URLEncodedUtils.format(params, "UTF-8"), null);
                    HttpClient client = new DefaultHttpClient();
                    HttpGet request = new HttpGet();
                    request.setURI(uri);
                    handler.onSuccess(EntityUtils.toString(client.execute(request).getEntity()));
                } catch (Exception e) {
                    handler.onError(e);
                }
            }
        }).start();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
