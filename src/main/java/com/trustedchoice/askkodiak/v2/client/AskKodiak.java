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

package com.trustedchoice.askkodiak.v2.client;

import com.trustedchoice.askkodiak.v2.model.company.Companies;
import com.trustedchoice.askkodiak.v2.model.company.Company;
import com.trustedchoice.askkodiak.v2.model.naics.NaicsCode;
import com.trustedchoice.askkodiak.v2.model.naics.NaicsDescription;
import com.trustedchoice.askkodiak.v2.model.naics.NaicsGroup;
import com.trustedchoice.askkodiak.v2.model.product.NaicsCodeEligibility;
import com.trustedchoice.askkodiak.v2.model.product.NaicsEligibility;
import com.trustedchoice.askkodiak.v2.model.product.Product;
import com.trustedchoice.askkodiak.v2.model.product.Products;
import com.trustedchoice.askkodiak.v2.model.suggest.NaicsCodeSuggestions;
import com.trustedchoice.askkodiak.v2.model.suggest.NaicsGroupSuggestions;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

import java.util.Map;

/**
 * The Ask Kodiak REST API has semantic, resource-oriented URLs, and uses HTTP codes to signify API response status. In
 * addition to standard HTTP response codes, we use features like HTTP authentication and HTTP verbs, so that just about
 * any HTTP client can be used to interface with the API. JSON is returned by all API responses, even errors.
 */
@Headers({ "Content-Type: application/json", "Accept: application/json" })
public interface AskKodiak {

    /////////////////////////
    // Products APIs
    // https://api.askkodiak.com/doc/v2/#api-Products
    /////////////////////////

