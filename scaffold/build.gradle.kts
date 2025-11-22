plugins {
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.13"
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    implementation("org.xerial:sqlite-jdbc:3.40.0.0")
    implementation("org.openjfx:javafx-controls:17.0.2")
    implementation("org.openjfx:javafx-fxml:17.0.2")
    implementation("org.mindrot:jbcrypt:0.4")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("org.slf4j:slf4j-simple:2.0.7")

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.testfx:testfx-junit5:4.0.16-alpha")
    testImplementation("org.mockito:mockito-core:5.3.1")
}

application {
    mainClass.set("com.example.restpos.MainApp")
}

tasks.test {
    useJUnitPlatform()
}
