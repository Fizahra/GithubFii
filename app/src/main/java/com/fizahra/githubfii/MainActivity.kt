package com.fizahra.githubfii

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.fizahra.githubfii.adapter.UserAdapter
import com.fizahra.githubfii.databinding.ActivityMainBinding
import com.fizahra.githubfii.model.UserResponse
import com.fizahra.githubfii.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var adapter : UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        mainViewModel.listUser.observe(this){
            mainViewModel.listUser
            if(it != null){
                binding.tvSearch.visibility = View.GONE
                adapter.setListUser(it)
            }
        }

        setListUsers()

        mainViewModel.toastMessage.observe(this) { toastMessage ->
            Toast.makeText(
                this@MainActivity,
                toastMessage,
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.menu_search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                mainViewModel.setSearchUser(query)
                searchView.clearFocus()
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        return true
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pbRv.visibility = View.VISIBLE
        } else {
            binding.pbRv.visibility = View.GONE
        }
    }

    private fun setListUsers() {
        val list = ArrayList<UserResponse.User>()
        adapter = UserAdapter(list)
        binding.apply {
            rvUsers.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUsers.setHasFixedSize(true)
            rvUsers.adapter = adapter
        }
    }

}