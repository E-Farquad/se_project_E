package co.edu.unal.unacompaamiento.Services;

import co.edu.unal.unacompaamiento.model.Request;
import retrofit2.Call;
import retrofit2.http.*;

public interface RequestService {
    @POST("api/request")
    Call<Request> PostMessage(@Body Request request);
}
