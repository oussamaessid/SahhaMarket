package app.sahhamarket.presentation.onboarding.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.sahhamarket.compose.components.HorizontalPagerIndicator
import app.sahhamarket.compose.theme.Black
import app.sahhamarket.compose.theme.DustyGray
import app.sahhamarket.compose.theme.FountainBlue
import app.sahhamarket.compose.theme.White
import app.sahhamarket.compose.theme.spacing
import app.sahhamarket.domain.model.OnboardingInfo
import app.sahhamarket.presentation.onboarding.vm.OnboardingViewModel
import app.sahhamarket.resources.R
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    goToOptionScreen: () -> Unit,
) {
    val state by viewModel.stateWelcome.collectAsStateWithLifecycle()

    OnboardingContent(
        state = state,
        goToOptionScreen = {
            viewModel.processAction(OnboardingViewModel.UiAction.GoToOptionScreen)
            goToOptionScreen()
        }
    )
}

@Composable
fun OnboardingContent(
    state: OnboardingViewModel.UiState,
    goToOptionScreen: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState { state.welcomeInfoList.size }
    var isLastPage by remember { mutableStateOf(false) }
    isLastPage = pagerState.currentPage == state.welcomeInfoList.size - 1

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .weight(1F),
            state = pagerState,
            pageSize = PageSize.Fill,
        ) { index ->
            OnboardingInfoPage(welcomeInfo = state.welcomeInfoList[index])
        }

        AnimatedVisibility(visible = isLastPage) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = MaterialTheme.spacing.l,
                        vertical = MaterialTheme.spacing.s,
                    ),
                shape = MaterialTheme.shapes.extraLarge,
                colors = ButtonDefaults.buttonColors(
                    containerColor = FountainBlue,
                    contentColor = White
                ),
                onClick = goToOptionScreen
            ) {
                Text(
                    text = stringResource(R.string.txt_btn_let_get_started),
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = MaterialTheme.spacing.xs)
                )
            }
        }

        AnimatedVisibility(visible = isLastPage.not()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = MaterialTheme.spacing.l,
                        vertical = MaterialTheme.spacing.s,
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    TextButton(
                        colors = ButtonDefaults.textButtonColors(contentColor = DustyGray),
                        onClick = goToOptionScreen
                    ) {
                        Text(
                            text = stringResource(R.string.txt_btn_skip),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    HorizontalPagerIndicator(
                        pageCount = state.welcomeInfoList.size,
                        currentPage = pagerState.currentPage,
                    )
                }

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    TextButton(
                        colors = ButtonDefaults.textButtonColors(contentColor = FountainBlue),
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.txt_btn_next),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun OnboardingInfoPage(
    welcomeInfo: OnboardingInfo,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painterResource(welcomeInfo.image),
            contentDescription = null,
        )

        Text(
            text = stringResource(welcomeInfo.title),
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center,
            color = Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = MaterialTheme.spacing.l,
                    bottom = MaterialTheme.spacing.s,
                )
        )

        Text(
            text = stringResource(welcomeInfo.subTitle),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            maxLines = 3,
            minLines = 3,
            overflow = TextOverflow.Ellipsis,
            color = DustyGray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = MaterialTheme.spacing.l,
                    end = MaterialTheme.spacing.l,
                    bottom = MaterialTheme.spacing.s
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingContentPreview() {
    OnboardingContent(
        OnboardingViewModel.UiState(
            welcomeInfoList = listOf(
                OnboardingInfo(
                    image = R.drawable.ic_welcome_1,
                    title = R.string.txt_title_welcome_one,
                    subTitle = R.string.txt_sub_title_welcome_one
                ),
                OnboardingInfo(
                    image = R.drawable.ic_welcome_2,
                    title = R.string.txt_title_welcome_two,
                    subTitle = R.string.txt_sub_title_welcome_two
                ),
                OnboardingInfo(
                    image = R.drawable.ic_welcome_3,
                    title = R.string.txt_title_welcome_three,
                    subTitle = R.string.txt_sub_title_welcome_three
                )
            )
        ),
        goToOptionScreen = {},
    )
}