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

import java.util.List;

/**
 * Product Grouping Object.
 */
public class ProductGrouping {
    /**
     * Number of products eligible for this productGrouping.
     */
    @JsonProperty("_numEligible")
    private Integer numEligible;

    /**
     * Number of products applicable to this productGrouping.
     */
    @JsonProperty("_numProducts")
    private Integer numProducts;

    /**
     * Cumulative score of products for this productGrouping.
     */
    @JsonProperty("_score")
    private Integer score;

    /**
     * Ask Kodiak Product Code value associated with the governing coverage implied by the group. Used in user
     * interface, commonly to select the correct icon for the results. For the catch-all "Other" productGrouping this
     * value will be _DEFAULT.
     */
    private String governingCode;

    /**
     * A human-readable identifier for the productGrouping.
     */
    private String name;

    /**
     * Summary product objects for this productGrouping.
     */
    private List<Product> products;

}
