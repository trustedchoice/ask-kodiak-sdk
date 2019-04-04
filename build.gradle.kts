plugins {
    `java-library`
    `maven-publish`
    id("io.freefair.lombok") version "3.2.0"
}

group = "com.trustedchoice"
version = "1.0.0"

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
    }
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])
        }
    }
}

val jacksonVersion = "2.9.8"
val feignVersion = "10.2.0"
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
