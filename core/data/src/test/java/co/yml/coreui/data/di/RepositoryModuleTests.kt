package co.yml.coreui.data.di

import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Test
import co.yml.coreui.core.data.PostRepositoryImpl
import co.yml.coreui.core.data.local.LocalPostDataSourceImpl
import co.yml.coreui.core.data.remote.RemotePostDataSourceImpl
import co.yml.coreui.core.test.FakePostApi
import co.yml.coreui.core.test.FakePostDao
import kotlin.test.assertTrue

class RepositoryModuleTests {
    private val fakePostApi = FakePostApi()
    private val fakePostDao = FakePostDao()

    @Test
    fun `test repository `() {
        val repo = PostRepositoryImpl(UnconfinedTestDispatcher(), LocalPostDataSourceImpl(fakePostDao), RemotePostDataSourceImpl(fakePostApi))
        assertTrue(repo != null)
    }

    @Test
    fun `test local repository `() {
        val repo = LocalPostDataSourceImpl(fakePostDao)
        assertTrue(repo != null)
    }

    @Test
    fun `test remote repository `() {
        val repo = RemotePostDataSourceImpl(fakePostApi)
        assertTrue(repo != null)
    }
}
