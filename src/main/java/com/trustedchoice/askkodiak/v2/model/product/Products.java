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

import java.util.List;
import java.util.Map;
import java.util.Optional;

@lombok.Data
public class Products {

    /**
     * A restatement of the class code hash requested.
     * Present if the request was made using an MD5 hash of 6 digit national industry code and description. In the event
     * a valid 2012 NAICS edition hash was submitted, it has been mapped to it's 2017 equivalent, and the 2017 hash
     * value will be found in this property.
     */
    private String hash;

    /**
     * The NAICS code (2-6 digit) for which the request applied.
     */
    private String code;

    /**
     * Description text associated with the NAICS code or hash for which the request was made.
     */
    private String description;

    /**
     * The type of NAICS group represented by the requested group (e.g. sector, subsector, industry-group,
     * international-industry, or national-industry). This will be present only on requests made using a 2-6 digit code.
     */
    private String type;

    /**
     * The NAICS sequence number of the requested group. This will be present only on requests made using a 2-6 digit
     * code.
     */
    private String seq;

    /**
     * The total count of products returned by this search.
     */
    private Integer count;

    /**
     * The number of products in each page of product results.
     */
    private Integer productsPerPage;

    /**
     * The page of results which the response represents.
     */
    private Integer page;

    /**
     * The total number of result pages available.
     */
    private Integer pages;

    /**
     * Are products in the products in the response limited to summary information only?
     */
    private Boolean summaryOnly;

    /**
     * Is the eligibility property attached to products in the results?
     */
    private Boolean includeEligibility;

    /**
     * An object summarizing the filters (if any) applied to the initial request.
     * TODO
     */
    private Map<String, Object> filters;

    /**
     * A list of 0-n product objects eligible for this code.
     */
    private List<Product> products;

    /**
     * An individual product eligible for the requested NAICS code.
     *
     * In the event that summaryOnly was set to true on the request, only the name, id, and ownerId properties will be
     * present on products in the results. If summaryOnly is omitted from the request or set to false, complete product
     * objects - rendered in accordance with the governing conditions as expressed by the request filters - will be
     * returned. For documentation about the singleton product instances in this array see the documentation for
     * Product/GetProduct. For requests made at the NAICS Group level (i.e. 2-6 digit codes) each product in the results
     * has been extended with a property _percentOfCodesEligibleInRequestedGroup which contains a numeric value
     * representing the percentage of codes in this group for which the product is eligible. 1 means, 100%, 0.016241
     * means a little less than 2% and so on. When present, results will be sorted by this property in descending order.
     */
    private Product product;

    /**
     * A restatement of the class code hash requested.
     * Present if the request was made using an MD5 hash of 6 digit national industry code and description. In the event
     * a valid 2012 NAICS edition hash was submitted, it has been mapped to it's 2017 equivalent, and the 2017 hash
     * value will be found in this property.
     */
    public Optional<String> getHash() {
        return Optional.ofNullable(hash);
    }

    /**
     * The type of NAICS group represented by the requested group (e.g. sector, subsector, industry-group,
     * international-industry, or national-industry). This will be present only on requests made using a 2-6 digit code.
     */
    public Optional<String> getType() {
        return Optional.ofNullable(type);
    }

    /**
     * The NAICS sequence number of the requested group. This will be present only on requests made using a 2-6 digit
     * code.
     */
    public Optional<String> getSeq() {
        return Optional.ofNullable(seq);
    }

    /**
     * An individual product eligible for the requested NAICS code.
     *
     * In the event that summaryOnly was set to true on the request, only the name, id, and ownerId properties will be
     * present on products in the results. If summaryOnly is omitted from the request or set to false, complete product
     * objects - rendered in accordance with the governing conditions as expressed by the request filters - will be
     * returned. For documentation about the singleton product instances in this array see the documentation for
     * Product/GetProduct. For requests made at the NAICS Group level (i.e. 2-6 digit codes) each product in the results
     * has been extended with a property _percentOfCodesEligibleInRequestedGroup which contains a numeric value
     * representing the percentage of codes in this group for which the product is eligible. 1 means, 100%, 0.016241
     * means a little less than 2% and so on. When present, results will be sorted by this property in descending order.
     */
    public Optional<Product> getProduct() {
        return Optional.ofNullable(product);
    }

}
