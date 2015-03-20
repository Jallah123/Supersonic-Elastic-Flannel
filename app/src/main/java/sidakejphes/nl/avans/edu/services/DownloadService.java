package sidakejphes.nl.avans.edu.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Entity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DownloadService extends IntentService {
    private static int result = Activity.RESULT_CANCELED;
    private static final String baseurl = "thetvdb.com/api/";
    private static final String API_KEY = "983E743A757CA344";
    public static final String URL = "url";
    public static final String FILEPATH = "filepath";
    public static final String FILENAME = "filename";
    public static final String RESULT = "result";
    public static final String NOTIFICATION = "sidakejphes.nl.avans.edu";

    public DownloadService(){
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent){
        String urlpath = intent.getStringExtra(URL);
        int code = Activity.RESULT_CANCELED;
        InputStream result = null;
        String path = "";
        File file = new File(Environment.getExternalStorageDirectory(), "serie.xml");
        try {
            URI uri = URIUtils.createURI("http", baseurl + API_KEY, -1, urlpath, null, null);
            String filename = intent.getStringExtra(FILENAME);
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(uri);
            HttpResponse resp =  client.execute(request);
            if(resp.getStatusLine().getStatusCode() == 200){
                code = Activity.RESULT_OK;
            }

            result = resp.getEntity().getContent();
            FileOutputStream fos = new FileOutputStream(file);
            int inByte;
            while((inByte = result.read()) != -1) {
                fos.write(inByte);
            }
            result.close();
            fos.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        publishResults(path, code);
    }

    private void publishResults(String outputPath, int code) {
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(FILEPATH, outputPath);
        intent.putExtra(RESULT, code);
        sendBroadcast(intent);
    }
}
