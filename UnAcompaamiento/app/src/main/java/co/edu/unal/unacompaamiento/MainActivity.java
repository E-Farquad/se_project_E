package co.edu.unal.unacompaamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import java.io.IOException;
import java.util.regex.Pattern;

import co.edu.unal.unacompaamiento.Services.LogInService;
import co.edu.unal.unacompaamiento.Utils.Cliente;
import co.edu.unal.unacompaamiento.model.User;
import co.edu.unal.unacompaamiento.model.Verifiable;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity{

    private EditText username;
    private EditText password;
    public static final String BaseURL = "http://192.168.1.120:8080/";
    private Boolean InDataBase=false;
    private Boolean IsStudent=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.et_correo);
        password = findViewById(R.id.et_contraseña);
        Button button = (Button) findViewById(R.id.ir_estudiante1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }
/*
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ir_estudiante1:
                openStudentMain();
                userLogin();

                break;

            default:

                break;
        }
    }
*/
    private void userLogin(){
        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();


        if(user.isEmpty()){
            username.setError("El usuario es requerido");
            username.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            password.setError("La contraseña es requerida");
            password.requestFocus();
            return;
        }
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create()).build();
        final Verifiable verifiable = new Verifiable(user,pass);
        LogInService postService = retrofit.create(LogInService.class);
        Call<Boolean> call = postService.VerifyIfUser(verifiable);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                System.out.println(response.body());
                InDataBase = response.body();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });

        if(InDataBase){

            call = postService.VerifyIfStudent(verifiable);
            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                    IsStudent = response.body();
                    if (IsStudent){
                        openStudentMain(verifiable.getUsername());
                    }else{
                        openTutorMain();
                    }
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    Log.e("Error", t.getMessage());
                }
            });


        }else{
            username.setError("Usuario o contraseña incorrectos");
        }

    }

    private void openStudentMain(String username) {
        Intent intent = new Intent(this, Estudiante1.class);
        intent.putExtra("Usuario", username);
        startActivity(intent);
    }

    private void openTutorMain() {
        Intent intent = new Intent(this, Tutor.class);
        startActivity(intent);
    }

}
