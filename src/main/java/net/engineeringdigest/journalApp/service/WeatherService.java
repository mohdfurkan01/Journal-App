package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//@Component
@Service
public class WeatherService {

    //access api key from yml file
    @Value("${weather.api.key}")
    private String apiKey;

    //private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    //for get method
    public WeatherResponse getWhether(String city){
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if(weatherResponse != null) {
            return weatherResponse;
        }else {
            String finalAPI = appCache.APP_CACHE.get("weather_api").replace("<city>", city).replace("<apiKey>", apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if(body != null) {
             redisService.set("weather_of_" + city,body,300l); //300 sec=5min
            }
            return body;
        }



//        String finalAPI = appCache.APP_CACHE.get("weather_api").replace("<city>", city).replace("<apiKey>", apiKey);
//        System.out.println("Calling weather API: " + finalAPI);
//        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
//        WeatherResponse body = response.getBody();
//        return body;
        //String urlTemplate = appCache.APP_CACHE.get("weather_api");

        //if (urlTemplate == null) {
          //  throw new IllegalStateException("Missing 'weather_api' key in APP_CACHE. Check DB values.");
        //}

        //String finalAPI = urlTemplate.replace("<city>", city).replace("<apiKey>", apiKey);
        //ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        //return response.getBody();
    }


    //for post method only knowledge purpose and not used practically
    //public WeatherResponse postWhether(String city){
        //String finalAPI = API.replace("CITY", city).replace("API_KEY", apiKey);

      //  String requestBody = "{\n" +
    //    "        \"userName\": \"Hamza\",\n" +
  //      "        \"password\": \"Hamza\", \n" +
//"} ";
        //HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.set("key", "value");
        //UserDetails user = User.builder().username("Hamza").password("Hamza").build();
        //HttpEntity<UserDetails> httpEntity = new HttpEntity<>(user, httpHeaders);

        //ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, httpEntity, WeatherResponse.class);
        //WeatherResponse body = response.getBody();
        //return body;
    //}
}

//The process of converting json response into corresponding java object is known as deserialization.