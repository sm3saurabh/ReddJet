package dev.saurabhmishra.redditclone.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.saurabhmishra.redditclone.utils.Wood
import java.lang.IllegalStateException

open class BaseViewModel: ViewModel() {

  protected fun <T> LiveData<T>.setValue(value: T) {
    if (this is MutableLiveData) {
      this.value = value
    } else {
      val message = "setValue can not be called on a Immutable LiveData instance"
      Wood.error(message, IllegalStateException(message))
    }
  }

  protected fun <T> LiveData<T>.postValue(value: T) {
    if (this is MutableLiveData) {
      this.postValue(value)
    } else {
      val message = "postValue can not be called on a Immutable LiveData instance"
      Wood.error(message, IllegalStateException(message))
    }
  }
}