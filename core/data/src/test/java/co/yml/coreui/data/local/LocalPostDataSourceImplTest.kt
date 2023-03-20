package co.yml.coreui.data.local

import kotlinx.coroutines.runBlocking
import org.junit.Test
import co.yml.coreui.core.common.model.Post
import co.yml.coreui.core.data.local.LocalPostDataSourceImpl
import co.yml.coreui.core.database.model.PostEntity
import co.yml.coreui.core.test.FakePostDao
import kotlin.test.assertTrue

class LocalPostDataSourceImplTest {
    private val fakePostDao = FakePostDao()

    @Test
    fun `test local repository `() {
        val repo = LocalPostDataSourceImpl(fakePostDao)
        assertTrue(repo != null)
    }

    @Test
    fun `test local repository get Items`() = runBlocking {
        fakePostDao.insert(PostEntity(1, 1, "title", "body"))
        val repo = LocalPostDataSourceImpl(fakePostDao)
        val result = repo.getPost()
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `test local repository add Item`() = runBlocking {
        val repo = LocalPostDataSourceImpl(fakePostDao)
        repo.add(Post(1, 1, "title", "body"))
        val result = repo.getPost()
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `test local repository add Items`() = runBlocking {
        val repo = LocalPostDataSourceImpl(fakePostDao)
        val sampleData = listOf<Post>(Post(1, 1, "title", "body"), Post(2, 2, "title", "body"), Post(3, 3, "title", "body"))
        repo.addAll(sampleData)
        val result = repo.getPost()
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `test local repository delete all items`() = runBlocking {
        val repo = LocalPostDataSourceImpl(fakePostDao)
        val sampleData = listOf<Post>(Post(1, 1, "title", "body"), Post(2, 2, "title", "body"), Post(3, 3, "title", "body"))
        repo.addAll(sampleData)
        val result = repo.getPost()
        assertTrue(result.isNotEmpty())
        repo.deleteAll()
        val newResult = repo.getPost()
        assertTrue(newResult.isEmpty())
    }
}
