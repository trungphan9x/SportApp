package us.reachmobi.sportapp.data.model

data class SearchEventSingle(
    val dateEvent: String? = "",
    val dateEventLocal: String? = "",
    val idAPIfootball: String? = "",
    val idAwayTeam: String? = "",
    val idEvent: String? = "",
    val idHomeTeam: String? = "",
    val idLeague: String? = "",
    val idSoccerXML: String? = "",
    val intAwayScore: String? = "",
    val intAwayShots: String? = "",
    val intHomeScore: String? = "",
    val intHomeShots: String? = "",
    val intRound: String? = "",
    val intSpectators: String? = "",
    val strAwayFormation: String? = "",
    val strAwayGoalDetails: String? = "",
    val strAwayLineupDefense: String? = "",
    val strAwayLineupForward: String? = "",
    val strAwayLineupGoalkeeper: String? = "",
    val strAwayLineupMidfield: String? = "",
    val strAwayLineupSubstitutes: String? = "",
    val strAwayRedCards: String? = "",
    val strAwayTeam: String? = "",
    val strAwayYellowCards: String? = "",
    val strBanner: String? = "",
    val strCity: String? = "",
    val strCountry: String? = "",
    val strDescriptionEN: String? = "",
    val strEvent: String? = "",
    val strEventAlternate: String? = "",
    val strFanart: String? = "",
    val strFilename: String? = "",
    val strHomeFormation: String? = "",
    val strHomeGoalDetails: String? = "",
    val strHomeLineupDefense: String? = "",
    val strHomeLineupForward: String? = "",
    val strHomeLineupGoalkeeper: String? = "",
    val strHomeLineupMidfield: String? = "",
    val strHomeLineupSubstitutes: String? = "",
    val strHomeRedCards: String? = "",
    val strHomeTeam: String? = "",
    val strHomeYellowCards: String? = "",
    val strLeague: String? = "",
    val strLocked: String? = "",
    val strMap: String? = "",
    val strOfficial: String? = "",
    val strPoster: String? = "",
    val strPostponed: String? = "",
    val strResult: String? = "",
    val strSeason: String? = "",
    val strSport: String? = "",
    val strSquare: String? = "",
    val strStatus: String? = "",
    val strTVStation: String? = "",
    val strThumb: String? = "",
    val strTime: String? = "",
    val strTimeLocal: String? = "",
    val strTimestamp: String? = "",
    val strTweet1: String? = "",
    val strTweet2: String? = "",
    val strTweet3: String? = "",
    val strVenue: String? = "",
    val strVideo: String? = ""
)
{
    var strHomeTeamBadge = ""
    var strAwayTeamBadge = ""
}