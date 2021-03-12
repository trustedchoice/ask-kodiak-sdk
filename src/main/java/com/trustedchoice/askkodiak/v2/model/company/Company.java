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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * An Ask Kodiak Company object.
 */
@lombok.Data
public class Company {

    /**
     * The unique group id (gid) of this company on Ask Kodiak.
     */
    private String id;

    /**
     * A description of the company as authored by them.
     */
    private String description;

    /**
     * Timestamp of when this company joined Ask Kodiak.
     */
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT, pattern = "s")
    private Timestamp joined;

    /**
     * A description of where the business is located.
     */
    private String location;

    /**
     * URL to a logo for the company.
     */
    private String logo;

    /**
     * The name of the company.
     */
    private String name;

    /**
     * The phone number of the company.
     */
    private String phone;

    /**
     * A list of product ids that belong to this company.
     */
    private List<String> products;

    /**
     * Preferred colors and theme properties for this company.
     */
    private Map<String, String> theme;

    /**
     * The main URL associated with this company.
     */
    private String website;

    /**
     * Is the company a carrier?
     */
    private Boolean isCarrier;

    /**
     * AM Best rating information for the company.
     */
    private AmBestRating amBest;

    /**
     * The NAIC Number of the company.
     */
    private String naic;

    /**
     * A shorthand nickname for the company, used in it's Ask Kodiak URL.
     */
    private String shortname;

    /**
     * The URL of the storefront of the company on Ask Kodiak if it has one.
     */
    private String storefrontURL;

    /**
     * The number of products in the products response for this company.
     */
    @JsonProperty("_numEligible")
    private Integer numEligible;

    /**
     * The number of products in the products response for this company that were able to be categorized in a defined
     * productGrouping.
     */
    @JsonProperty("_numEligibleGrouped")
    private Integer numEligibleGrouped;

    /**
     * The number of products in the products response for this company that were not able to be categorized in a
     * defined productGrouping.
     */
    @JsonProperty("_numEligibleNotGrouped")
    private Integer numEligibleNotGrouped;

    /**
     * The total number of products in the products response array.
     */
    @JsonProperty("_numProducts")
    private Integer numProducts;

    /**
     * The cumulative score of all products for this company returned in the response.
     */
    @JsonProperty("_score")
    private Integer score;

    /**
     * An indicator if this is a "Virtual" company created to represent a subset of products maintained in your own Ask
     * Kodiak account.
     */
    @JsonProperty("_virtual")
    private Boolean virtual;

    /**
     * The unique group id (gid) of this company on Ask Kodiak.
     */
    public Optional<String> getId() {
        return Optional.ofNullable(id);
    }

    /**
     * A description of the company as authored by them.
     */
    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    /**
     * Timestamp of when this company joined Ask Kodiak.
     */
    public Optional<Timestamp> getJoined() {
        return Optional.ofNullable(joined);
    }

    /**
     * A description of where the business is located.
     */
    public Optional<String> getLocation() {
        return Optional.ofNullable(location);
    }

    /**
     * URL to a logo for the company.
     */
    public Optional<String> getLogo() {
        return Optional.ofNullable(logo);
    }

    /**
     * The name of the company.
     */
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    /**
     * The phone number of the company.
     */
    public Optional<String> getPhone() {
        return Optional.ofNullable(phone);
    }

    /**
     * A list of product ids that belong to this company.
     */
    public Optional<List<String>> getProducts() {
        return Optional.ofNullable(products);
    }

    /**
     * Preferred colors and theme properties for this company.
     */
    public Optional<Map<String, String>> getTheme() {
        return Optional.ofNullable(theme);
    }

    /**
     * The main URL associated with this company.
     */
    public Optional<String> getWebsite() {
        return Optional.ofNullable(website);
    }

    /**
     * Is the company a carrier?
     */
    public Optional<Boolean> isCarrier() {
        return Optional.ofNullable(isCarrier);
    }

    /**
     * AM Best rating information for the company.
     */
    public Optional<AmBestRating> getAmBest() {
        return Optional.ofNullable(amBest);
    }

    /**
     * The NAIC Number of the company.
     */
    public Optional<String> getNaic() {
        return Optional.ofNullable(naic);
    }

    /**
     * A shorthand nickname for the company, used in it's Ask Kodiak URL.
     */
    public Optional<String> getShortname() {
        return Optional.ofNullable(shortname);
    }

    /**
     * The URL of the storefront of the company on Ask Kodiak if it has one.
     */
    public Optional<String> getStorefrontURL() {
        return Optional.ofNullable(storefrontURL);
    }

    /**
     * The number of products in the products response for this company.
     */
    public Optional<Integer> getNumEligible() {
        return Optional.ofNullable(numEligible);
    }

    /**
     * The number of products in the products response for this company that were able to be categorized in a defined
     * productGrouping.
     */
    public Optional<Integer> getNumEligibleGrouped() {
        return Optional.ofNullable(numEligibleGrouped);
    }

    /**
     * The number of products in the products response for this company that were not able to be categorized in a
     * defined productGrouping.
     */
    public Optional<Integer> getNumEligibleNotGrouped() {
        return Optional.ofNullable(numEligibleNotGrouped);
    }

    /**
     * The total number of products in the products response array.
     */
    public Optional<Integer> getNumProducts() {
        return Optional.ofNullable(numProducts);
    }

    /**
     * The cumulative score of all products for this company returned in the response.
     */
    public Optional<Integer> getScore() {
        return Optional.ofNullable(score);
    }

    /**
     * An indicator if this is a "Virtual" company created to represent a subset of products maintained in your own Ask
     * Kodiak account.
     */
    public Optional<Boolean> getVirtual() {
        return Optional.ofNullable(virtual);
    }

}
