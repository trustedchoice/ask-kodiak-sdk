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

package com.trustedchoice.askkodiak.v2.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Optional;

@lombok.Data
public class TimestampUidPair {

    /**
     * The Ask Kodiak UID of the user associated with the timestamp
     */
    private String by;

    /**
     * Timestamp represented with an action, event, or change.
     */
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT, pattern="s")
    private Timestamp on;

    /**
     * The Ask Kodiak UID of the user associated with the timestamp
     *
     * @return an Ask Kodiak UID
     */
    public Optional<String> getBy() {
        return Optional.ofNullable(by);
    }

    /**
     * Timestamp represented with an action, event, or change.
     *
     * @return a timestamp
     */
    public Optional<Timestamp> getOn() {
        return Optional.ofNullable(on);
    }
}
