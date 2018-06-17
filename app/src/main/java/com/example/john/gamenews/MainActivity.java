package com.example.john.gamenews;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.john.gamenews.Helpers.RetrofitUser;
import com.example.john.gamenews.Interface.GameNewsAPI;
import com.example.john.gamenews.Object.LoginUsuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextInputEditText usuario, contraseña;
    Button boton;
    private GameNewsAPI servicio;
    public static LoginUsuario loginUsuario;
    private static final String TAG = "USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.usuario = findViewById(R.id.usuario);
        this.contraseña = findViewById(R.id.contraseña);
        this.boton = findViewById(R.id.boton);
        this.servicio = RetrofitUser.getRetrofitInstance().create(GameNewsAPI.class);
    }

    protected void onResume() {
        super.onResume();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                findViewById(R.id.ProgressBar).setVisibility(View.VISIBLE);
                Call<LoginUsuario> autenticar = servicio.sign(usuario.getText().toString(), contraseña.getText().toString());
                autenticar.enqueue(new Callback<LoginUsuario>() {

                    @Override
                    public void onResponse(Call<LoginUsuario> call, Response<LoginUsuario> response) {

                        loginUsuario = response.body();
                        if (response.code() != 401) {
                            Intent intent = new Intent(MainActivity.this, NavDraw.class);
                            findViewById(R.id.ProgressBar).setVisibility(View.GONE);
                            startActivity(intent);
                            finish();

                        } else {
                            findViewById(R.id.ProgressBar).setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Datos Incorrectos", Toast.LENGTH_SHORT).show();
                            EditText usuario, contraseña;
                            usuario = findViewById(R.id.usuario);
                            contraseña = findViewById(R.id.contraseña);
                            usuario.setText("");
                            contraseña.setText("");
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginUsuario> call, Throwable t) {
                        Log.d(TAG, "onFailure" + t.getMessage());
                    }
                });
            }
        });
    }
}
