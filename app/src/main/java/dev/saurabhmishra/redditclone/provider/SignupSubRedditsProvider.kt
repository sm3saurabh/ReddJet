package dev.saurabhmishra.redditclone.provider

import dev.saurabhmishra.redditclone.model.SubReddit

interface SignupSubRedditsProvider {
  fun getListOfSubReddits(): List<SubReddit>
}

class SignupSubRedditsProviderImpl: SignupSubRedditsProvider {

  override fun getListOfSubReddits(): List<SubReddit> {
    return listOf(
      SubReddit("r/ContagiousLaughter"),
      SubReddit("r/Art"),
      SubReddit("r/ShowerThoughts"),
      SubReddit("r/Gaming"),
      SubReddit("Reddit"),
      SubReddit("Anything")
    )
  }

}