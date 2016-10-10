package br.fabio.professor.util;


import java.util.HashMap;
import java.util.Map;

public class HttpParam {
    private HashMap<String, String> param = new HashMap<String, String>();

    public HttpParam(){}

    public HttpParam add(String alias, String conteudo){
        param.put(alias, conteudo);
        return this;
    }

    public String getParam(){
        String ret = "";
        int i = 0;
        for(Map.Entry<String, String> linha : param.entrySet()) {
            String key = linha.getKey();
            String value = linha.getValue();

            if(i < param.size() - 1) {
                ret += key + "=" + value + "&";
            } else{
                ret += key + "=" + value;
            }
            i++;
        }

        return ret;
    }
}