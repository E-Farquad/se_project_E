package co.edu.unal.unacompaamiento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Tutor extends AppCompatActivity {

    Button inboxt;
    ListView infostd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);
        inboxt=findViewById(R.id.inboxt);
        inboxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tutor.this,Bandeja1.class));
                finish();

            }
        });

        infostd=(ListView) findViewById(R.id.list_std);

        ArrayList<String> listaEstudiantes=new ArrayList<>();
        listaEstudiantes.add("juan Jose 1");
        listaEstudiantes.add("juan Alberto 2");
        listaEstudiantes.add("juan Benito 3");
        listaEstudiantes.add("juan Carlos 4");
        listaEstudiantes.add("juan Esteban 5");
        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaEstudiantes);

        infostd.setAdapter(adaptador);
        infostd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(Tutor.this,Student_info.class));
                finish();
                /////

                //metodo para mostrar la info de los estudiantes
                Toast.makeText(parent.getContext(),"Estudiante: "+parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();


            }
        });


    }
}
