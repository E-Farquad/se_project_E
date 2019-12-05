package co.edu.unal.unacompaamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.unacompaamiento.Utils.TutorService;
import co.edu.unal.unacompaamiento.model.Profesor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Estudiante1 extends AppCompatActivity {

    Button ir_solicitud;
    Button ir_bandeja;
    ListView info_tutor;
    String ID_TUTOR;
    TextView idTutor;
    TutorService tutorService;
    List<Profesor> tutor = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        idTutor = (TextView) findViewById(R.id.IDTutor);
        ID_TUTOR= idTutor.getText().toString().trim();

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
        // adaptador de el arreglo de datos con la vista
        info_tutor= (ListView) findViewById(R.id.tutor_data);
        ArrayAdapter<CharSequence> adaptador=ArrayAdapter.createFromResource(this,R.array.arraychat,android.R.layout.simple_list_item_1);
        info_tutor.setAdapter(adaptador);

        tutorParaEstudiante(ID_TUTOR);

    }

    private void tutorParaEstudiante(String id_tutor) {

        Call<List<Profesor>> call = tutorService.getTutor(id_tutor);

        call.enqueue(new Callback<List<Profesor>>() {
            @Override
            public void onResponse(Call<List<Profesor>> call, Response<List<Profesor>> response) {
                tutor = response.body();
                listView.setAdapter(new TutorAdapter(Estudiante1.this, R.layout.activity_student, tutor));
            }

            @Override
            public void onFailure(Call<List<Profesor>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }
}