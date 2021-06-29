package com.marcin.data

import com.google.gson.annotations.SerializedName
import com.marcin.domain.Repository

data class RepositoryDto(
      val id: Int?,

      val name: String?,

      @SerializedName("full_name")
      val fullName: String?,

      val description: String?
) {

      fun map(): Repository? {
            return Repository(
                  id = id ?: return null,
                  name = name ?: return null,
                  fullName = fullName ?: return null,
                  description = description
            )
      }
}
