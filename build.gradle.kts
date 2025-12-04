plugins {
    `java-library`
    /*
     * The Maven Publish Plugin provides the ability to publish build artifacts to an Apache Maven repository
     * Docs: https://docs.gradle.org/current/userguide/publishing_maven.html
     */
    id("maven-publish")
    /*
     * The Signing Plugin adds the ability to digitally sign built files and artifacts
     * Docs: https://docs.gradle.org/current/userguide/signing_plugin.html
     */
    id("signing")
    id("io.freefair.lombok") version "3.2.0"
}

group = "com.trustedchoice"
version = "3.0.5"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

lombok {
    config.put("lombok.addLombokGeneratedAnnotation", "true")
}

tasks.register<Jar>("sourcesJar") {
    dependsOn(JavaPlugin.CLASSES_TASK_NAME)
    from(sourceSets.main.get().allJava)
    archiveClassifier.set("sources")
}

tasks.register<Jar>("javadocJar") {
    dependsOn(JavaPlugin.JAVADOC_TASK_NAME)
    from(tasks["javadoc"])
    archiveClassifier.set("javadoc")
}

// add licensing information to all artifacts
tasks.withType<Jar> {
    from(project.rootProject.projectDir) {
        include("LICENSE")
        into("META-INF")
    }
}

publishing {
    repositories {
        mavenLocal()
        maven {
            name = "SonaTypeOSSRH"
            url = uri("https://ossrh-staging-api.central.sonatype.com/service/local/staging/deploy/maven2")
            credentials {
                username = project.findProperty("ossrhUsername")?.toString()
                password = project.findProperty("ossrhPassword")?.toString()
            }
        }
    }

    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

            if (project.hasProperty("signing.keyId")) {
                signing {
                    sign(publishing.publications["mavenJava"])
                }
            }

            //Complying with pom requirements for maven central
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
                    developers {
                        developer {
                            id.set("aweigold")
                            name.set("Adam J. Weigold")
                            email.set("adam.weigold@trustedchoice.com")
                        }
                    }
                    scm {
                        url.set("https://github.com/trustedchoice/ask-kodiak-sdk")
                    }
                }
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

    compileOnly("org.projectlombok:lombok:1.18.6")
    annotationProcessor("org.projectlombok:lombok:1.18.6")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.0")

    testImplementation("ch.qos.logback:logback-classic:1.2.3")
}
