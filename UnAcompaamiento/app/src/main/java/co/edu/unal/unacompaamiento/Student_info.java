package co.edu.unal.unacompaamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Student_info extends AppCompatActivity {

    ListView infostd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        infostd=(ListView) findViewById(R.id.info_std);
        ArrayList<String> data_student=new ArrayList<>();
        data_student.add("Nombre: ");
        data_student.add("Carrera: ");
        data_student.add("Promedio: ");
        data_student.add("Semestre: ");
        data_student.add("Facultad: ");
        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,data_student);
        infostd.setAdapter(adaptador);
        infostd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }
}
