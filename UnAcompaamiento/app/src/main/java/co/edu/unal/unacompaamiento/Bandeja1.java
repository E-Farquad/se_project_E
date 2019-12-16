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

    public static final String BaseURL = "http://192.168.0.12:8080/";
    Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseURL).addConverterFactory(GsonConverterFactory.create()).build();
    RequestService requestService = retrofit.create(RequestService.class);


    private List mensajesInfo = new ArrayList();
    private List mensajesInfoDetallada = new ArrayList();

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
                Long recibe = 0L, envia = 0L;
                for(Request r : response.body()){
                    mensajesInfo.add(r.getRequest_date()+ "\n"
                                    + "Tutor: " + r.getTutor().getUsername() + "  Estudiante: " + r.getStudent().getUsername()
                                    + "\n" + r.getMessage());

                    mensajesInfoDetallada.add(r.getId());
                    mensajesInfoDetallada.add(r.getRequest_date());
                    mensajesInfoDetallada.add("Tutor: " + r.getTutor().getUsername() + "  Estudiante: " + r.getStudent().getUsername());
                    mensajesInfoDetallada.add(r.getMessage());
                    if(r.getReceiver() == r.getStudent().getId() || r.getReceiver() == r.getTutor().getId()){
                        recibe = r.getTransmitter();
                        envia = r.getReceiver();
                    }

                    mensajesInfoDetallada.add(r.getReceiver());
                    mensajesInfoDetallada.add(r.getTransmitter());


                }

                imprimirRequests(mensajesInfo, recibe, envia);
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

    void imprimirRequests(final List mensajesInfo, Long receptor, final Long emisor){
        System.out.println("Datos recogidos: "+receptor +", " + emisor);
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




        chatlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mensajeSeleccionado = (String) parent.getItemAtPosition(position);
                String[] datos = mensajeSeleccionado.split("\n");

                Long id_estudiante = 0L, id_tutor = 0L, receptor = 0L, transmisor = 0L;

                for(int i = 0; i < mensajesInfo.size(); i++){
                    if(mensajesInfo.get(i).equals(datos[1])){

                        id_estudiante = receptor;
                        id_tutor = emisor;
                        receptor = id_estudiante;
                        transmisor = id_tutor;
                    }
                }

                Intent intento = new Intent(Bandeja1.this, solicitud.class);
                intento.putExtra("ID_tutor", id_tutor);
                intento.putExtra("ID_student", id_estudiante);
                intento.putExtra("ID_transmisor", transmisor);
                intento.putExtra("ID_receptor", receptor);
                System.out.println("Datos de envío");
                System.out.println(id_tutor);
                System.out.println(id_estudiante);
                System.out.println(transmisor);
                System.out.println(receptor);
                startActivity(intento);



            }
        });

    }
}
