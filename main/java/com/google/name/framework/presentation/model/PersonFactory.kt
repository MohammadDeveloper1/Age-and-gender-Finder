package com.google.name.framework.presentation.model

object PersonFactory {
    fun createPerson(age: NameAgeImpl, gender: NameGenderImpl): Person {
        return Person(
            name = age.name,
            age = age.age,
            gender = gender.gender,
            country = gender.country
        )
    }

    fun getGender(gender: String?): Gender {
        return when (gender) {
            "male" -> Gender.MALE
            "female" -> Gender.FEMALE
            else -> Gender.NONE
        }
    }
}