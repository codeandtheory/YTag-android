package co.yml.coreui.core.data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Repository module, will bind the repository and data source instance based on the demand
 *
 * @constructor Create empty Repository module
 */
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule
