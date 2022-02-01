package dev.saurabhmishra.redditclone.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.saurabhmishra.redditclone.base.BaseViewModel
import dev.saurabhmishra.redditclone.extensions.safeLaunch
import dev.saurabhmishra.redditclone.provider.SignupSubRedditsProvider
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
  private val subRedditsProvider: SignupSubRedditsProvider
): BaseViewModel() {

  val signupAnimatedName: LiveData<String> = MutableLiveData()

  init {
    val subRedditNames = subRedditsProvider.getListOfSubReddits().map { it.name }

    emitSubRedditNamesInfinitely(subRedditNames)

  }

  private fun emitSubRedditNamesInfinitely(subRedditNames: List<String>) {
    viewModelScope.safeLaunch {
      // We need an infinite loop
      var index = 0
      while (true) {
        signupAnimatedName.setValue(subRedditNames[index])

        index = (index + 1) % subRedditNames.size

        // Delay or 3 seconds before moving to next iteration
        delay(3000L)
      }
    }
  }
}