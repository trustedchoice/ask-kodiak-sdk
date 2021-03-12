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

import java.util.List;
import java.util.Optional;

/**
 * Items that a rule includes if any.
 */
@lombok.Data
public class RuleInclude {
    /**
     * Highlights that this rule includes if any.
     */
    private List<String> highlights;

    /**
     * Guidelines that this rule includes if any.
     */
    private List<String> guidelines;

    /**
     * Notes that this rule includes if any.
     */
    private List<String> notes;

    /**
     * Collateral that this rule includes if any.
     */
    private List<Collateral> collateral;

    /**
     * Highlights that this rule includes if any.
     */
    public Optional<List<String>> getHighlights() {
        return Optional.ofNullable(highlights);
    }

    /**
     * Guidelines that this rule includes if any.
     */
    public Optional<List<String>> getGuidelines() {
        return Optional.ofNullable(guidelines);
    }

    /**
     * Notes that this rule includes if any.
     */
    public Optional<List<String>> getNotes() {
        return Optional.ofNullable(notes);
    }

    /**
     * Collateral that this rule includes if any.
     */
    public Optional<List<Collateral>> getCollateral() {
        return Optional.ofNullable(collateral);
    }
}
