/*
 * MIT License
 *
 * Copyright (c) 2025 Consumer Agent Portal, LLC (TrustedChoice.com)
 *                    Superkick Ventures, LLC (Ask Kodiak)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * ...
 */

package com.trustedchoice.askkodiak.v2.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.util.regex.Pattern;

/**
 * Appends the configured NAICS edition query parameter to AskKodiak v2 NAICS endpoints.
 * Thread-safe and side-effect free (no logging).
 */
public final class NaicsEditionRequestInterceptor implements RequestInterceptor {

    // Match AK v2 NAICS endpoints (absolute or relative) so the interceptor can add naicsEdition.
    // Covers:
    //   • /v2/naics/...           (e.g., /v2/naics/codes, /v2/naics/code/212111, etc.)
    //   • /v2/suggest/naics-codes/{term}
    // Allows an optional leading scheme/host (http[s]://...) and an optional ?query at the end.
    // Intentionally excludes non-v2 paths and other suggest routes.
    private static final Pattern NAICS_PATHS = Pattern.compile(
            "^(?:https?://[^/]+)?/v2/(?:naics(?:/.*)?|suggest/naics-codes(?:/[^?]+)?)(?:\\?.*)?$"
    );

    private final String edition;

    public NaicsEditionRequestInterceptor(String edition) {
        this.edition = edition;
    }

    @Override
    public void apply(RequestTemplate template) {
        // Normalize to path-only so regex works for absolute URLs too
        String full = template.url();
        String path = full.startsWith("http")
                ? full.replaceFirst("^https?://[^/]+", "")
                : full;

        if (NAICS_PATHS.matcher(path).matches()
                && !template.queries().containsKey("naicsEdition")
                && edition != null && !edition.isEmpty()) {
            template.query("naicsEdition", edition);
        }
    }
}
