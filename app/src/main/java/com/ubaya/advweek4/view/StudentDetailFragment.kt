package com.ubaya.advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ubaya.advweek4.R
import com.ubaya.advweek4.databinding.StudentListItemBinding
import com.ubaya.advweek4.util.loadImage
import com.ubaya.advweek4.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.student_list_item.*
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 * Use the [StudentDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentDetailFragment : Fragment(), ButtonDetailClickListener {
    private lateinit var viewModel : DetailViewModel
    private lateinit var dataBinding : StudentListItemBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<StudentListItemBinding>(
            inflater,R.layout.fragment_student_detail, container, false
        )
        return dataBinding.root
        //return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val getId = StudentListFragmentArgs.fromBundle(requireArguments()).id
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(getId)
        observeViewModel()
        dataBinding.listener = this

    }

    private fun observeViewModel() {
        viewModel.studentLoadData.observe(viewLifecycleOwner) {
            dataBinding.student = it
//            val student = it
//            Log.d("hasil",student.toString())
//            student?.let {
//                studentID.setText(it.id)
//                editName.setText(it.name)
//                editPhone.setText(it.phone)
//                editDOB.setText(it.dob)
//                imageStudentPhotoDetail.loadImage(student.photoUrl, progressLoadingStudentPhoto)
//                buttonNotif.setOnClickListener {
//                    Observable.timer(5, TimeUnit.SECONDS)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe {
//                            Log.d("delay","Notification delayed by five seconds!")
//                            student.name?.let {
//                                it1->
//                                MainActivity.showNotification(
//                                    it1, "Notification created", R.drawable.ic_baseline_error_24
//                                )
//                            }
//                        }
//                }
//            }
        }
    }

    override fun onButtonDetailClick(v: View) {
        TODO("Not yet implemented")
    }


}