    /**
     * Get products eligible a given NAICS code. code can be any valid 2017 NAICS edition:
     * <ol>
     * <li>Sector : A NAICS Sector Code. Generally a 2 digit numeric value, but occasionally a hyphenated range for
     * certain sectors. (e.g '11' or '44-45', representing Agriculture, Forestry, Fishing and Hunting and Retail Trade
     * respectively). See the NAICS/GetSectors interface for a full list.</li>
     * <li>Subsector : A 3 digit numeric NAICS Subsector code (e.g. '531', representing Real Estate)</li>
     * <li>National Industry : A 4 digit numeric NAICS National Industry Code (e.g. '3361', representing Motor Vehicle
     * Manufacturing)</li>
     * <li>International Industry : A 5 digit NAICS International Industry code (e.g. '11331', representing
     * Logging)</li>
     * <li>National Industry : A 6 digit NAICS National Industry code (e.g. '312120', representing Breweries)</li>
     * <li>Hash : An MD5 Hash of a six-digit National Industry code and sub-description (e.g.
     * '03da921f2510c7ab4e47dcd1f9061264', the calculated MD5 hash of 713990Miniature golf courses). This is the most
     * precise eligibility request that can be made. Please note, valid 2012 NAICS edition hashes will also be accepted,
     * although they will be mapped to their 2017 equivalent. The mapped hash will be included in the response payload.
     * The superset of pre-calculated hashes are available from the NAICS/GetCodes interface.</li>
     * </ol>
     * <p>
     * The API account acts as a member of your 'staff'. Products available to this user group will be included in
     * results.
     * <p>
     * In the event that the code requested is anything other than an MD5 hash of six-digit National Industry code +
     * sub-description, products with any eligibility whatsoever in the requested NAICS group will be included in the
     * response. Each product in the results is extended with a property representing the percentage of codes in the
     * requested group for which the product is eligible.
     *
     * @param code Any valid 2017 NAICS Code (at any level of the NAICS hierarchy) or MD5 Hash (6 Digit NAICS National
     *             Industry Code + Sub-Description). See above for a more detailed description of possible values.
     *             Conditional rules which pertain to the specified code will be applied to the results.
     * @return Eligible products
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/products/class-code/naics/{code}")
    Products getProductsEligibleForCode(@Param("code") String code) throws AskKodiakException;

    /**
     * Get products eligible a given NAICS code. code can be any valid 2017 NAICS edition:
     * <ol>
     * <li>Sector : A NAICS Sector Code. Generally a 2 digit numeric value, but occasionally a hyphenated range for
     * certain sectors. (e.g '11' or '44-45', representing Agriculture, Forestry, Fishing and Hunting and Retail Trade
     * respectively). See the NAICS/GetSectors interface for a full list.</li>
     * <li>Subsector : A 3 digit numeric NAICS Subsector code (e.g. '531', representing Real Estate)</li>
     * <li>National Industry : A 4 digit numeric NAICS National Industry Code (e.g. '3361', representing Motor Vehicle
     * Manufacturing)</li>
     * <li>International Industry : A 5 digit NAICS International Industry code (e.g. '11331', representing
     * Logging)</li>
     * <li>National Industry : A 6 digit NAICS National Industry code (e.g. '312120', representing Breweries)</li>
     * <li>Hash : An MD5 Hash of a six-digit National Industry code and sub-description (e.g.
     * '03da921f2510c7ab4e47dcd1f9061264', the calculated MD5 hash of 713990Miniature golf courses). This is the most
     * precise eligibility request that can be made. Please note, valid 2012 NAICS edition hashes will also be accepted,
     * although they will be mapped to their 2017 equivalent. The mapped hash will be included in the response payload.
     * The superset of pre-calculated hashes are available from the NAICS/GetCodes interface.</li>
     * </ol>
     * <p>
     * The API account acts as a member of your 'staff'. Products available to this user group will be included in
     * results.
     * <p>
     * In the event that the code requested is anything other than an MD5 hash of six-digit National Industry code +
     * sub-description, products with any eligibility whatsoever in the requested NAICS group will be included in the
     * response. Each product in the results is extended with a property representing the percentage of codes in the
     * requested group for which the product is eligible.
     *
     * @param code Any valid 2017 NAICS Code (at any level of the NAICS hierarchy) or MD5 Hash (6 Digit NAICS National
     *             Industry Code + Sub-Description). See above for a more detailed description of possible values.
     *             Conditional rules which pertain to the specified code will be applied to the results.
     * @param query Query parameters
     * @return Eligible products
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/products/class-code/naics/{code}")
    Products getProductsEligibleForCode(@Param("code") String code, @QueryMap EligibleQuery query) throws AskKodiakException;

    /**
     * Get products available to your group that are owned by the company represented by the specified group id (gid).
     * <p>
     * The API account acts as a member of your 'staff'. Products available to this user group will be included in
     * results. For a comprehensive set of interfaces to acquire your own products regardless of permission, see
     * Admin/Products
     *
     * @param gid The group id of the company on Ask Kodiak.
     * @return Company products
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/products/company/{gid}")
    Products getProductsForCompany(@Param("gid") String gid) throws AskKodiakException;

    /**
     * Get products available to your group that are owned by the company represented by the specified group id (gid).
     * <p>
     * The API account acts as a member of your 'staff'. Products available to this user group will be included in
     * results. For a comprehensive set of interfaces to acquire your own products regardless of permission, see
     * Admin/Products
     *
     * @param gid The group id of the company on Ask Kodiak.
     * @param query Query parameters
     * @return Company products
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/products/company/{gid}")
    Products getProductsForCompany(@Param("gid") String gid, @QueryMap CompanyQuery query) throws AskKodiakException;

    /**
     * Get all products available to the requesting user that match the request parameters (if any).
     *
     * @param query Query parameters
     * @return User products
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/products/user")
    Products getProductsForUser(@QueryMap UserQuery query) throws AskKodiakException;

    /////////////////////////
    // Product APIs
    // https://api.askkodiak.com/doc/v2/#api-Product
    /////////////////////////

    /**
     * Retrieve the details of a product using it's id.
     *
     * @param id The id of the product requested.
     * @return Product details
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/product/{id}")
    Product getProduct(@Param("id") String id) throws AskKodiakException;

    /**
     * Retrieve the details of a product using it's id.
     *
     * @param id The id of the product requested.
     * @param query Query parameters
     * @return Product details
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/product/{id}")
    Product getProduct(@Param("id") String id, @QueryMap ProductQuery query) throws AskKodiakException;

    /**
     * Get the eligibility of a product at any level (e.g. sector, subsector, industry-group, international-industry, or
     * national-industry). Returns an object representing which groups of the requested type the product is eligible for
     * and what percentage of the group is eligible.
     *
     * @param id The id of the product requested.
     * @param type Any one of the following: sector, subsector, industry-group, international-industry, or
     *             national-industry
     * @return The groups of the requested type that the product is eligible for.
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/product/{id}/eligibility-by-naics-type/{type}")
    Map<String, NaicsEligibility> getEligibilityByNaicsType(
            @Param("id") String id,
            @Param("type") String type) throws AskKodiakException;

    /**
     * Get the eligibility of a product for any valid 2-6 digit NAICS code or computed NAICS Hash combining 6 digit code
     * and sub-description.
     *
     * @param id The id of the product requested.
     * @param code Any one of the following:
     *             <ul>
     *             <li>
     *             A NAICS Sector : Generally a 2 digit numeric value, but occasionally a hyphenated range for certain
     *                              sectors. (e.g 11 or 44-45, representing Agriculture, Forestry, Fishing and Hunting
     *                              and Retail Trade respectively). See the NAICS/GetSectors interface for a full list.
     *             <li>
     *             A NAICS Subsector : A 3 digit numeric NAICS Subsector code (e.g. 531, representing Real Estate)
     *             <li>
     *             A NAICS National Industry : A 4 digit numeric NAICS National Industry Code (e.g. 3361, representing
     *                                         Motor Vehicle Manufacturing)
     *             <li>
     *             A NAICS International Industry : A 5 digit NAICS International Industry code (e.g. 11331,
     *                                              representing Logging)
     *             <li>
     *             A NAICS National Industry : A 6 digit NAICS National Industry code (e.g. 312120, representing
     *                                         Breweries)
     *             <li>
     *             A NAICS Hash : An MD5 Hash of a six-digit National Industry code and sub-description (e.g.
     *                            03da921f2510c7ab4e47dcd1f9061264, the calculated MD5 hash of 713990Miniature golf
     *                            courses). This is the most precise eligibility request that can be made. Please note,
     *                            valid 2012 NAICS edition hashes will also be accepted, although they will be mapped to
     *                            their 2017 equivalent. The superset of pre-calculated hashes are available from the
     *                            NAICS/GetCodes interface.
     *             </ul>
     * @return Eligibility response
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/product/{id}/is-eligible-for/{code}")
    NaicsCodeEligibility getEligibilityByCode(
            @Param("id") String id,
            @Param("code") String code) throws AskKodiakException;

    /////////////////////////
    // Suggest APIs
    // https://api.askkodiak.com/doc/v2/#api-Suggest
    /////////////////////////

    /**
     * Get suggested hashes associated with a search term. Term should be a string, that may describe a business, a
     * NAICS code, or a SIC Code. This interface is designed to be used in a 'suggest' control or search model where the
     * user is attempting to classify a risk.
     * <p>
     * This interface provides the highest level of specificity as it pertains to classification of a business. It is
     * recommended for that purpose over lower precision interfaces in this API group.
     *
     * @param term The text to search in the index.
     * @return Suggested hashes
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/suggest/naics-codes/{term}")
    NaicsCodeSuggestions getSuggestedNaicsCodes(@Param("term") String term) throws AskKodiakException;

    /**
     * Get suggested hashes associated with a search term. Term should be a string, that may describe a business, a
     * NAICS code, or a SIC Code. This interface is designed to be used in a 'suggest' control or search model where the
     * user is attempting to classify a risk.
     * <p>
     * This interface provides the highest level of specificity as it pertains to classification of a business. It is
     * recommended for that purpose over lower precision interfaces in this API group.
     *
     * @param term The text to search in the index.
     * @param query Query parameters
     * @return Suggested hashes
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/suggest/naics-codes/{term}")
    NaicsCodeSuggestions getSuggestedNaicsCodes(
            @Param("term") String term,
            @QueryMap SuggestQuery query) throws AskKodiakException;

    /**
     * Get suggested NAICS 2-6 digit groups for a search term. Term can equal a typo-tolerant string, or NAICS code.
     * This interface is designed to be used in a 'suggest' control or search model where the user is attempting to
     * classify a risk.
     * <p>
     * This interface will provide suggestions for 2-6 digit NAICS groups. For the highest level of specificity in
     * classification it is recommended that the Suggest/NAICSCodes interface be used.
     *
     * @param term The text to search in the index.
     * @return Suggested NAICS groups
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/suggest/naics-groups/{term}")
    NaicsGroupSuggestions getSuggestedNaicsGroups(@Param("term") String term) throws AskKodiakException;

    /**
     * Get suggested NAICS 2-6 digit groups for a search term. Term can equal a typo-tolerant string, or NAICS code.
     * This interface is designed to be used in a 'suggest' control or search model where the user is attempting to
     * classify a risk.
     * <p>
     * This interface will provide suggestions for 2-6 digit NAICS groups. For the highest level of specificity in
     * classification it is recommended that the Suggest/NAICSCodes interface be used.
     *
     * @param term The text to search in the index.
     * @param query Query parameters
     * @return Suggested NAICS groups
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/suggest/naics-groups/{term}")
    NaicsGroupSuggestions getSuggestedNaicsGroups(
            @Param("term") String term,
            @QueryMap SuggestQuery query) throws AskKodiakException;

    /////////////////////////
    // Company APIs
    // https://api.askkodiak.com/doc/v2/#api-Company
    /////////////////////////

    /**
     * Get the basic information about a company in Ask Kodiak, including their name, website, and other descriptive
     * information as available. Heads up, the properties on the object returned vary depending on the type of company
     * it is. See the examples below for the differences.
     *
     * @param gid The group id of the company on Ask Kodiak.
     * @return An Ask Kodiak Company object.
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/company/{gid}")
    Company getCompany(@Param("gid") String gid) throws AskKodiakException;

    /**
     * Get the basic information about companies with storefronts on Ask Kodiak, including their name, website, and
     * other descriptive information as available.
     * <p>
     * For purposes of this interface the term other includes any non-carrier entity with an Ask Kodiak storefront. This
     * could include MGAs, Program Writers, Aggregators, and etc.
     *
     * @return Companies
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/companies")
    Companies getCompanies() throws AskKodiakException;

    /**
     * Get the basic information about companies with storefronts on Ask Kodiak, including their name, website, and
     * other descriptive information as available.
     * <p>
     * For purposes of this interface the term other includes any non-carrier entity with an Ask Kodiak storefront. This
     * could include MGAs, Program Writers, Aggregators, and etc.
     *
     * @param query Query parameters
     * @return Companies
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/companies")
    Companies getCompanies(@QueryMap CompaniesQuery query) throws AskKodiakException;

    /////////////////////////
    // NAICS APIs
    // https://api.askkodiak.com/doc/v2/#api-NAICS
    /////////////////////////

    /**
     * Decode a NAICS MD5 hash into the 6 digit naics code and sub-description it represents.
     *
     * @param hash The MD5 hash to look up.
     * @return An object containing a numerical code and text description associated with the hash.
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/naics/code/{hash}")
    NaicsCode getCode(@Param("hash") String hash) throws AskKodiakException;

    /**
     * Get all computed NAICS hashes. These are 6 Digit National Industry Codes (the lowest NAICS level) plus the
     * sub-descriptions for the code.
     * <p>
     * As an illustrative example 03da921f2510c7ab4e47dcd1f9061264 is the calculated MD5 hash of 713990Miniature golf
     * courses and allows for differentiation between other sub-descriptions, of which 713990 has 58, ranging from golf
     * to hockey, to kayaking.
     * <p>
     * As a primary search index, these hashes can be used in subsequent calls to check eligibility for a business
     * description. This list is fairly static and changes only with new releases of NAICS every five years. It is
     * generally safe thusly to cache a copy for best performance.
     * <p>
     * For more on NAICS, visit: http://www.census.gov/eos/www/naics/
     *
     * @return A map keyed by hash containing values of all 6-digit NAICS codes plus their descriptions.
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/naics/codes")
    Map<String, NaicsCode> getCodes() throws AskKodiakException;

    /**
     * Get a long form text description for a NAICS group where available. Be aware, descriptions are not available for
     * all NAICS groups.
     *
     * @param groupNumber The Number of the NAICS group for which a description is desired.
     * @return An object containing a description for the requested group.
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/naics/description/{gid}")
    NaicsDescription getDescription(@Param("gid") int groupNumber) throws AskKodiakException;

    /**
     * Get any given NAICS group using its numerical group number. Heads up, treat group numbers as strings - at the
     * sector level there are clustered groups (e.g. 31-33, 44-45, and 48-49).
     *
     * @param groupNumber The Number of the requested NAICS group.
     * @return An object containing the detail associated with the requested group.
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/naics/group/{groupNumber}")
    NaicsGroup getGroup(@Param("groupNumber") String groupNumber) throws AskKodiakException;

    /**
     * Get a list of business entity types for use decoding the coded values associated with a product. These are
     * updated on occasion, so while it's advised that you cache the results for reference, do make sure and check for
     * changes every now and again.
     *
     * @return A map containing all business entity type key-value pairs.
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/ref-data/business-entity-types")
    Map<String, String> getBusinessEntityTypes() throws AskKodiakException;

    /////////////////////////
    // Products API query models
    // https://api.askkodiak.com/doc/v2/#api-Products
    /////////////////////////

    @lombok.Builder
    @lombok.Getter
    @lombok.Setter
    class EligibleQuery {
        /**
         * Filters response to include only products belonging to the requested owner(s). Use + as separator to include more
         * than company id. Usage owners=ABC123 or owners=ABC123+DEF456. A filter of owners=ABC123+DEF456 means products
         * owned by group with id ABC123 OR products owned by group with id DEF456.
         */
        String owners;

