package co.yml.coreui.core.data.local

import co.yml.coreui.core.common.model.Post
import co.yml.coreui.core.data.util.map
import co.yml.coreui.core.data.util.toPostEntityList
import co.yml.coreui.core.data.util.toPostList
import co.yml.coreui.core.database.dao.PostDao
import javax.inject.Inject

/**
 * Local post data source impl
 *
 * @property postDao
 * @constructor Create empty Local post data source impl
 */
class LocalPostDataSourceImpl @Inject constructor(private val postDao: PostDao) :
    LocalPostDataSource {

    override suspend fun getPost(): List<Post> {
        return postDao.getAllPost().toPostList()
    }

    override suspend fun add(post: Post) {
        postDao.insert(post.map())
    }

    override suspend fun delete(post: Post) {
        postDao.deleteItem(post.id)
    }

    override suspend fun addAll(post: List<Post>) {
        postDao.insert(post.toPostEntityList())
    }

    override suspend fun deleteAll() {
        postDao.deleteAll()
    }
}
