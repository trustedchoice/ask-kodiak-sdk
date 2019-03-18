/*
 * MIT License
 *
 * Copyright (c) 2019 Consumer Agent Portal, LLC (TrustedChoice.com)
 *                    Superkick Ventures, LLC (Ask Kodiak)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.trustedchoice.askkodiak.v2.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.Logger;
import feign.RequestInterceptor;
import feign.Response;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;

import java.io.IOException;
import java.util.Arrays;

public class AskKodiakClient {

    static class AskKodiakErrorDecoder implements ErrorDecoder {

        private ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public Exception decode(String methodKey, Response response) {
            try {
                JsonNode jsonNode = objectMapper.readTree(response.body().asInputStream());
                JsonNode message = jsonNode.get("message");
                JsonNode code = jsonNode.get("code");
                if (message != null) {
                    return new AskKodiakException(response.status(), message.textValue());
                } else if (code != null) {
                    return new AskKodiakException(response.status(), code.textValue());
                } else {
                    return new AskKodiakException(response.status(), response.reason());
                }
            } catch (IOException e) {
                // intentionally nothing
            }
            return new AskKodiakException(response.status(), response.reason());
        }
    }

    public static AskKodiak getInstance(String groupId, String apiKey) {
        return getInstance(groupId, apiKey, null);
    }

    public static AskKodiak getInstance(String groupId, String apiKey, RequestInterceptor... interceptors) {
        return getInstance(groupId, apiKey, "https://api.askkodiak.com", interceptors);
    }

    public static AskKodiak getInstance(String groupId,
                                        String apiKey,
                                        String endpoint,
                                        RequestInterceptor... interceptors) {
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Feign.Builder builder = Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .errorDecoder(new AskKodiakErrorDecoder());
        if (interceptors != null) {
            builder.requestInterceptors(Arrays.asList(interceptors));
        }
        builder.requestInterceptor(new BasicAuthRequestInterceptor(groupId, apiKey));

        builder.logger(new Slf4jLogger(AskKodiak.class));
        builder.logLevel(Logger.Level.FULL);
        return builder.target(AskKodiak.class, endpoint);
    }
}
