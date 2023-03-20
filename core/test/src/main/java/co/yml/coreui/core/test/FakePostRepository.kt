package co.yml.coreui.core.test

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import co.yml.coreui.core.common.AppResult
import co.yml.coreui.core.common.model.Post
import co.yml.coreui.core.data.PostRepository
import co.yml.coreui.core.data.util.map
import co.yml.coreui.core.data.util.toPostEntityList
import co.yml.coreui.core.data.util.toPostList

/**
 * Fake post repository
 *
 * @constructor Create empty Fake post repository
 */
class FakePostRepository(val fakePostDao: FakePostDao = FakePostDao(), val fakePostApi: FakePostApi = FakePostApi()) : PostRepository {

    private val _feed = MutableSharedFlow<List<Post>>()
    override val feed: SharedFlow<List<Post>> = _feed.asSharedFlow()

    override suspend fun add(post: Post) {
        fakePostDao.insert(post.map())
        emitData()
    }

    override suspend fun delete(postItem: Post) {
        fakePostDao.deleteItem(postItem.id)
        emitData()
    }

    override suspend fun loadInitialData(): AppResult<List<Post>?> {
        return try {
            val remoteData = fakePostApi.get()
            fakePostDao.deleteAll()
            if (remoteData != null) {
                fakePostDao.insert(remoteData.toPostList().toPostEntityList())
                emitData()
                AppResult.Success(remoteData.toPostList())
            } else {
                emitData()
                AppResult.Error((remoteData as AppResult.Error).exception)
            }
        } catch (ex: Exception) {
            emitData()
            AppResult.Error(ex)
        }
    }

    /**
     * Emit data
     *
     */
    suspend fun emitData() {
        _feed.emit(fakePostDao.getAllPost().toPostList())
    }
}
