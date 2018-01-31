package levkovskiy.com.dota2picker.model

import com.google.gson.annotations.SerializedName

class DataResponse {

    data class Hero(@SerializedName("pro_win")
                    val proWin: Int = 0,
                    val img: String = "",
                    @SerializedName("primary_attr")
                    val primaryAttr: String = "",
                    @SerializedName("attack_range")
                    val attackRange: Int = 0,
                    @SerializedName("attack_type")
                    val attackType: String = "",
                    @SerializedName("cm_enabled")
                    val cmEnabled: Boolean = false,
                    @SerializedName("5_win")
                    val Win5: Int = 0,
                    val roles: List<String>?,
                    val icon: String = "",
                    @SerializedName("5_pick")
                    val Pick5: Int = 0,
                    @SerializedName("base_mana")
                    val baseMana: Int = 0,
                    @SerializedName("localized_name")
                    val localizedName: String = "",
                    @SerializedName("2_pick")
                    val Pick2: Int = 0,
                    @SerializedName("pro_pick")
                    val proPick: Int = 0,
                    @SerializedName("1_win")
                    val Win1: Int = 0,
                    @SerializedName("base_health")
                    val baseHealth: Int = 0,
                    val legs: Int = 0,
                    @SerializedName("4_win")
                    val Win4: Int = 0,
                    val id: Int = 0,
                    @SerializedName("hero_id")
                    val heroId: Int = 0,
                    @SerializedName("3_pick")
                    val Pick3: Int = 0,
                    @SerializedName("6_pick")
                    val Pick6: Int = 0,
                    @SerializedName("str_gain")
                    val strGain: Double = 0.0,
                    @SerializedName("7_pick")
                    val Pick7: Int = 0,
                    @SerializedName("7_win")
                    val Win7: Int = 0,
                    @SerializedName("base_armor")
                    val baseArmor: Double = 0.0,
                    @SerializedName("2_win")
                    val Win2: Int = 0,
                    @SerializedName("1_pick")
                    val Pick1: Int = 0,
                    @SerializedName("attack_rate")
                    val attackRate: Double = 0.0,
                    @SerializedName("4_pick")
                    val Pick4: Int = 0,
                    @SerializedName("base_mana_regen")
                    val baseManaRegen: Double = 0.0,
                    @SerializedName("base_attack_max")
                    val baseAttackMax: Int = 0,
                    @SerializedName("base_int")
                    val baseInt: Int = 0,
                    @SerializedName("int_gain")
                    val intGain: Double = 0.0,
                    @SerializedName("base_str")
                    val baseStr: Int = 0,
                    @SerializedName("3_win")
                    val Win3: Int = 0,
                    @SerializedName("move_speed")
                    val moveSpeed: Int = 0,
                    @SerializedName("agi_gain")
                    val agiGain: Double = 0.0,
                    @SerializedName("turn_rate")
                    val turnRate: Double = 0.0,
                    @SerializedName("pro_ban")
                    val proBan: Int = 0,
                    @SerializedName("6_win")
                    val Win6: Int = 0,
                    val name: String = "",
                    @SerializedName("base_attack_min")
                    val baseAttackMin: Int = 0,
                    @SerializedName("projectile_speed")
                    val projectileSpeed: Int = 0,
                    @SerializedName("base_agi")
                    val baseAgi: Int = 0,
                    @SerializedName("base_health_regen")
                    val baseHealthRegen: Double = 0.0,
                    @SerializedName("base_mr")
                    val baseMr: Int = 0) {
        override fun toString(): String {
            return localizedName
        }


    }

    data class Matchup(val wins: Int = 0,
                       @SerializedName("games_played")
                       val gamesPlayed: Int = 0,
                       @SerializedName("hero_id")
                       val heroId: Int = 0) : Comparable<Matchup> {
        override fun compareTo(other: Matchup): Int {
            if (this.wins == 0)
                return -1
            else if (other.wins == 0)
                return 1
            return if (this.gamesPlayed / this.wins > other.gamesPlayed / other.wins)
                1
            else
                -1

        }
    }


}