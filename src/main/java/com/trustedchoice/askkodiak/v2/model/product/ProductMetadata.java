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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trustedchoice.askkodiak.v2.model.TimestampUidPair;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Metadata about a product including information like when it was first created, last updated, or last indexed for
 * search.
 */
@lombok.Data
public class ProductMetadata {

    /**
     * An object containing data about the level of completeness of this product.
     */
    private ProductCompleteness completeness;

    /**
     * If this product was originally created as a duplicate of another product, this field will contain the id of that
     * source product.
     */
    private String copiedFrom;

    /**
     * An object containing data about the creation of this product.
     */
    private TimestampUidPair created;

    /**
     * A timestamp representing the time this product was last indexed by the Ask Kodiak search engine.
     */
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT, pattern="s")
    private Timestamp lastIndexed;

    /**
     * An object containing data about the last update of this product.
     */
    private TimestampUidPair lastUpdated;

    /**
     * A map containing information about who it is shared with.
     */
    private Map<String, Boolean> permissions;

    /**
     * Is this product shared with staff of it's owner group?
     */
    private Boolean staff;

    /**
     * Is this product shared with companies trusted by it's owner group?
     */
    private Boolean trusted;

    /**
     * Is this product shared with companies not trusted by it's owner group?
     */
    private Boolean untrusted;

    /**
     * Conditional rule statistics for the product.
     */
    @JsonProperty("rule-stats")
    private ProductRuleStats ruleStats;

    /**
     * A map describing NAICS hashes eligible for this product.
     * For a complete list of hashes or to disambiguate an individual hash, see the NAICS interfaces.
     */
    private Map<String, Boolean> eligibility;

    /**
     * A list of tags for this product.
     */
    private List<Tag> tags;

    /**
     * An object containing data about the level of completeness of this product.
     */
    public Optional<ProductCompleteness> getCompleteness() {
          return Optional.ofNullable(completeness);
     }

    /**
     * If this product was originally created as a duplicate of another product, this field will contain the id of that
     * source product.
     */
    public Optional<String> getCopiedFrom() {
          return Optional.ofNullable(copiedFrom);
     }

    /**
     * An object containing data about the creation of this product.
     */
    public Optional<TimestampUidPair> getCreated() {
          return Optional.ofNullable(created);
     }

    /**
     * A timestamp representing the time this product was last indexed by the Ask Kodiak search engine.
     */
    public Optional<Timestamp> getLastIndexed() {
          return Optional.ofNullable(lastIndexed);
     }

    /**
     * An object containing data about the last update of this product.
     */
    public Optional<TimestampUidPair> getLastUpdated() {
          return Optional.ofNullable(lastUpdated);
     }

    /**
     * A map containing information about who it is shared with.
     */
    public Optional<Map<String, Boolean>> getPermissions() {
        return Optional.ofNullable(permissions);
    }

    /**
     * Is this product shared with staff of it's owner group?
     */
    public Optional<Boolean> getStaff() {
          return Optional.ofNullable(staff);
     }

    /**
     * Is this product shared with companies trusted by it's owner group?
     */
    public Optional<Boolean> getTrusted() {
          return Optional.ofNullable(trusted);
     }

    /**
     * Is this product shared with companies not trusted by it's owner group?
     */
    public Optional<Boolean> getUntrusted() {
          return Optional.ofNullable(untrusted);
     }

    /**
     * Conditional rule statistics for the product.
     */
    public Optional<ProductRuleStats> getRuleStats() {
          return Optional.ofNullable(ruleStats);
     }

    /**
     * A map describing NAICS hashes eligible for this product.
     * For a complete list of hashes or to disambiguate an individual hash, see the NAICS interfaces.
     */
    public Optional<Map<String, Boolean>> getEligibility() {
        return Optional.ofNullable(eligibility);
    }

}
