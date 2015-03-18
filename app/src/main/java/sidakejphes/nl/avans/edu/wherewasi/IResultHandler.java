package sidakejphes.nl.avans.edu.wherewasi;

import java.io.InputStream;

/**
 * Created by Jelle on 11-3-2015.
 */
public interface IResultHandler {
    public void onSuccess(InputStream result);
    public void onError(Exception e);
}
