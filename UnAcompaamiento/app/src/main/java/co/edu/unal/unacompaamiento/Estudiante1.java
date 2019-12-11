package co.edu.unal.unacompaamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.unacompaamiento.Services.EstudianteService;
import co.edu.unal.unacompaamiento.model.Estudiante;
import co.edu.unal.unacompaamiento.model.Profesor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Estudiante1 extends AppCompatActivity {

    TextView studentName;
    Button ir_solicitud;
    Button ir_bandeja;
    ListView info_tutor;
    private String nombreEstudiante = "";
    private List profesorData = new ArrayList();
    private Long tutorId;


    Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create()).build();
    EstudianteService service = retrofit.create(EstudianteService.class);

    public static final String BaseURL = "http://192.168.1.120:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        ir_solicitud=findViewById(R.id.ir_solicitud);
        ir_solicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Estudiante1.this,solicitud.class));
                finish();
            }
        });
        ir_bandeja=findViewById(R.id.ir_bandeja);
        ir_bandeja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Estudiante1.this,Bandeja1.class));
                finish();
            }

        });



        String username = getIntent().getStringExtra("Usuario");
        studentName(username);
        TutorId(username);

    }

    public void studentName(String username){
        Call<List<Estudiante>> call = service.getStudentInfo(username);

        call.enqueue(new Callback<List<Estudiante>>() {
            @Override
            public void onResponse(Call<List<Estudiante>> call, Response<List<Estudiante>> response) {
                if(response.isSuccessful()){

                    for(Estudiante e : response.body()){
                        nombreEstudiante = e.getName();
                        imprimirEstudiante(nombreEstudiante);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Estudiante>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

    public void TutorId (String username){
        Call<Long> callIdProfesor = service.getTutorId(username);

        callIdProfesor.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                if(response.isSuccessful()) {
                    tutorId = response.body();
                    TutorData(tutorId);
                }
            }


            @Override
            public void onFailure(Call<Long> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

    public void TutorData(Long id){

        Call<List<Profesor>> callProfesor = service.getTutorInfo(id);
        callProfesor.enqueue(new Callback<List<Profesor>>() {
            @Override
            public void onResponse(Call<List<Profesor>> call, Response<List<Profesor>> response) {
                if(response.isSuccessful()){

                    for(Profesor p : response.body()){
                        profesorData.add(p.getName());
                        profesorData.add(p.getEmail());
                        profesorData.add(p.getOffice());
                        profesorData.add(p.getOffice_hours());
                        profesorData.add(p.getDepartment());
                        imprimirTutor(profesorData);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Profesor>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

    public void imprimirEstudiante(String student_name){
        studentName = findViewById(R.id.student_name);
        studentName.setText(student_name);
    }

    public void imprimirTutor(List tutor_data){

        info_tutor = findViewById(R.id.tutor_data);
        ArrayAdapter<String> data = new ArrayAdapter<String>(Estudiante1.this,android.R.layout.simple_list_item_1,profesorData){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position,convertView,parent);
                TextView tv = view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.WHITE);
            return view;
            }
        };
        info_tutor.setAdapter(data);
    }
}