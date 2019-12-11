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

import co.edu.unal.unacompaamiento.model.Estudiante;

public class EstudianteAdapter extends ArrayAdapter<Estudiante> {
    private Context context;
    private List<Estudiante> estudiantes;

    public EstudianteAdapter(@NonNull Context context, int resource, @NonNull List<Estudiante> objects) {
        super(context, resource, objects);
        this.context = context;
        this.estudiantes = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.activity_main,parent,false);

        TextView txtID_Estudiante = (TextView)rowView.findViewById(R.id.ID_Estudiante);
        TextView txtNombre_Estudiante = (TextView)rowView.findViewById(R.id.Nombre_Estudiante);

        txtID_Estudiante.setText(String.format("ID:%s",estudiantes.get(position).getId()));
        txtNombre_Estudiante.setText(String.format("Nombre:%s",estudiantes.get(position).getName()));

        return rowView;
    }
}
