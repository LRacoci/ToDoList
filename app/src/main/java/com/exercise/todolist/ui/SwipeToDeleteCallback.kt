package com.exercise.todolist.ui

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import com.exercise.todolist.viewmodel.ItemViewModel

class SwipeToDeleteCallback(private val vm : ItemViewModel) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
    private val background = ColorDrawable(Color.WHITE)

    override fun onMove(todoList: RecyclerView, toDoHolder: RecyclerView.ViewHolder, toDoHolder2: RecyclerView.ViewHolder): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        vm.delete(position)

    }

    override fun onChildDraw(
        c : Canvas,
        recyclerView : RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX : Float,
        dY : Float,
        actionState : Int,
        isCurrentlyActive : Boolean
    ) {
        super.onChildDraw(
            c,
            recyclerView,
            viewHolder,
            dX,
            dY,
            actionState,
            isCurrentlyActive
        )
        val itemView : View = viewHolder.itemView
        val backgroundCornerOffset = 20

        when {
            dX > 0 -> {
                // Swiping to the right
                background.setBounds(
                    itemView.left,
                    itemView.top,
                    itemView.left + dX.toInt() + backgroundCornerOffset,
                    itemView.bottom
                )
            }
            dX < 0 -> {
                // Swiping to the left
                background.setBounds(
                    itemView.right + dX.toInt() - backgroundCornerOffset,
                    itemView.top,
                    itemView.right,
                    itemView.bottom
                )
            }
            else -> {
                // view is unSwiped
                background.setBounds(0, 0, 0, 0)
            }
        }
        background.draw(c)
    }
}