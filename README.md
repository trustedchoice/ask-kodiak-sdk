# Ask Kodiak Java SDK

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Javadocs](http://javadoc.io/badge/com.trustedchoice/ask-kodiak-sdk.svg)](http://javadoc.io/doc/com.trustedchoice/ask-kodiak-sdk)
[![MavenCentral](http://img.shields.io/badge/Maven_Central-1.0.1-yellow.svg)](https://oss.sonatype.org/content/groups/public/com/trustedchoice/ask-kodiak-sdk/)

## Overview

The Ask Kodiak Java SDK is a straightforward Java implementation of the
[Ask Kodiak API](https://api.askkodiak.com/doc/v2) for JVM environments.
API keys are required to use, you can obtain those keys from Company
Settings once you've created an account with Ask Kodiak.

The SDK is implemented with [Feign](https://github.com/OpenFeign/feign).

## Getting Started
Maven:
```xml
<dependency>
  <groupId>com.trustedchoice</groupId>
  <artifactId>ask-kodiak-sdk</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```
Gradle:
```groovy
compile 'com.trustedchoice:ask-kodiak-sdk:1.0.1'
```

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