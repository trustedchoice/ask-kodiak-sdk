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

package com.trustedchoice.askkodiak.v2.model.company;

import java.util.List;
import java.util.Map;

@lombok.Data
public class Companies {

    /**
     * The total count of companies returned by this query.
     */
    private Integer count;

    /**
     * The number of companies in each page of results.
     */
    private Integer companiesPerPage;

    /**
     * The page of results which the response represents.
     */
    private Integer page;

    /**
     * The total number of result pages available.
     */
    private Integer pages;

    /**
     * A map summarizing the filters (if any) applied to the query.
     */
    private Map<String, String> filters;

    /**
     * An array of 0-n companies objects eligible for this code.
     *
     * For documentation about the singleton company instances in this array see the documentation for
     * Company/GetCompany
     */
    private List<Company> companies;

}
