package com.kauuze.app.include;


import com.kauuze.app.config.contain.SpringContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * http请求工具类
 * @author kauuze
 * @email 3412879785@qq.com
 * @time 2019-02-24 12:30
 */
public class HttpUtils {

    public static String post(String url, MultiValueMap<String, String> query, MultiValueMap<String, String> body, HttpHeaders header) {
        RestTemplate restTemplate = SpringContext.getBean(RestTemplate.class);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url).queryParams(query);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, header);
        return restTemplate.postForObject(builder.build().encode().toUri(),httpEntity,String.class);
    }

    public static String get(String url,MultiValueMap<String, String> query) {
        RestTemplate restTemplate = SpringContext.getBean(RestTemplate.class);
        return restTemplate.getForObject(url,String.class,query);
    }
}
