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

package com.trustedchoice.askkodiak.v2.model.naics;

import java.util.List;

/**
 * An object containing the detail associated with the requested group.
 */
@lombok.Data
public class NaicsGroup {

    /**
     * The NAICS code number associated with the group.
     */
    private String code;

    /**
     * The hashes of unique combinations of 6 digit national industry codes and a their sub-description that are
     * children of this group.
     */
    private List<String> codes;

    /**
     * The direct descendants of this group if any.
     */
    private List<String> decendants;

    /**
     * The immediate parent of this group in the NAICS hierarchy.
     */
    private String parent;

    /**
     * The NAICS Sequence number of this group.
     */
    private String seq;

    /**
     * A description of the operation of businesses in this group.
     */
    private String title;

    /**
     * The type of the group. one of sector, subsector, industry-group, international-industry, or national-industry.
     */
    private String type;

}
