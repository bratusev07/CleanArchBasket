package ru.bratusev.basketfeature.presentation.games.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.attack.GameValues
import ru.bratusev.basketfeature.presentation.games.diagrams.HexagonView
import ru.bratusev.basketfeature.presentation.games.diagrams.ShotPercent
import ru.bratusev.basketfeature.presentation.games.diagrams.TableView
import ru.bratusev.basketfeature.presentation.games.diagrams.VerticalView
import ru.bratusev.domain.models.GameMoment
import ru.bratusev.domain.models.HexagonPoint
import ru.bratusev.domain.models.Player
import ru.bratusev.domain.models.ShotModel
import ru.bratusev.domain.models.VerticalPoint
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.util.Date
import java.util.Objects

class StatsFragment : Fragment() {

    private val vm: StatsViewModel by viewModel<StatsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_actions, container, false).also {
            val frame1 = it.findViewById<FrameLayout>(R.id.stats_frame1)
            val frame2 = it.findViewById<FrameLayout>(R.id.stats_frame2)
            val frame3 = it.findViewById<FrameLayout>(R.id.stats_frame3)
            val frame4 = it.findViewById<FrameLayout>(R.id.stats_frame4)
            val tableTeam = it.findViewById<TableView>(R.id.table_teamStats)
            val tableEnemy = it.findViewById<TableView>(R.id.table_enemyStats)

            var hexagonView: HexagonView
            var verticalView: VerticalView
            var myTeamShotPercent: ShotPercent
            var enemyTeamShotPercent: ShotPercent

            vm.actionListLive.observe(viewLifecycleOwner) {
                shareData(vm.actionListLive.value ?: ArrayList())
                val teamId = vm.actionListLive.value?.get(0)?.teamId
                tableTeam.setData(vm.actionListLive.value?.filter { elem -> elem.teamId == teamId } as ArrayList<GameMoment>)
                tableEnemy.setData(vm.actionListLive.value?.filter { elem -> elem.teamId != teamId } as ArrayList<GameMoment>)

                hexagonView = HexagonView(
                    requireContext(),
                    parseToHexagonPoints(vm.actionListLive.value ?: ArrayList())
                )
                verticalView = VerticalView(
                    requireContext(),
                    parseToVerticalPoints(vm.actionListLive.value ?: ArrayList())
                )
                val shotLists = parseToShotPoints(vm.actionListLive.value ?: ArrayList())
                myTeamShotPercent = ShotPercent(
                    requireContext(),
                    shotLists.filter {elem -> !elem.isEnemy } as ArrayList<ShotModel>
                )
                enemyTeamShotPercent = ShotPercent(
                    requireContext(),
                    shotLists.filter {elem -> elem.isEnemy } as ArrayList<ShotModel>
                )
                frame1.addView(hexagonView)
                frame2.addView(verticalView)
                frame3.addView(myTeamShotPercent)
                frame4.addView(enemyTeamShotPercent)
            }

            vm.getActions(gameId = GameValues.gameId)
        }
    }

    private fun parseToShotPoints(gameMoments: ArrayList<GameMoment>): ArrayList<ShotModel> {
       val pointShotList = ArrayList<ShotModel>()
        val myTeamId = try {
            gameMoments[0].teamId
        } catch (_: Exception) {
            return pointShotList
        }

        gameMoments.forEach { action ->
            pointShotList.add(
                ShotModel(
                    action.zone,
                    action.shotResult,
                    myTeamId != action.teamId
                )
            )
        }

        return pointShotList
    }

    private fun parseToHexagonPoints(value: ArrayList<GameMoment>): ArrayList<HexagonPoint> {
        val pointListHexagon = ArrayList<HexagonPoint>()
        val myTeamId = try {
            value[0].teamId
        } catch (_: Exception) {
            return pointListHexagon
        }

        value.forEach { action ->
            pointListHexagon.add(
                HexagonPoint(
                    attackType = action.attackType,
                    shotResult = action.shotResult,
                    isEnemy = action.teamId != myTeamId
                )
            )
        }

        return pointListHexagon
    }

    private fun parseToVerticalPoints(value: ArrayList<GameMoment>): ArrayList<VerticalPoint> {
        val pointListVertical = ArrayList<VerticalPoint>()
        val myTeamId = try {
            value[0].teamId
        } catch (_: Exception) {
            return pointListVertical
        }
        val timeType = value[0].timeType
        val myTeamShot = value.filter { elem -> elem.teamId == myTeamId }
        val enemyTeamShot = value.filter { elem -> elem.teamId != myTeamId }

        for (i in 1..timeType) {
            var count = 0
            myTeamShot.forEach { action ->
                if (action.time == i) count++
            }
            pointListVertical.add(VerticalPoint(i, count, false))
        }

        for (i in 1..timeType) {
            var count = 0
            enemyTeamShot.forEach { action ->
                if (action.time == i) count++
            }
            pointListVertical.add(VerticalPoint(i, count, true))
        }

        return pointListVertical
    }

    private fun shareData(listOfData: List<GameMoment>) {
        if(listOfData.isEmpty()) return
        val file = writeCsv(listOfData.sortedBy { elem -> elem.index })
        if (file.exists()) {
            val uri = FileProvider.getUriForFile(
                Objects.requireNonNull(requireContext()), "ru.bratusev.basketfeature.provider", file);
            val intent = Intent(Intent.ACTION_SEND)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.setType("*/*")
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    private fun writeCsv(listOfData: List<GameMoment>): File {
        val fileName = "data_${Date().time}.csv"
        val file = File(requireContext().filesDir, fileName)
        val writer = OutputStreamWriter(FileOutputStream(file))

        writer.write("""Номер, Четверть, На площадке, Тип владения, Передачи, Тип времени, Секунда, Начало атаки, Результат атаки, Тип атаки, Зона, Попадание/Промах, Тип фола, Результат фола, Способ потери""")
        writer.write(System.lineSeparator())
        listOfData.forEach {
            writer.write("${it.index}, " +
                    "${it.quater}, " +
                    "${playersToLine(it.playersOnField)}, " +
                    "${it.typeOfPossession}, " +
                    "${passToLine(it.passStory)}, " +
                    "${it.timeType}, " +
                    "${it.time}, " +
                    "${it.attackType}, " +
                    "${it.resultType}, " +
                    "${it.shot}, " +
                    "${it.zone}, " +
                    "${it.shotResult}, " +
                    "${it.foulType}, " +
                    "${it.foulResult}, " +
                    it.loss
            )
            writer.write(System.lineSeparator())
        }
        writer.flush()
        writer.close()

        return file
    }

    private fun playersToLine(playersOnField: java.util.ArrayList<Player>): String {
        var result = ""
        for (player in playersOnField) {
            result+= "${player.number} "
        }
        return result
    }

    private fun passToLine(passStory: ArrayList<Player>): String{
        var result = ""
        for (player in passStory) {
            result+= "${player.number} "
        }
        return result
    }

}