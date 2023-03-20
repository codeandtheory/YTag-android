package co.yml.coreui.data.remote

import kotlinx.coroutines.runBlocking
import org.junit.Test
import co.yml.coreui.core.common.AppResult
import co.yml.coreui.core.common.model.Post
import co.yml.coreui.core.data.remote.RemotePostDataSourceImpl
import co.yml.coreui.core.test.FakePostApi
import co.yml.coreui.core.test.PlaceHolderData
import kotlin.test.assertTrue

class RemotePostDataSourceImplTest {
    @Test
    fun `test remote repository `() {
        val fakePostApi = FakePostApi()
        val repo = RemotePostDataSourceImpl(fakePostApi)
        assertTrue(repo != null)
    }

    @Test
    fun `test remote repository fetch all posts`() = runBlocking {
        val fakePostApi = FakePostApi()
        fakePostApi.remotePostDataMock.setExpectSuccess()
        fakePostApi.remotePostDataMock.setExpectedResponse(PlaceHolderData.DUMMY_DATA_GET_ALL_POST)
        val repo = RemotePostDataSourceImpl(fakePostApi)
        val result = repo.fetchAllPost()
        assert(result is AppResult.Success)
    }

    @Test
    fun `test remote repository fetch all posts error scenario`() = runBlocking {
        val fakePostApi = FakePostApi()
        val repo = RemotePostDataSourceImpl(fakePostApi)
        fakePostApi.remotePostDataMock.setExpectFailure()
        fakePostApi.remotePostDataMock.setExpectedResponse(null)

        val result = repo.fetchAllPost()
        assert(result is AppResult.Error)
    }

    @Test
    fun `test remote repository add post`() = runBlocking {
        val fakePostApi = FakePostApi()
        val repo = RemotePostDataSourceImpl(fakePostApi)
        val result = repo.addPost(Post(1, 1, "title", "body"))
        assertTrue(result is AppResult.Loading)
    }
}
