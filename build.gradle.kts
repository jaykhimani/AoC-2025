plugins {
    kotlin("jvm") version "2.2.21"
}

sourceSets {
    main {
        kotlin.srcDir("src/main/kotlin")
        resources.srcDir("src/main/resources")
    }
}

tasks {
    wrapper {
        gradleVersion = "9.2.1"
    }
}
