package co.edu.unal.unacompaamiento.Utils;

import co.edu.unal.unacompaamiento.model.User;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET("listar/")
    Call<List<User>> getUser();
}
