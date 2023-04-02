package com.equisde.tresraya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    public static final String DIFICULTAD = "com.equisde.tresraya.DIFICULTAD";

    private int dificultad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button jugarButton = findViewById(R.id.jugarbutton);
        jugarButton.setOnClickListener(oyenteJugarButton);
    }

    private final View.OnClickListener oyenteJugarButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioGroup dificultadesRadioGroup = findViewById(R.id.dificultadesRadioGroup);
            switch (dificultad = dificultadesRadioGroup.getCheckedRadioButtonId()){
                case R.id.para2RadioButton:
                    dificultad = 0;
                    break;
                case R.id.facilRadioButton:
                    dificultad = 1;
                    break;
                case R.id.normalRadioButton:
                    dificultad = 2;
                    break;
            }

            Intent i = new Intent(MainActivity.this,tresEnRayaActivity.class);
            i.putExtra(DIFICULTAD, dificultad);
            startActivity(i);

        }//OnClick()
    };
}