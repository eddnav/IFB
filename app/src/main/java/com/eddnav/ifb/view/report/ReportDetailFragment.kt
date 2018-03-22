package com.eddnav.ifb.view.report

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eddnav.ifb.R
import com.eddnav.ifb.domain.patient.Patient
import com.eddnav.ifb.domain.report.Report
import com.eddnav.ifb.presentation.ReportDetailViewModel
import kotlinx.android.synthetic.main.fragment_report_detail.*

/**
 * @author
 */
class ReportDetailFragment : Fragment() {

    private lateinit var mViewModel: ReportDetailViewModel

    private var mId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mId = arguments!!.getLong(ARG_ID)
        } else {
            throw IllegalArgumentException("getArguments() is null, must define ${ReportDetailFragment.ARG_ID}. Please use the factory methods to create this fragment.")
        }

        mViewModel = ViewModelProviders.of(this).get(ReportDetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        mViewModel.get(mId!!).observe(this, Observer<Report> {
            populate(it!!)
        })

        super.onActivityCreated(savedInstanceState)
    }

    private fun populate(report: Report) {
        fullName.text = report.patient.fullName
        basicInfo.text = getString(R.string.value_basic_info)
                .format(
                        if (report.patient.sex === Patient.SEX_FEMALE) getString(R.string.value_female) else getString(R.string.value_male),
                        report.patient.age,
                        report.patient.weight)
        surgeryDescription.text = report.surgery.description
        surgeryDuration.text = getString(R.string.value_surgery_duration).format(report.surgery.duration)
        bloodVolume.text = getString(R.string.value_blood_volume).format(report.patient.bloodVolume)
        hemoglobin.text = getString(R.string.value_hemoglobin).format(report.patient.hemoglobin, report.patient.minHemoglobin)
        fasting.text = getString(R.string.value_fasting).format(report.patient.fasting)
        surgicalStress.text = getString(R.string.value_surgical_stress).format(report.patient.surgicalStress, Patient.MAX_SURGICAL_STRESS)
        bh1h.text = getString(R.string.value_2f).format(report.hydrationSchedule.base[0])
        bh2h.text = getString(R.string.value_2f).format(report.hydrationSchedule.base[1])
        bh3h.text = getString(R.string.value_2f).format(report.hydrationSchedule.base[2])
        bh4h.text = getString(R.string.value_2f).format(report.hydrationSchedule.base[3])
        il1h.text = getString(R.string.value_2f).format(report.hydrationSchedule.insensibleLosses[0])
        il2h.text = getString(R.string.value_2f).format(report.hydrationSchedule.insensibleLosses[1])
        il3h.text = getString(R.string.value_2f).format(report.hydrationSchedule.insensibleLosses[2])
        il4h.text = getString(R.string.value_2f).format(report.hydrationSchedule.insensibleLosses[3])
        f1h.text = getString(R.string.value_2f).format(report.hydrationSchedule.fasting[0])
        f2h.text = getString(R.string.value_2f).format(report.hydrationSchedule.fasting[1])
        f3h.text = getString(R.string.value_2f).format(report.hydrationSchedule.fasting[2])
        f4h.text = getString(R.string.value_2f).format(report.hydrationSchedule.fasting[3])
        ss1h.text = getString(R.string.value_2f).format(report.hydrationSchedule.surgicalStress[0])
        ss2h.text = getString(R.string.value_2f).format(report.hydrationSchedule.surgicalStress[1])
        ss3h.text = getString(R.string.value_2f).format(report.hydrationSchedule.surgicalStress[2])
        ss4h.text = getString(R.string.value_2f).format(report.hydrationSchedule.surgicalStress[3])
        crystalloids.text = getString(R.string.value_crystalloids).format(report.patient.intake.crystalloids)
        colloids.text = getString(R.string.value_colloids).format(report.patient.intake.colloids)
        hemoderivatives.text = getString(R.string.value_hemoderivatives).format(report.patient.intake.hemoderivatives)
        drugInfusions.text = getString(R.string.value_drug_infusions).format(report.patient.intake.drugInfusions)
        totalIntake.text = getString(R.string.value_total).format(report.patient.intake.total)
        diuresis.text = getString(R.string.value_diuresis).format(report.patient.output.diuresis)
        aspiration.text = getString(R.string.value_aspiration).format(report.patient.output.aspiration)
        compresses.text = getString(R.string.value_compresses).format(report.patient.output.compresses)
        levinsTube.text = getString(R.string.value_levins_tube).format(report.patient.output.levinsTube)
        totalOutput.text = getString(R.string.value_total).format(report.patient.output.total)
        ffb.text = getString(R.string.value_ffb).format(report.finalFluidBalance)
        mabl.text = getString(R.string.value_mabl).format(report.minimumAllowableBloodLoss)
        hourlyDiuresis.text = getString(R.string.value_hourly_diuresis).format(report.hourlyDiuresis)
        urineOutput.text = getString(R.string.value_urine_output).format(report.urineOutput)
        intakeSupply.text = getString(R.string.value_intake_supply).format(report.intakeSupply)
    }

    companion object {

        private const val ARG_ID = "arg_id"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param id Id of the report.
         * @return A new instance of fragment ReportDetailFragment.
         */
        fun newInstance(id: Long): ReportDetailFragment {
            val fragment = ReportDetailFragment()
            val args = Bundle()
            args.putLong(ARG_ID, id)
            fragment.arguments = args
            return fragment
        }
    }
}
