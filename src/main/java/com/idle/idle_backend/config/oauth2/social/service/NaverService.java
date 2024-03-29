package com.idle.idle_backend.config.oauth2.social.service;

import com.idle.idle_backend.config.jwt.JwtTokenProvider;
import com.idle.idle_backend.config.oauth2.social.dto.GetLoginTokenResponse;
import com.idle.idle_backend.user.domain.User;
import com.idle.idle_backend.user.domain.UserRepository;
import com.idle.idle_backend.user.enums.Providers;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class NaverService {

    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String clientId;


    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String client_secret;


    public String getNaverToken(String code, String state) throws ParseException {
        // 인가코드로 토큰받기
        String host = "https://nid.naver.com/oauth2.0/token";

        RestTemplate rt = new RestTemplate();
        rt.setRequestFactory(new HttpComponentsClientHttpRequestFactory()); // restTemplate 에러 메세지 확인 설정

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("grant_type", "authorization_code");
        param.add("client_id", clientId);
        param.add("client_secret", client_secret);
        param.add("code", code);
        param.add("state", state);

        HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(param, headers);
        ResponseEntity<String> res = rt.exchange(host,
                HttpMethod.POST,
                req,
                String.class);

        JSONParser jsonParser = new JSONParser();
        JSONObject parse = (JSONObject) jsonParser.parse(res.getBody());

        return (String) parse.get("access_token");

    }


    public Map<String, String> getNaverUserInfo(String access_token) {
        String host = "https://openapi.naver.com/v1/nid/me";
        Map<String, String> result = new HashMap<>(); //key, value json 형식으로 데이터 내보내기 위해 hashMap 사용
        try {
            URL url = new URL(host);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //request header 부분
            urlConnection.setRequestProperty("Authorization", "Bearer " +access_token);
            urlConnection.setRequestMethod("GET");

            try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))){
                String line;
                StringBuilder res = new StringBuilder();
                while((line=br.readLine())!=null)
                {
                    res.append(line);
                }
                // int responseCode = urlConnection.getResponseCode();
                // System.out.println(responseCode);

                JSONParser parser = new JSONParser();
                JSONObject obj = (JSONObject) parser.parse(res.toString());

                System.out.println(obj);   // response 부분이 따로 { } 로 되어 있음
                JSONObject response = (JSONObject) obj.get("response");


                String id = response.get("id").toString();


                result.put("id",id);

            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return result;

    }

    public GetLoginTokenResponse NaverLogin(String code, String state) throws IOException, ParseException {
        String NaveraccessToken = this.getNaverToken(code, state);// 인가 코드로 네이버 서버에 네이버 엑세스 토큰 요청
        Map<String, String> userInfo = this.getNaverUserInfo(NaveraccessToken);  //네이버 서버에 네이버 엑세스 토큰으로 유저정보 요청
        Optional<User> id = getUserByEmail(userInfo.get("id"));
        boolean isNewUser = false;
        if (id.isEmpty()) {
            User user = new User();
            user.setProviderId(userInfo.get("id"));
            user.setProvider(Providers.NAVER);
            userRepository.save(user);
            isNewUser = true;
            return new GetLoginTokenResponse(this.jwtTokenProvider.makeJwtToken(user.getId(), 30), isNewUser);
        }
        User findUser = id.get();
        return new GetLoginTokenResponse(this.jwtTokenProvider.makeJwtToken(findUser.getId(), 30), isNewUser);
    }

    public Optional<User> getUserByEmail(String providerId) {
        return userRepository.findByProviderId(providerId);
    }

}