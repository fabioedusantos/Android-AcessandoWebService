package br.fabio.professor.acessandowebservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import br.fabio.professor.util.Http;
import br.fabio.professor.util.HttpParam;

public class CadastroActivity extends AppCompatActivity {

    private EditText nome;
    private EditText usuario;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nome = (EditText) findViewById(R.id.txt_nome);
        usuario = (EditText) findViewById(R.id.txt_usuario);
        senha = (EditText) findViewById(R.id.txt_senha);
    }

    public void salvar(View v){
        final String textNome = nome.getText().toString();
        final String textUsuario = usuario.getText().toString();
        final String textSenha = senha.getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpParam p = new HttpParam();
                p.add("nome", textNome);
                p.add("usuario", textUsuario);
                p.add("senha", textSenha);
                String param = p.getParam();

                final String ret = Http.post("http://10.10.2.47/projeto/index.php/webservice/add", param);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject ob = new JSONObject(ret);
                            long status = ob.getInt("status");
                            //O WebService retorna o id da inserção no banco se sucesso!
                            if(status > 0){
                                Toast.makeText(getBaseContext(), "Salvo com Sucesso!", Toast.LENGTH_SHORT).show();
                            } else{
                                Toast.makeText(getBaseContext(), "Erro ao Salvar!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Log.e("apk", e.getMessage());
                        }
                    }
                });
            }
        }).start();


    }

}
