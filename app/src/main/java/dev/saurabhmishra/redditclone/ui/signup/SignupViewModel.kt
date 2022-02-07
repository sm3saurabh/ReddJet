package dev.saurabhmishra.redditclone.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.saurabhmishra.redditclone.base.BaseViewModel
import dev.saurabhmishra.redditclone.model.SubReddit
import dev.saurabhmishra.redditclone.provider.SignupSubRedditsProvider
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
  private val subRedditsProvider: SignupSubRedditsProvider
): BaseViewModel() {

  val signupAnimatedName: LiveData<SubReddit> = MutableLiveData()

  private val subReddits = subRedditsProvider.getListOfSubReddits()
  private var currentIndex = 0


  val emitNextSubReddit = {
    signupAnimatedName.setValue(subReddits[currentIndex])
    currentIndex = (currentIndex + 1) % subReddits.size
  }
}