plugins {
    kotlin("jvm")
    id("maven-publish")
}

val GROUP_ID: String = project.properties["group.id"].toString()
val VERSION: String = project.properties["version"].toString()
val ARTIFACT_ID = "AutoBuilder.Processor"

group = GROUP_ID
version = VERSION

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(project(":AutoBuilder.Annotations"))
    implementation(kotlin("stdlib"))
    implementation("com.squareup:kotlinpoet:1.17.0")
    implementation("com.squareup:kotlinpoet-ksp:1.17.0")
    implementation("com.google.devtools.ksp:symbol-processing-api:2.0.0-1.0.21")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(19)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = GROUP_ID
            artifactId = ARTIFACT_ID
            version = VERSION
        }
    }
    repositories {
        mavenLocal()
    }
}