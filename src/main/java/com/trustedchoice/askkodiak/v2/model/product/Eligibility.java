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

/**
 * An Ask Kodiak eligible object.
 */
@lombok.Data
public class Eligibility {
    /**
     * A restatement of the product id to which this response applies.
     */
    private String id;

    /**
     * Conditions (e.g. class, geography, etc) which have been contemplated in coming to a determination of eligibility.
     */
    private Filters filters;

    /**
     * A true/false indication if the product is eligible given the combination of conditions specified in the initial request.
     */
    private Boolean eligible;

    /**
     * Conditions (e.g. class, geography, etc) which have been contemplated in coming to a determination of eligibility.
     */
    public Optional<Filters> getFilters() {
        return Optional.ofNullable(filters);
    }

    /**
     * A true/false indication if the product is eligible given the combination of conditions specified in the initial request.
     */
    public Optional<Boolean> getEligible() {
        return Optional.ofNullable(eligible);
    }
}
