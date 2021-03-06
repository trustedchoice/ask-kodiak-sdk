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

package com.trustedchoice.askkodiak.v2.model.mapping;

import com.trustedchoice.askkodiak.v2.model.classifications.Taxonomy;

import java.util.List;

@lombok.Data
public class ClassificationCodeMappings {
    /**
     * A restatement of the NAICS identifier provided in the request
     */
    private String naics;

    /**
     * The type of NAICS identifier provided. One of 'sector', 'subsector', 'industry-group', 'international-industry',
     * 'national-industry', or 'code'
     */
    private String type;

    /**
     * A text description of the NAICS identifier provided on the request
     */
    private String title;

    /**
     * The number of custom classification code mappings matched to the requested NAICS
     */
    private Integer count;

    /**
     * A restatement of the ownerId identifier provided in the request
     */
    private String ownerId;

    /**
     * The name of the group that owns the custom classification taxonomy to which the request applies.
     */
    private String ownerName;

    /**
     * Summary of the taxonomy for which the response applies.
     */
    private Taxonomy taxonomy;

    /**
     * A list of custom classification code objects which map to the requested NAICS
     */
    private List<ClassificationCodeMapping> mappings;
}
