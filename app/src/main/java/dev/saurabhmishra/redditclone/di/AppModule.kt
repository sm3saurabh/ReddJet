package dev.saurabhmishra.redditclone.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.saurabhmishra.redditclone.provider.SignupSubRedditsProvider
import dev.saurabhmishra.redditclone.provider.SignupSubRedditsProviderImpl

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  fun provideSignupSubRedditsProvider(): SignupSubRedditsProvider {
    return SignupSubRedditsProviderImpl()
  }
}