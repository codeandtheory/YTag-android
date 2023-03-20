package co.yml.coreui.core.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import co.yml.coreui.core.database.DATABASE_VERSION
import co.yml.coreui.core.database.dao.PostDao
import co.yml.coreui.core.database.model.PostEntity

/**
 * App data base
 *
 * @constructor Create empty App data base
 */
@Database(entities = [PostEntity::class], version = DATABASE_VERSION)
abstract class AppDataBase : RoomDatabase() {
    /**
     * My model dao
     *
     * @return
     */
    abstract fun myModelDao(): PostDao
}
