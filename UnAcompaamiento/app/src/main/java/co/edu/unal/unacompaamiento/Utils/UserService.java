package co.edu.unal.unacompaamiento.Utils;

import co.edu.unal.unacompaamiento.model.User;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.*;

public interface UserService {

    @POST("UserLogin")
    Call<User> userLogin(
        @Field("username") String username,
        @Field("contraseña") String contraseña
    );

}
