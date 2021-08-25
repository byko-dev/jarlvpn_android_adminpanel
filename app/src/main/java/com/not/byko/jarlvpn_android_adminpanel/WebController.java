package com.not.byko.jarlvpn_android_adminpanel;

import com.not.byko.jarlvpn_android_adminpanel.models.ChangeConfigRequest;
import com.not.byko.jarlvpn_android_adminpanel.models.ConfigRequest;
import com.not.byko.jarlvpn_android_adminpanel.models.ConfigResponse;
import com.not.byko.jarlvpn_android_adminpanel.models.Configs;
import com.not.byko.jarlvpn_android_adminpanel.models.DeleteCodeRequest;
import com.not.byko.jarlvpn_android_adminpanel.models.DiscountCode;
import com.not.byko.jarlvpn_android_adminpanel.models.LoginRequest;
import com.not.byko.jarlvpn_android_adminpanel.models.LoginResponse;
import com.not.byko.jarlvpn_android_adminpanel.models.NewsListResponse;
import com.not.byko.jarlvpn_android_adminpanel.models.ServersListResponse;
import com.not.byko.jarlvpn_android_adminpanel.models.StatusModel;
import com.not.byko.jarlvpn_android_adminpanel.models.WebConfig;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WebController {

    private static String jwtToken = "";
    private static long expireTokenDate = 0;

    private String backend_api = "http://10.0.2.2:8080/api";

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

        ResponseEntity<LoginResponse> response = restTemplate.exchange(backend_api + "/adminpanel/login",
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

            ResponseEntity<String[]> responseEntity = restTemplate.exchange(backend_api + "/adminpanel/gutboard",
                    HttpMethod.POST, entity, String[].class);

            return Arrays.asList(responseEntity.getBody().clone());

        }else{
            //redirect to login panel
        }
        return Collections.emptyList();
    }

    public List<DiscountCode> getDiscountCodeList(){
        if(Utils.checkJwtBeforeRequest(jwtToken, expireTokenDate)){
            HttpEntity<?> entity = new HttpEntity<>(authorizedHeader());

            ResponseEntity<DiscountCode[]> responseEntity = restTemplate.exchange(backend_api + "/adminpanel/discountlist",
                    HttpMethod.GET, entity, DiscountCode[].class);
            return Arrays.asList(responseEntity.getBody());
        }else{
            //redirect to login panel
        }
        return Collections.emptyList();
    }

    public List<NewsListResponse> getNewsList(){
        if(Utils.checkJwtBeforeRequest(jwtToken, expireTokenDate)){
            HttpEntity<?> entity = new HttpEntity<>(authorizedHeader());

            ResponseEntity<NewsListResponse[]> responseEntity = restTemplate.exchange(backend_api + "/adminpanel/allnews",
                    HttpMethod.GET, entity, NewsListResponse[].class);
            return Arrays.asList(responseEntity.getBody());
        }else{
            //redirect to login panel or something
        }

        return Collections.emptyList();
    }

    public List<ServersListResponse> getServerList(){
        if(Utils.checkJwtBeforeRequest(jwtToken, expireTokenDate)){
            HttpEntity<?> entity = new HttpEntity<>(authorizedHeader());

            ResponseEntity<ServersListResponse[]> responseEntity = restTemplate.exchange(backend_api + "/adminpanel/gutboard/jarlservers",
                    HttpMethod.GET, entity, ServersListResponse[].class);
            return Arrays.asList(responseEntity.getBody());
        }else{
            //something
        }
        return Collections.emptyList();
    }


    public WebConfig getDetails(){
        if(Utils.checkJwtBeforeRequest(jwtToken, expireTokenDate)){
            HttpEntity<?> entity = new HttpEntity<>(authorizedHeader());

            ResponseEntity<WebConfig> responseEntity = restTemplate.exchange(backend_api + "/adminpanel/gutconfig",
                    HttpMethod.GET, entity, WebConfig.class);
            return responseEntity.getBody();
        }else{
            //something
        }
        return new WebConfig();
    }

    public List<Configs> getWireGuardConfigurations(){
        if(Utils.checkJwtBeforeRequest(jwtToken, expireTokenDate)){
            HttpEntity<?> entity = new HttpEntity<>(authorizedHeader());

            ResponseEntity<Configs[]> responseEntity = restTemplate.exchange(backend_api + "/adminpanel/allconfigs",
                    HttpMethod.GET, entity, Configs[].class);
            return Arrays.asList(responseEntity.getBody());
        }else{
            //something
        }
        return Collections.emptyList();
    }

    public ConfigResponse getConfigContent(String usernameId, String configName){
        if(Utils.checkJwtBeforeRequest(jwtToken, expireTokenDate)) {
            ConfigRequest configRequest = new ConfigRequest(usernameId, configName);

            HttpEntity<ConfigRequest> entity = new HttpEntity<ConfigRequest>( configRequest, authorizedHeader());

            ResponseEntity<ConfigResponse> responseEntity = restTemplate.exchange(backend_api + "/adminpanel/download",
                    HttpMethod.POST, entity, ConfigResponse.class);

            return responseEntity.getBody();
        }else{
            //something
        }
        return new ConfigResponse();
    }

    public StatusModel deleteCode(String code){
        if(Utils.checkJwtBeforeRequest(jwtToken, expireTokenDate)){
            DeleteCodeRequest deleteCodeRequest = new DeleteCodeRequest(code);

            HttpEntity<DeleteCodeRequest> entity = new HttpEntity<DeleteCodeRequest>( deleteCodeRequest, authorizedHeader());

            ResponseEntity<StatusModel> responseEntity = restTemplate.exchange(backend_api + "/adminpanel/delete/discountcode",
                    HttpMethod.POST, entity, StatusModel.class);
            return responseEntity.getBody();
        }else{
            //something
        }
        return new StatusModel();
    }

    public StatusModel setGutConfig(Float oneMonthPrice, Integer sixMonthsDiscount){
        if(Utils.checkJwtBeforeRequest(jwtToken, expireTokenDate)){
            ChangeConfigRequest changeConfigRequest = new ChangeConfigRequest(oneMonthPrice, sixMonthsDiscount);

            HttpEntity<ChangeConfigRequest> entity = new HttpEntity<ChangeConfigRequest>(changeConfigRequest, authorizedHeader());
            ResponseEntity<StatusModel> responseEntity = restTemplate.exchange(backend_api + "/adminpanel/gutconfig",
                    HttpMethod.POST, entity, StatusModel.class);
            return responseEntity.getBody();
        }else{
            //something
        }
        return new StatusModel();
    }




    public static void logout(){
        jwtToken = "";
        expireTokenDate = 0;
    }
}
