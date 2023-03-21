import co.yml.coreui.jacoco.addExclusion
import co.yml.coreui.jacoco.setModuleTestCoverageLimits

plugins {
    id("co.yml.coreui.library")
    id("co.yml.coreui.library.jacoco")
    id("co.yml.coreui.hilt")
}

private val limits = mutableMapOf(
    "instruction" to 0.0,
    "branch" to 0.0,
    "line" to 0.0,
    "complexity" to 0.0,
    "method" to 0.0,
    "class" to 0.0
)
setModuleTestCoverageLimits(limits)

android {
    namespace = "co.yml.coreui.core.common"
}
