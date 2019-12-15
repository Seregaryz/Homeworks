package com.example.homeworks.ui.racer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworks.R
import com.example.homeworks.ui.dialog.AddDialogFragment
import kotlinx.android.synthetic.main.fragment_racers.*

class RacersFragment : Fragment() {

    private var racerAdapter: RacerAdapter? = null
    private val racerList: ArrayList<Racer> = getDataSource()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_racers, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        racerAdapter = RacerAdapter(getDataSource()){racer ->
            deleteRacer(racer)
        }
        rv_racers.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = racerAdapter
        }
        setRecyclerViewItemTouchListener()
        floating_action_button.setOnClickListener {
            AddDialogFragment.show(checkNotNull(activity).supportFragmentManager) {x, y -> updateList(x,y)}
        }
    }

    private fun setRecyclerViewItemTouchListener() {
        val itemTouchCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                viewHolder1: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val index = viewHolder.adapterPosition
                racerList.removeAt(index)
                racerAdapter?.updateList(racerList)
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        rv_racers.addItemDecoration(itemTouchHelper)
        itemTouchHelper.attachToRecyclerView(rv_racers)
    }

    fun updateList(racer: Racer, position : String) {
        var index = position.toInt()
        if(index > racerList.size || index == -1) index = racerList.size
        racerList.add(index, racer)
        racerAdapter?.updateList(racerList)
    }

    private fun deleteRacer(racer: Racer) {
        racerList.remove(racer)
        racerAdapter?.updateList(racerList)
    }

    companion object {
        fun newInstance(): RacersFragment = RacersFragment()
    }

    fun getDataSource(): ArrayList<Racer> = arrayListOf(
        Racer(
            "Daniil Kvyat", "Scuderia Toro Rosso"
        ),
        Racer(
            "Max Verstappen","Red Bull Racing"
        ),
        Racer(
            "Lewis Hamilton",  "Mercedes"
        ),
        Racer(
            "Robert Kubica", "Williams"
        ),
        Racer(
            "Romain Grosjean", "Haas"
        ),
        Racer(
            "Charles Leclerc", "Scuderia Ferrari"
        ),
        Racer(
            "Sergio Perez", "Racing Point"
        ),
        Racer(
            "Kimi Räikkönen", "Alfa Romeo"
        ),
        Racer(
            "Daniel Ricciardo", "Renault"
        ),
        Racer(
            "Carlos Sainz", "McLaren"
        )

    )

}
