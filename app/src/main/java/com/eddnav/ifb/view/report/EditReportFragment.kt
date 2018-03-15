package com.eddnav.ifb.view.report

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.eddnav.ifb.R
import com.eddnav.ifb.domain.report.Report
import com.eddnav.ifb.presentation.EditReportViewModel
import com.thedeadpixelsociety.passport.*
import kotlinx.android.synthetic.main.fragment_edit_report.*

/**
 * UI for saving/editing reports.
 *
 * // TODO: as we aren't using two way bindings, the view model has no clue of what's happening in the UI, please save and load instance state accordingly.
 * @author Eduardo Naveda
 */
class EditReportFragment : Fragment() {

    private lateinit var mViewModel: EditReportViewModel
    private lateinit var mValidator: Passport
    private lateinit var mReport: Report

    // TODO: Rename and change types of parameters
    var mParam1: String? = null
    var mParam2: String? = null

    //private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mParam1 = arguments?.getString(ARG_PARAM1)
        mParam2 = arguments?.getString(ARG_PARAM2)

        mViewModel = ViewModelProviders.of(this).get(EditReportViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_report, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.load()
        mViewModel.report.observe(this, Observer<Report> {
            mReport = it!!
            populate()
        })

        mValidator = createValidator()

        val adapter = ArrayAdapter<String>(view!!.context, R.layout.spinner_item, resources.getStringArray(R.array.sex_labels))
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        sex.adapter = adapter

        saveButton.setOnClickListener {
            if (mValidator.validate(this, ValidationMethod.IMMEDIATE)) {
                mViewModel.save(mReport)
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }*/

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        /*if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }*/
    }

    override fun onDetach() {
        super.onDetach()
        //mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    /*
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }*/

    private fun populate() {
        firstName.setText(mReport.patient.firstName)
        lastName.setText(mReport.patient.lastName)
    }

    private fun save() {
        mReport.patient.firstName = firstName.text.toString()
        mViewModel.save(mReport)
    }

    private fun createValidator(): Passport {
        return passport {
            rules<String>(firstNameInputLayout) {
                notEmpty(getString(R.string.validation_not_empty))
            }
            rules<String>(lastNameInputLayout) {
                notEmpty(getString(R.string.validation_not_empty))
            }
        }
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EditReportFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): EditReportFragment {
            val fragment = EditReportFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