        /**
         * Request that eligible hashes be included on products in the response. Potentially increases response payload
         * significantly, especially in those cases where the results include a large number of products. If this parameter
         * is missing, excluded, or set to false eligible classes will not be present on products in the response.
         */
        Boolean includeEligibility;

        /**
         * Request that only summary information be returned for products in the response including (if available on
         * resultant products) name, ownerId, id, coverageType, and logo. If this parameter is missing, excluded, or set to
         * false all available properties will be present on products in the response. Please Note: in the event that
         * summaryOnly is set to true, the includeEligibility parameter will be forced to false.
         */
        Boolean summaryOnly;

        /**
         * Filter response to include only products available in requested geography(s) and apply any conditional rules
         * which pertain to them. Use + as separator to include more than 1 ISO 3166-2 code. Usage geos=US-HI or
         * geos=US-MN+CA-ON. A filter of geos=US-MN+US-HI means products available in Minnesota OR Hawaii.
         */
        String geos;

        /**
         * Filter response to include only products matching the requested coverage or coverages. Use + as separator to
         * include more than 1 product code. Usage productCodes=BOP or productCodes=BOP+WORK. A filter of
         * productCodes=BOP+WORK means products that have BOP OR Work Comp coverage.
         */
        String productCodes;

        /**
         * Filters response to include only products expressly eligible for a given entity type or types. Use + as separator
         * to include more than 1 entity type code. Usage entityTypes=AS or entityTypes=AS+CCORP. A filter of
         * entityTypes=AS+CCORP means include products eligible to either 'Association' or 'C-Corporation' entity types. A
         * full list of valid entity types can be attained from ReferenceData/BusinessEntityTypes
         */
        String entityTypes;

