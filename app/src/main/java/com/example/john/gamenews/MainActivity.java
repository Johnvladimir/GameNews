package com.example.john.gamenews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.john.gamenews.Helpers.RetrofitUser;
import com.example.john.gamenews.Interface.GameNewsAPI;
import com.example.john.gamenews.Object.LoginUsuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText usuario, contraseña;
    Button boton;
    private GameNewsAPI servicio;
    public static LoginUsuario loginUsuario;
    private static final String TAG = "USER";
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.usuario);
        contraseña = findViewById(R.id.contraseña);
        boton = findViewById(R.id.boton);

        this.servicio = RetrofitUser.getRetrofitInstance().create(GameNewsAPI.class);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                findViewById(R.id.ProgressBar).setVisibility(View.VISIBLE);
                Call<LoginUsuario> autenticar = servicio.sign(usuario.getText().toString(), contraseña.getText().toString());
                autenticar.enqueue(new Callback<LoginUsuario>() {

                    @Override
                    public void onResponse(Call<LoginUsuario> call, Response<LoginUsuario> response) {
                        if (response.isSuccessful()) {

                            loginUsuario = response.body();

                            if (loginUsuario.getToken() != null) {
                                findViewById(R.id.ProgressBar).setVisibility(View.GONE);
                                Intent intent = new Intent(MainActivity.this, NavDraw.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Log.d(TAG, "onResponse: " + response.errorBody());
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
