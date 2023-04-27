package co.yml.ytag

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Application test
 *
 * @constructor Create empty Application test
 */
@RunWith(AndroidJUnit4::class)
class ApplicationTest {
    /**
     * Test app context
     *
     */
    @Test
    fun testAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertTrue(appContext.packageName.contains("co.yml.ytag"))
    }
}
