package com.marcin.data

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class RepositoriesDtoMappingTest {

    private val expectedRepositories = listOf(com.marcin.testdata.RepositoriesTestData.repo1, com.marcin.testdata.RepositoriesTestData.repo2, com.marcin.testdata.RepositoriesTestData.repo3)

    @Test
    fun `Repositories are mapped to domain objects`() {
        val mappedRepositories = parseJsonFromFile().items.mapNotNull { it.map() }

        assertThat(mappedRepositories.size, equalTo(expectedRepositories.size))
        assertThat(mappedRepositories, equalTo(expectedRepositories))
    }

    private fun parseJsonFromFile(): RepositoriesDto {
        val bufferedReader = javaClass.classLoader?.getResourceAsStream("repositories.json")?.bufferedReader()
        val inputString = bufferedReader.use { it?.readText() }

        val type = object : TypeToken<RepositoriesDto>() {}.type
        return GsonBuilder().create().fromJson(
            inputString,
            type
        )
    }
}
