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
public class NaicsCodeSuggestion {

    /**
     * The NAICS code which corresponds with the hit.
     */
    private String code;

    /**
     * An MD5 hash of the 6 digit code and sub-description.
     */
    private String hash;

    /**
     * The business description of the hit.
     */
    private String description;

    /**
     * A list with the NAICS lineage of the hit.
     */
    private List<String> path;

    /**
     * A space delimited list of SIC codes which map to the hit.
     */
    private String sic;

    /**
     * The NAICS sector to which this hit belongs.
     */
    private String sectorCode;

    /**
     * The title of the NAICS sector to which this hit belongs.
     */
    private String sectorTitle;

    /**
     * The NAICS subsector (3 digit NAICS) to which this hit belongs.
     */
    private String subsectorCode;

    /**
     * The title of the NAICS subsector to which this hit belongs.
     */
    private String subsectorTitle;

    /**
     * The NAICS industry group (4 digit NAICS) to which this hit belongs.
     */
    private String industryGroupCode;

    /**
     * The title of the NAICS industry group to which this hit belongs.
     */
    private String industryGroupTitle;

    /**
     * The NAICS international industry group (5 digit NAICS) to which this hit belongs.
     */
    private String internationalIndustryCode;

    /**
     * The title of the NAICS international industry group to which this hit belongs.
     */
    private String internationalIndustryTitle;

    /**
     * The NAICS national industry group (6 digit NAICS) to which this hit belongs.
     */
    private String nationalIndustryCode;

    /**
     * The title of the NAICS national industry group to which this hit belongs.
     */
    private String nationalIndustryTitle;

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
