package co.edu.unal.unacompaamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.unacompaamiento.Utils.EstudianteService;
import co.edu.unal.unacompaamiento.model.Estudiante;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tutor extends AppCompatActivity {

    EstudianteService estudianteService;
    TextView id_tutor;
    String ID_TUTOR;
    List<Estudiante> listaEstudiantes = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);

        listView = (ListView) findViewById(R.id.listaTutor);
        id_tutor = (TextView) findViewById(R.id.id_tutor);

        ID_TUTOR = id_tutor.getText().toString().trim();

        listarEstudiantesDeTutor(ID_TUTOR);
    }

    public void listarEstudiantesDeTutor(String id_tutor){
        Call<List<Estudiante>> call = estudianteService.getEstudiantes(id_tutor);

        call.enqueue(new Callback<List<Estudiante>>() {
            @Override
            public void onResponse(Call<List<Estudiante>> call, Response<List<Estudiante>> response) {
                listaEstudiantes = response.body();
                listView.setAdapter(new EstudianteAdapter(Tutor.this, R.layout.activity_tutor, listaEstudiantes));
            }

            @Override
            public void onFailure(Call<List<Estudiante>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }
}
