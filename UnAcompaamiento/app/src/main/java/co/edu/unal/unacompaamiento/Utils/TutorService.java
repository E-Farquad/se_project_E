package co.edu.unal.unacompaamiento.Utils;
import java.util.List;
import co.edu.unal.unacompaamiento.model.Profesor;
import retrofit2.http.GET;
import retrofit2.Call;

public interface TutorService {

    @GET("/tutor/{id}")
    Call<List<Profesor>> getTutor(String id_tutor);

}
