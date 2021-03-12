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

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Optional;

/**
 * An individual conditional rule.
 */
@lombok.Data
public class Rule {
    /**
     * The id's of conditional rules triggered by this request. Useful for debugging.
     */
    @JsonProperty("_triggered-by")
    private Map<String, Boolean> triggeredBy;

    /**
     * Items that this rule includes if any.
     */
    private RuleInclude include;

    /**
     * Items that this rule exclude if any.
     */
    private RuleExclude exclude;

    /**
     * Triggering conditions for the rule.
     */
    private RuleWhen when;

    /**
     * The id's of conditional rules triggered by this request. Useful for debugging.
     */
    public Optional<Map<String, Boolean>> getTriggeredBy() {
        return Optional.ofNullable(triggeredBy);
    }

    /**
     * Items that this rule includes if any.
     */
    public Optional<RuleInclude> getInclude() {
        return Optional.ofNullable(include);
    }

    /**
     * Items that this rule exclude if any.
     */
    public Optional<RuleExclude> getExclude() {
        return Optional.ofNullable(exclude);
    }

    /**
     * Triggering conditions for the rule.
     */
    public Optional<RuleWhen> getWhen() {
        return Optional.ofNullable(when);
    }
}
