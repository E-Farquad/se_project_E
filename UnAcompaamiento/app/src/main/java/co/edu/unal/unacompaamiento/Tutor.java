package co.edu.unal.unacompaamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import co.edu.unal.unacompaamiento.Services.ProfesorService;
import co.edu.unal.unacompaamiento.model.Estudiante;
import co.edu.unal.unacompaamiento.model.Profesor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Tutor extends AppCompatActivity {

    TextView tutorName;

    Button inbox;
    ListView students;
    private String nombreTutor = "";
    private List estudiantes = new ArrayList();
    private List estudiantesInfoBasica = new ArrayList();

    public static final String BaseURL = "http://192.168.0.11:8080/";
    Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create()).build();
    ProfesorService service = retrofit.create(ProfesorService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);


        inbox=findViewById(R.id.inbox);
        inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Tutor.this,Bandeja1.class));
            }

        });

        String username = getIntent().getStringExtra("Usuario");
        tutorName(username);

    }

    public void tutorName(String username){
        Call<List<Profesor>> call = service.getTutorInfo(username);

        call.enqueue(new Callback<List<Profesor>>() {
            @Override
            public void onResponse(Call<List<Profesor>> call, Response<List<Profesor>> response) {
                if(response.isSuccessful()){
                    for(Profesor p : response.body()){
                        nombreTutor = p.getName();
                        imprimirNombreProfesor(nombreTutor);

                        final Long tutor_id = p.getId();
                        StudentsTutor(tutor_id);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Profesor>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

    public void StudentsTutor(Long tutorId){
        Call<List<Estudiante>> call = service.getStudentsInfo(tutorId);

        call.enqueue(new Callback<List<Estudiante>>() {
            @Override
            public void onResponse(Call<List<Estudiante>> call, Response<List<Estudiante>> response) {
                if(response.isSuccessful()){
                    for(Estudiante e : response.body()){
                        //lista para mostrar info básica de los estudiantes en la ListView del tutor
                        estudiantesInfoBasica.add(e.getName() + " - " + e.getCareer());


                        //Lista para mostar info detallada del estudiante
                        estudiantes.add(e.getName());
                        estudiantes.add(e.getEmail());
                        estudiantes.add(e.getCareer());
                        estudiantes.add("Avance: "+e.getProgress());
                        estudiantes.add("PA - "+e.getPa());
                        estudiantes.add("PAPA - "+e.getPapa());
                        estudiantes.add("PAPPI - "+e.getPappi());
                    }
                    System.out.println(estudiantesInfoBasica);
                    System.out.println("Estudiantes completo: ");
                    System.out.println(estudiantes);
                    imprimirEstudiantes(estudiantes, estudiantesInfoBasica);
                }
            }

            @Override
            public void onFailure(Call<List<Estudiante>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

    public void imprimirNombreProfesor(String tutor_name){
        tutorName = findViewById(R.id.tutor_name);
        tutorName.setText(tutor_name);
    }

    public void imprimirEstudiantes(final List infoDetalladaEstudiante, final List infoBasicaEstudiantes){
        students = findViewById(R.id.listaTutor);
        final ArrayAdapter<String> list_data = new ArrayAdapter<String>(Tutor.this, android.R.layout.simple_list_item_2, android.R.id.text1, infoBasicaEstudiantes){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position,convertView,parent);
                TextView tv1 = view.findViewById(android.R.id.text1);
                tv1.setTextColor(Color.WHITE);
                //String datos = (String) estudiantesInfoBasica.get(position);
                //String[] estudiante = datos.split(" - ");
                //tv1.setText(estudiante[0] + "\n" +estudiante[1]);

                return view;
            }
        };
        students.setAdapter(list_data);

        students.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String estudianteSeleccionado = (String) parent.getItemAtPosition(position);
                String[] datos = estudianteSeleccionado.split(" - ");


                Intent intent = new Intent(Tutor.this, Student_info.class);
                intent.putExtra("Nombre",datos[0]);

                String correo = "", carrera = "", avance = "", pa = "", papa = "", pappi = "";


                for(int i = 0; i < infoDetalladaEstudiante.size(); i++){
                    if(infoDetalladaEstudiante.get(i).equals(datos[0])){
                        correo = (String)infoDetalladaEstudiante.get(i+1);
                        carrera = (String)infoDetalladaEstudiante.get(i+2);
                        avance = (String)infoDetalladaEstudiante.get(i+3);
                        pa = (String)infoDetalladaEstudiante.get(i+4);
                        papa = (String)infoDetalladaEstudiante.get(i+5);
                        pappi = (String)infoDetalladaEstudiante.get(i+6);
                        break;
                    }
                }

                intent.putExtra("Correo",correo);
                intent.putExtra("Carrera",carrera);
                intent.putExtra("Avance", avance);
                intent.putExtra("PA",pa);
                intent.putExtra("PAPA",papa);
                intent.putExtra("PAPPI",pappi);

                startActivity(intent);
            }
        });
    }

}
