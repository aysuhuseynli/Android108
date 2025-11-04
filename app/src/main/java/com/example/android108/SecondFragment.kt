package com.example.android108

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.android108.databinding.FragmentSecondBinding
import com.example.android108.db.RoomDB
import com.example.android108.db.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondFragment : Fragment() {

    private var binding: FragmentSecondBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let { binding ->
            binding.submitBtn.setOnClickListener {
                val username = binding.username.text.toString()
                val age = binding.age.text.toString()
                val userDao = RoomDB.getInstance(requireContext()).userDao()
                val userInfo = UserEntity(username = username, age = age)

                CoroutineScope(Dispatchers.IO).launch {
                    userDao.insert(userInfo)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(),"successful!", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}