package com.example.android108

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android108.databinding.FragmentFirstBinding
import com.example.android108.db.RoomDB
import com.example.android108.db.UserDao
import com.example.android108.db.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirstFragment : Fragment() {

    private var binding: FragmentFirstBinding? = null
    private var userList: MutableList<UserEntity> = mutableListOf()
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            val userDao = RoomDB.getInstance(requireContext()).userDao()

            CoroutineScope(Dispatchers.IO).launch {
                userList = userDao.getAllUsers().toMutableList()
                withContext(Dispatchers.Main) {
                    adapter = UserAdapter(userList)
                    it.recyclerView.adapter = adapter
                    it.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                }
            }

            handleSwipe(userDao)

            it.floatButton.setOnClickListener {
                findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
            }
        }
    }

    fun handleSwipe(userDao: UserDao){
        val swipeHandler = object : SwipeToDeleteCallBack(requireActivity()){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return  false
            }

            override fun onSwiped(
                viewHolder: RecyclerView.ViewHolder,
                direction: Int
            ) {
                val position = viewHolder.adapterPosition
                val user = userList[position]

                CoroutineScope(Dispatchers.IO).launch {
                    userDao.delete(user)
                    withContext(Dispatchers.Main){
                        adapter.removeAt(position)
                    }
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding?.recyclerView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}