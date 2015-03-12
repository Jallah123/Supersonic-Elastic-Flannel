package sidakejphes.nl.avans.edu.wherewasi;

/**
 * Created by Jelle on 11-3-2015.
 */
public interface IResultHandler {
    public void onSuccess(String result);
    public void onError(Exception e);
}
