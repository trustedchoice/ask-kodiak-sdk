plugins {
    `java-library`
    id("maven-publish")
    id("signing") // enabled, but will only sign if keys exist
}

group = "com.trustedchoice"
version = "3.0.5"

repositories { mavenCentral() }

java {
    // Build with JDK 17 toolchain…
    toolchain { languageVersion.set(JavaLanguageVersion.of(17)) }
    // …but emit Java 8 bytecode if your consumers are still on 1.8:
    tasks.withType<JavaCompile>().configureEach {
        options.release.set(8)
        options.encoding = "UTF-8"
    }
    withSourcesJar()
    withJavadocJar()
}

tasks.test { useJUnitPlatform() }

// Add LICENSE into jars
tasks.withType<Jar>().configureEach {
    from(project.rootProject.projectDir) {
        include("LICENSE")
        into("META-INF")
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            pom {
                name.set("ask-kodiak-sdk")
                description.set("The Ask Kodiak Java SDK is a straightforward Java implementation of the Ask Kodiak API for JVM environments.")
                url.set("https://github.com/trustedchoice/ask-kodiak-sdk")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("http://www.opensource.org/licenses/mit-license.php")
                        distribution.set("repo")
                    }
                }
                developers {
                    developer {
                        id.set("aweigold")
                        name.set("Adam J. Weigold")
                        email.set("adam.weigold@trustedchoice.com")
                    }
                }
                scm { url.set("https://github.com/trustedchoice/ask-kodiak-sdk") }
            }
        }
    }
}
val jacksonVersion = "2.9.8"
val feignVersion = "11.1"
val slf4jVersion = "1.7.26"

dependencies {
    api("com.fasterxml.jackson.core:jackson-core:$jacksonVersion")
    api("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    api("io.github.openfeign:feign-core:$feignVersion")
    api("io.github.openfeign:feign-jackson:$feignVersion")
    api("io.github.openfeign:feign-slf4j:$feignVersion")
    api("org.slf4j:slf4j-api:$slf4jVersion")

    // Lombok via deps (no Freefair plugin)
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")
    testCompileOnly("org.projectlombok:lombok:1.18.32")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.32")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.0")
    testImplementation("ch.qos.logback:logback-classic:1.2.3")

    repositories {
        // Always allow local testing:
        mavenLocal()

        // Remote repo (enable only when creds are present)
        // If you use Sonatype OSSRH, prefer s01 host for newer projects:
        if (findProperty("ossrhUsername") != null && findProperty("ossrhPassword") != null) {
            maven {
                name = "OSSRH"
                // For older projects you may still be on oss.sonatype.org; adjust as needed.
                setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2")
                credentials {
                    username = findProperty("ossrhUsername") as String
                    password = findProperty("ossrhPassword") as String
                }
            }
        }
    }
}

// Only sign if signing keys are provided (so local dev isn't blocked)
if (findProperty("signing.keyId") != null) {
    signing {
        sign(publishing.publications["mavenJava"])
    }
}
