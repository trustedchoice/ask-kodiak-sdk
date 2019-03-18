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

import com.trustedchoice.askkodiak.v2.model.Range;

import java.util.Optional;

/**
 * An object describing the min/max full and part time employees of a company eligible for a product.
 */
@lombok.Data
public class Employees {

    /**
     * An object describing the number of full time employees of a company eligible for this product.
     */
    private Range fullTime;

    /**
     * An object describing the number of part time employees of a company eligible for this product.
     */
    private Range partTime;

    /**
     * An object describing the number of full time employees of a company eligible for this product.
     */
    public Optional<Range> getFullTime() {
        return Optional.ofNullable(fullTime);
    }

    /**
     * An object describing the number of part time employees of a company eligible for this product.
     */
    public Optional<Range> getPartTime() {
        return Optional.ofNullable(partTime);
    }

}
