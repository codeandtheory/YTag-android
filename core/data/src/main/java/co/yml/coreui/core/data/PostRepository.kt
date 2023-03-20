package co.yml.coreui.core.data

import kotlinx.coroutines.flow.SharedFlow
import co.yml.coreui.core.common.AppResult
import co.yml.coreui.core.common.model.Post

interface PostRepository {
    /**
     * post flow object for getting all post data from locally.
     */
    val feed: SharedFlow<List<Post>>

    /**
     * adding post data into the remote and locally.
     */
    suspend fun add(postItem: Post)

    /**
     * deleting post data from the local repo.
     */
    suspend fun delete(postItem: Post)

    /**
     * Fetch all post data from remote server and push to the local database.
     */
    suspend fun loadInitialData(): AppResult<List<Post>?>
}
