package dev.saurabhmishra.redditclone.provider

import dev.saurabhmishra.redditclone.model.SubReddit

interface SignupSubRedditsProvider {
  fun getListOfSubReddits(): List<SubReddit>
}

class SignupSubRedditsProviderImpl: SignupSubRedditsProvider {

  override fun getListOfSubReddits(): List<SubReddit> {
    return listOf(
      SubReddit("ContagiousLaughter"),
      SubReddit("Art"),
      SubReddit("ShowerThoughts"),
      SubReddit("Gaming"),
    )
  }

}