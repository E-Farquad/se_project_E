package co.edu.unal.unacompaamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Estudiante1 extends AppCompatActivity {

    Button ir_solicitud;
    Button ir_bandeja;
    ListView info_tutor;

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
        // adaptador de el arreglo de datos con la vista
        info_tutor= (ListView) findViewById(R.id.tutor_data);
        ArrayAdapter<CharSequence> adaptador=ArrayAdapter.createFromResource(this,R.array.arraychat,android.R.layout.simple_list_item_1);
        info_tutor.setAdapter(adaptador);

    }}