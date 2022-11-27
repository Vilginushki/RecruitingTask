package io.github.vilginushki;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserClient {
    //HttpResponse need to have this client builded
    private static HttpClientBuilder httpClient = HttpClientBuilder.create();

    //In my case this class is the main one, so there is also main Method, it can be deleted and then there will be only users public method
    public static void main(String[] args) {
        List<User> userDTOs = users();
    }

    public static List<User> users(){
        try {
            //Create get request and set URL and Header
            HttpGet getRequest = new HttpGet("https://randomuser.me/api/?results=5");
            getRequest.addHeader("accept", "application/xml");

            //Send the request
            HttpResponse response = httpClient.build().execute(getRequest);
            //verify if the response is valid
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }
            //Pull response object
            HttpEntity httpEntity = response.getEntity();
            String apiOutput = EntityUtils.toString(httpEntity);
            //create output list
            List<User> users = new ArrayList<>();
            //create array of all users data
            JSONArray responseArray = new JSONObject(apiOutput).getJSONArray("results");
            //iterate through all users
            for (int i = 0; i < responseArray.length(); i++) {
                //get one userdata
                JSONObject userData = responseArray.getJSONObject(i);
                //get all needed attributes
                String gender = userData.getString("gender");
                String firstName = userData.getJSONObject("name").getString("first");
                String lastName = userData.getJSONObject("name").getString("last");
                String city = userData.getJSONObject("location").getString("city");

                UUID loginUUID = UUID.fromString(userData.getJSONObject("login").getString("uuid"));
                LocalDateTime registrationDate = LocalDateTime.parse(userData.getJSONObject("registered").getString("date"), DateTimeFormatter.ISO_DATE_TIME);
                //Add user to list
                users.add(new User(gender, firstName, lastName, city, loginUUID, registrationDate));
            }
            return users;
        } catch (Exception e) {
            System.out.println("Something went wrong, maybe api server is down?");
            System.out.println(e);
        }
        return null;
    }
}
