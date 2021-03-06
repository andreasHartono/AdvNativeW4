package com.ubaya.advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.advweek4.model.Student
import com.ubaya.advweek4.view.StudentListFragment
import com.ubaya.advweek4.view.StudentListFragmentArgs

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    val studentLoadData = MutableLiveData<Student>()
    val TAG = "detailtag"
    private var queue: RequestQueue? = null
    //var getId = StudentListFragmentArgs.fromBundle(requireArguments()).id
    fun fetch(studentID:Int) {
//        val student1 = Student("16055","Nonie","1998/03/28","5718444778",
//            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
//        studentLoadData.value = student1

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php?id=$studentID"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
               val sType = object : TypeToken<Student>() {}.type
                val result = Gson().fromJson<Student>(it,sType)
                studentLoadData.value = result
                Log.d("showvoley",it)
            },
            {
                Log.d("showvoley",it.toString())
            }
        ).apply {
            tag = TAG
        }
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}