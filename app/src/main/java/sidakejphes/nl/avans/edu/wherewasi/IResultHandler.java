package sidakejphes.nl.avans.edu.wherewasi;

import java.io.InputStream;

public interface IResultHandler {
    public void onSuccess(InputStream result);
    public void onError(Exception e);
}
