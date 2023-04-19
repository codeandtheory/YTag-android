package co.yml.coreui.common

import co.yml.coreui.core.common.di.DispatcherModule
import co.yml.coreui.core.common.di.DispatcherModule_ProvideNetworkDispatcherFactory.provideNetworkDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Test coroutine provider
 *
 * @constructor Create empty Test coroutine provider
 */
class TestCoroutineProvider {

    /**
     * Result_catches_errors
     *
     */
    @Test
    fun result_catches_errors() = runTest {
        val dispatcher = provideNetworkDispatcher(DispatcherModule())
        assertTrue(dispatcher != null)
    }
}
