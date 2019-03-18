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

import java.util.Optional;

/**
 * A singleton wholesaler member of the wholesalers object.
 */
@lombok.Data
public class Wholesaler {

    /**
     * The name of the wholesaler
     */
    private String company;

    /**
     * The name of an individual contact at the wholesaler.
     */
    private String contact;

    /**
     * A URL for this wholesaler
     */
    private String url;

    /**
     * A phone number for this wholesaler.
     */
    private String phone;

    /**
     * The name of the wholesaler
     */
    public Optional<String> getCompany() {
        return Optional.ofNullable(company);
    }

    /**
     * The name of an individual contact at the wholesaler.
     */
    public Optional<String> getContact() {
        return Optional.ofNullable(contact);
    }

    /**
     * A URL for this wholesaler
     */
    public Optional<String> getUrl() {
        return Optional.ofNullable(url);
    }

    /**
     * A phone number for this wholesaler.
     */
    public Optional<String> getPhone() {
        return Optional.ofNullable(phone);
    }


}
