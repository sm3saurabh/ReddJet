package dev.saurabhmishra.redditclone.ui.signup

import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import dev.saurabhmishra.redditclone.R
import dev.saurabhmishra.redditclone.theme.RedditCloneTheme
import dev.saurabhmishra.redditclone.utils.Wood
import kotlin.math.floor

@Composable
fun SignupScreen(signupViewModel: SignupViewModel = viewModel()) {

  val subRedditName by signupViewModel.signupAnimatedName.observeAsState("Reddit")

  // The box that contains video player and the signup content
  Surface(Modifier.fillMaxSize()) {
    SignupVideoPlayer()
    SignupContent(subRedditName = subRedditName)
  }
}

@Composable
fun SignupVideoPlayer() {
  val sampleVideo = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
  val context = LocalContext.current
  val player = ExoPlayer.Builder(context).build()
  val playerView = PlayerView(context)
  playerView.useController = false
  playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
  val mediaItem = MediaItem.fromUri(sampleVideo)

  player.setMediaItem(mediaItem)
  playerView.player = player

  LaunchedEffect(player) {
    player.prepare()
    player.playWhenReady = true

  }
  AndroidView(factory = {
    playerView
  })
}

@Composable
fun SignupContent(subRedditName: String) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(dimensionResource(id = R.dimen.default_padding))
  ) {

    // 1. Skip
    Text(
      text = stringResource(id = R.string.action_skip),
      modifier = Modifier
        .padding(dimensionResource(id = R.dimen.text_padding))
        .wrapContentSize()
        .align(Alignment.End)
        .clickable {
          Wood.debug("Skip clicked")
        },
      style = MaterialTheme.typography.caption,
    )

    // Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_padding)))

    // 2. Reddit logo
    Image(
      painter = painterResource(id = R.drawable.ic_reddit_icon),
      contentDescription = stringResource(id = R.string.label_logo),
      modifier = Modifier
        .size(dimensionResource(id = R.dimen.logo_size))
        .align(Alignment.CenterHorizontally)
    )

    // 3. Dive into subreddits
    DiveIntoSubRedditAnimator(modifier = Modifier
      .weight(1f)
      .align(Alignment.CenterHorizontally), subRedditName = subRedditName)

    // 4. Terms and conditions
    TermsAndConditionsText(modifier = Modifier
      .align(Alignment.CenterHorizontally)
      .padding(
        bottom = dimensionResource(
          id = R.dimen.default_padding
        )
      )
    )

    // 5. Sign up buttons
    SocialSignupSection(modifier = Modifier.align(Alignment.CenterHorizontally))

    // 6. Already a member text
    Text(
      text = stringResource(id = R.string.label_already_member),
      style = MaterialTheme.typography.overline,
      modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(top = dimensionResource(id = R.dimen.default_padding)),
    )

  }
}


@Composable
fun DiveIntoSubRedditAnimator(modifier: Modifier, subRedditName: String) {
  Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
    Text(
      text = stringResource(id = R.string.label_dive_into),
      style = MaterialTheme.typography.h4,
      modifier = Modifier.fillMaxWidth(),
      textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.default_padding)))


    AnimatedSubRedditName(subRedditName)
  }
}

@Composable
private fun AnimatedSubRedditName(subRedditName: String) {
  val nameState = remember { mutableStateOf(0f) }

  LaunchedEffect(key1 = subRedditName) {
    animate(
      0f,
      subRedditName.length.toFloat(),
      animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
    ) { value, _ ->
      nameState.value = value
    }
  }

  // Name of subreddits with animations
  val currentIndex = floor(nameState.value).toInt()

  val subRedditNameState = if (currentIndex <= subRedditName.length) {
    subRedditName.substring(0, floor(nameState.value).toInt())
  } else ""

  Text(
    text = subRedditNameState,
    style = MaterialTheme.typography.h4,
    modifier = Modifier.fillMaxWidth(),
    textAlign = TextAlign.Center
  )

}

@Composable
fun TermsAndConditionsText(modifier: Modifier) {
  Text(
    text = buildAnnotatedString {
      append("By continuing, you agree to our\n")

      withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
        append("User Agreement")
      }

      append(" and ")

      withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
        append("Privacy Policy")
      }

    },
    style = MaterialTheme.typography.caption,
    textAlign = TextAlign.Center,
    modifier = modifier,
  )
}

@Composable
fun SocialSignupSection(modifier: Modifier) {
  Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {

    val signupButtonModifier = Modifier
      .fillMaxWidth()
      .padding(
        start = dimensionResource(id = R.dimen.social_signup_button_padding),
        end = dimensionResource(id = R.dimen.social_signup_button_padding),
      )

    // 1. Signup with google
    SocialSignupButton(
      imageId = R.drawable.ic_google_icon,
      descriptionId = R.string.label_sign_up_with_google,
      buttonTextId = R.string.label_sign_up_with_google,
      modifier = signupButtonModifier,
      cornerRadiusId = R.dimen.button_corner_radius
    ) {
      Wood.debug("Signup with google clicked")
    }

    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.signup_spacer_height)))

    // 2. Signup with apple
    SocialSignupButton(
      imageId = R.drawable.ic_apple_icon,
      descriptionId = R.string.label_sign_up_with_apple,
      buttonTextId = R.string.label_sign_up_with_apple,
      modifier = signupButtonModifier,
      cornerRadiusId = R.dimen.button_corner_radius
    ) {
      Wood.debug("Signup with apple clicked")
    }

    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.signup_spacer_height)))

    // 3. Signup with email
    SocialSignupButton(
      imageId = R.drawable.ic_email_icon,
      descriptionId = R.string.label_sign_up_with_email,
      buttonTextId = R.string.label_sign_up_with_email,
      modifier = signupButtonModifier,
      cornerRadiusId = R.dimen.button_corner_radius
    ) {
      Wood.debug("Signup with email clicked")
    }
  }
}

@Composable
fun SocialSignupButton(
  modifier: Modifier,
  @DimenRes cornerRadiusId: Int,
  @DrawableRes imageId: Int,
  @StringRes descriptionId: Int,
  @StringRes buttonTextId: Int,
  socialOnClick: () -> Unit,
) {
  Button(
    modifier = modifier,
    onClick = socialOnClick,
    colors = ButtonDefaults.buttonColors(
      backgroundColor = Color.Transparent.copy(alpha = 0.3f),
    ),
    elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
    shape = RoundedCornerShape(dimensionResource(id = cornerRadiusId))
  ) {

    Image(
      painter = painterResource(id = imageId),
      contentDescription = stringResource(id = descriptionId),
    )

    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.button_drawable_padding)))

    Text(text = stringResource(id = buttonTextId), style = MaterialTheme.typography.button)
  }
}

//@Composable
//@Preview
//fun PreviewSignupScreen() {
//  RedditCloneTheme(darkTheme = false) {
//    SignupScreen()
//  }
//}

@Composable
@Preview
fun PreviewSignupScreenDarkMode() {
  RedditCloneTheme(darkTheme = true) {
    // The box that contains video player and the signup content
    Surface(Modifier.fillMaxSize()) {
      SignupVideoPlayer()
      SignupContent(subRedditName = "Test Reddit")
    }
  }
}