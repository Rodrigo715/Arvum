package com.example.hackunam.arvum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AgregarPlanta extends AppCompatActivity {

    EditText comoLlamas, especie;
    Button btn_agregarPlanta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_planta);

        Spinner spinner= (Spinner) findViewById(R.id.spinner_tipo);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array,android.R.layout.simple_spinner_item );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);


        comoLlamas= (EditText) findViewById(R.id.comoLlamas);
        especie= (EditText) findViewById(R.id.especie);
        btn_agregarPlanta= (Button) findViewById(R.id.btn_agregarPlanta);



        btn_agregarPlanta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent=new Intent();
                resultIntent.putExtra("comoLlamas", comoLlamas.getText().toString());
                resultIntent.putExtra("especie", especie.getText().toString());
                setResult(MainActivity.RESULT_OK, resultIntent);
                finish();
            }
        });









    }
}
