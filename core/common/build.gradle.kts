import co.yml.ytag.jacoco.addExclusion
import co.yml.ytag.jacoco.setModuleTestCoverageLimits

plugins {
    id("co.yml.ytag.library")
    id("co.yml.ytag.library.jacoco")
    id("co.yml.ytag.hilt")
}

private val excludedFiles = mutableSetOf(
    "**/co.yml.ytag/core/common/model/*",
    "**/co.yml.ytag/core/common/di/*"
)
private val limits = mutableMapOf(
    "instruction" to 0.0,
    "branch" to 0.0,
    "line" to 0.0,
    "complexity" to 0.0,
    "method" to 0.0,
    "class" to 0.0
)
addExclusion(excludedFiles)
setModuleTestCoverageLimits(limits)

android {
    namespace = "co.yml.ytag.core.common"
}
