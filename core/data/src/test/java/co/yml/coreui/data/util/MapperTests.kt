package co.yml.coreui.data.util

import org.junit.Test
import co.yml.coreui.core.common.model.Post
import co.yml.coreui.core.data.util.map
import co.yml.coreui.core.data.util.toPostEntityList
import co.yml.coreui.core.data.util.toPostList
import co.yml.coreui.core.data.util.toPostRequest
import co.yml.coreui.core.database.model.PostEntity
import co.yml.coreui.core.network.model.PostDTO
import co.yml.coreui.core.network.model.PostRequest
import kotlin.test.assertTrue

class MapperTests {

    @Test
    fun `test PostDtoMap `() {
        val post = PostDTO(0, 1, "title", "body").map()
        assertTrue(post != null)
    }

    @Test
    fun `test Post Map `() {
        val post = Post(0, 1, "title", "body").map()
        assertTrue(post != null)
    }

    @Test
    fun `test Post Entity Map `() {
        val post = PostEntity(0, 1, "title", "body").map()
        assertTrue(post != null)
    }

    @Test
    fun `test Post toPostRequest `() {
        val post = Post(0, 1, "title", "body").toPostRequest()
        assertTrue(post is PostRequest)
    }

    @Test
    fun `test PostList toPostEntityList `() {
        val postList = listOf<Post>(Post(0, 1, "title", "body"), Post(1, 1, "title1", "body1"), Post(0, 2, "title2", "body2"), Post(3, 1, "title3", "body3"))
        val postEntityList = postList.toPostEntityList()
        assertTrue(postEntityList.isNotEmpty())
    }

    @Test
    fun `test PostList toPostEntityList when list is empty`() {
        val postList = listOf<Post>()
        val postEntityList = postList.toPostEntityList()
        assertTrue(postEntityList.isEmpty())
    }

    @Test
    fun `test PostDtoList toPostList `() {
        val postDToList = listOf<PostDTO>(PostDTO(0, 1, "title", "body"), PostDTO(1, 1, "title1", "body1"), PostDTO(0, 2, "title2", "body2"), PostDTO(3, 1, "title3", "body3"))
        val postList = postDToList.toPostList()
        assertTrue(postList.isNotEmpty())
    }

    @Test
    fun `test PostDtoList toPostList  when list is Empty`() {
        val postDToList = listOf<PostDTO>()
        val postList = postDToList.toPostList()
        assertTrue(postList.isEmpty())
    }

    @Test
    fun `test PosEntityList toPostList `() {
        val postEntityList = listOf<PostEntity>(PostEntity(0, 1, "title", "body"), PostEntity(1, 1, "title1", "body1"), PostEntity(0, 2, "title2", "body2"), PostEntity(3, 1, "title3", "body3"))
        val postList = postEntityList.toPostList()
        assertTrue(postList.isNotEmpty())
    }

    @Test
    fun `test PosEntityList toPostList when list is empty`() {
        val postEntityList = listOf<PostEntity>()
        val postList = postEntityList.toPostList()
        assertTrue(postList.isEmpty())
    }
}
