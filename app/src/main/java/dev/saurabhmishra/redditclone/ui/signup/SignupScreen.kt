package dev.saurabhmishra.redditclone.ui.signup

import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
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
import dev.saurabhmishra.redditclone.R
import dev.saurabhmishra.redditclone.theme.RedditCloneTheme
import dev.saurabhmishra.redditclone.utils.Wood

@Composable
fun SignupScreen() {
  // The box that contains video player and the signup content
  Surface(Modifier.fillMaxSize()) {
    SignupVideoPlayer()
    SignupContent()
  }
}

@Composable
fun SignupVideoPlayer() {
  // TODO - Replace with actual 
  Image(painter = ColorPainter(Color.Blue), contentDescription = "Some random shit")
}

@Composable
fun SignupContent() {
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
      painter = painterResource(id = R.drawable.ic_launcher_foreground),
      contentDescription = stringResource(id = R.string.label_logo),
      modifier = Modifier
        .size(dimensionResource(id = R.dimen.logo_size))
        .align(Alignment.CenterHorizontally)
    )

    // 3. Dive into subreddits
    DiveIntoSubRedditAnimator(modifier = Modifier
      .weight(1f)
      .align(Alignment.CenterHorizontally))

    // 4. Terms and conditions
    TermsAndConditionsText(modifier = Modifier
      .align(Alignment.CenterHorizontally)
      .padding(
        bottom = dimensionResource(
          id = R.dimen.default_padding)
      )
    )

    // 5. Sign up buttons
    SocialSignupSection(modifier = Modifier.align(Alignment.CenterHorizontally))

    // 6. Already a member text
    Text(
      text = stringResource(id = R.string.label_already_member),
      style = MaterialTheme.typography.caption,
      modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(top = dimensionResource(id = R.dimen.default_padding)),
    )

  }
}


@Composable
fun DiveIntoSubRedditAnimator(modifier: Modifier) {
  Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
    Text(
      text = stringResource(id = R.string.label_dive_into),
      style = MaterialTheme.typography.h4,
      modifier = Modifier.align(Alignment.CenterHorizontally),
    )

    // Name of subreddits with animations
  }
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
      imageId = R.drawable.ic_launcher_foreground,
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
      imageId = R.drawable.ic_launcher_foreground,
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
      imageId = R.drawable.ic_launcher_foreground,
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
      modifier = Modifier.size(dimensionResource(id = R.dimen.button_drawable_size)),
    )

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
    SignupScreen()
  }
}