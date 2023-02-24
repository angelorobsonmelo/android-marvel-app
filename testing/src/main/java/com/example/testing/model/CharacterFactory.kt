package com.example.testing.model

import com.example.core.domain.model.Character

class CharacterFactory {

    fun create(hero: Hero) = when (hero) {
        Hero.ABomb -> {
            Character(
                name = "A-Bomb (HAS)",
                imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg"
            )
        }
        Hero.ThreeDMan -> {
            Character(
                name = "3-D Man",
                imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg"
            )
        }
    }

    sealed class Hero {
        object ThreeDMan : Hero()
        object ABomb : Hero()
    }
}