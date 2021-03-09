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

package com.trustedchoice.askkodiak.v2.model.company;

import java.util.List;
import java.util.Map;

@lombok.Data
public class CompanyProducts {
    /**
     * the total number of companies applicable to the request.
     */
    private Integer companyCount;

    /**
     * the total number of products applicable to the request (including products ineligible for the request conditions
     * belonging to a company that has at least one eligible product for those same conditions).
     */
    private Integer productCount;

    /**
     * the total number of eligible products across all companies in the response under the governing request
     * conditions.
     */
    private Integer eligibleProductCount;

    /**
     * the total number of eligible products across all companies in the response under the governing request
     * conditions that were able to be assigned to a defined productGroup other than the catch-all group Other.
     */
    private Integer eligibleGroupedProductCount;

    /**
     * the total number of eligible products across all companies in the response under the governing request
     * conditions that were not able to be assigned to a defined productGroup and thusly fell into the catch-all group
     * Other.
     */
    private Integer eligibleNotGroupedProductCount;

    /**
     * an indicator if products ineligible for the request are present in the products arrays on each of the companies.
     */
    private Boolean includeIneligible;

    /**
     * source of productGroupings applied to response. One of system-default, search-preference, or request.
     */
    private String productGroupingsSource;

    /**
     * source of 'Virtual' Companies found in the response companies array. One of none, search-preference, or request.
     */
    private String virtualCompaniesSource;

    /**
     * the total number of companies per page in the response.
     */
    private Integer companiesPerPage;

    /**
     * the 0-based page number of the response results.
     */
    private Integer page;

    /**
     * the total number of the result pages available.
     */
    private Integer pages;

    /**
     * An object summarizing the filters (if any) applied to the request. Use this to confirm the correct application
     * of any request parameters like naicsCodes, annualRevenue, geos, etc.
     */
    private Map<String, Object> filters;

    /**
     * An array of 0-n company objects with at least one product available for this request.
     */
    private List<Company> companies;

}
