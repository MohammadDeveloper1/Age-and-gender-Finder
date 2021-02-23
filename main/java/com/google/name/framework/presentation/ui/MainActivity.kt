package com.google.name.framework.presentation.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.name.R
import com.google.name.framework.presentation.model.Person
import com.google.name.framework.presentation.model.PersonFactory
import com.google.name.framework.presentation.state.MainViewEvent
import com.google.name.framework.presentation.state.MainViewState
import com.neovisionaries.i18n.CountryCode
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private var selectedCountry: CountryCode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPersonObserver()
        initSimpleCountrySpinner()

        button_submit.setOnClickListener {
            setViewEvent()
        }

        spinner_country.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedCountry = CountryCode.getByAlpha2Code((parent!!.selectedItem.toString()))
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun getPersonObserver() {
        val observer = Observer<MainViewState<Person>> { viewState ->
            when (viewState) {
                is MainViewState.Loaded -> {
                    displayProgressBar(false)
                    setNameText(viewState.data)
                }
                is MainViewState.Error -> {
                    displayProgressBar(false)
                    text_name.text = viewState.errorMessage
                }
                is MainViewState.Loading -> {
                    displayProgressBar(true)
                }
            }
        }
        viewModel.viewState.observe(this, observer)
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun getCountryList(): ArrayList<String> {
        val list: ArrayList<String> = ArrayList()
        for (code: CountryCode in CountryCode.values()) {
            list.add(code.name)
        }
        return list
    }

    private fun initSimpleCountrySpinner() {
        val countryList: ArrayList<String> = getCountryList()
        val adapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, countryList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_country.adapter = adapter
    }

    private fun getName(): String {
        return text_enter_name.text!!.trim().toString()
    }

    private fun setNameText(person: Person) {
        val builder: StringBuilder = java.lang.StringBuilder()
        builder.apply {
            append(
                "name: ${person.name}\n" +
                        "age: ${person.age}\n" +
                        "gender: ${PersonFactory.getGender((person.gender))}\n" +
                        "country: ${person.country}"
            )
        }
        text_name.text = builder.toString()
    }

    private fun setViewEvent() {
        selectedCountry?.alpha2?.let {
            viewModel.setEvent(MainViewEvent.GetPersonWithCountry(getName(), it))
        }?.run {
            viewModel.setEvent(MainViewEvent.GetName(getName()))
        }
    }
}