package co.yml.coreui.core.network.apis

import co.yml.coreui.core.network.model.PostDTO
import co.yml.coreui.core.network.model.PostRequest

/**
 * RemotePostDataSource will take care all post related remote operation.
 */
interface PostApi {
    /**
     * get, will fetch all data from remote server
     */
    suspend fun get(): List<PostDTO>?

    /**
     * post will add post data to remote server.
     */
    suspend fun post(postRequest: PostRequest): Boolean
}
