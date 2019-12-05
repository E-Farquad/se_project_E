package co.edu.unal.unacompaamiento;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import co.edu.unal.unacompaamiento.model.Profesor;

public class TutorAdapter extends ArrayAdapter<Profesor> {

    private Context context;
    private List<Profesor> Tutor;

    public TutorAdapter(@NonNull Context context, int resource, @NonNull List<Profesor> objects) {
        super(context, resource, objects);
        this.context = context;
        this.Tutor = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.activity_student,parent,false);

        TextView txtID_Tutor = (TextView)rowView.findViewById(R.id.IDTutor);
        TextView txtNombre_Tutor = (TextView)rowView.findViewById(R.id.NombreTutor);
        TextView txtHorario_Tutor = (TextView)rowView.findViewById(R.id.HorarioTutor);
        TextView txtOficina_Tutor = (TextView)rowView.findViewById(R.id.OficinaTutor);

        txtID_Tutor.setText(String.format("ID tutor:%s",Tutor.get(position).getId()));
        txtNombre_Tutor.setText(String.format("Nombre tutor:%s",Tutor.get(position).getName()));
        txtHorario_Tutor.setText(String.format("Horario tutor:%s",Tutor.get(position).getHorario()));
        txtOficina_Tutor.setText(String.format("Oficina tutor:%s",Tutor.get(position).getOficina()));


        return rowView;
    }
}
