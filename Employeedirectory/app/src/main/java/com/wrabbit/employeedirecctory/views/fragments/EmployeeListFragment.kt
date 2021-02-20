package com.wrabbit.employeedirecctory.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.wrabbit.employeedirecctory.R
import com.wrabbit.employeedirecctory.util.EMPLOYEE_ID
import com.wrabbit.employeedirecctory.util.EMPLOYEE_NAME
import com.wrabbit.employeedirecctory.viewModels.viewModel.EmployeeViewModel
import com.wrabbit.employeedirecctory.views.adapters.EmployeeAdapter
import kotlinx.android.synthetic.main.fragment_employee_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */

class EmployeeListFragment : Fragment(R.layout.fragment_employee_list) {

    private val viewModel by viewModels<EmployeeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.apply {
            this.layoutManager = LinearLayoutManager(this.context)
            this.adapter = EmployeeAdapter { employee ->
                val bundle =
                    bundleOf(EMPLOYEE_ID to employee.id, EMPLOYEE_NAME to employee.name)
                findNavController().navigate(R.id.action_listfragment_to_detailsfragment, bundle)
            }
            this.addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    (this.layoutManager as LinearLayoutManager).orientation
                )
            )
        }

        viewModel.employeeListLiveData.observe(this){ employeeList ->
            cardViewListLoading.visibility = when{
                employeeList.isEmpty() -> View.VISIBLE
                else -> View.GONE
            }

            (recyclerView.adapter as EmployeeAdapter).submitList(employeeList)

        }
    }

}