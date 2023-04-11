package com.example.okta.servicewrapper.exception;

import com.google.gson.Gson;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;


public class CustomErrorDecoder implements ErrorDecoder {

    public static final Gson GSON = new Gson();

    Logger logger = LoggerFactory.getLogger(ErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        Response.Body responseBody = response.body();
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());
        String responseStr = null;
        try {
            responseStr = new String(responseBody.asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(responseStr);
        }

        if (responseStatus.is5xxServerError()) {
            return new RestApiServerException(response.status(), responseStr);
        } else if (responseStatus.is4xxClientError()) {
            return new RestApiClientException(response.status(), responseStr);
        } else {
            return new Exception("Generic Exception");
        }
    }

    private String getMessage(Response.Body responseBody) {
        String responseStr = null;
        try {
            responseStr = new String(responseBody.asInputStream().readAllBytes(), StandardCharsets.UTF_8);
            Map<String, Object> stringObjectMap = GSON.fromJson(responseStr, Map.class);
            if (Objects.nonNull(stringObjectMap.get("Message"))) {
                responseStr = String.valueOf(stringObjectMap.get("Message"));
            }
            if (Objects.nonNull(stringObjectMap.get("message"))) {
                responseStr = String.valueOf(stringObjectMap.get("message"));
            }
        } catch (Exception e) {
            logger.error("Conversion BLC Message Failed from Error Message ".concat(e.getMessage()));
        }
        return responseStr;
    }
}
