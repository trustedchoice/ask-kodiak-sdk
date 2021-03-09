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

import java.util.List;

/**
 * Conditions (e.g. class, geography, etc) which have been contemplated in coming to a determination of eligibility.
 */
@lombok.Data
public class Filters {

    /**
     * Given the specified geography(s) if any. Use + as separator to include more than one ISO 3166-2 code. Usage
     * geos=US-MA or geos=CA-ON+CA-BC. A filter of geos=US-MN+US-HI means Minnesota OR Hawaii.
     */
    private String geos;

    /**
     * Given the specified group(s) if any. Use + as separator to include more than one naics group. Usage
     * naicsGroups=722514 or naicsGroups=722514+722515.
     */
    private String naicsGroups;

    /**
     * Given the specified codes(s) if any.
     */
    private String naicsCodes;

    /**
     * Given the specified entity type(s) if any. Use + as separator to include more than 1 entity type code. Usage
     * entityTypes=AS or entityTypes=AS+CCORP.
     */
    private List<String> entityTypes;

    /**
     * Given the custom classification(s) if any. Specify classifications as a composite key made up of the taxonomy
     * identifier (tid) to which the code belongs and the code id (cid) itself delimited by a colon (:).
     * <p>
     * As an illustrative example, to consider eligibility for bop code 12345 the value would be bop:12345. More than
     * one classification can be specified. In this event, use + as separator to include more than on. For example
     * bop:12345+bop:78901+wc:1111.
     * <p>
     * In the event that this parameter is specified, products must be explicitly correlated by their owner to the
     * requested taxonomy to be considered to be eligible. In other words if the taxonomy is not linked to the product
     * by the owner it will not be eligible for this request.
     */
    private String classifications;

    /**
     * Given the custom classification groups(s) if any. Specify classificationGroups as a composite key made up of the
     * taxonomy identifier (tid) to which the code belongs and the classification group id (cgid) itself delimited by a
     * colon (:).
     * <p>
     * As an illustrative example, to consider eligibility for bop group retail the value would be bop:retail. More
     * than one classification group can be specified. In this event, use + as separator to include more than on. For
     * example bop:retail+bop:wholesale+wc:office.
     * <p>
     * In the event that this parameter is specified, products must be explicitly correlated by their owner to the
     * requested taxonomy to be considered to be eligible. In other words if the taxonomy is not linked to the product
     * by the owner it will not be eligible for this request.
     */
    private String classificationGroups;

    /**
     * Given the specified annual payroll amount if any. Usage annualPayroll=10000000
     */
    private Integer annualPayroll;

    /**
     * Given the specified annual revenue amount if any. Usage annualRevenue=50000000
     */
    private Integer annualRevenue;

    /**
     * Given the specified number of full time employees if any. Usage fullTimeEmployees=100
     */
    private Integer fullTimeEmployees;

    /**
     * Given the specified number of part time employees if any. Usage partTimeEmployees=24
     */
    private Integer partTimeEmployees;

    /**
     * Given the specified Total Insured Value (TIV) if any. Usage tiv=1000000
     */
    private Integer tiv;

    /**
     * Given the specified number of scheduled vehicles if any. Usage vehicles=10
     */
    private Integer vehicles;

    /**
     * Given the specified number of scheduled locations if any. Usage vehicles=5
     */
    private Integer locations;

    /**
     * Given the specified number of scheduled buildings if any. Usage buildings=2
     */
    private Integer buildings;

    /**
     * Given the specified square footage if any. Usage squareFootage=1200
     */
    private Integer squareFootage;

    /**
     * Given the specified building age in years if any. Usage buildingAge=200
     */
    private Integer buildingAge;

    /**
     * Given the specified number of years in business if any. Usage yearsInBusiness=5
     */
    private Integer yearsInBusiness;

    /**
     * Given the specified number of years operating experience in the industry if any. Usage yearsInIndustry=10
     */
    private Integer yearsInIndustry;


}
