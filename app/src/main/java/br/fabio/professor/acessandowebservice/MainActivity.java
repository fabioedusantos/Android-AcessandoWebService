package br.fabio.professor.acessandowebservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import br.fabio.professor.util.Http;
import br.fabio.professor.util.HttpParam;

public class MainActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = (EditText) findViewById(R.id.txt_usuario);
        senha = (EditText) findViewById(R.id.txt_senha);
    }

    public void entrar(View v){
        final String user = usuario.getText().toString();
        final String pass = senha.getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpParam p = new HttpParam();
                p.add("usuario", user);
                p.add("senha", pass);
                String param = p.getParam();

                final String ret = Http.post("http://10.10.2.47/projeto/index.php/WebService/login", param);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject ob = new JSONObject(ret);
                            boolean logado = ob.getBoolean("login");

                            Log.e("apk", logado + "");
                            if(logado){
                                Toast.makeText(MainActivity.this, "Logado com Sucesso!", Toast.LENGTH_SHORT).show();
                            } else{
                                Toast.makeText(MainActivity.this, "Usuário/Senha inválido(s)!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e){
                            Log.e("apk", e.getMessage());
                        }
                    }
                });

            }
        }).start();


    }
}
