package com.not.byko.jarlvpn_android_adminpanel;

import android.widget.ArrayAdapter;

import com.not.byko.jarlvpn_android_adminpanel.models.DiscountCode;
import com.not.byko.jarlvpn_android_adminpanel.models.LoginRequest;
import com.not.byko.jarlvpn_android_adminpanel.models.LoginResponse;
import com.not.byko.jarlvpn_android_adminpanel.models.Root;
import com.not.byko.jarlvpn_android_adminpanel.models.UsersList;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

public class WebController {

    private static String jwtToken = "";
    private static long expireTokenDate = 0;

    private RestTemplate restTemplate = new RestTemplate();


    public HttpHeaders authorizedHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwtToken);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }

    public Boolean loginToAdminPanel(String login, String password){

        LoginRequest loginRequest = new LoginRequest(login, password);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<LoginRequest> httpEntity = new HttpEntity<LoginRequest>(loginRequest, headers);

        ResponseEntity<LoginResponse> response = restTemplate.exchange("http://10.0.2.2:8080/login",
                HttpMethod.POST, httpEntity, LoginResponse.class);

        if(response.getStatusCode().is2xxSuccessful()){
            jwtToken = response.getBody().getJwt();
            expireTokenDate = System.currentTimeMillis();

            return true;
        }else{
            return false;
        }
    }

    public List<String> getUsersList(){

        if(Utils.checkJwtBeforeRequest(jwtToken, expireTokenDate)){
            HttpEntity<?> entity = new HttpEntity<>(authorizedHeader());

            ResponseEntity<UsersList> responseEntity = restTemplate.exchange("http://10.0.2.2:8080/users",
                    HttpMethod.GET, entity, UsersList.class);

            return responseEntity.getBody().getUsers();

        }else{
            //redirect to login panel
        }
        return Collections.emptyList();
    }

    public List<DiscountCode> getDiscountCodeList(){
        if(Utils.checkJwtBeforeRequest(jwtToken, expireTokenDate)){
            HttpEntity<?> entity = new HttpEntity<>(authorizedHeader());

            ResponseEntity<Root> responseEntity = restTemplate.exchange("http://10.0.2.2:8080/promocodes",
                    HttpMethod.GET, entity, Root.class);
            return responseEntity.getBody().getDiscountCodeList();
        }else{
            //redirect to login panel
        }
        return Collections.emptyList();
    }




    public static void logout(){
        jwtToken = "";
        expireTokenDate = 0;
    }

}
