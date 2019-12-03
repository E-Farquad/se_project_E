package co.edu.unal.unacompaamiento.Utils;

import java.util.List;

import co.edu.unal.unacompaamiento.model.Estudiante;
import retrofit2.Call;
import retrofit2.http.GET;

public interface EstudianteService {

    @GET("listar/")
    Call<List<Estudiante>> getEstudiantes();

}
