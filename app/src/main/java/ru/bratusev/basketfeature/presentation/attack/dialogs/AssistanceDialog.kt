package ru.bratusev.basketfeature.presentation.attack.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.bratusev.basketfeature.R

class AssistanceDialog() : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.assistance_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundResource(R.drawable.rounded_top_corners)

        view.findViewById<AppCompatButton>(R.id.assistance_noBtn).setOnClickListener {
            //findNavController().navigate(R.id.action_attackFoulFragment_to_timeFragment)
            dismiss()
        }
        view.findViewById<AppCompatButton>(R.id.assistance_noBtn).setOnClickListener {
            //findNavController().navigate(R.id.action_attackFoulFragment_to_timeFragment)
            dismiss()
        }
    }
}