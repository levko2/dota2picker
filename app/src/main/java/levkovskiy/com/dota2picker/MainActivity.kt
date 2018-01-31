package levkovskiy.com.dota2picker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearLayoutManager.HORIZONTAL
import android.widget.ArrayAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import levkovskiy.com.dota2picker.adapter.HeroesAdapter
import levkovskiy.com.dota2picker.api.IDotaApi
import levkovskiy.com.dota2picker.model.DataResponse
import java.util.*

class MainActivity : AppCompatActivity() {

    private val api = IDotaApi.create()
    private var allHeroes: ArrayList<DataResponse.Hero> = arrayListOf()
    private var selectedHero: DataResponse.Hero? = null
    private val gson = Gson()
    private lateinit var selectedHeroesAdapter: HeroesAdapter
    private lateinit var recommendedHeroesAdapter: HeroesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getHeroesFromFile()

        rv_selected_heroes.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        rv_recommended_heroes.layoutManager = LinearLayoutManager(this, HORIZONTAL, false)

        selectedHeroesAdapter = HeroesAdapter()
        recommendedHeroesAdapter = HeroesAdapter()

        rv_selected_heroes.adapter = selectedHeroesAdapter
        rv_recommended_heroes.adapter = recommendedHeroesAdapter

        et_input_hero?.apply {
            setAdapter(ArrayAdapter(context, android.R.layout.simple_expandable_list_item_1, allHeroes))
            threshold = 1
        }
        btn_submit.setOnClickListener({
            allHeroes
                    .filter { it.localizedName == et_input_hero.text.toString() }
                    .forEach { selectedHero = it }
                    .apply {
                        if (selectedHero != null) {
                            matchupSelectedHero()
                            selectedHero?.let { it1 -> selectedHeroesAdapter.add(it1) }
                            et_input_hero.text = null
                            selectedHero = null
                        }
                    }
        })
    }

    private fun getHeroesFromFile() {
        val localList = Utils.JSONResourceReader(resources, R.raw.heroes)
        allHeroes = gson.fromJson(localList, object : TypeToken<List<DataResponse.Hero>>() {}.type)
    }

    private fun matchupSelectedHero() {
        api.getMatchup(selectedHero!!.id).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    result
                            .filter { value -> value.gamesPlayed > 50 && recommendedHeroesAdapter.itemCount < 5 }
                            .forEach { recommendedHeroesAdapter.add(allHeroes[it.heroId - 1]) }

                }, { error ->
                    error.printStackTrace()
                })
    }

}
