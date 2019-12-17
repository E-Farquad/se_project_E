package co.edu.unal.unacompaamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Student_info extends AppCompatActivity {

    ListView infostd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        String nombre = getIntent().getStringExtra("Nombre");
        String correo = getIntent().getStringExtra("Correo");
        String carrera = getIntent().getStringExtra("Carrera");
        String avance = getIntent().getStringExtra("Avance");
        String pa = getIntent().getStringExtra("PA");
        String papa = getIntent().getStringExtra("PAPA");
        String pappi = getIntent().getStringExtra("PAPPI");


        infostd=(ListView) findViewById(R.id.info_std);
        ArrayList<String> data_student=new ArrayList<>();
        data_student.add(nombre);
        data_student.add(correo);
        data_student.add(carrera);
        data_student.add(avance);
        data_student.add(pa);
        data_student.add(papa);
        data_student.add(pappi);

        ArrayAdapter<String> datosEstudiante = new ArrayAdapter<String>(Student_info.this, android.R.layout.simple_list_item_1, data_student){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position,convertView,parent);
                TextView tv1 = view.findViewById(android.R.id.text1);
                tv1.setTextColor(Color.WHITE);

                return view;
            }
        };
        infostd.setAdapter(datosEstudiante);
    }
}
