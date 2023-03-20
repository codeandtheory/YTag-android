package co.yml.coreui.core.data.remote

import co.yml.coreui.core.common.AppResult
import co.yml.coreui.core.common.model.Post
import co.yml.coreui.core.data.util.toPostList
import co.yml.coreui.core.network.apis.PostApi
import javax.inject.Inject

/**
 * Remote post data source impl
 *
 * @property api
 * @constructor Create empty Remote post data source impl
 */
class RemotePostDataSourceImpl @Inject constructor(private val api: PostApi) :
    RemotePostDataSource {
    override suspend fun fetchAllPost(): AppResult<List<Post>?> {
        val data = try {
            val data: List<Post>? = api.get()?.toPostList()
            AppResult.Success(data)
        } catch (exc: Exception) {
            AppResult.Error(exc)
        }
        return data
    }

    override suspend fun addPost(postRequest: Post): AppResult<Boolean> {
        // todo implemenet post api
        return AppResult.Loading
    }
}
