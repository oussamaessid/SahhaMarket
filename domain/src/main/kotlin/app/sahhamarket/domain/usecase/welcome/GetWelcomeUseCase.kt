package app.sahhamarket.domain.usecase.welcome

import app.sahhamarket.domain.model.WelcomeInfo
import app.sahhamarket.resources.R
import javax.inject.Inject

class GetWelcomeUseCase @Inject constructor() {

    operator fun invoke(): List<WelcomeInfo> =
        buildList {
            add(
                WelcomeInfo(
                    image = R.drawable.ic_welcome_1,
                    title = R.string.txt_title_welcome_one,
                    subTitle = R.string.txt_sub_title_welcome_one
                )
            )
            add(
                WelcomeInfo(
                    image = R.drawable.ic_welcome_2,
                    title = R.string.txt_title_welcome_two,
                    subTitle = R.string.txt_sub_title_welcome_two
                )
            )
            add(
                WelcomeInfo(
                    image = R.drawable.ic_welcome_3,
                    title = R.string.txt_title_welcome_three,
                    subTitle = R.string.txt_sub_title_welcome_three
                )
            )
        }
}