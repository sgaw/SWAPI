package sgaw.playground.com.swapiapp.data;

import android.content.Context;
import android.graphics.Movie;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by shirleygaw on 13/12/15.
 */
public class SWAPIApi {
    private static final String BASE_URL = "http://swapi.co";

    public interface SWAPIService {
        @GET("/api/people/")
        Call<Universe.SwapiResponse> listCharacters(@Query("page") int page);
    }

    private static SWAPIApi sSingleton = null;
    private final SWAPIService mService;

    private SWAPIApi(SWAPIService service) {
        mService = service;
    }

    public static SWAPIApi get() {
        if (sSingleton == null) {
            sSingleton = new SWAPIApi(getService());
        }
        return sSingleton;
    }

    public void getMovieCharacters(final Callback<Universe.SwapiResponse> movieCharactersCallback) throws IOException {
        Call<Universe.SwapiResponse> foo = mService.listCharacters(2);
        foo.enqueue(movieCharactersCallback);
    }

    private static SWAPIService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit.create(SWAPIService.class);
    }
}
