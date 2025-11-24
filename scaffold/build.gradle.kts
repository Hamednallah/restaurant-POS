plugins {
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

dependencies {
    implementation("org.kordamp.ikonli:ikonli-javafx:12.4.0")
    implementation("org.kordamp.ikonli:ikonli-fontawesome5-pack:12.4.0")
    implementation("io.github.mkpaz:atlantafx-base:2.1.0")
    implementation("org.xerial:sqlite-jdbc:3.40.0.0")
    implementation("org.openjfx:javafx-controls:21.0.2")
    implementation("org.openjfx:javafx-fxml:21.0.2")
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

javafx {
    modules("javafx.controls", "javafx.fxml")
}

tasks.test {
    useJUnitPlatform()
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }
}
