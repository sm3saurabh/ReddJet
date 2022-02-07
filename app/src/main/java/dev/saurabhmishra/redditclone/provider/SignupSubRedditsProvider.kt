package dev.saurabhmishra.redditclone.provider

import dev.saurabhmishra.redditclone.model.SubReddit

interface SignupSubRedditsProvider {
  fun getListOfSubReddits(): List<SubReddit>
}

class SignupSubRedditsProviderImpl: SignupSubRedditsProvider {

  override fun getListOfSubReddits(): List<SubReddit> {
    return listOf(
      SubReddit("Reddit", "https://cdn.videvo.net/videvo_files/video/free/2012-07/large_watermarked/FreeSmokeYoutube_preview.mp4"),
      SubReddit("Anything", "https://cdn.videvo.net/videvo_files/video/free/2019-07/large_watermarked/190625_04_CityMisc_HD_05_preview.mp4"),
      SubReddit("r/ContagiousLaughter", "https://player.vimeo.com/external/436376002.sd.mp4?s=158eaf12c8cbf5006eec7d43035231bb2e5a5778&profile_id=165&oauth2_token_id=57447761"),
      SubReddit("r/Art", "https://player.vimeo.com/external/324597323.sd.mp4?s=1c76afc658a0f38cc1a464c9777103b9225127db&profile_id=164&oauth2_token_id=57447761"),
      SubReddit("r/ShowerThoughts", "https://player.vimeo.com/external/483221534.sd.mp4?s=624a02586713da8f3117a5dafa1f58e3d3d35093&profile_id=165&oauth2_token_id=57447761"),
      SubReddit("r/Gaming", "https://player.vimeo.com/external/500628042.sd.mp4?s=1aef9b2062713668cff84486e90ed511329cc31e&profile_id=165&oauth2_token_id=57447761"),
    )
  }

}