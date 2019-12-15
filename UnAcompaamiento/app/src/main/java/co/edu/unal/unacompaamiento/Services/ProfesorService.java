package co.edu.unal.unacompaamiento.Services;

import java.util.List;
import co.edu.unal.unacompaamiento.model.Profesor;
import co.edu.unal.unacompaamiento.model.Estudiante;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProfesorService {

    @GET("api/tutorInfoByUsername/{username}")
    Call<List<Profesor>> getTutorInfo(@Path(value = "username") String username);

    @GET("api/tutorStudents/{tutor_id}")
    Call<List<Estudiante>> getStudentsInfo(@Path(value = "tutor_id") Long tutorId);

}
