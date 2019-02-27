package com.exercise.todolist.ui.activity

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.exercise.todolist.*
import com.exercise.todolist.model.Item
import com.exercise.todolist.ui.ItemListAdapter
import com.exercise.todolist.ui.SwipeToDeleteCallback
import com.exercise.todolist.viewmodel.ItemViewModel

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var itemViewModel: ItemViewModel

    companion object {
        const val newWordActivityRequestCode = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //val recycler_view = findViewById<RecyclerView>(R.id.recycler_view)
        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        val adapter = ItemListAdapter(this, itemViewModel)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)


        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(itemViewModel))
        itemTouchHelper.attachToRecyclerView(recycler_view)


        newItemButton.setOnClickListener {
            val intent = Intent(this@MainActivity, NewItemActivity::class.java)
            startActivityForResult(intent,
                newWordActivityRequestCode
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.apply {
                val title = getStringExtra(NewItemActivity.TITLE)
                val description = getStringExtra(NewItemActivity.DESCRIPTION)
                val done = false // Starts false
                val item = Item(title, description, done)
                itemViewModel.insert(item)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when(item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
