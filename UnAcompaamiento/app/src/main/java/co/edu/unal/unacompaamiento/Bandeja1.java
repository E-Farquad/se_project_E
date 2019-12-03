package co.edu.unal.unacompaamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Bandeja1 extends AppCompatActivity {

    ListView chatlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bandeja1);

        chatlist= (ListView) findViewById(R.id.list_mens);

        // adaptador de el arreglo de datos con la vista
        ArrayAdapter<CharSequence> adaptador=ArrayAdapter.createFromResource(this,R.array.arraychat,android.R.layout.simple_list_item_1);
        chatlist.setAdapter(adaptador);

        chatlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //metodo para mostrar la info de los estudiantes
                Toast.makeText(parent.getContext(),"estudiante: "+parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
