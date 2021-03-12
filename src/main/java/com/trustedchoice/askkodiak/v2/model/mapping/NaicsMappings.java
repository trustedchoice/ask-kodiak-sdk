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

package com.trustedchoice.askkodiak.v2.model.mapping;

import java.util.List;

@lombok.Data
public class NaicsMappings {
    /**
     * A text description of the SIC identifier provided on the request
     */
    private String title;

    /**
     * A text description of the SIC identifier provided on the request
     */
    private Integer code;

    /**
     * A URL at which more information about the SIC code can be found
     */
    private String url;

    /**
     * The industry group number to which the request SIC belongs
     */
    private Integer industryGroupCode;

    /**
     * The title of the industry group to which the request SIC belongs
     */
    private String industryGroupTitle;

    /**
     * The major group number to which the request SIC belongs
     */
    private Integer majorGroupCode;

    /**
     * The title of the major group to which the request SIC belongs
     */
    private String majorGroupTitle;

    /**
     * The division code to which the string belongs
     */
    private String divisionCode;

    /**
     * The title of the division to which the request SIC belongs
     */
    private String divisionTitle;

    /**
     * The number of NAICS mappings matched to the requested SIC
     */
    private Integer count;

    /**
     * A list of NAICS objects which map to the requested SIC
     */
    private List<NaicsMapping> mappings;
}
