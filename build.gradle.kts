import java.security.MessageDigest

plugins {
    `java-library`
    id("maven-publish")
    id("signing")
    id("io.freefair.lombok") version "8.10.2"
}

group = "com.momentumedge"
version = "3.0.6"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
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
    }

    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

            pom {
                name.set("ask-kodiak-sdk")
                description.set("The Ask Kodiak Java SDK is a straightforward Java implementation of the Ask Kodiak API for JVM environments.")
                url.set("https://github.com/momentumedge/ask-kodiak-sdk")
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
                        email.set("adam.weigold@momentumamp.com")
                    }
                }
                scm {
                    url.set("https://github.com/momentumedge/ask-kodiak-sdk")
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}

// Generates md5 and sha1 checksums for all artifacts that will go into the bundle.
// Maven Central Portal requires these — it does NOT generate them automatically.
tasks.register("generateChecksums") {
    group = "publishing"
    dependsOn("signMavenJavaPublication")

    doLast {
        val artifactVersion = project.version.toString()
        val artifactId = "ask-kodiak-sdk"
        val libsDir = layout.buildDirectory.dir("libs").get().asFile
        val pubDir = layout.buildDirectory.dir("publications/mavenJava").get().asFile

        val filesToChecksum = mapOf(
            libsDir.resolve("$artifactId-$artifactVersion.jar") to null,
            libsDir.resolve("$artifactId-$artifactVersion-sources.jar") to null,
            libsDir.resolve("$artifactId-$artifactVersion-javadoc.jar") to null,
            // POM lives under a different name — checksum files must match its renamed form in the bundle
            pubDir.resolve("pom-default.xml") to "$artifactId-$artifactVersion.pom",
        )

        filesToChecksum.forEach { (file, outputBaseName) ->
            val bytes = file.readBytes()
            val baseName = outputBaseName ?: file.name

            val md5 = MessageDigest.getInstance("MD5").digest(bytes)
                .joinToString("") { byte -> "%02x".format(byte) }
            file.resolveSibling("$baseName.md5").writeText(md5)

            val sha1 = MessageDigest.getInstance("SHA-1").digest(bytes)
                .joinToString("") { byte -> "%02x".format(byte) }
            file.resolveSibling("$baseName.sha1").writeText(sha1)
        }
    }
}

// Creates the bundle ZIP required by the Maven Central Portal Publisher API.
// Upload the output with:
//   TOKEN=$(echo -n "$ossrhUsername:$ossrhPassword" | base64)
//   curl --request POST \
//     --header "Authorization: Bearer $TOKEN" \
//     --form bundle=@build/bundle/central-portal-bundle.zip \
//     "https://central.sonatype.com/api/v1/publisher/upload?name=ask-kodiak-sdk-VERSION&publishingType=USER_MANAGED"
tasks.register<Zip>("createCentralPortalBundle") {
    group = "publishing"
    description = "Bundles signed artifacts for upload to Maven Central Portal"

    dependsOn("generateChecksums")

    val artifactVersion = project.version.toString()
    val artifactGroup = project.group.toString()
    val artifactId = "ask-kodiak-sdk"
    val basePath = "${artifactGroup.replace(".", "/")}/$artifactId/$artifactVersion"

    archiveFileName.set("central-portal-bundle.zip")
    destinationDirectory.set(layout.buildDirectory.dir("bundle"))

    into(basePath) {
        from(layout.buildDirectory.dir("libs")) {
            include("*.jar", "*.jar.asc", "*.jar.md5", "*.jar.sha1")
        }
        from(layout.buildDirectory.dir("publications/mavenJava")) {
            include("pom-default.xml")
            rename { "$artifactId-$artifactVersion.pom" }
        }
        from(layout.buildDirectory.dir("publications/mavenJava")) {
            include("pom-default.xml.asc")
            rename { "$artifactId-$artifactVersion.pom.asc" }
        }
        // POM checksums are written with the renamed basename so they can be included directly
        from(layout.buildDirectory.dir("publications/mavenJava")) {
            include("$artifactId-$artifactVersion.pom.md5", "$artifactId-$artifactVersion.pom.sha1")
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
