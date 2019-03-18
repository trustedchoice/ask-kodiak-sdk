# Ask Kodiak Java SDK

## :warning: This is still in beta, as such, expect changes. :warning:

## Overview

The Ask Kodiak Java SDK is a straightforward Java implementation of the
[Ask Kodiak API](https://api.askkodiak.com/doc/v2) for JVM environments.
API keys are required to use, you can obtain those keys from Company
Settings once you've created an account with Ask Kodiak.

The SDK is implemented with [Feign](https://github.com/OpenFeign/feign).

## Getting Started
:construction: At the current time, you'll have to build this yourself,
however it will be available on Maven central in the future.
:construction:

## Building
```bash
$ ./gradlew build
```

## Supported Environments
The Ask Kodiak Java SDK supports Java version 8 and above.

## Documentation
**For a full list of options supported by each interface, see the
[Ask Kodiak API](https://api.askkodiak.com/doc/v2) documentation. **

### Basic Usage
To construct a client:

```java
AskKodiak askKodiak = AskKodiakClient.getInstance("GROUP ID", "API KEY");
```
:exclamation: The client constructs a
[Jackson ObjectMapper](https://github.com/FasterXML/jackson) for each
instance which may perform slowly on your first request due to initial
cache builds using reflection.  You will likely want to maintain your
instance as a singleton.

#### TODO document example method calls.

## License

Licensed under the MIT license