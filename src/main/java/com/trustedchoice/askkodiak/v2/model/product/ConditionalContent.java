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

package com.trustedchoice.askkodiak.v2.model.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Optional;

@lombok.Data
public class ConditionalContent {
    /**
     * Conditional content resolved for this particular request based on geographies passed in the request.
     */
    private Map<String, Rule> geos;

    /**
     * Conditional content based on 2-6 digit NAICS groups specified in the request.
     */
    @JsonProperty("naics-groups")
    private Map<String, Rule> naicsGroups;

    /**
     * Conditional content based on NAICS hashes specified in the request.
     */
    @JsonProperty("naics-codes")
    private Map<String, Rule> naicsCodes;

    /**
     * Conditional content resolved for this particular request based on geographies passed in the request.
     */
    public Optional<Map<String, Rule>> getGeos() {
        return Optional.ofNullable(geos);
    }

    /**
     * Conditional content based on 2-6 digit NAICS groups specified in the request.
     */
    public Optional<Map<String, Rule>> getNaicsGroups() {
        return Optional.ofNullable(naicsGroups);
    }

    /**
     * Conditional content based on NAICS hashes specified in the request.
     */
    public Optional<Map<String, Rule>> getNaicsCodes() {
        return Optional.ofNullable(naicsCodes);
    }
}
