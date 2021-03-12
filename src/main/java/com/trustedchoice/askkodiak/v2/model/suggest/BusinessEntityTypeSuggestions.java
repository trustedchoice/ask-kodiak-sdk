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

package com.trustedchoice.askkodiak.v2.model.suggest;

import java.util.List;

@lombok.Data
public class BusinessEntityTypeSuggestions {

    /**
     * The total number of hits matching the query.
     */
    private Integer nbHits;

    /**
     * The page of results which the response represents.
     */
    private Integer page;

    /**
     * The total number of result pages available.
     */
    private Integer nbPages;

    /**
     * The number of hits in each page of product results.
     */
    private Integer hitsPerPage;

    /**
     * A restatement of the search query.
     */
    private String query;

    /**
     * The amount of time, in milliseconds, which the request took to process.
     */
    private Long processingTimeMS;

    /**
     * A boolean indicating if the nbHits count was exhaustive or approximate.
     */
    private Boolean exhaustiveNbHits;

    /**
     * An array of potential matches (hits) for the search string
     */
    private List<BusinessEntityTypeSuggestion> hits;

}
