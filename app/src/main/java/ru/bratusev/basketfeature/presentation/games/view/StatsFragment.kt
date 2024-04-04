package ru.bratusev.basketfeature.presentation.games.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.bratusev.basketfeature.R
import ru.bratusev.basketfeature.presentation.games.diagrams.HexagonView
import ru.bratusev.basketfeature.presentation.games.diagrams.VerticalView
import kotlin.random.Random

class StatsFragment : Fragment() {

    private val vm: StatsViewModel by viewModel<StatsViewModel>()

    private val messageList = ArrayList<String>().also {
        it.add("прессинг")
        it.add("зона")
        it.add("раннее")
        it.add("отрыв")
        it.add("позиционное")
        it.add("шанс")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_actions, container, false).also {

            val pointList1 = ArrayList<Point>()
            val pointList2 = ArrayList<Point>()
            for (i in 0..5) {
                pointList1.add(Point(value = Random.nextFloat() * 0.8f + 0.1f))
                pointList2.add(Point(value = Random.nextFloat() * 0.8f + 0.1f))
            }

            val pointList3 = ArrayList<Int>()
            val pointList4 = ArrayList<Int>()
            for (i in 0..200) {
                pointList3.add(Random.nextInt(1, 25))
                pointList4.add(Random.nextInt(1, 25))
            }

            val frame1 = it.findViewById<FrameLayout>(R.id.stats_frame1)
            val frame2 = it.findViewById<FrameLayout>(R.id.stats_frame2)
            val hexagonView = HexagonView(requireContext(), messageList, pointList1, pointList2)
            val verticalView = VerticalView(requireContext(), pointList3, pointList4)

            frame1.addView(hexagonView)
            frame2.addView(verticalView)
        }
    }

    class Point(var x: Float = 0f, var y: Float = 0f, val value: Float = 0f){
        override fun toString(): String {
            return "$x $y $value"
        }
    }

}