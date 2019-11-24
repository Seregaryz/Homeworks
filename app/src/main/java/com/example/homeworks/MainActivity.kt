package com.example.homeworks

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter: RacerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = RacerAdapter(getDataSource()) { racer ->
            navigateToDesc(racer)
        }

        rv_racers.adapter = adapter

        sr_main.setOnRefreshListener {
            Handler().postDelayed({
                Log.e("Ss", "refresh")
                sr_main.isRefreshing = false
            }, 2000)
        }
    }

    private fun navigateToDesc(
       racer: Racer
    ) {
        startActivity(StatisticActivity.createIntent(this, racer))
    }


    private fun getDataSource(): List<Racer> = arrayListOf(
        Racer(
            "Daniil Kvyat", 25, "Scuderia Toro Rosso",
            92, 167, R.mipmap.kvyat
        ),
        Racer(
            "Max Verstappen", 22, "Red Bull Racing",
            99, 890, R.mipmap.verstappen
        ),
        Racer(
            "Lewis Hamilton", 34, "Mercedes",
            247, 3381, R.mipmap.hamilton
        ),
        Racer(
            "Robert Kubica", 34, "Williams",
            94, 274, R.mipmap.kubica
        ),
        Racer(
            "Romain Grosjean", 33, "Haas",
            163, 389, R.mipmap.grosjean
        ),
        Racer(
            "Charles Leclerc", 22, "Scuderia Ferrari",
            39, 275, R.mipmap.leclerc
        ),
        Racer(
            "Sergio Perez", 29, "Racing Point",
            175, 572, R.mipmap.perez
        ),
        Racer(
            "Kimi Räikkönen", 40, "Alfa Romeo",
            312, 1847, R.mipmap.raikkonen
        ),
        Racer(
            "Daniel Ricciardo", 30, "Renault",
            168, 1024, R.mipmap.ricciardo
        ),
        Racer(
            "Carlos Sainz", 25, "McLaren",
            99, 247, R.mipmap.sainz
        )

    )

}
