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

package com.trustedchoice.askkodiak.v2.model.product;

import java.util.Map;
import java.util.Optional;

@lombok.Data
public class ProductRuleStats {

    /**
     * A map describing which geographies this product has rules for. Keys are the geo code.
     */
    private Map<String, String> forGeos;

    /**
     * Does this product have specific content based on geography?
     */
    private Boolean hasGeoContent;

    /**
     * Does this product have specific content based on NAICS codes?
     */
    private Boolean hasNAICSContent;

    /**
     * Number of rules this product has based on NAICS hashes.
     */
    private Integer numCodeRules;

    /**
     * Number of rules this product has based on geography.
     */
    private Integer numGeoRules;

    /**
     * Number of rules this product has based on NAICS groups.
     */
    private Integer numGroupRules;

    /**
     * In the event that the governing conditions of the query caused content to be included, the ids of the rules which
     * were applied can be found in this object as keys.
     */
    private Map<String, Boolean> triggered;

    /**
     * A map describing which geographies this product has rules for. Keys are the geo code.
     */
    public Optional<Map<String, String>> forGeos() {
        return Optional.ofNullable(forGeos);
    }

    /**
     * Does this product have specific content based on geography?
     */
    public Optional<Boolean> hasGeoContent() {
        return Optional.ofNullable(hasGeoContent);
    }

    /**
     * Does this product have specific content based on NAICS codes?
     */
    public Optional<Boolean> hasNAICSContent() {
        return Optional.ofNullable(hasNAICSContent);
    }

    /**
     * Number of rules this product has based on NAICS hashes.
     */
    public Optional<Integer> getNumCodeRules() {
        return Optional.ofNullable(numCodeRules);
    }

    /**
     * Number of rules this product has based on geography.
     */
    public Optional<Integer> getNumGeoRules() {
        return Optional.ofNullable(numGeoRules);
    }

    /**
     * Number of rules this product has based on NAICS groups.
     */
    public Optional<Integer> getNumGroupRules() {
        return Optional.ofNullable(numGroupRules);
    }

    /**
     * In the event that the governing conditions of the query caused content to be included, the ids of the rules which
     * were applied can be found in this object as keys.
     */
    public Optional<Map<String, Boolean>> getTriggered() {
        return Optional.ofNullable(triggered);
    }

}
