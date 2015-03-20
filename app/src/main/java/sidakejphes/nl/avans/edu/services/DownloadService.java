package sidakejphes.nl.avans.edu.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    private static final String baseurl = "http://thetvdb.com/api/";
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
        urlpath = baseurl + API_KEY + urlpath;
        String filename = intent.getStringExtra(FILENAME);
        File output = new File(Environment.getExternalStorageDirectory(), filename);
        if(output.exists()) {
            output.delete();
        }

        InputStream stream = null;
        FileOutputStream fos = null;

        try {
            URL url = new URL(urlpath);
            stream = url.openConnection().getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            stream = url.openConnection().getInputStream();
            fos = new FileOutputStream(output.getPath());
            int next = -1;
            while((next = reader.read()) != -1) {
                fos.write(next);
            }
            result = Activity.RESULT_OK;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(stream != null) {
                try {
                    stream.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos != null) {
                try {
                    fos.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
        publishResults(output.getAbsolutePath(), result);
    }

    private void publishResults(String outputPath, int result) {
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(FILEPATH, outputPath);
        intent.putExtra(RESULT, result);
        sendBroadcast(intent);
    }
}
