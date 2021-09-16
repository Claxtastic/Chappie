import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.5.10"
}

group = "net.thomasclaxton"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
  maven("https://jitpack.io")
  maven("https://m2.dv8tion.net/releases")
  // Kord Snapshots Repository (Optional):
  maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
  compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  compileOnly("com.google.apis:google-api-services-youtube:v3-rev20210811-1.32.1")

  implementation("com.sedmelluq:lavaplayer:1.3.78")
  implementation("org.slf4j:slf4j-simple:1.7.32")
  implementation("dev.kord:kord-core:0.8.0-M5")
  implementation("com.github.sapher:youtubedl-java:1.+")

  testImplementation(kotlin("test"))
}

tasks.test {
  useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
  kotlinOptions.jvmTarget = "1.8"
}