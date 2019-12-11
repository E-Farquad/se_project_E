package co.edu.unal.unacompaamiento.Services;

import java.util.List;
import co.edu.unal.unacompaamiento.model.Profesor;
import co.edu.unal.unacompaamiento.model.Estudiante;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EstudianteService {

    @GET("api/studentInfoByUsername/{username}")
    Call<List<Estudiante>> getStudentInfo(@Path(value = "username") String username);

    @GET("api/tutorIdByStudentUsername/{username}")
    Call<Long> getTutorId(@Path(value = "username") String username);

    @GET("api/studentTutorById/{id}")
    Call<List<Profesor>> getTutorInfo(@Path(value = "id") Long tutorId);

}
