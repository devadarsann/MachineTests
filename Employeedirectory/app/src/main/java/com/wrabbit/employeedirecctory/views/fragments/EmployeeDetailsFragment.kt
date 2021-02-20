package com.wrabbit.employeedirecctory.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.navArgument
import com.bumptech.glide.Glide
import com.wrabbit.employeedirecctory.R
import com.wrabbit.employeedirecctory.util.EMPLOYEE_ID
import com.wrabbit.employeedirecctory.viewModels.viewModel.DetailsViewModel
import kotlinx.android.synthetic.main.fragment_employee_details.*
import kotlinx.android.synthetic.main.list_item_employee.*

class EmployeeDetailsFragment : Fragment(R.layout.fragment_employee_details) {

    private val detailsViewModel by viewModels<DetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            detailsViewModel.employeeLiveData(bundle.getInt(EMPLOYEE_ID)).observe(this){ employee->
                employee?.let { employe->
                    textView_name.text = employe.name
                    textView_email.text = employe.email
                    textView_phone.text = employe.phone
                    textView_website.text = employe.website
                    textView_companydetails.text = "${employe.company?.companyname}"
                    textView_address.text = "${employe.address?.street}\n${employe.address?.city}\n${employe.address?.zipcode}"
                    Glide.with(view.context).load(employe.profile_image).fitCenter().into(imageview_profile)
                }
            }
        }
    }
}