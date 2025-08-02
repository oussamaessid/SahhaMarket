package app.sahhamarket.domain.usecase.onboarding

import app.sahhamarket.domain.model.OnboardingInfo
import app.sahhamarket.resources.R
import javax.inject.Inject

class GetOnboardingUseCase @Inject constructor() {

    operator fun invoke(): List<OnboardingInfo> =
        buildList {
            add(
                OnboardingInfo(
                    image = R.drawable.ic_welcome_1,
                    title = R.string.txt_title_welcome_one,
                    subTitle = R.string.txt_sub_title_welcome_one
                )
            )
            add(
                OnboardingInfo(
                    image = R.drawable.ic_welcome_2,
                    title = R.string.txt_title_welcome_two,
                    subTitle = R.string.txt_sub_title_welcome_two
                )
            )
            add(
                OnboardingInfo(
                    image = R.drawable.ic_welcome_3,
                    title = R.string.txt_title_welcome_three,
                    subTitle = R.string.txt_sub_title_welcome_three
                )
            )
        }
}