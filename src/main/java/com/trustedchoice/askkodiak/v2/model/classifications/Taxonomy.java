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

package com.trustedchoice.askkodiak.v2.model.classifications;

import java.util.Optional;

@lombok.Data
public class Taxonomy {
    /**
     * Identifer for taxonomy object. It can be used in subsequent requests to acquire additional detail including
     * child codes and mappings.
     */
    private String id;

    /**
     * The title of the taxonomy
     */
    private String title;

    /**
     * A user-oriented description of the taxonomy.
     */
    private String description;

    /**
     * A user-oriented description of the taxonomy.
     */
    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }
}
