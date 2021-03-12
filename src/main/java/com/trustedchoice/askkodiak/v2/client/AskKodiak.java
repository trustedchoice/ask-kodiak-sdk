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

import com.trustedchoice.askkodiak.v2.model.company.CompanyProducts;
import com.trustedchoice.askkodiak.v2.model.geography.Geo;
import com.trustedchoice.askkodiak.v2.model.naics.*;
import com.trustedchoice.askkodiak.v2.model.referral.Referral;
import com.trustedchoice.askkodiak.v2.model.company.Companies;
import com.trustedchoice.askkodiak.v2.model.company.CompaniesTrustedBy;
import com.trustedchoice.askkodiak.v2.model.company.Company;
import com.trustedchoice.askkodiak.v2.model.mapping.ClassificationCodeMappings;
import com.trustedchoice.askkodiak.v2.model.mapping.NaicsCustomMappings;
import com.trustedchoice.askkodiak.v2.model.mapping.NaicsMappings;
import com.trustedchoice.askkodiak.v2.model.mapping.SicMappings;
import com.trustedchoice.askkodiak.v2.model.product.*;
import com.trustedchoice.askkodiak.v2.model.suggest.*;
import com.trustedchoice.askkodiak.v2.model.classifications.*;
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
@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface AskKodiak {

    /////////////////////////
    // Products APIs
    // https://api.askkodiak.com/doc/v2/#api-Products
    /////////////////////////

    /**
     * Get all products available to the requesting user that match the request parameters (if any).
     *
     * @param query Query parameters
     * @return User products
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/products/user")
    Products getProductsForUser(@QueryMap UserQuery query) throws AskKodiakException;

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
     * @param code  Any valid 2017 NAICS Code (at any level of the NAICS hierarchy) or MD5 Hash (6 Digit NAICS National
     *              Industry Code + Sub-Description). See above for a more detailed description of possible values.
     *              Conditional rules which pertain to the specified code will be applied to the results.
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
     * Get all products available to the requesting user that match the request options (if any) indexed by companies
     * with public storefronts on Ask Kodiak. The response includes an array of companies each having an array of
     * productGroupings itself a cluster of related products based on coverage type as further described below. Use
     * this interface if your use case is company-centric, for example if you desire to display a list of companies
     * each having a list of eligible/ineligible products. For a product-centric implementation, see
     * Products/GetProductsForCompany.
     * <p>
     * By default the response includes all products owned by each company - including those which may not be eligible
     * given the request parameters - with each decorated accordingly with a boolean property _eligible. This will
     * assist in the determination of a declarative 'no' value by product. Companies who have at least one product
     * eligible for the request will be included, others will be excluded from the response. During processing each
     * product is assigned a numeric score as a measure of it's relevance to the specific conditions of the request and
     * overall level of quality of the content itself. That value is assigned to each product as the property _score.
     * companies are sorted in order of appetite for the specific request as measured by the number of eligible
     * products which fell into the requested productGroups (excluding "Other") and cumulative score of member
     * products. productGroupings are sorted by number of eligible products within, in descending order. _score values
     * associated with products are aggregated at the productGrouping and company level.
     * <p>
     * Because this interface can return a very large number of results individual product objects with each
     * productGrouping each company are summarized. Extended product detail for a singleton product within the results
     * can be acquired at a later time using their id via Product/GetProduct.
     * <p>
     * If relevant products owned by your own company apply your company will be included in the response regardless if
     * you have a public storefront or not. For more information on how to further extended this capability through the
     * use a a response modifier to create 'virtual' companies based on subsets of products in your account see
     * Products/GetProductsByCompanyPost.
     * <p>
     * For more fine grained control of which productGroupings should be applied to the response see
     * Products/GetProductsByCompanyPost. Otherwise system-default values will be used which cluster products by common
     * related coverage type values.
     *
     * @param query Company products query
     * @return Products object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/products/by-company")
    CompanyProducts getProductsByCompany(@QueryMap CompanyProductsQuery query) throws AskKodiakException;

    /**
     * Get products eligible a given custom classification code in specified classification taxonomy.
     * <p>
     * Returns products that are linked to the classification taxonomy with the identifier taxonomyId and eligible for the custom classification with the identifier codeId.
     * <p>
     * Products not explicitly correlated by their owner to the requested taxonomy will not be returned even if they are eligible for the NAICS code(s) the custom classification code represents.
     * <p>
     * taxonomyId and codeId are case sensitive.
     *
     * @param taxonomyId A valid taxonomy identifier. Valid identifiers are not longer than 32 characters and are
     *                   alphanumeric but can also include underscores and hyphens.
     * @param codeId A valid classification code identifier. Valid identifiers are not longer than 32 characters and are
     *               alphanumeric but can also include underscores and hyphens.
     * @return Products object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/products/class-code/custom/{taxonomyId}/codes/{codeId}")
    Products getProductsEligibleForCustomClassCode(@Param("taxonomyId") String taxonomyId,
                                                   @Param("codeId") String codeId) throws AskKodiakException;

    /**
     * Get products eligible a given custom classification code in specified classification taxonomy.
     * <p>
     * Returns products that are linked to the classification taxonomy with the identifier taxonomyId and eligible for the custom classification with the identifier codeId.
     * <p>
     * Products not explicitly correlated by their owner to the requested taxonomy will not be returned even if they are eligible for the NAICS code(s) the custom classification code represents.
     * <p>
     * taxonomyId and codeId are case sensitive.
     *
     * @param taxonomyId A valid taxonomy identifier. Valid identifiers are not longer than 32 characters and are
     *                   alphanumeric but can also include underscores and hyphens.
     * @param codeId     A valid classification code identifier. Valid identifiers are not longer than 32 characters and are
     *                   alphanumeric but can also include underscores and hyphens.
     * @param query      CompaniesQuery parameters
     * @return Products object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/products/class-code/custom/{taxonomyId}/codes/{codeId}")
    Products getProductsEligibleForCustomClassCode(@Param("taxonomyId") String taxonomyId,
                                                   @Param("codeId") String codeId,
                                                   EligibleClassQuery query) throws AskKodiakException;

    /**
     * Get products eligible a given custom classification group (a collection of custom class codes) in specified
     * classification taxonomy.
     * <p>
     * Returns products that are linked to the classification taxonomy with the identifier taxonomyId and eligible for
     * any NAICS value mapped to the custom classification group with the identifier classGroupId.
     * <p>
     * Products not explicitly correlated by their owner to the requested taxonomy will not be returned even if they
     * are eligible for the NAICS code(s) the custom classification group represents.
     * <p>
     * taxonomyId and classGroupId are case sensitive.
     *
     * @param taxonomyId   A valid taxonomy identifier. Valid identifiers are not longer than 32 characters and are
     *                     alphanumeric but can also include underscores and hyphens.
     * @param classGroupId A valid classification code identifier. Valid identifiers are not longer than 32 characters
     *                     and are alphanumeric but can also include underscores and hyphens.
     * @return Products object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/products/class-code/custom/{taxonomyId}/groups/{classGroupId}")
    Products getProductsEligibleForCustomClassGroup(@Param("taxonomyId") String taxonomyId,
                                                    @Param("classGroupId") String classGroupId) throws AskKodiakException;

    /**
     * Get products eligible a given custom classification group (a collection of custom class codes) in specified
     * classification taxonomy.
     * <p>
     * Returns products that are linked to the classification taxonomy with the identifier taxonomyId and eligible for
     * any NAICS value mapped to the custom classification group with the identifier classGroupId.
     * <p>
     * Products not explicitly correlated by their owner to the requested taxonomy will not be returned even if they
     * are eligible for the NAICS code(s) the custom classification group represents.
     * <p>
     * taxonomyId and classGroupId are case sensitive.
     *
     * @param taxonomyId   A valid taxonomy identifier. Valid identifiers are not longer than 32 characters and are
     *                     alphanumeric but can also include underscores and hyphens.
     * @param classGroupId A valid classification code identifier. Valid identifiers are not longer than 32 characters
     *                     and are alphanumeric but can also include underscores and hyphens.
     * @param query        Eligible query object
     * @return Products object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/products/class-code/custom/{taxonomyId}/groups/{classGroupId}")
    Products getProductsEligibleForCustomClassGroup(@Param("taxonomyId") String taxonomyId,
                                                    @Param("classGroupId") String classGroupId,
                                                    EligibleQuery query) throws AskKodiakException;

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
     * @param id    The id of the product requested.
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
     * @param id   The id of the product requested.
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
     * @param id   The id of the product requested.
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

    /**
     * Get the names of all factors (variables) which may cause a rule to trigger for the requested product.
     * <p>
     * This API answers the question "What else should be asked" to resolve conditional content with the highest degree
     * of accuracy. Almost always a subset of the results of Product/GetProductFactors. Any variable in the response
     * object can possibly trigger a rule that may cause variation in the corresponding product content or eligibility.
     * Use this interface if you're building a UI and want to ask only those questions which are relevant or which
     * resolve content for the subject product to the fullest.
     * <p>
     * Can be invoked progressively using request parameters as more information becomes known to determine which
     * factors are still in play.
     *
     * @param id Product ID
     * @return Factors object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/product/{id}/conditional-factors")
    Factors getConditionalFactors(@Param("id") String id) throws AskKodiakException;

    /**
     * Get the names of all factors (variables) which may cause a rule to trigger for the requested product.
     * <p>
     * This API answers the question "What else should be asked" to resolve conditional content with the highest degree
     * of accuracy. Almost always a subset of the results of Product/GetProductFactors. Any variable in the response
     * object can possibly trigger a rule that may cause variation in the corresponding product content or eligibility.
     * Use this interface if you're building a UI and want to ask only those questions which are relevant or which
     * resolve content for the subject product to the fullest.
     * <p>
     * Can be invoked progressively using request parameters as more information becomes known to determine which
     * factors are still in play.
     *
     * @param id    Product ID
     * @param query Filter query object
     * @return Factors object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/product/{id}/conditional-factors")
    Factors getConditionalFactors(@Param("id") String id, @QueryMap FilterQuery query) throws AskKodiakException;

    /**
     * Get unprocessed conditional rules for the requested product. Please note, this interface exists primarily for
     * debugging. The rules expressed in the response are automatically applied to other product interfaces based on
     * the parameters of the specific request. This interface simply provides a getter for all the rules that have been
     * specified for a product.
     *
     * @param id The id of the product requested.
     * @return Conditional rules (if any) represented as an object.
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/product/{id}/conditional-rules")
    Map<String, Rule> getConditionalRules(@Param("id") String id) throws AskKodiakException;

    /**
     * Get the names of all factors (variables) which may influence the eligibility of subject product.
     * <p>
     * This API answers the question "What should be asked" to resolve eligibility highest degree of accuracy. Use this
     * interface if you're building a UI and want to ask only those questions which are relevant to the ultimate
     * eligibility of the product.
     *
     * @param id Product ID
     * @return Factors object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/product/{id}/factors")
    Factors getFactors(@Param("id") String id) throws AskKodiakException;

    /**
     * Given the specified conditions, is the product eligible?
     *
     * @param id      The id of the product requested.
     * @param filters Filter query object
     * @return Eligible object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/product/{id}/is-eligible")
    Eligibility getEligibility(@Param("id") String id, @QueryMap FilterQuery filters) throws AskKodiakException;

    /**
     * Render conditional content for the product associated with the specified conditions. Applicable conditional
     * content will be rendered in the response payload.
     *
     * @param id      The id of the product requested.
     * @param filters Filter query object
     * @return ConditionalContent object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/product/{id}/conditional-content")
    ConditionalContent renderConditionalContent(
            @Param("id") String id,
            @QueryMap FilterQuery filters) throws AskKodiakException;


    /////////////////////////
    // Suggest APIs
    // https://api.askkodiak.com/doc/v2/#api-Suggest
    /////////////////////////

    /**
     * Get suggested business entity types for a search term from the list of platform supported business entity types.
     * This interface is designed to be used in a 'suggest' control or search model where the user is attempting to
     * describe the legal basis of a business.
     *
     * @param term The text value to search.
     * @return Suggested business entity types
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/suggest/business-entity-types/{term}")
    BusinessEntityTypeSuggestions getSuggestedBusinessEntityTypes(@Param("term") String term) throws AskKodiakException;

    /**
     * Get suggested business entity types for a search term from the list of platform supported business entity types.
     * This interface is designed to be used in a 'suggest' control or search model where the user is attempting to
     * describe the legal basis of a business.
     *
     * @param term  The text value to search.
     * @param query Query parameters
     * @return Suggested business entity types
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/suggest/business-entity-types/{term}")
    BusinessEntityTypeSuggestions getSuggestedBusinessEntityTypes(
            @Param("term") String term,
            @QueryMap SuggestQuery query) throws AskKodiakException;

    /**
     * Get suggested naics codes, geos, product codes or business entity types for a search term simultaneously across
     * collections. This interface as used to power the main Ask Kodiak search control as it returns all of the
     * distinct properties by which a user can originate a search flow.
     *
     * @param term The text value to search.
     * @return Suggested results
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/suggest/comprehensive/{term}")
    ComprehensiveSuggestions getSuggestedComprehensive(@Param("term") String term) throws AskKodiakException;

    /**
     * Get suggested naics codes, geos, product codes or business entity types for a search term simultaneously across
     * collections. This interface as used to power the main Ask Kodiak search control as it returns all of the
     * distinct properties by which a user can originate a search flow.
     *
     * @param term  The text value to search.
     * @param query Query parameters
     * @return Suggested results
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/suggest/comprehensive/{term}")
    ComprehensiveSuggestions getSuggestedComprehensive(
            @Param("term") String term,
            @QueryMap SuggestQuery query) throws AskKodiakException;

    /**
     * Get suggested geographies for a search term from the list of platform supported geographies. This interface is
     * designed to be used in a 'suggest' control or search model where the user is attempting to describe a risk
     * location.
     *
     * @param term The text value to search.
     * @return Suggested geos
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/suggest/geos/{term}")
    GeoSuggestions getSuggestedGeos(@Param("term") String term) throws AskKodiakException;

    /**
     * Get suggested geographies for a search term from the list of platform supported geographies. This interface is
     * designed to be used in a 'suggest' control or search model where the user is attempting to describe a risk
     * location.
     *
     * @param term Geo suggestions object
     * @param query Query parameters
     * @return Suggested geos
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/suggest/geos/{term}")
    GeoSuggestions getSuggestedGeos(
            @Param("term") String term,
            @QueryMap SuggestQuery query) throws AskKodiakException;

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
     * @param term  The text to search in the index.
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
     * @param term  The text to search in the index.
     * @param query Query parameters
     * @return Suggested NAICS groups
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/suggest/naics-groups/{term}")
    NaicsGroupSuggestions getSuggestedNaicsGroups(
            @Param("term") String term,
            @QueryMap SuggestQuery query) throws AskKodiakException;

    /**
     * Get suggested product (coverage) codes for a search term. This interface is designed to be used in a 'suggest'
     * control or search model where the user is attempting to describe a coverage type.
     *
     * @param term The text value to search.
     * @return Suggested product codes
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/suggest/product-codes/{term}")
    ProductCodeSuggestions getSuggestedProductCodes(@Param("term") String term) throws AskKodiakException;

    /**
     * Get suggested product (coverage) codes for a search term. This interface is designed to be used in a 'suggest'
     * control or search model where the user is attempting to describe a coverage type.
     *
     * @param term  The text value to search.
     * @param query Query parameters
     * @return Suggested product codes
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/suggest/product-codes/{term}")
    ProductCodeSuggestions getSuggestedProductCodes(
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
     * Given a code, return it's NAICS parentage.
     *
     * @param groupNumber The number of the NAICS group to determine path (ancestry) for.
     * @return The NAICS path array of the requested group.
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/naics/utils/get-path/{groupNumber}")
    Path getPath(@Param("groupNumber") String groupNumber) throws AskKodiakException;

    /**
     * Get detailed information about all naics sectors, as well as their descendant codes and groups. For more on
     * NAICS sectors, see http://www.census.gov/cgi-bin/sssd/naics/naicsrch?chart=2012
     * <p>
     * If only summary level information for sectors is required, see also NAICS/GetSummaryForGroupType
     *
     * @return Map of sector NAICS group objects
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/naics/sectors")
    Map<String, NaicsGroup> getSectors() throws AskKodiakException;

    /**
     * Get a comprehensive list of all valid naics groups of the requested type. A way to quickly get "all the NAICS
     * sector codes", or "all the naics international industry group numbers" etc.
     *
     * @param type The requested group type. One of: sector, subsector, industry-group, international-industry, or
     *             national-industry
     * @return An object whose keys are all valid NAICS groups of the requested type. The value of each key is the
     * title of the group.
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/naics/summary/{type}")
    Map<String, String> getSummaryForGroupType(@Param("type") String type) throws AskKodiakException;

    /**
     * Get a comprehensive list of all valid naics groups indexed by type (e.g. sector, subsector, industry-group,
     * international-industry, or national-industry).
     * <p>
     * This is a quick way to get a list of all valid naics groups clustered by type. If only one type of codes (e.g.
     * just 6 digit national industry codes) is required, consider using NAICS/GetSummaryForGroupType
     *
     * @return An object whose keys are NAICS types and values are maps whose keys are NAICS group codes and values are
     * the titles of each NAICS group.
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/naics/summary")
    Map<String, Map<String, String>> getSummary() throws AskKodiakException;

    /////////////////////////
    // SIC APIs
    // https://api.askkodiak.com/doc/v2/#api-SIC
    /////////////////////////

    /**
     * Get the details associated with a given SIC code
     *
     * @param sic SIC code
     * @return SIC object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/sic/code/{sic}")
    Sic getSicCode(@Param("sic") String sic) throws AskKodiakException;

    /////////////////////////
    // Classifications APIs
    // https://api.askkodiak.com/doc/v2/#api-Classifications
    /////////////////////////

    /**
     * Get a list of all custom classification taxonomies for the requested company. A classification taxonomy is an
     * owner-defined classification system. For example it might represent line-of-business specific or rating related
     * classification codes.
     *
     * @param ownerId The group id of the company on Ask Kodiak for which taxonomies should be listed.
     * @return Taxonomies object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/classifications/{ownerId}")
    Taxonomies getTaxonomies(@Param("ownerId") String ownerId) throws AskKodiakException;

    /**
     * Gets the identifier, description, and title of the requested classification taxonomy. A classification taxonomy
     * is an owner-defined classification system. For example it might represent line-of-business specific or rating
     * related classification codes.
     *
     * @param ownerId    The group id of the company on Ask Kodiak to which the requested taxonomy belongs.
     * @param taxonomyId The identifier of the taxonomy requested.
     * @return Taxonomy object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/classifications/{ownerId}/{taxonomyId}")
    Taxonomy getTaxonomy(
            @Param("ownerId") String ownerId,
            @Param("taxonomyId") String taxonomyId) throws AskKodiakException;

    /**
     * Get a list of the custom classifications codes that make up a classification taxonomy.
     *
     * @param ownerId    The group id of the company on Ask Kodiak to which the requested taxonomy belongs.
     * @param taxonomyId The identifier of the taxonomy to which the requested classification codes belong.
     * @return Taxonomy codes object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/classifications/{ownerId}/{taxonomyId}/codes")
    TaxonomyCodes getTaxonomyCodes(
            @Param("ownerId") String ownerId,
            @Param("taxonomyId") String taxonomyId) throws AskKodiakException;

    /**
     * Get details for the requested custom classification code.
     *
     * @param ownerId    The group id of the company on Ask Kodiak to which the requested taxonomy belongs.
     * @param taxonomyId The identifier of the taxonomy to which the requested classification code belongs.
     * @param codeId     The identifier of the class code for which detail should be returned.
     * @return Taxonomy code object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/classifications/{ownerId}/{taxonomyId}/codes/{codeId}")
    TaxonomyCode getTaxonomyCode(
            @Param("ownerId") String ownerId,
            @Param("taxonomyId") String taxonomyId,
            @Param("codeId") String codeId) throws AskKodiakException;

    /**
     * Get a list of the NAICS HD (sub-description) mappings for the requested classification code. To go the other
     * direction - from NAICS to a custom code - see: Mapping/NAICS to Custom.
     *
     * @param ownerId    The group id of the company on Ask Kodiak to which the requested taxonomy belongs.
     * @param taxonomyId The identifier of the taxonomy to which the requested classification code belongs.
     * @param codeId     The identifier of the class code for which mappings should be returned.
     * @return Taxonomy code mappings object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/classifications/{ownerId}/{taxonomyId}/codes/{codeId}/mappings")
    TaxonomyCodeMappings getTaxonomyCodeMappings(
            @Param("ownerId") String ownerId,
            @Param("taxonomyId") String taxonomyId,
            @Param("codeId") String codeId) throws AskKodiakException;

    /**
     * Get a list of the custom classifications groups (if-any, each a collection of individual codes) that make up a
     * taxonomy. Classification groups are a way for companies to group related classification codes, for example
     * 'retail', or 'wholesaler' or 'health care'.
     *
     * @param ownerId    The group id of the company on Ask Kodiak to which the requested taxonomy belongs.
     * @param taxonomyId The identifier of the taxonomy for which classification groups should be returned.
     * @return Classification groups object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/classifications/{ownerId}/{taxonomyId}/groups")
    ClassificationGroups getClassificationGroups(
            @Param("ownerId") String ownerId,
            @Param("taxonomyId") String taxonomyId) throws AskKodiakException;

    /**
     * Get details for the requested custom classification group.
     *
     * @param ownerId The group id of the company on Ask Kodiak to which the requested taxonomy belongs.
     * @param taxonomyId The identifier of the taxonomy to which the requested classification group belongs.
     * @param classificationGroupId The identifier of the classification group for which detail should be returned.
     * @return Classification group object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/classifications/{ownerId}/{taxonomyId}/codes/{classificationGroupId}")
    ClassificationGroup getClassificationGroup(
            @Param("ownerId") String ownerId,
            @Param("taxonomyId") String taxonomyId,
            @Param("classificationGroupId") String classificationGroupId) throws AskKodiakException;

    /**
     * Get a list of the classification codes that belong to a classification group in a taxonomy.
     *
     * @param ownerId The group id of the company on Ask Kodiak to which the requested taxonomy belongs.
     * @param taxonomyId The identifier of the taxonomy to which the requested classification group belongs.
     * @param classificationGroupId The identifier of the classification group for which detail should be returned.
     * @return Classification group codes object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/classifications/{ownerId}/{taxonomyId}/groups/{classificationGroupId}/codes")
    ClassificationGroupCodes getClassificationGroupCodes(
            @Param("ownerId") String ownerId,
            @Param("taxonomyId") String taxonomyId,
            @Param("classificationGroupId") String classificationGroupId) throws AskKodiakException;

    /**
     * Get a list of the products available to the user to which a given taxonomy applies.
     *
     * @param ownerId The group id of the company on Ask Kodiak to which the requested taxonomy belongs.
     * @param taxonomyId The identifier of the taxonomy to which the requested classification codes belong.
     * @return Taxonomy products object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/classifications/{ownerId}/{taxonomyId}/products")
    TaxonomyProducts getTaxonomyProducts(
            @Param("ownerId") String ownerId,
            @Param("taxonomyId") String taxonomyId) throws AskKodiakException;

    /////////////////////////
    // Mapping APIs
    // https://api.askkodiak.com/doc/v2/#api-Mapping
    /////////////////////////

    /**
     * Get all HD NAICS mappings for a given custom classification taxonomy
     *
     * @param gid The group identifier associated with the owner of the custom taxonomy.
     * @param tid The identifier of the taxonomy for which mappings should be returned.
     * @return Naics custom mapping object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/mapping/naics/classifications/{gid}/{tid}")
    NaicsCustomMappings getAllNaicsForCustomMappings(
            @Param("gid") String gid,
            @Param("tid") String tid) throws AskKodiakException;

    /**
     * Get zero to many custom classification code mappings for a given NAICS identifier.
     *
     * @param naics The identifier of the NAICS to be mapped. This value can be any valid 2-6 digit NAICS code or MD5
     *              hash of the national industry code and individual sub-description (NAICS HD).
     * @param gid The group identifier associated with the owner of the custom taxonomy.
     * @param tid The identifier of the taxonomy for which mappings should be returned.
     * @return Classification code mappings object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/mapping/naics/{naics}/classifications/{gid}/{tid}")
    ClassificationCodeMappings getNaicsToCustomMappings(
            @Param("naics") String naics,
            @Param("gid") String gid,
            @Param("tid") String tid) throws AskKodiakException;

    /**
     * Get a list of SIC mappings for a given NAICS identifier. Please note, because NAICS is a newer model and
     * replaced SIC directly, it may cover definitions of business that do not exist in the SIC model. In this case no
     * mappings will be returned. It is also possible that a NAICS code may have applicability to more than one SIC
     * code, especially so when the target NAICS is a 2-6 digit group. Thusly expect the mapping results to be
     * zero-to-many.
     *
     * @param naics The identifier of the NAICS to be mapped. This value can be any valid 2-6 digit NAICS code or MD5
     *              hash of the national industry code and individual sub-description (NAICS HD).
     * @return Sic mappings object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/mapping/naics/{naics}/sic")
    SicMappings getNaicsToSicMappings(@Param("naics") String naics) throws AskKodiakException;

    /**
     * Get a list of NAICS mappings for a given SIC identifier.
     *
     * @param sic The identifier of the four-digit SIC code to be mapped.
     * @return Naics mappings object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/mapping/sic/{sic}/naics")
    NaicsMappings getSicToNaicsMappings(@Param("sic") String sic) throws AskKodiakException;

    /////////////////////////
    // Reference Data APIs
    // https://api.askkodiak.com/doc/v2/#api-Reference_Data
    /////////////////////////

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

    /**
     * Get geographies supported by Ask Kodiak as objects indexed by an ISO 3166-2 code. For more information on the
     * ISO 3166 standard, see https://www.iso.org/iso-3166-country-codes.html
     *
     * @param query Geographies query object
     * @return An object containing information about the supported geographic subdivisions (states, provinces,
     * territories, etc).
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/ref-data/geos")
    Map<String, Geo> getGeographies(@QueryMap GeographiesQuery query) throws AskKodiakException;

    /**
     * Get a list of product codes for use decoding the coded values associated with a product. These are updated on
     * occasion, so while it's advised that you cache the results for reference, do make sure and check for changes
     * every now and again.
     *
     * @return An object containing all product code key-value pairs.
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/ref-data/product-codes")
    Map<String, String> getProductCodes() throws AskKodiakException;

    /////////////////////////
    // Analytics APIs
    // https://api.askkodiak.com/doc/v2/#api-Reference_Data
    /////////////////////////

    /**
     * Retrieve the details of a referral using it's id.
     *
     * @param id The id of the referral requested.
     * @return Data about the referral as an object.
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/analytics/referral/{id}")
    Referral getReferral(@Param("id") String id) throws AskKodiakException;

    /**
     * Retrieve all of your groups referrals from Ask Kodiak
     *
     * @return An object whose properties are a comprehensive list of singleton referral objects for the group.
     * Referral objects are described in more detail in the documentation for Analytics/GetReferral
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/analytics/referrals")
    Map<String, Referral> getReferrals() throws AskKodiakException;

    /////////////////////////
    // Admin APIs
    // https://api.askkodiak.com/doc/v2/#api-Reference_Data
    /////////////////////////

    /**
     * Get products owned by your your group regardless of their permission.
     *
     * @param query User query object
     * @return Products object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/admin/products")
    Products getProducts(@QueryMap ProductsQuery query) throws AskKodiakException;

    /////////////////////////
    // Network APIs
    // https://api.askkodiak.com/doc/v2/#api-Network
    /////////////////////////

    /**
     * Get a list of all the companies who 'trust' your company on Ask Kodiak.
     *
     * @param query Companies trusted by query object
     * @return Companies trusted by object
     * @throws AskKodiakException error
     */
    @RequestLine("GET /v2/network/trusted-by")
    CompaniesTrustedBy getCompaniesTrustedBy(@QueryMap CompaniesTrustedBy query) throws AskKodiakException;

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
         * Filters request to include only products owned by the specified company type. Usage companyType=carrier or
         * companyType=other.
         */
        String companyType;

        /**
         * Filters request to include only products of with the requested relationship(s) to the searching company.
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
         * Limit the response to a maximum number of products.
         */
        Integer productsPerPage;

        /**
         * Get specific page of results. Page numbers are zero-based, so to retrieve the 6th page, you should set page=5.
         * If productsPerPage is not specified all products will be included in the results.
         */
        Integer page;

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
         * Filters response to include only products with expressly stated eligibility for entities who have a given amount
         * of operating experience in the industry. Usage yearsInIndustry=10
         */
        String yearsInIndustry;

        /**
         * Request that only admitted products be included. If this parameter is omitted no filter will be applied to
         * the response with regard to admitted status. Usage admitted=true or admitted=false
         */
        Boolean admitted;

        /**
         * Limits the query to a subset of products by filtering the response to include only the requested product(s)
         * in the response. Use + as separator to include more than product id. Usage
         * products=a61a1c21df9b4971a1b7e569014d128c or
         * products=a61a1c21df9b4971a1b7e569014d128c+c22624b9777d47e2914ffa27c733149b. A filter of
         * products=a61a1c21df9b4971a1b7e569014d128c+c22624b9777d47e2914ffa27c733149b means only include the products
         * with the id a61a1c21df9b4971a1b7e569014d128c OR c22624b9777d47e2914ffa27c733149b (i.e. no other products
         * will be present in the response).
         */
        String products;
    }

    @lombok.Builder
    @lombok.Getter
    @lombok.Setter
    class CompanyQuery {
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
         * Limit the response to a maximum number of products.
         */
        Integer productsPerPage;

        /**
         * Get specific page of results. Page numbers are zero-based, so to retrieve the 6th page, you should set
         * page=5. If productsPerPage is not specified all products will be included in the results.
         */
        Integer page;

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
         * Limits the query to a subset of products by filtering the response to include only the requested product(s)
         * in the response. Use + as separator to include more than product id. Usage
         * products=a61a1c21df9b4971a1b7e569014d128c or
         * products=a61a1c21df9b4971a1b7e569014d128c+c22624b9777d47e2914ffa27c733149b. A filter of
         * products=a61a1c21df9b4971a1b7e569014d128c+c22624b9777d47e2914ffa27c733149b means only include the products
         * with the id a61a1c21df9b4971a1b7e569014d128c OR c22624b9777d47e2914ffa27c733149b (i.e. no other products
         * will be present in the response).
         */
        String products;

        /**
         * Filter response to include only products with eligibility for a specific custom (product owner defined)
         * classification code and apply conditional rules which pertain to any NAICS codes mapped to the custom code.
         * Specify classifications as a composite key made up of the taxonomy identifier (tid) to which the code belongs
         * and the code id (cid) itself delimited by a colon (:).
         * <p>
         * As an illustrative example, to filter for only products eligible for bop code 12345 the value would be
         * bop:12345. More than one classification can be specified. In this event, use + as separator to include more
         * than on. For example bop:12345+bop:78901+wc:1111.
         * <p>
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
         * <p>
         * Valid values are one of:
         * <p>
         * owner - products owned by the company making the request.
         * <p>
         * inNetwork - products owned by a company that 'trusts' the company making the request.
         * <p>
         * outOfNetwork - products owned by a company that has no relationship with the company making the request.
         * <p>
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
         * <p>
         * Usage products=a61a1c21df9b4971a1b7e569014d128c or
         * products=a61a1c21df9b4971a1b7e569014d128c+c22624b9777d47e2914ffa27c733149b.
         * <p>
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
         * <p>
         * As an illustrative example, to filter for only products eligible for bop code 12345 the value would be
         * bop:12345. More than one classification can be specified. In this event, use + as separator to include more
         * than on. For example bop:12345+bop:78901+wc:1111.
         * <p>
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

    @lombok.Builder
    @lombok.Getter
    @lombok.Setter
    class CompanyProductsQuery {
        /**
         * Limit the response to a maximum number of companies.
         */
        Integer companiesPerPage;

        /**
         * Get specific page of results. Page numbers are zero-based, so to retrieve the 6th page, you should set
         * page=5. If companiesPerPage is not specified all companies will be included in the results.
         */
        Integer page;

        /**
         * Should ineligible products be included in the products array for companies in the response. Defaults to true
         * if not specified so that the requestor can differentiate ineligible from ambiguous.
         */
        Boolean includeIneligible;

        /**
         * Filters response to include only products belonging to the requested owner(s). Use + as separator to include
         * more than company id. Usage owners=ABC123 or owners=ABC123+DEF456. A filter of owners=ABC123+DEF456 means
         * products owned by group with id ABC123 OR products owned by group with id DEF456.
         */
        String owners;

        /**
         * Filters request by company type. Usage companyType=carrier or companyType=other. If this parameter is
         * omitted, all companies with a storefront on Ask Kodiak will be returned.
         */
        String companyType;

        /**
         * Filters request to include only products of with the requested relationship(s) to the searching company.
         * Valid values are one of:
         * <p>
         * owner - products owned by the company making the request.
         * <p>
         * inNetwork - products owned by a company that 'trusts' the company making the request.
         * <p>
         * outOfNetwork - products owned by a company that has no relationship with the company making the request.
         * <p>
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
         * entityTypes=AS+CCORP means include products eligible to either 'Association' or 'C-Corporation' entity
         * types. A full list of valid entity types can be attained from ReferenceData/BusinessEntityTypes
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
         * Limits the query to a subset of products by filtering the response to include only the requested product(s)
         * in the response. Use + as separator to include more than product id.
         * <p>
         * Usage products=a61a1c21df9b4971a1b7e569014d128c or
         * products=a61a1c21df9b4971a1b7e569014d128c+c22624b9777d47e2914ffa27c733149b.
         * <p>
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
         * <p>
         * As an illustrative example, to filter for only products eligible for bop code 12345 the value would be
         * bop:12345. More than one classification can be specified. In this event, use + as separator to include more
         * than on. For example bop:12345+bop:78901+wc:1111.
         * <p>
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

    @lombok.Builder
    @lombok.Getter
    @lombok.Setter
    class EligibleClassQuery {
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
         * <p>
         * Valid values are one of:
         * <p>
         * owner - products owned by the company making the request.
         * <p>
         * inNetwork - products owned by a company that 'trusts' the company making the request.
         * <p>
         * outOfNetwork - products owned by a company that has no relationship with the company making the request.
         * <p>
         * Use + as separator to include more than interest level. Usage interestLevels=outOfNetwork or
         * interestLevels=owner+inNetwork.
         */
        String interestLevels;

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
         * Limit the response to a maximum number of products.
         */
        Integer productsPerPage;

        /**
         * Get specific page of results. Page numbers are zero-based, so to retrieve the 6th page, you should set
         * page=5. If productsPerPage is not specified all products will be included in the results.
         */
        Integer page;
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

        /**
         * Apply conditional rules which pertain to any NAICS codes mapped to the custom code (mappings are product
         * owner defined). Specify classifications as a composite key made up of the taxonomy identifier (tid) to which
         * the code belongs and the code id (cid) itself delimited by a colon (:).
         * <p>
         * As an illustrative example, to apply conditional content for bop code 12345 the value would be bop:12345.
         * More than one classification can be specified. In this event, use + as separator to include more than on.
         * For example bop:12345+bop:78901+wc:1111.
         * <p>
         * Products must be explicitly correlated by their owner to the requested taxonomy for conditional rules
         * correlated to NAICS codes mapped to the classification to be triggered. Mappings from the custom class code
         * to NAICS are defined by the owner of the product.
         */
        String classifications;

        /**
         * Apply conditional rules which pertain to any NAICS codes mapped to the custom custom classification group -
         * a set of custom classification code (mappings are product owner defined). Specify classificationGroups as a
         * composite key made up of the taxonomy identifier (tid) to which the code belongs and the classification
         * group id (cgid) itself delimited by a colon (:).
         * <p>
         * As an illustrative example, to apply conditional content for bop group retail the value would be bop:retail.
         * More than one classification group can be specified. In this event, use + as separator to include more than
         * on. For example bop:retail+bop:wholesale+wc:office.
         * <p>
         * Products must be explicitly correlated by their owner to the requested taxonomy for conditional rules
         * correlated to the NAICS codes mapped to the custom classification group(s) to be triggered. Mappings from
         * the custom class code to NAICS are defined by the owner of the product.
         */
        String classificationGroups;

        /**
         * Causes any applicable conditional content to be included for a given entity type or types. Use + as
         * separator to include more than 1 entity type code. Usage entityTypes=AS or entityTypes=AS+CCORP.
         */
        String entityTypes;

        /**
         * Causes any applicable conditional content to be included for a given annual payroll amount. Usage
         * annualPayroll=10000000
         */
        String annualPayroll;

        /**
         * Causes any applicable conditional content to be included for a given annual revenue amount. Usage
         * annualRevenue=50000000
         */
        String annualRevenue;

        /**
         * Causes any applicable conditional content to be included for a given number of full time employees. Usage
         * fullTimeEmployees=100
         */
        String fullTimeEmployees;

        /**
         * Causes any applicable conditional content to be included for a given number of part time employees. Usage
         * partTimeEmployees=24
         */
        String partTimeEmployees;

        /**
         * Causes any applicable conditional content to be included for a specific total insured value (TIV). Usage
         * tiv=1000000
         */
        String tiv;

        /**
         * Causes any applicable conditional content to be included for a specific number of scheduled vehicles. Usage
         * vehicles=10
         */
        String vehicles;

        /**
         * Causes any applicable conditional content to be included for a specific number of scheduled locations. Usage
         * vehicles=5
         */
        String locations;

        /**
         * Causes any applicable conditional content to be included for a specific number of scheduled buildings. Usage
         * buildings=2
         */
        String buildings;

        /**
         * Causes any applicable conditional content to be included for a specific square footage. Usage
         * squareFootage=1200
         */
        String squareFootage;

        /**
         * Causes any applicable conditional content to be included for buildings of a specific age in years. Usage
         * buildingAge=200
         */
        String buildingAge;

        /**
         * Causes any applicable conditional content to be included for entities who have been in business for a given
         * amount time. Usage yearsInBusiness=5
         */
        String yearsInBusiness;

        /**
         * Causes any applicable conditional content to be included for entities who have a given amount of operating
         * experience in the industry. Usage yearsInIndustry=10
         */
        String yearsInIndustry;

    }

    @lombok.Builder
    @lombok.Getter
    @lombok.Setter
    class FilterQuery {
        /**
         * Given the specified geography(s) if any. Use + as separator to include more than one ISO 3166-2 code. Usage
         * geos=US-MA or geos=CA-ON+CA-BC. A filter of geos=US-MN+US-HI means Minnesota OR Hawaii.
         */
        String geos;

        /**
         * Given the specified group(s) if any. Use + as separator to include more than one naics group. Usage
         * naicsGroups=722514 or naicsGroups=722514+722515.
         */
        String naicsGroups;

        /**
         * Given the specified codes(s) if any.
         */
        String naicsCodes;

        /**
         * Given the specified entity type(s) if any. Use + as separator to include more than 1 entity type code. Usage
         * entityTypes=AS or entityTypes=AS+CCORP.
         */
        String entityTypes;

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
         *
         * Only available on {@link #getEligibility(String, FilterQuery)} (String, FilterQuery)}
         */
        String classifications;

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
         *
         * Only available on {@link #getEligibility(String, FilterQuery)}
         */
        String classificationGroups;

        /**
         * Given the specified annual payroll amount if any. Usage annualPayroll=10000000
         */
        Integer annualPayroll;

        /**
         * Given the specified annual revenue amount if any. Usage annualRevenue=50000000
         */
        Integer annualRevenue;

        /**
         * Given the specified number of full time employees if any. Usage fullTimeEmployees=100
         */
        Integer fullTimeEmployees;

        /**
         * Given the specified number of part time employees if any. Usage partTimeEmployees=24
         */
        Integer partTimeEmployees;

        /**
         * Given the specified Total Insured Value (TIV) if any. Usage tiv=1000000
         */
        Integer tiv;

        /**
         * Given the specified number of scheduled vehicles if any. Usage vehicles=10
         */
        Integer vehicles;

        /**
         * Given the specified number of scheduled locations if any. Usage vehicles=5
         */
        Integer locations;

        /**
         * Given the specified number of scheduled buildings if any. Usage buildings=2
         */
        Integer buildings;

        /**
         * Given the specified square footage if any. Usage squareFootage=1200
         */
        Integer squareFootage;

        /**
         * Given the specified building age in years if any. Usage buildingAge=200
         */
        Integer buildingAge;

        /**
         * Given the specified number of years in business if any. Usage yearsInBusiness=5
         */
        Integer yearsInBusiness;

        /**
         * Given the specified number of years operating experience in the industry if any. Usage yearsInIndustry=10
         */
        Integer yearsInIndustry;
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
         * Only available on {@link #getSuggestedNaicsGroups(String, SuggestQuery)} and
         * {@link #getSuggestedNaicsGroups(String, SuggestQuery)}
         */
        String groupType;

        /**
         * Limit the results to a result type (i.e. only naics-codes). Valid values are naics-code, product-code,
         * business-entity-type, or geo. If omitted, results of all types will be present in the results. Usage:
         * filters=_type:naics-code.
         *
         * Only available on {@link #getSuggestedComprehensive(String, SuggestQuery)}
         */
        String filters;

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

    /////////////////////////
    // Reference Data API query models
    // https://api.askkodiak.com/doc/v2/#api-Reference_Data
    /////////////////////////

    @lombok.Builder
    @lombok.Getter
    @lombok.Setter
    class GeographiesQuery {
        /**
         * Filters response to include only subdivisions belonging to the requested country. Value must be an
         * ISO 3166-1 standard country code. Use + as separator to include subdivisions from more than one country in
         * the response. Usage countries=CA, countries=US, or countries=US+CA.
         */
        String countries;

        /**
         * Filters response to include only subdivisions of the requested type. Use + as separator to include
         * subdivisions of more than one type in the response. Usage subdivisionTypes=territory,
         * subdivisionTypes=state, or subdivisionTypes=territory+state+province+district. In the event the subdivision
         * type description is more than one word, separate the words with an underscore. As an example:
         * subdivisionTypes=outlying_area. The superset of subdivision types of supported geographies can be derived
         * from an unfiltered request to this endpoint.
         */
        String subdivisionTypes;
    }

    /////////////////////////
    // Admin API query models
    // https://api.askkodiak.com/doc/v2/#api-Admin
    /////////////////////////

    @lombok.Builder
    @lombok.Getter
    @lombok.Setter
    class ProductsQuery {

        /**
         * Filter response to include only products with the requested permission or permissions. Use + as separator to
         * include more than 1 permission code. Usage permission=staff or permission=staff+trusted. A filter of
         * productCodes=staff+trusted means all products which have read access granted to staff or trusted agencies.
         */
        String permissions;

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
         * resultant products) actions, coverageType, id, logo, name, ownerId, ownerLogo, and ownerName. If this
         * parameter is missing, excluded, or set to false all available properties will be present on products in the
         * response. Please Note: in the event that summaryOnly is set to true, the includeEligibility parameter will
         * be forced to false.
         */
        Boolean summaryOnly;

        /**
         * Filter response to include only products available in requested geography(s) and apply any conditional
         * rules which pertain to them. Use + as separator to include more than 1 ISO 3166-2 code. Usage geos=US-HI or
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
         * entityTypes=AS+CCORP means include products eligible to either 'Association' or 'C-Corporation' entity
         * types. A full list of valid entity types can be attained from ReferenceData/BusinessEntityTypes
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
         * scheduled locations. Usage vehicles=5
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
        String admitted;

        /**
         * Limits the query to a subset of products by filtering the response to include only the requested product(s)
         * in the response. Use + as separator to include more than product id. Usage
         * products=a61a1c21df9b4971a1b7e569014d128c or
         * products=a61a1c21df9b4971a1b7e569014d128c+c22624b9777d47e2914ffa27c733149b. A filter of
         * products=a61a1c21df9b4971a1b7e569014d128c+c22624b9777d47e2914ffa27c733149b means only include the products
         * with the id a61a1c21df9b4971a1b7e569014d128c OR c22624b9777d47e2914ffa27c733149b (i.e. no other products
         * will be present in the response).
         */
        String products;

        /**
         *
         * Filter response to include only products with eligibility for a specific custom (product owner defined)
         * classification code and apply conditional rules which pertain to any NAICS codes mapped to the custom code.
         * Specify classifications as a composite key made up of the taxonomy identifier (tid) to which the code
         * belongs and the code id (cid) itself delimited by a colon (:).
         *
         * As an illustrative example, to filter for only products eligible for bop code 12345 the value would be
         * bop:12345. More than one classification can be specified. In this event, use + as separator to include more
         * than on. For example bop:12345+bop:78901+wc:1111.
         *
         * Products not explicitly correlated by their owner to the requested taxonomy will not be returned even if
         * they are eligible for the NAICS code(s) the custom classification code(s) represents. Mappings from the
         * custom class code to NAICS are defined by the owner of the product.
         */
        String classifications;

        /**
         * Filter response to include only products with eligibility for a specific custom classification group - a
         * set of custom classification codes - and apply any conditional rules which pertain to NAICS classifications
         * mapped to the custom group. Specify classificationGroups as a composite key made up of the taxonomy
         * identifier (tid) to which the code belongs and the classification group id (cgid) itself delimited by a
         * colon (:).
         *
         * As an illustrative example, to filter for only products eligible for bop group retail the value would be
         * bop:retail. More than one classification group can be specified. In this event, use + as separator to
         * include more than on. For example bop:retail+bop:wholesale+wc:office.
         *
         * Products not explicitly correlated by their owner to the requested taxonomy will not be returned even if
         * they are eligible for the NAICS groups(s) the custom classification group(s) represents. Mappings from the
         * custom class code to NAICS are defined by the owner of the product.
         */
        String classificationGroups;

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

    /////////////////////////
    // Network API query models
    // https://api.askkodiak.com/doc/v2/#api-Network
    /////////////////////////

    @lombok.Builder
    @lombok.Getter
    @lombok.Setter
    class CompaniesTrustedByQuery {
        /**
         * Filters request by company type. Usage companyType=carrier or companyType=other. If this parameter is
         * omitted, all companies who trust your company on Ask Kodiak will be returned.
         */
        String companyType;

        /**
         * Optionally exclude companies whom your company has excluded from search results by preference. In other
         * words, for purposes of this request, ignore companies whom you're ignoring in Ask Kodiak search results.
         * Usage: ignoreExcludedCompanies=true. If omitted or if false this filter will not be applied to the response.
         */
        Boolean ignoreExcludedCompanies;
    }

}
