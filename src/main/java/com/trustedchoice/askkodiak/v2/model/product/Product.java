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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trustedchoice.askkodiak.v2.model.Range;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * An Ask Kodiak product object.
 */
@lombok.Data
public class Product {

    /**
     * An object describing which business types are eligible for this product (e.g. 'C Corporation', 'Limited Liability
     * Company', etc.) Will be a value found in the ReferenceData/BusinessEntityTypes
     */
    private Map<String, Boolean> acceptableEntities;

    /**
     * Actions a user can take to get access to this product.
     */
    private Action actions;

    /**
     * An admitted product is one that follows guidelines set forth by the state, and is therefore licensed in the state
     * or country in which the insured’s exposure is located. If an admitted carrier becomes insolvent, the state
     * guarantee fund steps in to pay out claims and premium remuneration where applicable.
     */
    private Boolean admitted;

    /**
     * An object describing the min/max annual payroll of a company eligible for this product.
     */
    private Range annualPayroll;

    /**
     * An object describing the min/max annual revenue of a company eligible for this product.
     */
    private Range annualRevenue;

    /**
     * An object describing the min/max number of scheduled buildings eligible for this product.
     */
    private Range buildings;

    /**
     * An object describing the min/max building age eligible for this product.
     */
    private Range buildingAge;

    /**
     * A list of collateral associated with this product. Each individual piece of collateral is represented as an
     * object in the list.
     */
    private List<Collateral> collateral;

    /**
     * A list of strings describing coverages provided by this product.
     */
    private List<String> coverageType;

    /**
     * A free-form text description of this product.
     */
    private String description;

    /**
     * An object describing the min/max full and part time employees of a company eligible for this product.
     */
    private Employees employees;

    /**
     * A map describing the geographies in which this product is available.
     */
    private Map<String, Boolean> geos;

    /**
     * A list of free-form strings describing underwriting guidelines for this product.
     */
    private List<String> guidelines;

    /**
     * A list of free-form strings describing highlights for this product.
     * (e.g. "High limits", "Excellent Claims Service", etc.)
     */
    private List<String> highlights;

    /**
     * An object describing the min/max number of scheduled locations eligible for this product.
     */
    private Range locations;

    /**
     * Metadata about this product including information like when it was first created, last updated, or last indexed
     * for search.
     */
    private ProductMetadata meta;

    /**
     * How long must a business have been in operation to be eligible for this product?
     */
    private Integer minYearsInBusiness;

    /**
     * How much experience doing business in the industry must a company have to be eligible for this product?
     */
    private Integer minYearsInIndustry;

    /**
     * The NAIC code of the writing company for the product.
     */
    private String naic;

    /**
     * A list of notes represented as free-form strings that apply to this product based on the governing geos,
     * naicsCodes, or naicsGroups specified in the query.
     */
    private List<String> notes;

    /**
     * The name of this product.
     * For example, "Commercial Auto", or "Business Owners"
     */
    private String name;

    /**
     * The Ask Kodiak ID of the group that owns this product.
     * For more information about obtaining data about the group see Company/GetCompany.
     */
    private String ownerId;

    /**
     * The URL of the owners logo.
     */
    private String ownerLogo;

    /**
     * The Ask Kodiak group type of the owner. Will be one of carrier or agency.
     *
     * Not configured as an enum for forward compatibility.
     */
    private String ownerType;

    /**
     * The typical premium amounts associated with policies written for this product, represented as min and max values.
     */
    private Range premiumSize;

    /**
     * An object describing the min/max square footage eligible for this product.
     */
    private Range squareFootage;

    /**
     * An object describing the min/max total insured value eligible for this product.
     */
    private Range tiv;

    /**
     * An object describing the min/max number of scheduled vehicles eligible for this product.
     */
    private Range vehicles;

    /**
     * The unique ID of this product in Ask Kodiak.
     */
    private String id;

    /**
     * A map describing the eligibility profile of the product for any naicsGroups. Keys of this map are the naics code
     * hashes.
     */
    private Map<String, Boolean> eligibility;

