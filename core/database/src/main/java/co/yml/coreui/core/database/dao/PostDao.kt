package co.yml.coreui.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.yml.coreui.core.database.POST_TABLE
import co.yml.coreui.core.database.model.PostEntity

/**
 * Post dao
 *
 * @constructor Create empty Post dao
 */
@Dao
interface PostDao {
    /**
     * Get all post
     *
     * @return
     */
    @Query("SELECT * FROM $POST_TABLE ORDER BY id DESC")
    fun getAllPost(): List<PostEntity>

    /**
     * Insert
     *
     * @param post
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: PostEntity)

    /**
     * Insert
     *
     * @param post
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: List<PostEntity>)

    /**
     * Delete all
     *
     */
    @Query("DELETE FROM $POST_TABLE")
    suspend fun deleteAll()

    /**
     * Delete item
     *
     */
    @Query("DELETE FROM $POST_TABLE WHERE id =:postId")
    suspend fun deleteItem(postId: Int)
}
