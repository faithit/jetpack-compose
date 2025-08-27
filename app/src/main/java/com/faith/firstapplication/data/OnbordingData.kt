package com.faith.firstapplication.data
import com.faith.firstapplication.R

data class OnboardingItem(
    val title: String,
    val description: String,
    val imageRes: Int
)
val onboardingItems = listOf(
OnboardingItem(
title = "Welcome to Our School App",
description = "Manage students, teachers, and classes easily.",
imageRes = R.drawable.school
),
OnboardingItem(
title = "For Teachers",
description = "Upload notes, assignments, and track progress.",
imageRes = R.drawable.teacher
),
OnboardingItem(
title = "For Students",
description = "View notes, submit assignments, and check results.",
imageRes = R.drawable.student
)
)