package com.example.database_project.char_creator

import com.example.database_project.room_db.AllFourTablesJoined

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

    lateinit var queryResults: List<AllFourTablesJoined>

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

    fun resetAttributes(){
        //CharActivity1 data
        name = ""
        race = ""
        faction = ""

        //CharActivity2 data
        hair = ""
        ears = ""
        eyes = ""

        //CharActivity3 data
        user_class = ""
        user_currency = 0.0
        faction_reputation = ""
        faction_ideology = ""

        queryResults = listOf()
    }

    fun setRaceImage(race: String): String{
        when(race){
            "Human" -> {
                return "race_human"
            }
            "Dwarf"-> {
                return "race_dwarf"
            }
            "Elf"-> {
                return "race_elf"
            }
        }//end when()
        return "nft_monkey"
    }//end setRaceImage

    fun setHairImage(Hair: String): String{
        when(Hair){
            "Long" -> {
                return "hair_long"
            }
            "Short"-> {
                return "hair_short"
            }
            "Fuzzy"-> {
                return "hair_fuzzy"
            }
        }//end when()
        return "nft_monkey"
    }//end setHairImage

    fun setEyesImage(eye: String): String{
        when(eye){
            "Round" -> {
                return "eyes_round"
            }
            "Squinted"-> {
                return "eyes_squinted"
            }
            "Lazy"-> {
                return "eyes_lazy"
            }
        }//end when()
        return "nft_monkey"
    }//end setEyesImage


    fun setEarsImage(ear: String): String{
        when(ear){
            "Round" -> {
                return "ears_round"
            }
            "Pointed"-> {
                return "ears_pointed"
            }
            "Square"-> {
                return "ears_square"
            }
        }//end when()
        return "nft_monkey"
    }//end setEarsImage




}