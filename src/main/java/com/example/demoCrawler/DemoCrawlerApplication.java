package com.example.demoCrawler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.http.*;

import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class DemoCrawlerApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(DemoCrawlerApplication.class, args);

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://api.petfinder.com/v2/oauth2/token?=vari&=re");

// Request parameters and other properties. body -> form-data in our caose of post request
        List<NameValuePair> params = new ArrayList<NameValuePair>(3);
        params.add(new BasicNameValuePair("grant_type", "client_credentials"));
        params.add(new BasicNameValuePair("client_id", "H3m0Q53cyo1VADKYNCng4yC91ONyLlZIYXlcL0rnJ10138X5ns"));
        params.add(new BasicNameValuePair("client_secret", "nUnyrVZ80CRnyAZ9xkgll6UF8O5hdGRWd1Axn0aa"));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

//Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        String postResponse = EntityUtils.toString(entity);
        System.out.println("POST response in JSON format: " + postResponse);


        ObjectMapper mapper = new ObjectMapper();
        TokenMapper tokenMapper = mapper.readValue(postResponse, TokenMapper.class);


        HttpGet httpGet = new HttpGet("https://api.petfinder.com/v2/animals?type=dog&page=2");
        System.out.println("extracted access token:: " + tokenMapper.getAccessToken());

        httpGet.setHeader("Authorization", "Bearer " + tokenMapper.getAccessToken());

        response = httpclient.execute(httpGet);
        entity = response.getEntity();
        System.out.println("GET response in JSON format:");
        System.out.println(EntityUtils.toString(entity));
    }
}
