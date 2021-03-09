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

import java.util.Optional;

/**
 * A SIC match for the NAICS
 */
@lombok.Data
public class SicMapping {
    /**
     * The title of the SIC
     */
    private String title;

    /**
     * The numeric code value of the SIC
     */
    private Integer code;

    /**
     * A URL at which more information about the SIC code can be found
     */
    private String url;

    /**
     * The numeric industry group code (parent) of the SIC
     */
    private Integer industryGroupCode;

    /**
     * The title of the industry group (parent) of the SIC
     */
    private String industryGroupTitle;

    /**
     * The numeric industry group code (grandparent) of the SIC
     */
    private Integer majorGroupCode;

    /**
     * The title of the industry group (grandparent) of the SIC
     */
    private String majorGroupTitle;

    /**
     * The alpha division code of the SIC
     */
    private String divisionCode;

    /**
     * The title of the division in which this SIC code resides
     */
    private String divisionTitle;

    /**
     * The title of the SIC
     */
    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    /**
     * The numeric code value of the SIC
     */
    public Optional<Integer> getCode() {
        return Optional.ofNullable(code);
    }

    /**
     * A URL at which more information about the SIC code can be found
     */
    public Optional<String> getUrl() {
        return Optional.ofNullable(url);
    }

    /**
     * The numeric industry group code (parent) of the SIC
     */
    public Optional<Integer> getIndustryGroupCode() {
        return Optional.ofNullable(industryGroupCode);
    }

    /**
     * The title of the industry group (parent) of the SIC
     */
    public Optional<String> getIndustryGroupTitle() {
        return Optional.ofNullable(industryGroupTitle);
    }

    /**
     * The numeric industry group code (grandparent) of the SIC
     */
    public Optional<Integer> getMajorGroupCode() {
        return Optional.ofNullable(majorGroupCode);
    }

    /**
     * The title of the industry group (grandparent) of the SIC
     */
    public Optional<String> getMajorGroupTitle() {
        return Optional.ofNullable(majorGroupTitle);
    }

    /**
     * The alpha division code of the SIC
     */
    public Optional<String> getDivisionCode() {
        return Optional.ofNullable(divisionCode);
    }

    /**
     * The title of the division in which this SIC code resides
     */
    public Optional<String> getDivisionTitle() {
        return Optional.ofNullable(divisionTitle);
    }

}