    /**
     * URL to a logo for this product.
     */
    private String logo;

    /**
     * Tags, if any, which apply to this product under the request conditions. Object keys are the string value of the
     * tag.
     */
    private Tag tags;

    /**
     * An indicator that the product is eligible for the given request.
     */
    @JsonProperty("_eligible")
    private Boolean eligible;

    /**
     * A numeric measure of the product's relevance to the specific conditions of the request and overall level of
     * quality and completeness.
     */
    @JsonProperty("_score")
    private Integer score;

    /**
     * A map describing the eligibility profile of the product for any naicsGroups specified in the initial query. Keys
     * of this map are the codes, their values are a float representing the percentage of NAICS hashes in the group
     * which are eligible for the product.
     */
    @JsonProperty("_eligibilityForGroupFilters")
    private Map<String, Float> eligibilityForGroupFilters;

    /**
     * An object describing which business types are eligible for this product (e.g. 'C Corporation', 'Limited Liability
     * Company', etc.) Will be a value found in the ReferenceData/BusinessEntityTypes
     */
    public Optional<Map<String, Boolean>> getAcceptableEntities() {
          return Optional.ofNullable(acceptableEntities);
     }

    /**
     * Actions a user can take to get access to this product.
     */
    public Optional<Action> getActions() {
          return Optional.ofNullable(actions);
     }

    /**
     * An admitted product is one that follows guidelines set forth by the state, and is therefore licensed in the state
     * or country in which the insured’s exposure is located. If an admitted carrier becomes insolvent, the state
     * guarantee fund steps in to pay out claims and premium remuneration where applicable.
     */
    public Optional<Boolean> getAdmitted() {
          return Optional.ofNullable(admitted);
     }

    /**
     * An object describing the min/max annual payroll of a company eligible for this product.
     */
    public Optional<Range> getAnnualPayroll() {
          return Optional.ofNullable(annualPayroll);
     }

    /**
     * An object describing the min/max annual revenue of a company eligible for this product.
     */
    public Optional<Range> getAnnualRevenue() {
          return Optional.ofNullable(annualRevenue);
     }

    /**
     * An object describing the min/max number of scheduled buildings eligible for this product.
     */
    public Optional<Range> getBuildings() {
        return Optional.ofNullable(buildings);
    }

    /**
     * An object describing the min/max building age eligible for this product.
     */
    public Optional<Range> getBuildingAge() {
        return Optional.ofNullable(buildingAge);
    }

    /**
     * A list of collateral associated with this product. Each individual piece of collateral is represented as an
     * object in the list.
     */
    public Optional<List<Collateral>> getCollateral() {
          return Optional.ofNullable(collateral);
     }

    /**
     * A list of strings describing coverages provided by this product.
     */
    public Optional<List<String>> getCoverageType() {
          return Optional.ofNullable(coverageType);
     }

    /**
     * A free-form text description of this product.
     */
    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    /**
     * An object describing the min/max full and part time employees of a company eligible for this product.
     */
    public Optional<Employees> getEmployees() {
        return Optional.ofNullable(employees);
    }

    /**
     * A map describing the geographies in which this product is available.
     */
    public Optional<Map<String, Boolean>> getGeos() {
          return Optional.ofNullable(geos);
     }

    /**
     * A list of free-form strings describing underwriting guidelines for this product.
     */
    public Optional<List<String>> getGuidelines() {
          return Optional.ofNullable(guidelines);
     }

    /**
     * A list of free-form strings describing highlights for this product.
     * (e.g. "High limits", "Excellent Claims Service", etc.)
     */
    public Optional<List<String>> getHighlights() {
          return Optional.ofNullable(highlights);
     }

    /**
     * An object describing the min/max number of scheduled locations eligible for this product.
     */
    public Optional<Range> getLocations() {
        return Optional.ofNullable(locations);
    }

