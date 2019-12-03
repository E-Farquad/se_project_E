package co.edu.unal.unacompaamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

import co.edu.unal.unacompaamiento.Utils.Cliente;
import co.edu.unal.unacompaamiento.model.User;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.et_correo);
        password = findViewById(R.id.et_contraseña);

        findViewById(R.id.ir_estudiante1);
    }

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ir_estudiante1:

                userLogin();

            break;

            default:

            break;
        }
    }
}
