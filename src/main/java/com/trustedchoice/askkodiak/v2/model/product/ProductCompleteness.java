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

/**
 * An object containing data about the level of completeness of a product.
 */
@lombok.Data
public class ProductCompleteness {

    /**
     * The total number of NAICS hashes for which this product is marked as eligible.
     */
    private Integer eligibleHashCount;

    /**
     * The percentage value complete where 0 equals nothing filled in, and 100 equals everything has been entered.
     */
    private Float percentage;

    /**
     * A completeness profile of the product by attribute type.
     */
    private Map<String, Boolean> profile;

    /**
     * The total number of NAICS hashes for which this product is marked as eligible.
     */
    public Optional<Integer> getEligibleHashCount() {
        return Optional.ofNullable(eligibleHashCount);
    }

    /**
     * The percentage value complete where 0 equals nothing filled in, and 100 equals everything has been entered.
     */
    public Optional<Float> getPercentage() {
        return Optional.ofNullable(percentage);
    }

    /**
     * A completeness profile of the product by attribute type.
     */
    public Optional<Map<String, Boolean>> getProfile() {
        return Optional.ofNullable(profile);
    }

}
