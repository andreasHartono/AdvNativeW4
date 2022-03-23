package com.ubaya.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewTreeLifecycleOwner
import com.ubaya.advweek4.R
import com.ubaya.advweek4.viewmodel.DetailViewModel
import com.ubaya.advweek4.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_student_detail.*

/**
 * A simple [Fragment] subclass.
 * Use the [StudentDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentDetailFragment : Fragment() {
    private lateinit var viewModel : DetailViewModel
    private val detailStudentAdapter = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            val getId = StudentListFragmentArgs.fromBundle(requireArguments()).id
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetch(getId)
            observeViewModel()
        }

    }

    private fun observeViewModel() {
        viewModel.studentLoadData.observe(viewLifecycleOwner) {
            val student = viewModel.studentLoadData.value
            student?.let {
                editID.setText(it.id)
                editName.setText(it.name)
                editPhone.setText(it.phone)
                editDOB.setText(it.dob)
            }
        }
    }


}