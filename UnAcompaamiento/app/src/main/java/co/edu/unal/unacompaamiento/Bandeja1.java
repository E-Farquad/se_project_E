package co.edu.unal.unacompaamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.unacompaamiento.Services.ProfesorService;
import co.edu.unal.unacompaamiento.Services.RequestService;
import co.edu.unal.unacompaamiento.model.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Bandeja1 extends AppCompatActivity {

    ListView chatlist;

    public static final String BaseURL = "http://192.168.0.11:8080/";
    Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create()).build();
    RequestService requestService = retrofit.create(RequestService.class);


    private List mensajesInfo = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bandeja1);

        chatlist= findViewById(R.id.list_mens);
        Long id_receiver = getIntent().getLongExtra("ID_receptor",0);
        Call<List<Request>> call = requestService.getRequestsByReceiverId(id_receiver);
        call.enqueue(new Callback<List<Request>>() {
            @Override
            public void onResponse(Call<List<Request>> call, Response<List<Request>> response) {
                for(Request r : response.body()){
                    mensajesInfo.add(r.getRequest_date()
                                    + "\nTutor: " + r.getTutor().getUsername() + "  Estudiante: " + r.getStudent().getUsername()
                                    + "\n" + r.getMessage());

                }

                imprimirRequests(mensajesInfo);
            }

            @Override
            public void onFailure(Call<List<Request>> call, Throwable t) {

            }
        });







        /*
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
        */
    }

    void imprimirRequests(final List mensajesInfo){

        final ArrayAdapter<String> list_data = new ArrayAdapter<String>(Bandeja1.this, android.R.layout.simple_list_item_2, android.R.id.text1, mensajesInfo){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position,convertView,parent);
                TextView tv1 = view.findViewById(android.R.id.text1);
                tv1.setTextColor(Color.WHITE);

                return view;
            }
        };
        chatlist.setAdapter(list_data);



    }
}
