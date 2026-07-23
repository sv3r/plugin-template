plugins {
    java
    alias(libs.plugins.run.paper)
}

val javaVersion = providers.gradleProperty("java.version").get().toInt()
val minecraftVersion = providers.gradleProperty("minecraft.version").get()

group = providers.gradleProperty("maven.group").get()
version = providers.gradleProperty("plugin.version").get()

repositories {
    mavenCentral()
    maven {
        name = "PaperMC"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    // JUnit
    testImplementation(platform(libs.junit.bom))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // PaperMC
    compileOnly(libs.paper.api)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }
}

tasks.processResources {
    val properties = mapOf(
        "name" to project.name,
        "version" to project.version.toString(),
        "main" to providers.gradleProperty("plugin.main").get(),
        "description" to providers.gradleProperty("plugin.description").get(),
        "author" to providers.gradleProperty("plugin.author").get(),
        "website" to providers.gradleProperty("plugin.website").get(),
        "minecraftVersion" to minecraftVersion,
    )

    inputs.properties(properties)

    filesMatching("plugin.yml") {
        expand(properties)
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

tasks.withType<AbstractArchiveTask>().configureEach {
    isPreserveFileTimestamps = false
    isReproducibleFileOrder = true
}

tasks.jar {
    from(rootProject.file("LICENSE")) {
        into("META-INF")
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.runServer {
    minecraftVersion(minecraftVersion)
}
