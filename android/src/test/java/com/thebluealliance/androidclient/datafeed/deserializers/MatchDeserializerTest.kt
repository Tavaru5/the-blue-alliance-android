package com.thebluealliance.androidclient.datafeed.deserializers

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.thebluealliance.androidclient.models.Alliance
import com.thebluealliance.androidclient.models.MatchAlliance
import com.thebluealliance.androidclient.models.MatchAlliancesContainerKotlin
import com.thebluealliance.androidclient.models.MatchKotlin
import com.thebluealliance.androidclient.models.MatchScoreBreakdown
import com.thebluealliance.androidclient.models.MatchVideo
import com.thebluealliance.androidclient.models.ScoreBreakdown
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MatchDeserializerTest {
    lateinit var moshi: Moshi
    lateinit var jsonAdapter: JsonAdapter<MatchKotlin>
    val sampleJson: String = "{\"actual_time\":1709416315,\"alliances\":{\"blue\":{\"dq_team_keys\":[],\"score\":90,\"surrogate_team_keys\":[],\"team_keys\":[\"frc2145\",\"frc245\",\"frc1\"]},\"red\":{\"dq_team_keys\":[],\"score\":79,\"surrogate_team_keys\":[],\"team_keys\":[\"frc3618\",\"frc5084\",\"frc70\"]}},\"comp_level\":\"f\",\"event_key\":\"2024miket\",\"key\":\"2024miket_f1m1\",\"match_number\":1,\"post_result_time\":1709416545,\"predicted_time\":1709416449,\"score_breakdown\":{\"blue\":{\"adjustPoints\":0,\"autoAmpNoteCount\":0,\"autoAmpNotePoints\":0,\"autoLeavePoints\":6,\"autoLineRobot1\":\"Yes\",\"autoLineRobot2\":\"Yes\",\"autoLineRobot3\":\"Yes\",\"autoPoints\":26,\"autoSpeakerNoteCount\":4,\"autoSpeakerNotePoints\":20,\"autoTotalNotePoints\":20,\"coopNotePlayed\":false,\"coopertitionBonusAchieved\":false,\"coopertitionCriteriaMet\":false,\"endGameHarmonyPoints\":0,\"endGameNoteInTrapPoints\":0,\"endGameOnStagePoints\":6,\"endGameParkPoints\":1,\"endGameRobot1\":\"Parked\",\"endGameRobot2\":\"StageLeft\",\"endGameRobot3\":\"StageRight\",\"endGameSpotLightBonusPoints\":0,\"endGameTotalStagePoints\":7,\"ensembleBonusAchieved\":false,\"ensembleBonusOnStageRobotsThreshold\":2,\"ensembleBonusStagePointsThreshold\":10,\"foulCount\":0,\"foulPoints\":6,\"g206Penalty\":false,\"g408Penalty\":false,\"g424Penalty\":false,\"melodyBonusAchieved\":false,\"melodyBonusThreshold\":18,\"melodyBonusThresholdCoop\":15,\"melodyBonusThresholdNonCoop\":18,\"micCenterStage\":false,\"micStageLeft\":false,\"micStageRight\":false,\"rp\":0,\"techFoulCount\":1,\"teleopAmpNoteCount\":6,\"teleopAmpNotePoints\":6,\"teleopPoints\":58,\"teleopSpeakerNoteAmplifiedCount\":7,\"teleopSpeakerNoteAmplifiedPoints\":35,\"teleopSpeakerNoteCount\":5,\"teleopSpeakerNotePoints\":10,\"teleopTotalNotePoints\":51,\"totalPoints\":90,\"trapCenterStage\":false,\"trapStageLeft\":false,\"trapStageRight\":false},\"red\":{\"adjustPoints\":0,\"autoAmpNoteCount\":0,\"autoAmpNotePoints\":0,\"autoLeavePoints\":4,\"autoLineRobot1\":\"No\",\"autoLineRobot2\":\"Yes\",\"autoLineRobot3\":\"Yes\",\"autoPoints\":34,\"autoSpeakerNoteCount\":6,\"autoSpeakerNotePoints\":30,\"autoTotalNotePoints\":30,\"coopNotePlayed\":false,\"coopertitionBonusAchieved\":false,\"coopertitionCriteriaMet\":false,\"endGameHarmonyPoints\":0,\"endGameNoteInTrapPoints\":0,\"endGameOnStagePoints\":9,\"endGameParkPoints\":0,\"endGameRobot1\":\"CenterStage\",\"endGameRobot2\":\"StageLeft\",\"endGameRobot3\":\"StageRight\",\"endGameSpotLightBonusPoints\":0,\"endGameTotalStagePoints\":9,\"ensembleBonusAchieved\":false,\"ensembleBonusOnStageRobotsThreshold\":2,\"ensembleBonusStagePointsThreshold\":10,\"foulCount\":3,\"foulPoints\":5,\"g206Penalty\":false,\"g408Penalty\":false,\"g424Penalty\":false,\"melodyBonusAchieved\":false,\"melodyBonusThreshold\":18,\"melodyBonusThresholdCoop\":15,\"melodyBonusThresholdNonCoop\":18,\"micCenterStage\":false,\"micStageLeft\":false,\"micStageRight\":false,\"rp\":0,\"techFoulCount\":0,\"teleopAmpNoteCount\":4,\"teleopAmpNotePoints\":4,\"teleopPoints\":40,\"teleopSpeakerNoteAmplifiedCount\":3,\"teleopSpeakerNoteAmplifiedPoints\":15,\"teleopSpeakerNoteCount\":6,\"teleopSpeakerNotePoints\":12,\"teleopTotalNotePoints\":31,\"totalPoints\":79,\"trapCenterStage\":false,\"trapStageLeft\":false,\"trapStageRight\":false}},\"set_number\":1,\"time\":1709414040,\"videos\":[{\"key\":\"oUtQLT3H7Ac\",\"type\":\"youtube\"}],\"winning_alliance\":\"blue\"}"

    @Before
    fun setUp() {
        moshi = Moshi.Builder().add(MatchAdapter()).build()
        jsonAdapter = moshi.adapter(MatchKotlin::class.java)
    }

    @Test
    fun testMatchDeserializer() {
        val mockMatch = MatchKotlin(
            key = "2024miket_f1m1",
            eventKey = "2024miket",
            compLevel = "f",
            matchNumber = 1,
            setNumber = 1,
            winningAlliance = Alliance.BLUE,
            time = 1709414040L,
            alliances = MatchAlliancesContainerKotlin(
                red = MatchAlliance(79, listOf("frc3618", "frc5084", "frc70")),
                blue = MatchAlliance(90, listOf("frc2145", "frc245", "frc1"))
            ),
            scoreBreakdown = MatchScoreBreakdown(
                red = ScoreBreakdown(false, false),
                blue = ScoreBreakdown(false, false)
            ),
            videos = listOf(MatchVideo("oUtQLT3H7Ac", "youtube"))
        )
        val match = jsonAdapter.fromJson(sampleJson)
        assertNotNull(match)
        assert(match == mockMatch)
//        assert(match!!.matchNumber == mockMatch.matchNumber)
//        assert(match.key == mockMatch.key)
//        assert(match.eventKey == mockMatch.eventKey)
//        assert(match.time == mockMatch.time)
//        assert(match.compLevel == mockMatch.compLevel)
//        assert(match.winningAlliance == mockMatch.winningAlliance)
//        assert(match.scoreBreakdown == mockMatch.scoreBreakdown)
//        assert(match.alliances == mockMatch.alliances)
//        assert(match.videos == mockMatch.videos)
    }

    @Test
    fun testMatchSerialization() {
        val match = jsonAdapter.fromJson(sampleJson)
        val serializedJson = jsonAdapter.toJson(match)
        val deserializedMatch = jsonAdapter.fromJson(serializedJson)
        assert(match == deserializedMatch)
    }
}