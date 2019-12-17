package co.edu.unal.unacompaamiento.Services;

import co.edu.unal.unacompaamiento.model.Verifiable;
import retrofit2.Call;
import retrofit2.http.*;

public interface LogInService {

    @POST("api/VerifyUser")
    Call<Boolean> VerifyIfUser(@Body Verifiable possibleUser);

    @POST("api/VerifyStudent")
    Call<Boolean> VerifyIfStudent(@Body Verifiable possibleUser);

    @POST("api/VerifyTutor")
    Call<Boolean> VerifyIfTutor(@Body Verifiable possibleUser);

}
