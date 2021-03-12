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

import java.util.Optional;

@lombok.Data
public class Factors {

    /**
     * If present, indicates that a user-supplied preference for admitted or non admitted capacity may influence
     * applicability of the subject product.
     */
    private Boolean admitted;

    /**
     * If present, indicates that risk geography may also have bearing on the product.
     */
    private Boolean geos;

    /**
     * If present, indicates that NAICS code may have bearing on the product.
     */
    private Boolean naics;

    /**
     * If present, indicates that entity type (LLC, C-Corp, etc.) may also have bearing on the product.
     */
    private Boolean entityTypes;

    /**
     * If present, indicates that annual revenue may also have bearing on the product.
     */
    private Boolean annualRevenue;

    /**
     * If present, indicates that annual payroll may also have bearing on the product.
     */
    private Boolean annualPayroll;

    /**
     * If present, indicates that the number of full time employees may also have bearing on the product.
     */
    private Boolean fullTimeEmployees;

    /**
     * If present, indicates that the number of part time employees may also have bearing on the product.
     */
    private Boolean partTimeEmployees;

    /**
     * If present, indicates that the number of years in business may also have bearing on the product.
     */
    private Boolean yearsInBusiness;

    /**
     * If present, indicates that the number of years experience in the industry may also have bearing on the product.
     */
    private Boolean yearsInIndustry;

    /**
     * If present, indicates that total insured value may also have bearing on the product.
     */
    private Boolean tiv;

    /**
     * If present, indicates that the number of vehicles may also have bearing on the product.
     */
    private Boolean vehicles;

    /**
     * If present, indicates that the number of locations may also have bearing on the product.
     */
    private Boolean locations;

    /**
     * If present, indicates that the number of buildings may also have bearing on the product.
     */
    private Boolean buildings;

    /**
     * If present, indicates that square footage may also have bearing on the product.
     */
    private Boolean squareFootage;

    /**
     * If present, indicates that building age may also have bearing on the product.
     */
    private Boolean buildingAge;

    /**
     * If present, indicates that a user-supplied preference for admitted or non admitted capacity may influence
     * applicability of the subject product.
     */
    public Optional<Boolean> getAdmitted() {
        return Optional.ofNullable(admitted);
    }

    /**
     * If present, indicates that risk geography may also have bearing on the product.
     */
    public Optional<Boolean> getGeos() {
        return Optional.ofNullable(geos);
    }

    /**
     * If present, indicates that NAICS code may have bearing on the product.
     */
    public Optional<Boolean> getNaics() {
        return Optional.ofNullable(naics);
    }

    /**
     * If present, indicates that entity type (LLC, C-Corp, etc.) may also have bearing on the product.
     */
    public Optional<Boolean> getEntityTypes() {
        return Optional.ofNullable(entityTypes);
    }

    /**
     * If present, indicates that annual revenue may also have bearing on the product.
     */
    public Optional<Boolean> getAnnualRevenue() {
        return Optional.ofNullable(annualRevenue);
    }

    /**
     * If present, indicates that annual payroll may also have bearing on the product.
     */
    public Optional<Boolean> getAnnualPayroll() {
        return Optional.ofNullable(annualPayroll);
    }

    /**
     * If present, indicates that the number of full time employees may also have bearing on the product.
     */
    public Optional<Boolean> getFullTimeEmployees() {
        return Optional.ofNullable(fullTimeEmployees);
    }

    /**
     * If present, indicates that the number of part time employees may also have bearing on the product.
     */
    public Optional<Boolean> getPartTimeEmployees() {
        return Optional.ofNullable(partTimeEmployees);
    }

    /**
     * If present, indicates that the number of years in business may also have bearing on the product.
     */
    public Optional<Boolean> getYearsInBusiness() {
        return Optional.ofNullable(yearsInBusiness);
    }

    /**
     * If present, indicates that the number of years experience in the industry may also have bearing on the product.
     */
    public Optional<Boolean> getYearsInIndustry() {
        return Optional.ofNullable(yearsInIndustry);
    }

    /**
     * If present, indicates that total insured value may also have bearing on the product.
     */
    public Optional<Boolean> getTiv() {
        return Optional.ofNullable(tiv);
    }

    /**
     * If present, indicates that the number of vehicles may also have bearing on the product.
     */
    public Optional<Boolean> getVehicles() {
        return Optional.ofNullable(vehicles);
    }

    /**
     * If present, indicates that the number of locations may also have bearing on the product.
     */
    public Optional<Boolean> getLocations() {
        return Optional.ofNullable(locations);
    }

    /**
     * If present, indicates that the number of buildings may also have bearing on the product.
     */
    public Optional<Boolean> getBuildings() {
        return Optional.ofNullable(buildings);
    }

    /**
     * If present, indicates that square footage may also have bearing on the product.
     */
    public Optional<Boolean> getsquareFootage() {
        return Optional.ofNullable(squareFootage);
    }

    /**
     * If present, indicates that building age may also have bearing on the product.
     */
    public Optional<Boolean> getBuildingAge() {
        return Optional.ofNullable(buildingAge);
    }
}
