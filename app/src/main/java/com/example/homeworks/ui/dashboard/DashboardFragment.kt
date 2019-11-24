package com.example.homeworks.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homeworks.R
import com.example.homeworks.ui.dialog.AddDialogFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    private var adapter: RacerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_dashboard, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = RacerAdapter(getDataSource())
        rv_racers?.adapter = adapter
        floating_action_button.setOnClickListener {
//            val fragmentTransaction = childFragmentManager.beginTransaction()
//            val prev = childFragmentManager.findFragmentByTag("dialog")
//            if (prev != null) {
//                fragmentTransaction.remove(prev)
//            }
//            fragmentTransaction.addToBackStack(null)
            AddDialogFragment.show(checkNotNull(activity).supportFragmentManager) {x, y -> updateList(x,y)}
        }
    }

    fun updateList(racer: Racer, position : String) {
        adapter?.addNewRacer(racer, position.toInt())
    }

    companion object {
        fun newInstance(): DashboardFragment = DashboardFragment()
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