package com.example.usointent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;
import java.util.HashMap;
import java.util.Map;


public class SegundaActivity extends AppCompatActivity {

    protected static final int TIMER_RUNTIME = 10000;
    protected boolean nbActivo;
    protected ProgressBar nProgressBar;
    DatabaseReference mRootReference;
    Button mButtonSubirDatosFirebase;
    EditText mEditTextDatoNombreyApellidoUsuario, mEditTextDatoDNIUsuario, mEditTextTelefonoUsuario, mEditTextCorreoUsuario, mEditTextContrasenaUsuario;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.segundaactividad);


        mButtonSubirDatosFirebase = findViewById(R.id.btnSubirDatos);
        mEditTextDatoNombreyApellidoUsuario = findViewById(R.id.etNombreyApellidos);
        mEditTextDatoDNIUsuario = findViewById(R.id.etDNI);
        mEditTextTelefonoUsuario = findViewById(R.id.etTelefono);
        mEditTextCorreoUsuario = findViewById(R.id.etCorreo);
        mEditTextContrasenaUsuario = findViewById(R.id.etContraseña);

        mRootReference = FirebaseDatabase.getInstance().getReference();

        mButtonSubirDatosFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NombreyApellido = mEditTextDatoNombreyApellidoUsuario.getText().toString();
                String DNI = mEditTextDatoDNIUsuario.getText().toString();
                int Telefono = Integer.parseInt(mEditTextTelefonoUsuario.getText().toString());
                String Correo = mEditTextCorreoUsuario.getText().toString();
                String Contrasena = mEditTextContrasenaUsuario.getText().toString();

                Map<String, Object> datosUsuario = new HashMap<>();
                datosUsuario.put("Nombre y Apellido", NombreyApellido);
                datosUsuario.put("DNI", DNI);
                datosUsuario.put("Telefono", Telefono);
                datosUsuario.put("Correo", Correo);
                datosUsuario.put("Contraseña", Contrasena);


                mRootReference.child("Usuario").push().setValue(datosUsuario);
                }
        });

        nProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        final Thread timerThread = new Thread() {
            @Override
            public void run() {
                nbActivo = true;
                try {
                    int espera = 0;
                    while (nbActivo && (espera < TIMER_RUNTIME)) {
                        sleep(200);
                        if (nbActivo) {
                            espera += 200;
                            actualizarProgress(espera);
                        }
                    }
                } catch (InterruptedException e) {
                } finally {
                    onContinuar();
                }
            }
        };
        timerThread.start();
    }

    public void actualizarProgress(final int timePassed){
        if(null != nProgressBar){
            final int progress = nProgressBar.getMax() * timePassed
                    /TIMER_RUNTIME;
            nProgressBar.setProgress(progress);
        }
    }
    public void onContinuar(){
        Log.d("mensajeFinal", "Su barra de progreso acaba de cargar");
    }

        public void onClickAtras(View view) {
        Intent Atras = new Intent(this,MainActivity.class);
        startActivity(Atras);
         }
}
