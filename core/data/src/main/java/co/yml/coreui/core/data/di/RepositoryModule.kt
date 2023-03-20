package co.yml.coreui.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import co.yml.coreui.core.data.PostRepository
import co.yml.coreui.core.data.PostRepositoryImpl
import co.yml.coreui.core.data.local.LocalPostDataSource
import co.yml.coreui.core.data.local.LocalPostDataSourceImpl
import co.yml.coreui.core.data.remote.RemotePostDataSource
import co.yml.coreui.core.data.remote.RemotePostDataSourceImpl

/**
 * Repository module, will bind the repository and data source instance based on the demand
 *
 * @constructor Create empty Repository module
 */
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    /**
     * Bind post repository
     *
     * @param postRepository
     * @return
     */
    @Binds
    fun bindPostRepository(postRepository: PostRepositoryImpl): PostRepository

    /**
     * Bind local post data source
     *
     * @param localPostDataSource
     * @return
     */
    @Binds
    fun bindLocalPostDataSource(localPostDataSource: LocalPostDataSourceImpl): LocalPostDataSource

    /**
     * Bind remote post data source
     *
     * @param remotePostDataSource
     * @return
     */
    @Binds
    fun bindRemotePostDataSource(remotePostDataSource: RemotePostDataSourceImpl): RemotePostDataSource
}
