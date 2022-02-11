package dev.saurabhmishra.redditclone.di

import android.content.Context
import com.google.android.exoplayer2.ExoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

  @Provides
  fun provideExoPlayer(@ApplicationContext context: Context): ExoPlayer {
    return ExoPlayer.Builder(context).build()
  }
}