package dev.saurabhmishra.redditclone.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.exoplayer2.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.saurabhmishra.redditclone.base.BaseViewModel
import dev.saurabhmishra.redditclone.model.SubReddit
import dev.saurabhmishra.redditclone.provider.SignupSubRedditsProvider
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
  subRedditsProvider: SignupSubRedditsProvider,
  private val exoPlayer: ExoPlayer
): BaseViewModel() {

  val signupAnimatedName: LiveData<SubReddit> = MutableLiveData()

  private val subReddits = subRedditsProvider.getListOfSubReddits()
  private var currentIndex = 0

  val emitNextSubReddit: () -> Unit = {
    signupAnimatedName.setValue(subReddits[currentIndex])
    currentIndex = (currentIndex + 1) % subReddits.size
  }

  val fetchExoPlayer: () -> ExoPlayer = {
    exoPlayer
  }

  init {
    emitNextSubReddit.invoke()
  }
}