    /**
     * Metadata about this product including information like when it was first created, last updated, or last indexed
     * for search.
     */
    public Optional<ProductMetadata> getMeta() {
          return Optional.ofNullable(meta);
     }

    /**
     * How long must a business have been in operation to be eligible for this product?
     */
    public Optional<Integer> getMinYearsInBusiness() {
          return Optional.ofNullable(minYearsInBusiness);
     }

    /**
     * How much experience doing business in the industry must a company have to be eligible for this product?
     */
    public Optional<Integer> getMinYearsInIndustry() {
          return Optional.ofNullable(minYearsInIndustry);
     }

    /**
     * The NAIC code of the writing company for the product.
     */
    public Optional<String> getNaic() {
          return Optional.ofNullable(naic);
     }

    /**
     * A list of notes represented as free-form strings that apply to this product based on the governing geos,
     * naicsCodes, or naicsGroups specified in the query.
     */
    public Optional<List<String>> getNotes() {
          return Optional.ofNullable(notes);
     }

    /**
     * The name of this product.
     * For example, "Commercial Auto", or "Business Owners"
     */
    public Optional<String> getName() {
          return Optional.ofNullable(name);
     }

    /**
     * The Ask Kodiak ID of the group that owns this product.
     * For more information about obtaining data about the group see Company/GetCompany.
     */
    public Optional<String> getOwnerId() {
          return Optional.ofNullable(ownerId);
     }

    /**
     * The URL of the owners logo.
     */
    public Optional<String> getOwnerLogo() {
        return Optional.ofNullable(ownerLogo);
    }

    /**
     * The Ask Kodiak group type of the owner. Will be one of carrier or agency.
     *
     * Not configured as an enum for forward compatibility.
     */
    public Optional<String> getOwnerType() {
          return Optional.ofNullable(ownerType);
     }

    /**
     * The typical premium amounts associated with policies written for this product, represented as min and max values.
     */
    public Optional<Range> getPremiumSize() {
          return Optional.ofNullable(premiumSize);
     }

    /**
     * An object describing the min/max square footage eligible for this product.
     */
    public Optional<Range> getSquareFootage() {
        return Optional.ofNullable(squareFootage);
    }

    /**
     * An object describing the min/max total insured value eligible for this product.
     */
    public Optional<Range> getTiv() {
        return Optional.ofNullable(tiv);
    }

    /**
     * An object describing the min/max number of scheduled vehicles eligible for this product.
     */
    public Optional<Range> getVehicles() {
        return Optional.ofNullable(vehicles);
    }

    /**
     * The unique ID of this product in Ask Kodiak.
     */
    public Optional<String> getId() {
          return Optional.ofNullable(id);
     }

    /**
     * A map describing the eligibility profile of the product for any naicsGroups. Keys of this map are the naics code
     * hashes.
     */
    public Optional<Map<String, Boolean>> getEligibility() {
          return Optional.ofNullable(eligibility);
     }

    /**
     * A map describing the eligibility profile of the product for any naicsGroups specified in the initial query.
     * Keys of this map are the codes, their values are a float representing the percentage of NAICS hashes in the group
     * which are eligible for the product.
     */
    public Optional<Map<String, Float>> getEligibilityForGroupFilters() {
          return Optional.ofNullable(eligibilityForGroupFilters);
     }

    /**
     * URL to a logo for this product.
     */
    public Optional<String> getLogo() {
        return Optional.ofNullable(logo);
    }

    /**
     * An indicator that the product is eligible for the given request.
     */
    public Optional<Boolean> getEligible() {
        return Optional.ofNullable(eligible);
    }

    /**
     * A numeric measure of the product's relevance to the specific conditions of the request and overall level of
     * quality and completeness.
     */
    public Optional<Integer> getScore() {
        return Optional.ofNullable(score);
    }

    /**
     * Tags, if any, which apply to this product under the request conditions. Object keys are the string value of the
     * tag.
     */
    public Optional<Tag> getTags() {
        return Optional.ofNullable(tags);
    }

}