        /**
         * Filters response to include only products matching the requested tag or tags. Use + as separator to include more
         * than 1 tag. Usage tags=external-website or tags=external-website+internal-portal. A filter of
         * tags=external-website+internal-portal+marketABC123 means all products which have either the tag external-website,
         * internal-portal, or marketABC123.
         */
        String tags;

        /**
         * Filters response to include only products with expressly stated eligibility for a given annual payroll amount.
         * Usage annualPayroll=10000000
         */
        String annualPayroll;

        /**
         * Filters response to include only products with expressly stated eligibility for a given annual revenue amount.
         * Usage annualRevenue=50000000
         */
        String annualRevenue;

        /**
         * Filters response to include only products in the specified premium range or amount. If specifying a range, min
         * and max values should be separated with a dash as shown in the example. Usage anticipatedPremium=1000 or
         * anticipatedPremium=1000-10000.
         */
        String anticipatedPremium;

        /**
         * Filters response to include only products with expressly stated eligibility for a given number of full time
         * employees. Usage fullTimeEmployees=100
         */
        String fullTimeEmployees;

        /**
         * Filters response to include only products with expressly stated eligibility for a given number of part time
         * employees. Usage partTimeEmployees=24
         */
        String partTimeEmployees;

        /**
         * Filters response to include only products with expressly stated eligibility for entities who have been in
         * business for a given amount time. Usage yearsInBusiness=5
         */
        String yearsInBusiness;

