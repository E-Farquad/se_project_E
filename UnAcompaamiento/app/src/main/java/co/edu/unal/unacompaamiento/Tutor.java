package co.edu.unal.unacompaamiento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.unacompaamiento.Services.EstudianteService;
import co.edu.unal.unacompaamiento.model.Estudiante;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tutor extends AppCompatActivity {
/*
    EstudianteService estudianteService;
    List<Estudiante> listaEstudiantes = new ArrayList<>();
    ListView listView;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);

        //listView = (ListView) findViewById(R.id.listaTutor);
        //listarEstudiantes();
    }
    /*
    public void listarEstudiantes(){
        Call<List<Estudiante>> call = estudianteService.getEstudiantes();

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
    */
}
