package co.edu.unal.unacompaamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

import co.edu.unal.unacompaamiento.Services.EstudianteService;
import co.edu.unal.unacompaamiento.Services.ProfesorService;
import co.edu.unal.unacompaamiento.Services.RequestService;
import co.edu.unal.unacompaamiento.model.Estudiante;
import co.edu.unal.unacompaamiento.model.Profesor;
import co.edu.unal.unacompaamiento.model.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class solicitud extends AppCompatActivity {

    Button send;
    EditText message;
    public static final String BaseURL = "http://192.168.0.11:8080/";
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud);

        message = findViewById(R.id.message);
        send=findViewById(R.id.ir_solicitud);
        send.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Long idTutor = getIntent().getLongExtra("ID_tutor",0);
                 Long idEstudiante = getIntent().getLongExtra("ID_student",0);
                 final Long idTransmisor = getIntent().getLongExtra("ID_transmisor",0);
                 final Long idReceptor = getIntent().getLongExtra("ID_receptor",0);

                 Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create()).build();
                 ProfesorService profesorService = retrofit.create(ProfesorService.class);
                 EstudianteService  estudianteService = retrofit.create(EstudianteService.class);
                 final RequestService requestService = retrofit.create(RequestService.class);
                 Call<Profesor> call = profesorService.getTutorById(idTutor);
                 final Call<Estudiante> call2 = estudianteService.getStudentById(idEstudiante);
                 call.enqueue(new Callback<Profesor>() {
                     @Override
                     public void onResponse(Call<Profesor> call, Response<Profesor> response) {
                         final Profesor p = response.body();
                         System.out.println("profesor respondio");
                         call2.enqueue(new Callback<Estudiante>() {
                             @Override
                             public void onResponse(Call<Estudiante> call, Response<Estudiante> response) {
                                 Estudiante e = response.body();
                                 System.out.println("estudiante respondio");

                                 String ahora = dateFormat.format(new Date());
                                 //ahora = ahora.replace(" ","T");
                                 System.out.println("ahora: "+ahora);
                                 Request NuevoMensaje = new Request(e,p,ahora,message.getText().toString(),idTransmisor,idReceptor);
                                 System.out.println("request creado: \n"+ NuevoMensaje.getId());
                                 System.out.println(NuevoMensaje.getStudent().getId());
                                 System.out.println(NuevoMensaje.getTutor().getId());
                                 System.out.println(NuevoMensaje.getRequest_date());
                                 System.out.println(NuevoMensaje.getMessage());
                                 System.out.println(NuevoMensaje.getTransmitter());
                                 System.out.println(NuevoMensaje.getReceiver());
                                 Call<Request> call3 = requestService.PostMessage(NuevoMensaje);
                                 call3.enqueue(new Callback<Request>() {
                                     @Override
                                     public void onResponse(Call<Request> call, Response<Request> response) {

                                         System.out.println("Mensaje colocado: " + response.message());
                                         finish();
                                     }

                                     @Override
                                     public void onFailure(Call<Request> call, Throwable t) {

                                     }
                                 });


                             }

                             @Override
                             public void onFailure(Call<Estudiante> call, Throwable t) {

                             }
                         });


                     }

                     @Override
                     public void onFailure(Call<Profesor> call, Throwable t) {

                     }
                 });

             }
        });

    }
}
