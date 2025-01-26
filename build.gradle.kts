import io.gitlab.arturbosch.detekt.Detekt

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.detekt.plugin) apply true
}
dependencies {
    detektPlugins(libs.detekt)
}
detekt {
    toolVersion = "1.23.7"
    config.setFrom(file("detekt.yml"))
    baseline = file("detekt-baseline.xml")
    buildUponDefaultConfig = true
    val input = projectDir
    val exclude = listOf("**/build/**", "**/resources/**")
    source.setFrom(fileTree(input) {
        exclude(exclude)
    })
    parallel = true // Run Detekt in parallel
}

tasks.withType<Detekt>().configureEach {
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
    }
}
