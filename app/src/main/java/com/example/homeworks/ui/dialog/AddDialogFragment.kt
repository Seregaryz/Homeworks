package com.example.homeworks.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.homeworks.R
import com.example.homeworks.ui.racer.Racer
import kotlinx.android.synthetic.main.fragment_add_dialog.view.*

class AddDialogFragment(private val callback: (Racer, String) -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.fragment_add_dialog, null, false)
        val dialog = AlertDialog.Builder (
           ContextThemeWrapper(
               checkNotNull(context),
               R.style.ThemeOverlay_MaterialComponents
           )
        )
            .setTitle("Add new racer")
            .setPositiveButton("Apply") {_, _ ->
                callback(
                    Racer(
                        dialogView.et_racer_name?.text.toString(),
                        dialogView.et_racer_team?.text.toString()
                    ),
                    dialogView.et_position?.text?.toString() ?: "-1"
                )
            }
            .setNegativeButton("Cancel") { _, _ ->
                dismiss()
            }
            .setView(dialogView)
            .create()
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        return dialog

    }

    companion object {

        fun show(fragmentManager: FragmentManager,
                 callback: (Racer, String) -> Unit): AddDialogFragment =
            AddDialogFragment(callback).apply {
                show(fragmentManager, AddDialogFragment::class.java.name)
            }
        }

    }
