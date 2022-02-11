package dev.saurabhmishra.redditclone.provider

import dev.saurabhmishra.redditclone.model.SubReddit

interface SignupSubRedditsProvider {
  fun getListOfSubReddits(): List<SubReddit>
}

class SignupSubRedditsProviderImpl: SignupSubRedditsProvider {

  override fun getListOfSubReddits(): List<SubReddit> {
    return listOf(
      SubReddit("r/ContagiousLaughter", "asset:///contagious_laughter_video.mp4"),
      SubReddit("r/Art", "asset:///art_video.mp4"),
      SubReddit("r/ShowerThoughts", "asset:///shower_thoughts_video.mp4"),
      SubReddit("r/Gaming", "asset:///gaming_video.mp4"),
      SubReddit("Reddit", "asset:///reddit_video.mp4"),
      SubReddit("Anything", "asset:///anything_video.mp4"),
    )
  }

}