        /**
         * Filters response to include only products with expressly stated eligibility for entities who have a given amount
         * of operating experience in the industry. Usage yearsInIndustry=10
         */
        String yearsInIndustry;

        /**
         * Limit the response to a maximum number of products.
         */
        Integer productsPerPage;

        /**
         * Get specific page of results. Page numbers are zero-based, so to retrieve the 6th page, you should set page=5.
         * If productsPerPage is not specified all products will be included in the results.
         */
        Integer page;

    }

    @lombok.Builder
    @lombok.Getter
    @lombok.Setter
    class CompanyQuery {

        /**
         * Filter response to include only products with eligibility for a specific 2-6 digit NAICS group or groups and
         * apply any conditional rules which pertain to them. Use + as separator to include more than one naics group.
         * Usage naicsGroups=722514 or naicsGroups=44-45+722515.
         */
        String naicsGroups;

        /**
         * Filter response to include only products with eligibility for a specific hash or hashes and apply any
         * conditional rules which pertain to them. Use + as separator to include more than one hash. Usage
         * naicsCodes=9d709a5f8cefe02c3ba71bdd3a4c3e28 or
         * naicsCodes=9d709a5f8cefe02c3ba71bdd3a4c3e28+4797cab0bb586ec0a98da773878ef97d.
         */
        String naicsCodes;

        /**
         * Request that eligible hashes be included on products in the response. Potentially increases response payload
         * significantly, especially in those cases where the results include a large number of products. If this
         * parameter is missing, excluded, or set to false eligible classes will not be present on products in the
         * response.
         */
        Boolean includeEligibility;

        /**
         * Request that only summary information be returned for products in the response including (if available on
         * resultant products) name, ownerId, id, coverageType, and logo. If this parameter is missing, excluded, or set
         * to false all available properties will be present on products in the response. Please Note: in the event that
         * summaryOnly is set to true, the includeEligibility parameter will be forced to false.
         */
        Boolean summaryOnly;

        /**
         * Filter response to include only products available in requested geography(s) and apply any conditional rules
         * which pertain to them. Use + as separator to include more than 1 ISO 3166-2 code. Usage geos=US-HI or
         * geos=US-MN+CA-ON. A filter of geos=US-MN+US-HI means products available in Minnesota OR Hawaii.
         */
        String geos;

        /**
         * Filter response to include only products matching the requested coverage or coverages. Use + as separator to
         * include more than 1 product code. Usage productCodes=BOP or productCodes=BOP+WORK. A filter of
         * productCodes=BOP+WORK means products that have BOP OR Work Comp coverage.
         */
        String productCodes;

        /**
         * Filters response to include only products expressly eligible for a given entity type or types. Use + as
         * separator to include more than 1 entity type code. Usage entityTypes=AS or entityTypes=AS+CCORP. A filter of
         * entityTypes=AS+CCORP means include products eligible to either 'Association' or 'C-Corporation' entity types.
         * A full list of valid entity types can be attained from ReferenceData/BusinessEntityTypes
         */
        String entityTypes;

        /**
         * Filters response to include only products matching the requested tag or tags. Use + as separator to include
         * more than 1 tag. Usage tags=external-website or tags=external-website+internal-portal. A filter of
         * tags=external-website+internal-portal+marketABC123 means all products which have either the tag
         * external-website, internal-portal, or marketABC123.
         */
        String tags;

        /**
         * Filters response to include only products with expressly stated eligibility for a given annual payroll
         * amount. Usage annualPayroll=10000000
         */
        String annualPayroll;

        /**
         * Filters response to include only products with expressly stated eligibility for a given annual revenue
         * amount. Usage annualRevenue=50000000
         */
        String annualRevenue;

        /**
         * Filters response to include only products in the specified premium range or amount. If specifying a range,
         * min and max values should be separated with a dash as shown in the example. Usage anticipatedPremium=1000 or
         * anticipatedPremium=1000-10000.
         */
        String anticipatedPremium;

        /**
         * Filters response to include only products with expressly stated eligibility for a given number of full time
         * employees. Usage fullTimeEmployees=100
         */
        String fullTimeEmployees;

        /**
         * Filters response to include only products with expressly stated eligibility for a given number of part time
         * employees. Usage partTimeEmployees=24
         */
        String partTimeEmployees;

        /**
         * Filters response to include only products with expressly stated eligibility for entities who have been in
         * business for a given amount time. Usage yearsInBusiness=5
         */
        String yearsInBusiness;

        /**
         * Filters response to include only products with expressly stated eligibility for entities who have a given
         * amount of operating experience in the industry. Usage yearsInIndustry=10
         */
        String yearsInIndustry;

        /**
         * Limit the response to a maximum number of products.
         */
        Integer productsPerPage;

        /**
         * Get specific page of results. Page numbers are zero-based, so to retrieve the 6th page, you should set
         * page=5. If productsPerPage is not specified all products will be included in the results.
         */
        Integer page;

    }

    @lombok.Builder
    @lombok.Getter
    @lombok.Setter
    class UserQuery {

        /**
         * Filters response to include only products belonging to the requested owner(s). Use + as separator to include
         * more than company id. Usage owners=ABC123 or owners=ABC123+DEF456. A filter of owners=ABC123+DEF456 means
         * products owned by group with id ABC123 OR products owned by group with id DEF456.
         */
        String owners;

        /**
         * Filters request to include only products owned by the specified company type. Usage companyType=carrier or
         * companyType=other.
         */
        String companyType;

        /**
         * Filters request to include only products of with the requested relationship(s) to the searching company.
         *
         * Valid values are one of:
         *
         * owner - products owned by the company making the request.
         *
         * inNetwork - products owned by a company that 'trusts' the company making the request.
         *
         * outOfNetwork - products owned by a company that has no relationship with the company making the request.
         *
         * Use + as separator to include more than interest level. Usage interestLevels=outOfNetwork or
         * interestLevels=owner+inNetwork.
         */
        String interestLevels;

        /**
         * Filter response to include only products with eligibility for a specific 2-6 digit NAICS group or groups and
         * apply any conditional rules which pertain to them. Use + as separator to include more than one naics group.
         * Usage naicsGroups=722514 or naicsGroups=44-45+722515.
         */
        String naicsGroups;

        /**
         * Filter response to include only products with eligibility for a specific hash or hashes and apply any
         * conditional rules which pertain to them. Use + as separator to include more than one hash. Usage
         * naicsCodes=9d709a5f8cefe02c3ba71bdd3a4c3e28 or
         * naicsCodes=9d709a5f8cefe02c3ba71bdd3a4c3e28+4797cab0bb586ec0a98da773878ef97d.
         */
        String naicsCodes;

        /**
         * Request that eligible hashes be included on products in the response. Potentially increases response payload
         * significantly, especially in those cases where the results include a large number of products. If this
         * parameter is missing, excluded, or set to false eligible classes will not be present on products in the
         * response.
         */
        Boolean includeEligibility;

        /**
         * Request that only summary information be returned for products in the response including (if available on
         * resultant products) name, ownerId, id, coverageType, and logo. If this parameter is missing, excluded, or set
         * to false all available properties will be present on products in the response. Please Note: in the event that
         * summaryOnly is set to true, the includeEligibility parameter will be forced to false.
         */
        Boolean summaryOnly;

        /**
         * Filter response to include only products available in requested geography(s) and apply any conditional rules
         * which pertain to them. Use + as separator to include more than 1 ISO 3166-2 code. Usage geos=US-HI or
         * geos=US-MN+CA-ON. A filter of geos=US-MN+US-HI means products available in Minnesota OR Hawaii.
         */
        String geos;

        /**
         * Filter response to include only products matching the requested coverage or coverages. Use + as separator to
         * include more than 1 product code. Usage productCodes=BOP or productCodes=BOP+WORK. A filter of
         * productCodes=BOP+WORK means products that have BOP OR Work Comp coverage.
         */
        String productCodes;

        /**
         * Filters response to include only products expressly eligible for a given entity type or types. Use + as
         * separator to include more than 1 entity type code. Usage entityTypes=AS or entityTypes=AS+CCORP. A filter of
         * entityTypes=AS+CCORP means include products eligible to either 'Association' or 'C-Corporation' entity types.
         * A full list of valid entity types can be attained from ReferenceData/BusinessEntityTypes
         */
        String entityTypes;

        /**
         * Filters response to include only products matching the requested tag or tags. Use + as separator to include
         * more than 1 tag. Usage tags=external-website or tags=external-website+internal-portal. A filter of
         * tags=external-website+internal-portal+marketABC123 means all products which have either the tag
         * external-website, internal-portal, or marketABC123.
         */
        String tags;

        /**
         * Filters response to include only products with expressly stated eligibility for a given annual payroll
         * amount. Usage annualPayroll=10000000
         */
        String annualPayroll;

        /**
         * Filters response to include only products with expressly stated eligibility for a given annual revenue
         * amount. Usage annualRevenue=50000000
         */
        String annualRevenue;

        /**
         * Filters response to include only products in the specified premium range or amount. If specifying a range,
         * min and max values should be separated with a dash as shown in the example. Usage anticipatedPremium=1000 or
         * anticipatedPremium=1000-10000.
         */
        String anticipatedPremium;

        /**
         * Filters response to include only products with expressly stated eligibility for a given number of full time
         * employees. Usage fullTimeEmployees=100
         */
        String fullTimeEmployees;

        /**
         * Filters response to include only products with expressly stated eligibility for a given number of part time
         * employees. Usage partTimeEmployees=24
         */
        String partTimeEmployees;

        /**
         * Filters response to include only products with expressly stated eligibility for a specific total insured
         * value (TIV). Usage tiv=1000000
         */
        String tiv;

        /**
         * Filters response to include only products with expressly stated eligibility for a specific number of
         * scheduled vehicles. Usage vehicles=10
         */
        String vehicles;

        /**
         * Filters response to include only products with expressly stated eligibility for a specific number of
         * scheduled locations. Usage locations=5
         */
        String locations;

        /**
         * Filters response to include only products with expressly stated eligibility for a specific number of
         * scheduled buildings. Usage buildings=2
         */
        String buildings;

        /**
         * Filters response to include only products with expressly stated eligibility for a specific square footage.
         * Usage squareFootage=1200
         */
        String squareFootage;

        /**
         * Filters response to include only products with expressly stated eligibility for buildings of a specific age
         * in years. Usage buildingAge=200
         */
        String buildingAge;

        /**
         * Filters response to include only products with expressly stated eligibility for entities who have been in
         * business for a given amount time. Usage yearsInBusiness=5
         */
        String yearsInBusiness;

        /**
         * Filters response to include only products with expressly stated eligibility for entities who have a given
         * amount of operating experience in the industry. Usage yearsInIndustry=10
         */
        String yearsInIndustry;

        /**
         * Request that only admitted products be included. If this parameter is omitted no filter will be applied to
         * the response with regard to admitted status. Usage admitted=true or admitted=false
         */
        Boolean admitted;

        /**
         * Limit the response to a maximum number of products.
         */
        Integer productsPerPage;

        /**
         * Get specific page of results. Page numbers are zero-based, so to retrieve the 6th page, you should set
         * page=5. If productsPerPage is not specified all products will be included in the results.
         */
        Integer page;

        /**
         * Limits the query to a subset of products by filtering the response to include only the requested product(s)
         * in the response. Use + as separator to include more than product id.
         *
         * Usage products=a61a1c21df9b4971a1b7e569014d128c or
         * products=a61a1c21df9b4971a1b7e569014d128c+c22624b9777d47e2914ffa27c733149b.
         *
         * A filter of products=a61a1c21df9b4971a1b7e569014d128c+c22624b9777d47e2914ffa27c733149b means only include the
         * products with the id a61a1c21df9b4971a1b7e569014d128c OR c22624b9777d47e2914ffa27c733149b (i.e. no other
         * products will be present in the response).
         */
        String products;

        /**
         * Filter response to include only products with eligibility for a specific custom (product owner defined)
         * classification code and apply conditional rules which pertain to any NAICS codes mapped to the custom code.
         * Specify classifications as a composite key made up of the taxonomy identifier (tid) to which the code belongs
         * and the code id (cid) itself delimited by a colon (:).
         *
         * As an illustrative example, to filter for only products eligible for bop code 12345 the value would be
         * bop:12345. More than one classification can be specified. In this event, use + as separator to include more
         * than on. For example bop:12345+bop:78901+wc:1111.
         *
         * Products not explicitly correlated by their owner to the requested taxonomy will not be returned even if they
         * are eligible for the NAICS code(s) the custom classification code(s) represents. Mappings from the custom
         * class code to NAICS are defined by the owner of the product.
         */
        String classifications;

        /**
         * Filters response to include only products with expressly stated eligibility for entities who have a given
         * amount of operating experience in the industry. Usage yearsInIndustry=10
         */
        String classificationGroups;

    }

    /////////////////////////
    // Product API query models
    // https://api.askkodiak.com/doc/v2/#api-Product
    /////////////////////////

    @lombok.Builder
    @lombok.Getter
    @lombok.Setter
    class ProductQuery {

        /**
         * Request that eligible NAICS hashes be included in the response. May significantly increase response payload,
         * especially in those cases where the product has extensive eligibility. If this parameter is missing,
         * excluded, or set to false eligible classes will not be present in the response.
         */
        Boolean includeEligibility;

        /**
         * Will cause location specific conditional content to be included for the requested geography(s) if any.
         * <p>
         * Use + as separator to include more than one geo code. Usage geos=US-MA or geos=CA-ON+CA-BC. A filter of
         * geos=US-MN+US-HI means Minnesota OR Hawaii.
         */
        String geos;

        /**
         * Will cause naics-group-based conditional content to be included for the requested group(s) if any.
         * <p>
         * Use + as separator to include more than one naics group. Usage naicsGroups=722514 or
         * naicsGroups=722514+722515.
         */
        String naicsGroups;

        /**
         * Will cause naics-code-based conditional content to be included for the requested codes(s) if any.
         * <p>
         * Use + as separator to include more than one hash. Usage naicsCodes=9d709a5f8cefe02c3ba71bdd3a4c3e28 or
         * naicsCodes=9d709a5f8cefe02c3ba71bdd3a4c3e28+4797cab0bb586ec0a98da773878ef97d.
         */
        String naicsCodes;

    }

    /////////////////////////
    // Suggest API query models
    // https://api.askkodiak.com/doc/v2/#api-Suggest
    /////////////////////////

    @lombok.Builder
    @lombok.Getter
    @lombok.Setter
    class SuggestQuery {

        /**
         * Limit the results to a certain NAICS group type. Use this for example to restrict your search to only 6 digit
         * NAICS National Industry Codes. Valid values are sector, subsector, industry-group, international-industry, or
         * national-industry. If omitted, groups of all types will be present in the results.
         * <p>
         * Only available on {@link #getSuggestedNaicsGroups(String, SuggestQuery)}
         */
        String groupType;

        /**
         * The number of hits in each page of product results. If not specified, defaults to 20.
         */
        Integer hitsPerPage;

        /**
         * Specify the page to retrieve. If not specified, defaults to 0.
         */
        Integer page;
    }

    /////////////////////////
    // Company API query models
    // https://api.askkodiak.com/doc/v2/#api-Company
    /////////////////////////

    @lombok.Builder
    @lombok.Getter
    @lombok.Setter
    class CompaniesQuery {

        /**
         * Filters request by company type. Usage companyType=carrier or companyType=other. If this parameter is
         * omitted, all companies with a storefront on Ask Kodiak will be returned.
         */
        String companyType;

        /**
         * Limit the response to a maximum number of companies.
         */
        Integer companiesPerPage;

        /**
         * Get specific page of results. Page numbers are zero-based, so to retrieve the 6th page, you should set
         * page=5. If companiesPerPage is not specified all companies will be included in the results.
         */
        Integer page;

    }

}
