/*
 * MIT License
 *
 * Copyright (c) 2021 Consumer Agent Portal, LLC (TrustedChoice.com)
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

package com.trustedchoice.askkodiak.v2.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The AskKodiak API expects good number of parameters to be delimited with a "+".
 * When a request URL is encoded by replacing "+" with "%2B", then the API does not return results as expected.
 * This {@link RequestInterceptor} converts occurrences of "%2B" back to "+"
 */
public class PlusSignEncodingRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {

        Map<String, Collection<String>> newQueries =
            template.queries()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, this::unencodePlusSigns));

        template.queries(new HashMap<>());
        template.queries(newQueries);

    }

    private List<String> unencodePlusSigns(Map.Entry<String, Collection<String>> entry) {

        return
            entry
                .getValue()
                .stream()
                .map(s -> s.replace("%2B", "+"))
                .collect(Collectors.toList());

    }

}
