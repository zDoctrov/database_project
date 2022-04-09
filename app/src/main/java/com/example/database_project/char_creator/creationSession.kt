package com.example.database_project.char_creator

//Objects in Kotlin only have one memory location, so we can temporarily store data in it
object creationSession {
    //CharActivity1 data
    var name: String = ""
    var race: String = ""
    var faction: String = ""

    //CharActivity2 data
    var hair: String = ""
    var ears: String = ""
    var eyes: String = ""

    //CharActivity3 data
    var user_class: String = ""
    var user_currency: Double = 0.0
    var faction_reputation: String = ""
    var faction_ideology: String = ""

    fun printAllData(){
        print("Name: $name\n" +
                "Race: $race\n" +
                "Faction: $faction\n" +
                "Hair: $hair\n" +
                "Ears: $ears\n" +
                "Eyes: $eyes\n" +
                "Class: $user_class\n\n" +

                "Currency: $user_currency\n" +
                "Reputation: $faction_reputation\n" +
                "Ideology: $faction_ideology\n"
        )
    }
}