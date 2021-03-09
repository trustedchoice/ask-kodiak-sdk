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

package com.trustedchoice.askkodiak.v2.model.referral;

import java.util.Map;
import java.util.Optional;

/**
 * Data about the referral as an object.
 */
@lombok.Data
public class Referral {
    /**
     * An object documenting the properties of the query string on the source url (if any) at the time of the referral.
     */
    private Map<String, String> search;

    /**
     * The URL from which the referral took place.
     */
    private String source;

    /**
     * The URL to which the referral was directed.
     */
    private String url;

    /**
     * Data about the user who was referred from Ask Kodiak to the referral URL
     */
    private User user;

    /**
     * An object documenting the properties of the query string on the source url (if any) at the time of the referral.
     */
    public Optional<Map<String, String>> getSearch() {
        return Optional.ofNullable(search);
    }

    /**
     * The URL from which the referral took place.
     */
    public Optional<String> getSource() {
        return Optional.ofNullable(source);
    }

    /**
     * The URL to which the referral was directed.
     */
    public Optional<String> getUrl() {
        return Optional.ofNullable(url);
    }

    /**
     * Data about the user who was referred from Ask Kodiak to the referral URL
     */
    public Optional<User> getUser() {
        return Optional.ofNullable(user);
    }
}
