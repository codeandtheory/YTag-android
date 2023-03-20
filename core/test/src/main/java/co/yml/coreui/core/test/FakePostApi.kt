package co.yml.coreui.core.test

import io.ktor.client.call.*
import io.ktor.client.request.*
import co.yml.coreui.core.network.Routes
import co.yml.coreui.core.network.apis.PostApi
import co.yml.coreui.core.network.model.PostDTO
import co.yml.coreui.core.network.model.PostRequest

/**
 * Fake post api
 *
 * @constructor Create empty Fake post api
 */
class FakePostApi : PostApi {
    val remotePostDataMock = RemotePostDataMock()

    override suspend fun get(): List<PostDTO>? {
        val data: List<PostDTO> = remotePostDataMock.httpClient.get {
            url(Routes.POST)
        }.body()
        return data
    }

    override suspend fun post(postRequest: PostRequest): Boolean {
        val data: Boolean = remotePostDataMock.httpClient.post {
            url(Routes.POST)
        }.body()
        return data
    }
}
