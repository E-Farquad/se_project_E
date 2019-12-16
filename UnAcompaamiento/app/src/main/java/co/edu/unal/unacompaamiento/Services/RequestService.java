package co.edu.unal.unacompaamiento.Services;

import java.util.List;

import co.edu.unal.unacompaamiento.model.Estudiante;
import co.edu.unal.unacompaamiento.model.Request;
import retrofit2.Call;
import retrofit2.http.*;

public interface RequestService {
    @POST("api/request")
    Call<Request> PostMessage(@Body Request request);

    @GET("api/requestByReceiverID/{receiver}")
    Call<List<Request>> getRequestsByReceiverId(@Path(value = "receiver") Long tutorId);
}
