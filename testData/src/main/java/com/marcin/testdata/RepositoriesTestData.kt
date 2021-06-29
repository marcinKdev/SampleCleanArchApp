package com.marcin.testdata

import com.marcin.domain.Repository

object RepositoriesTestData {

      val repo1 = Repository(
            id = 3432266,
            name =  "kotlin",
            fullName = "JetBrains/kotlin",
            description = "The Kotlin Programming Language. "
      )

      val repo2 = Repository(
            id = 91829561,
            name =  "KotlinUdemy",
            fullName = "hussien89aa/KotlinUdemy",
            description = "Learn how to make online games, and apps for Android O, like Pokémon , twitter,Tic Tac Toe, and notepad using Kotlin"
      )

      val repo3 = Repository(
            id = 111071830,
            name =  "KotlinMvp",
            fullName = "git-xuhao/KotlinMvp",
            description = ":fire: 基于Kotlin+MVP+Retrofit+RxJava+Glide 等架构实现短视频类小项目，简约风格及详细注释，欢迎 star or fork！"
      )
}