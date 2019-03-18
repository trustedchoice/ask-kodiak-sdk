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

/**
 * A match for the search term.
 */
@lombok.Data
public class NaicsGroupSuggestion {

    /**
     * The NAICS code which corresponds with the hit.
     */
    private String code;

    /**
     * The business description of the hit.
     */
    private String title;

    /**
     * The NAICS sequence number of the hit.
     */
    private String seq;

    /**
     * A list of NAICS groups which are direct descendants of the hit.
     */
    private List<String> decendants;

    /**
     * A list of codes that have replaced this one in subsequent NAICS editions.
     */
    private List<String> replacedBy;

    /**
     * A list of codes that this one replaces from earlier NAICS editions.
     */
    private List<String> replaces;

    /**
     * If available, the payroll related to this 6 digit Industry Group as of the latest US Economic Census.
     */
    private Integer economicCensus2012payroll;

    /**
     * If available, the number of US establishments of this Industry Group type as of the latest US Economic Census.
     */
    private Integer economicCensus2012numEstablishments;

    /**
     * If available, the number of individuals employed within this Industry Group as of the latest US Economic Census.
     */
    private Integer economicCensus2012employees;

    /**
     * If available, the number total receipts produced by this Industry Group as of the latest US Economic Census.
     */
    private Integer economicCensus2012receipts;

    /**
     * An internal tracking identifier for the record.
     */
    private String objectID;

}
