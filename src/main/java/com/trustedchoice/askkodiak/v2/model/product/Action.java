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

import java.util.Map;
import java.util.Optional;

/**
 * Actions a user can take to get access to a product.
 */
@lombok.Data
public class Action {

    /**
     * Free form text contact information.
     */
    private String contact;

    /**
     * Free form text representing how the user should go about getting access to this product.
     */
    private String other;

    /**
     * The target URL of this product.
     */
    private String url;

    /**
     * A map containing information about which wholesalers can provide access to this product.
     */
    private Map<String, Wholesaler> wholesalers;

    /**
     * Free form text contact information.
     */
    public Optional<String> getContact() {
        return Optional.ofNullable(contact);
    }

    /**
     * Free form text representing how the user should go about getting access to this product.
     */
    public Optional<String> getOther() {
        return Optional.ofNullable(other);
    }

    /**
     * The target URL of this product.
     */
    public Optional<String> getUrl() {
        return Optional.ofNullable(url);
    }

    /**
     * A list containing information about which wholesalers can provide access to this product.
     */
    public Optional<Map<String, Wholesaler>> getWholesalers() {
        return Optional.ofNullable(wholesalers);
    }

}
