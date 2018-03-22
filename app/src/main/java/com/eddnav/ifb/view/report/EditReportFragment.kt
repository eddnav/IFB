package com.eddnav.ifb.view.report

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.ArrayAdapter
import com.eddnav.ifb.R
import com.eddnav.ifb.domain.patient.Patient
import com.eddnav.ifb.domain.report.Report
import com.eddnav.ifb.presentation.EditReportViewModel
import com.thedeadpixelsociety.passport.Passport
import com.thedeadpixelsociety.passport.ValidationMethod
import com.thedeadpixelsociety.passport.notBlank
import com.thedeadpixelsociety.passport.passport
import kotlinx.android.synthetic.main.fragment_edit_report.*

/**
 * UI for saving/editing reports. No data binding as of now, a copy of the model is kept as
 * UI state, then it's passed back to the view model.
 *
 * @author Eduardo Naveda
 */
class EditReportFragment : Fragment() {

    private lateinit var mViewModel: EditReportViewModel
    private lateinit var mValidator: Passport
    private var mListener: OnFragmentInteractionListener? = null

    private var mId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        mId = arguments?.getLong(ARG_ID)

        mViewModel = ViewModelProviders.of(this).get(EditReportViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_report, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (mId != null) {
            mViewModel.get(mId!!).observe(this, Observer<Report> {
                savedInstanceState ?: populate(it!!)
            })
        } else {
            savedInstanceState ?: populate(Report.default())
        }

        mViewModel.successEvent.observe(this, Observer {
            mListener?.onSaveSuccess()
        })

        mValidator = createValidator()

        val adapter = ArrayAdapter<String>(view!!.context, R.layout.spinner_item, resources.getStringArray(R.array.sex_labels))
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        sex.adapter = adapter

        if (savedInstanceState !== null) {
            restore(savedInstanceState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(STATE_FIRST_NAME, firstName.text.toString())
        outState.putString(STATE_LAST_NAME, lastName.text.toString())
        outState.putString(STATE_AGE, age.text.toString())
        outState.putString(STATE_WEIGHT, weight.text.toString())
        outState.getInt(STATE_SEX, sex.selectedItemPosition)
        outState.putString(STATE_SURGERY_DESCRIPTION, surgeryDescription.text.toString())
        outState.putString(STATE_SURGERY_DURATION, surgeryDuration.text.toString())
        outState.putString(STATE_BLOOD_VOLUME, bloodVolume.text.toString())
        outState.putString(STATE_FASTING, fasting.text.toString())
        outState.putString(STATE_SURGICAL_STRESS, surgicalStress.text.toString())
        outState.putString(STATE_HEMOGLOBIN, hemoglobin.text.toString())
        outState.putString(STATE_MIN_HEMOGLOBIN, minHemoglobin.text.toString())
        outState.putString(STATE_CRYSTALLOIDS, crystalloids.text.toString())
        outState.putString(STATE_HEMODERIVATIVES, hemoderivatives.text.toString())
        outState.putString(STATE_DRUG_INFUSIONS, drugInfusions.text.toString())
        outState.putString(STATE_DIURESIS, diuresis.text.toString())
        outState.putString(STATE_ASPIRATION, aspiration.text.toString())
        outState.putString(STATE_COMPRESSES, compresses.text.toString())
        outState.putString(STATE_LEVINS_TUBE, levinsTube.text.toString())

        super.onSaveInstanceState(outState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_edit_report_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                attemptSave()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun populate(report: Report) {
        firstName.setText(report.patient.firstName)
        lastName.setText(report.patient.lastName)
        age.setText(report.patient.age.toString())
        weight.setText(report.patient.weight.toString())
        sex.setSelection(if (report.patient.sex == Patient.SEX_FEMALE) 0 else 1)
        surgeryDescription.setText(report.surgery.description)
        surgeryDuration.setText(report.surgery.duration.toString())
        bloodVolume.setText(report.patient.bloodVolume.toString())
        fasting.setText(report.patient.fasting.toString())
        surgicalStress.setText(report.patient.surgicalStress.toString())
        hemoglobin.setText(report.patient.hemoglobin.toString())
        minHemoglobin.setText(report.patient.minHemoglobin.toString())
        crystalloids.setText(report.patient.intake.crystalloids.toString())
        colloids.setText(report.patient.intake.colloids.toString())
        hemoderivatives.setText(report.patient.intake.hemoderivatives.toString())
        drugInfusions.setText(report.patient.intake.drugInfusions.toString())
        diuresis.setText(report.patient.output.diuresis.toString())
        aspiration.setText(report.patient.output.aspiration.toString())
        compresses.setText(report.patient.output.compresses.toString())
        levinsTube.setText(report.patient.output.levinsTube.toString())
    }

    /**
     * The [android.support.design.widget.TextInputLayout] class does not reset the error view size
     * when setting the error to null, leaving the space even after fixing the error,
     * setting [android.support.design.widget.TextInputLayout.isErrorEnabled] to false fixes this.
     */
    private fun resetErrors() {
        firstNameInputLayout.isErrorEnabled = false
        lastNameInputLayout.isErrorEnabled = false
        ageInputLayout.isErrorEnabled = false
        weightInputLayout.isErrorEnabled = false
        surgeryDescriptionInputLayout.isErrorEnabled = false
        surgeryDurationInputLayout.isErrorEnabled = false
        bloodVolumeInputLayout.isErrorEnabled = false
        fastingInputLayout.isErrorEnabled = false
        surgicalStressInputLayout.isErrorEnabled = false
        hemoglobinInputLayout.isErrorEnabled = false
        minHemoglobinInputLayout.isErrorEnabled = false
        crystalloidsInputLayout.isErrorEnabled = false
        colloidsInputLayout.isErrorEnabled = false
        hemoderivativesInputLayout.isErrorEnabled = false
        drugInfusionsInputLayout.isErrorEnabled = false
        diuresisInputLayout.isErrorEnabled = false
        aspirationInputLayout.isErrorEnabled = false
        compressesInputLayout.isErrorEnabled = false
        levinsTubeInputLayout.isErrorEnabled = false
    }

    private fun validate(): Boolean {
        var pass = true

        if (!mValidator.validate(firstNameInputLayout, ValidationMethod.IMMEDIATE)) pass = false
        if (!mValidator.validate(lastNameInputLayout, ValidationMethod.IMMEDIATE)) pass = false
        if (!mValidator.validate(ageInputLayout, ValidationMethod.IMMEDIATE)) pass = false
        if (!mValidator.validate(weightInputLayout, ValidationMethod.IMMEDIATE)) pass = false
        if (!mValidator.validate(surgeryDescriptionInputLayout, ValidationMethod.IMMEDIATE)) pass = false
        if (!mValidator.validate(surgeryDurationInputLayout, ValidationMethod.IMMEDIATE)) pass = false
        if (!mValidator.validate(bloodVolumeInputLayout, ValidationMethod.IMMEDIATE)) pass = false
        if (!mValidator.validate(fastingInputLayout, ValidationMethod.IMMEDIATE)) pass = false
        if (!mValidator.validate(surgicalStressInputLayout, ValidationMethod.IMMEDIATE)) pass = false
        if (!mValidator.validate(hemoglobinInputLayout, ValidationMethod.IMMEDIATE)) pass = false
        if (!mValidator.validate(minHemoglobinInputLayout, ValidationMethod.IMMEDIATE)) pass = false
        if (!mValidator.validate(crystalloidsInputLayout, ValidationMethod.IMMEDIATE)) pass = false
        if (!mValidator.validate(colloidsInputLayout, ValidationMethod.IMMEDIATE)) pass = false
        if (!mValidator.validate(hemoderivativesInputLayout, ValidationMethod.IMMEDIATE)) pass = false
        if (!mValidator.validate(drugInfusionsInputLayout, ValidationMethod.IMMEDIATE)) pass = false
        if (!mValidator.validate(diuresisInputLayout, ValidationMethod.IMMEDIATE)) pass = false
        if (!mValidator.validate(aspirationInputLayout, ValidationMethod.IMMEDIATE)) pass = false
        if (!mValidator.validate(compressesInputLayout, ValidationMethod.IMMEDIATE)) pass = false
        if (!mValidator.validate(levinsTubeInputLayout, ValidationMethod.IMMEDIATE)) pass = false

        return pass
    }

    private fun createValidator(): Passport {
        return passport {
            rules<String>(firstNameInputLayout) {
                notBlank(getString(R.string.validation_not_blank))
            }
            rules<String>(lastNameInputLayout) {
                notBlank(getString(R.string.validation_not_blank))
            }
            rules<String>(ageInputLayout) {
                notBlank(getString(R.string.validation_not_blank))
                rule({ it.toIntOrNull() != null }, { getString(R.string.validation_valid_integer) })
                rule({ it.toInt() > 0 }, { getString(R.string.validation_greater_than_0) })
            }
            rules<String>(weightInputLayout) {
                notBlank(getString(R.string.validation_not_blank))
                rule({ it.toDoubleOrNull() != null }, { getString(R.string.validation_valid_decimal) })
                rule({ it.toDoubleOrNull() != null || it.toDouble() > 0 }, { getString(R.string.validation_greater_than_0) })
            }
            rules<String>(surgeryDescriptionInputLayout) {
                notBlank(getString(R.string.validation_not_blank))
            }
            rules<String>(surgeryDurationInputLayout) {
                notBlank(getString(R.string.validation_not_blank))
                rule({ it.toDoubleOrNull() != null }, { getString(R.string.validation_valid_decimal) })
                rule({ it.toDouble() > 0 }, { getString(R.string.validation_greater_than_0) })
            }
            rules<String>(bloodVolumeInputLayout) {
                notBlank(getString(R.string.validation_not_blank))
                rule({ it.toDoubleOrNull() != null }, { getString(R.string.validation_valid_decimal) })
                rule({ it.toDouble() > 0 }, { getString(R.string.validation_greater_than_0) })
            }
            rules<String>(fastingInputLayout) {
                notBlank(getString(R.string.validation_not_blank))
                rule({ it.toDoubleOrNull() != null }, { getString(R.string.validation_valid_decimal) })
                rule({ it.toDouble() >= 0 }, { getString(R.string.validation_equal_or_greater_than_0) })
            }
            rules<String>(surgicalStressInputLayout) {
                notBlank(getString(R.string.validation_not_blank))
                rule({ it.toIntOrNull() != null }, { getString(R.string.validation_valid_integer) })
                rule({ it.toInt() >= 0 }, { getString(R.string.validation_equal_or_greater_than_0) })
                rule({ it.toInt() < Patient.MAX_SURGICAL_STRESS }, { getString(R.string.validation_greater_than_x).format(Patient.MAX_SURGICAL_STRESS) })
            }
            rules<String>(hemoglobinInputLayout) {
                notBlank(getString(R.string.validation_not_blank))
                rule({ it.toDoubleOrNull() != null }, { getString(R.string.validation_valid_decimal) })
                rule({ it.toDouble() > 0 }, { getString(R.string.validation_greater_than_0) })
            }
            rules<String>(minHemoglobinInputLayout) {
                notBlank(getString(R.string.validation_not_blank))
                rule({ it.toDoubleOrNull() != null }, { getString(R.string.validation_valid_decimal) })
                rule({ it.toDouble() > 0 }, { getString(R.string.validation_greater_than_0) })
            }
            rules<String>(crystalloidsInputLayout) {
                notBlank(getString(R.string.validation_not_blank))
                rule({ it.toDoubleOrNull() != null }, { getString(R.string.validation_valid_decimal) })
                rule({ it.toDouble() >= 0 }, { getString(R.string.validation_equal_or_greater_than_0) })
            }
            rules<String>(colloidsInputLayout) {
                notBlank(getString(R.string.validation_not_blank))
                rule({ it.toDoubleOrNull() != null }, { getString(R.string.validation_valid_decimal) })
                rule({ it.toDouble() >= 0 }, { getString(R.string.validation_equal_or_greater_than_0) })
            }
            rules<String>(hemoderivativesInputLayout) {
                notBlank(getString(R.string.validation_not_blank))
                rule({ it.toDoubleOrNull() != null }, { getString(R.string.validation_valid_decimal) })
                rule({ it.toDouble() >= 0 }, { getString(R.string.validation_equal_or_greater_than_0) })
            }
            rules<String>(drugInfusionsInputLayout) {
                notBlank(getString(R.string.validation_not_blank))
                rule({ it.toDoubleOrNull() != null }, { getString(R.string.validation_valid_decimal) })
                rule({ it.toDouble() >= 0 }, { getString(R.string.validation_equal_or_greater_than_0) })
            }
            rules<String>(diuresisInputLayout) {
                notBlank(getString(R.string.validation_not_blank))
                rule({ it.toDoubleOrNull() != null }, { getString(R.string.validation_valid_decimal) })
                rule({ it.toDouble() >= 0 }, { getString(R.string.validation_equal_or_greater_than_0) })
            }
            rules<String>(aspirationInputLayout) {
                notBlank(getString(R.string.validation_not_blank))
                rule({ it.toDoubleOrNull() != null }, { getString(R.string.validation_valid_decimal) })
                rule({ it.toDouble() >= 0 }, { getString(R.string.validation_equal_or_greater_than_0) })
            }
            rules<String>(compressesInputLayout) {
                notBlank(getString(R.string.validation_not_blank))
                rule({ it.toDoubleOrNull() != null }, { getString(R.string.validation_valid_decimal) })
                rule({ it.toDouble() >= 0 }, { getString(R.string.validation_equal_or_greater_than_0) })
            }
            rules<String>(levinsTubeInputLayout) {
                notBlank(getString(R.string.validation_not_blank))
                rule({ it.toDoubleOrNull() != null }, { getString(R.string.validation_valid_decimal) })
                rule({ it.toDouble() >= 0 }, { getString(R.string.validation_equal_or_greater_than_0) })
            }
        }
    }

    private fun restore(savedInstanceState: Bundle) {
        firstName.setText(savedInstanceState.getString(STATE_FIRST_NAME))
        lastName.setText(savedInstanceState.getString(STATE_LAST_NAME))
        age.setText(savedInstanceState.getString(STATE_AGE))
        weight.setText(savedInstanceState.getString(STATE_WEIGHT))
        sex.setSelection(savedInstanceState.getInt(STATE_SEX))
        surgeryDescription.setText(savedInstanceState.getString(STATE_SURGERY_DESCRIPTION))
        surgeryDuration.setText(savedInstanceState.getString(STATE_SURGERY_DURATION))
        bloodVolume.setText(savedInstanceState.getString(STATE_BLOOD_VOLUME))
        fasting.setText(savedInstanceState.getString(STATE_FASTING))
        surgicalStress.setText(savedInstanceState.getString(STATE_SURGICAL_STRESS))
        hemoglobin.setText(savedInstanceState.getString(STATE_HEMOGLOBIN))
        minHemoglobin.setText(savedInstanceState.getString(STATE_MIN_HEMOGLOBIN))
        crystalloids.setText(savedInstanceState.getString(STATE_CRYSTALLOIDS))
        colloids.setText(savedInstanceState.getString(STATE_COLLOIDS))
        hemoderivatives.setText(savedInstanceState.getString(STATE_HEMODERIVATIVES))
        drugInfusions.setText(savedInstanceState.getString(STATE_DRUG_INFUSIONS))
        diuresis.setText(savedInstanceState.getString(STATE_DIURESIS))
        aspiration.setText(savedInstanceState.getString(STATE_ASPIRATION))
        compresses.setText(savedInstanceState.getString(STATE_COMPRESSES))
        levinsTube.setText(savedInstanceState.getString(STATE_LEVINS_TUBE))
    }

    private fun attemptSave() {
        resetErrors()
        if (validate()) save()
    }

    private fun save() {
        val report = Report.default()
        report.patient.firstName = firstName.text.toString()
        report.patient.lastName = lastName.text.toString()
        report.patient.age = age.text.toString().toInt()
        report.patient.weight = weight.text.toString().toDouble()
        report.patient.sex = if (sex.selectedItemPosition == 0) Patient.SEX_FEMALE else Patient.SEX_MALE
        report.surgery.description = surgeryDescription.text.toString()
        report.surgery.duration = surgeryDuration.text.toString().toDouble()
        report.patient.bloodVolume = bloodVolume.text.toString().toDouble()
        report.patient.fasting = fasting.text.toString().toDouble()
        report.patient.surgicalStress = surgicalStress.text.toString().toInt()
        report.patient.hemoglobin = hemoglobin.text.toString().toDouble()
        report.patient.minHemoglobin = minHemoglobin.text.toString().toDouble()
        report.patient.intake.crystalloids = crystalloids.text.toString().toDouble()
        report.patient.intake.colloids = colloids.text.toString().toDouble()
        report.patient.intake.hemoderivatives = hemoderivatives.text.toString().toDouble()
        report.patient.intake.drugInfusions = drugInfusions.text.toString().toDouble()
        report.patient.output.diuresis = diuresis.text.toString().toDouble()
        report.patient.output.aspiration = aspiration.text.toString().toDouble()
        report.patient.output.compresses = compresses.text.toString().toDouble()
        report.patient.output.levinsTube = levinsTube.text.toString().toDouble()

        mViewModel.save(report)
    }

    interface OnFragmentInteractionListener {
        fun onSaveSuccess()
    }

    companion object {

        private const val STATE_FIRST_NAME = "state_first_name"
        private const val STATE_LAST_NAME = "state_last_name"
        private const val STATE_AGE = "state_age"
        private const val STATE_WEIGHT = "state_weight"
        private const val STATE_SEX = "state_sex"
        private const val STATE_SURGERY_DESCRIPTION = "state_surgery_description"
        private const val STATE_SURGERY_DURATION = "state_surgery_duration"
        private const val STATE_BLOOD_VOLUME = "state_blood_volume"
        private const val STATE_FASTING = "state_fasting"
        private const val STATE_SURGICAL_STRESS = "state_surgical_stress"
        private const val STATE_HEMOGLOBIN = "state_hemoglobin"
        private const val STATE_MIN_HEMOGLOBIN = "state_min_hemoglobin"
        private const val STATE_CRYSTALLOIDS = "state_crystalloids"
        private const val STATE_COLLOIDS = "state_colloids"
        private const val STATE_HEMODERIVATIVES = "state_hemoderivatives"
        private const val STATE_DRUG_INFUSIONS = "state_drug_infusions"
        private const val STATE_DIURESIS = "state_diuresis"
        private const val STATE_ASPIRATION = "state_aspiration"
        private const val STATE_COMPRESSES = "state_compresses"
        private const val STATE_LEVINS_TUBE = "state_levins_tube"

        private const val ARG_ID = "arg_id"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param id Id of the editing subject.
         * @return A new instance of fragment EditReportFragment.
         */
        fun newInstance(id: Long): EditReportFragment {
            val fragment = EditReportFragment()
            val args = Bundle()
            args.putLong(ARG_ID, id)
            fragment.arguments = args
            return fragment
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment.
         */
        fun newInstance(): EditReportFragment {
            return EditReportFragment()
        }
    }
}
