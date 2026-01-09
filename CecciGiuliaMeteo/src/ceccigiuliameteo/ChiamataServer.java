/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ceccigiuliameteo;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author alice
 */
public class ChiamataServer {
    private static final String chiaveAPI = "346504ae8848f197d1754fafc918ee6a";

    //ATTUALE
    private static final String URLBaseOggi = "https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&lang=it&appid=" + chiaveAPI;
    //DOMANI
    private static final String URLBasePrevisione ="https://api.openweathermap.org/data/2.5/forecast?q=%s&units=metric&lang=it&appid=" + chiaveAPI;

    public static Root getMeteoAttuale(String citta) throws Exception {
        String stringaURLOggi = String.format(URLBaseOggi, citta);
        URL urlOggi = new URL(stringaURLOggi);
        HttpURLConnection connessioneOggi = (HttpURLConnection) urlOggi.openConnection();
        connessioneOggi.setRequestMethod("GET");
        
        //prende il json dal servizio
        BufferedReader br1 = new BufferedReader(new InputStreamReader(connessioneOggi.getInputStream()));
        
        //Lo trasformo basandomi su Root
        Gson gson = new Gson();
        return gson.fromJson(br1, Root.class); 
    }
    
    public static RootPrevisioni getPrevisione(String citta) throws Exception {
    String url = String.format(URLBasePrevisione, citta);
    URL u = new URL(url);

    HttpURLConnection conn = (HttpURLConnection) u.openConnection();
    conn.setRequestMethod("GET");

    BufferedReader br = new BufferedReader(
        new InputStreamReader(conn.getInputStream())
    );

    Gson gson = new Gson();
    return gson.fromJson(br, RootPrevisioni.class);
}
}
