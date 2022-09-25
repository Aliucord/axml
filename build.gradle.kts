group = "com.aliucord"
version = "1.0.1"

plugins {
    id("java-library")
    id("maven-publish")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.1")
}

java {
    withSourcesJar()
}

publishing {
    publications {
        register("axml", MavenPublication::class) {
            artifactId = "axml"
            artifact(tasks["jar"])
            artifact(tasks["sourcesJar"])
        }
    }

    repositories {
        val username = System.getenv("MAVEN_USERNAME")
        val password = System.getenv("MAVEN_PASSWORD")

        if (username != null && password != null) {
            maven {
                credentials {
                    this.username = username
                    this.password = password
                }
                setUrl("https://maven.aliucord.com/snapshots")
            }
        } else {
            mavenLocal()
        }
    }
}
