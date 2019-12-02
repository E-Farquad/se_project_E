package co.edu.unal.unacompaamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class solicitud extends AppCompatActivity {

    Button send;
    Button tutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud);

     tutor=findViewById(R.id.ir_tutor);
     tutor.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(solicitud.this,Tutor.class));
             finish();
         }
     });

    }
}
