package com.equisde.tresraya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class tresEnRayaActivity extends AppCompatActivity {



    private int dificultad;
    private String elSiguiente = "o";
    public static String GANADOR = "com.equisde.com.equisde.tresraya.GANADOR";

    private String ultimojugador = "";
    private TextView[] tablero = new TextView[9];
    private int [][] jugadasqGanan = {{ 0 , 1 , 2 },
                                      { 3 , 4 , 5 },
                                      { 6 , 7 , 8 },
                                      { 0 , 3 , 6 },
                                      { 1 , 4 , 7 },
                                      { 2 , 5 , 8 },
                                      { 0 , 4 , 8 },
                                      { 2 , 4 , 6 }};


    private String valorCasilla = "x";
    private boolean estado = true;
    private TextView casillaTextView1;
    private TextView casillaTextView2;
    private TextView casillaTextView3;
    private TextView casillaTextView4;
    private TextView casillaTextView5;
    private TextView casillaTextView6;
    private TextView casillaTextView7;
    private TextView casillaTextView8;
    private TextView turnoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tres_en_raya);

        iniciarJuego();

        Intent intent =getIntent();
        dificultad = intent.getIntExtra(MainActivity.DIFICULTAD ,0);
        turnoTextView = findViewById(R.id.turnoTextView);

        Button menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(oyenteMenuButoon);
        Button reiniciarButton = findViewById(R.id.reiniciarButton);
        reiniciarButton.setOnClickListener(oyenteReiniciarButton);
        reiniciarJuego();
    }

    public void iniciarJuego() {
        TextView casillaTextView0 = findViewById(R.id.casillaTextView0);
        casillaTextView0.setOnClickListener(oyenteCasilla0);
        tablero[0] = casillaTextView0;

        casillaTextView1 = findViewById(R.id.casillaTextView1);
        casillaTextView1.setOnClickListener(oyenteCasilla1);
        tablero[1] = casillaTextView1;

        casillaTextView2 = findViewById(R.id.casillaTextView2);
        casillaTextView2.setOnClickListener(oyenteCasilla2);
        tablero[2] = casillaTextView2;

        casillaTextView3 = findViewById(R.id.casillaTextView3);
        casillaTextView3.setOnClickListener(oyenteCasilla3);
        tablero[3] = casillaTextView3;

        casillaTextView4 = findViewById(R.id.casillaTextView4);
        casillaTextView4.setOnClickListener(oyenteCasilla4);
        tablero[4] = casillaTextView4;

        casillaTextView5 = findViewById(R.id.casillaTextView5);
        casillaTextView5.setOnClickListener(oyenteCasilla5);
        tablero[5] = casillaTextView5;

        casillaTextView6 = findViewById(R.id.casillaTextView6);
        casillaTextView6.setOnClickListener(oyenteCasilla6);
        tablero[6] = casillaTextView6;

        casillaTextView7 = findViewById(R.id.casillaTextView7);
        casillaTextView7.setOnClickListener(oyenteCasilla7);
        tablero[7] = casillaTextView7;

        casillaTextView8 = findViewById(R.id.casillaTextView8);
        casillaTextView8.setOnClickListener(oyenteCasilla8);
        tablero[8] = casillaTextView8;
    }
    private final View.OnClickListener oyenteCasilla0 = view -> tocaCasilla(0);
    private final View.OnClickListener oyenteCasilla1 = view -> tocaCasilla(1);
    private final View.OnClickListener oyenteCasilla2 = view -> tocaCasilla(2);
    private final View.OnClickListener oyenteCasilla3 = view -> tocaCasilla(3);
    private final View.OnClickListener oyenteCasilla4 = view -> tocaCasilla(4);
    private final View.OnClickListener oyenteCasilla5 = view -> tocaCasilla(5);
    private final View.OnClickListener oyenteCasilla6 = view -> tocaCasilla(6);
    private final View.OnClickListener oyenteCasilla7 = view -> tocaCasilla(7);
    private final View.OnClickListener oyenteCasilla8 = view -> tocaCasilla(8);

    private  void reiniciarJuego(){
        turnoTextView.setText("x");
        //estado = true;
        for (TextView textView : tablero)
            textView.setText("");

        valorCasilla = elSiguiente;
        if(elSiguiente.equals("o"))
            elSiguiente = "x";
        else
            elSiguiente = "o";
    }// reiniciarJuego()

    private final View.OnClickListener oyenteMenuButoon = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(tresEnRayaActivity.this, MainActivity.class);
            startActivity(i);
            finish();  //Terminar actividad actual

        }
    };// oyenteMenuButton()

    private final  View.OnClickListener oyenteReiniciarButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            iniciarJuego();
            reiniciarJuego();
        }//OnClick()
    };

    private void tocaCasilla(int casilla) {
        if(tablero[casilla].getText().equals("") && estado){
            tablero[casilla].setText(valorCasilla);
            cambiarJugador();
            esJuegoTerminado();
        }
    }
    private void cambiarJugador() {
        if (valorCasilla.equals("x")){
            turnoTextView.setText("o");
            valorCasilla = "o";
        }
        else{
            turnoTextView.setText("x");
            valorCasilla = "x";
        }

    }
    private void esJuegoTerminado() {

        for (int f = 0; f < jugadasqGanan.length; f++) {
            for (int c = 0; c < jugadasqGanan[0].length; c++) {
                if (tablero[jugadasqGanan[f][0]].getText().equals("x") && tablero[jugadasqGanan[f][1]].getText().equals("x") && tablero[jugadasqGanan[f][2]].getText().equals("x")) {
                    ultimojugador = "x";
                    tablero[jugadasqGanan[f][c]].setText("x");

                    tablero[jugadasqGanan[f][0]].setBackgroundColor(ContextCompat.getColor(this, R.color.verde_ganador));
                    tablero[jugadasqGanan[f][1]].setBackgroundColor(ContextCompat.getColor(this, R.color.verde_ganador));
                    tablero[jugadasqGanan[f][2]].setBackgroundColor(ContextCompat.getColor(this, R.color.verde_ganador));

                    estado = false;
                    Handler manejador = new Handler();
                    Runnable hilo = new Runnable() {
                        @Override
                        public void run() {
                            termina(1);
                        }
                    };
                    manejador.postDelayed(hilo, 2000);

                } else if (tablero[jugadasqGanan[f][0]].getText().equals("o") && tablero[jugadasqGanan[f][1]].getText().equals("o") && tablero[jugadasqGanan[f][2]].getText().equals("o")) {
                    ultimojugador = "o";

                    tablero[jugadasqGanan[f][0]].setBackgroundColor(ContextCompat.getColor(this, R.color.verde_ganador));
                    tablero[jugadasqGanan[f][1]].setBackgroundColor(ContextCompat.getColor(this, R.color.verde_ganador));
                    tablero[jugadasqGanan[f][2]].setBackgroundColor(ContextCompat.getColor(this, R.color.verde_ganador));

                    estado = false;
                    tablero[jugadasqGanan[f][c]].setText("o");
                    Handler manejador = new Handler();
                    Runnable hilo = new Runnable() {
                        @Override
                        public void run() {
                            termina(0);
                        }
                    };
                    manejador.postDelayed(hilo, 2000);
                }
            }
        }
    }

    private void termina( int valor) {
        Intent i = new Intent(tresEnRayaActivity.this, Resultado.class);
        i.putExtra(GANADOR, valor);
        startActivity(i);
        finish();
    }
}