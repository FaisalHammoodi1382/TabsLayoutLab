/*
 Name: Faisal Hammoodi
 Student ID: n01701382
*/

package faisal.hammoodi.tabslayout;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Rightdi extends Fragment {

    private TextView tvReceived;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_right, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvReceived = view.findViewById(R.id.faiTvReceivedColor);

        // Default: NO DATA (gray)
        tvReceived.setText(getString(R.string.no_data));
        tvReceived.setTextColor(ContextCompat.getColor(requireContext(), R.color.fai_gray));

        // Listen for result from LeftFa (Tab 1)
        getParentFragmentManager().setFragmentResultListener(
                LeftFa.RESULT_KEY,
                this,
                (requestKey, bundle) -> {
                    String color = bundle.getString(LeftFa.BUNDLE_COLOR, getString(R.string.no_data));
                    applyColorToText(color);
                }
        );

        // Sports section
        CheckBox cbHockey = view.findViewById(R.id.faiCbHockey);
        CheckBox cbBasketball = view.findViewById(R.id.faiCbBasketball);
        CheckBox cbBaseball = view.findViewById(R.id.faiCbBaseball);
        Button btnSport = view.findViewById(R.id.faiBtnSport);

        btnSport.setOnClickListener(v -> {
            ArrayList<String> selected = new ArrayList<>();

            if (cbHockey.isChecked()) selected.add(getString(R.string.hockey));
            if (cbBasketball.isChecked()) selected.add(getString(R.string.basketball));
            if (cbBaseball.isChecked()) selected.add(getString(R.string.baseball));


            String message = selected.isEmpty()
                    ? getString(R.string.no_sport)
                    : String.join("\n", selected);

            AlertDialog dialog = new AlertDialog.Builder(requireContext())
                    .setIcon(R.drawable.ic_sports)
                    .setTitle(getString(R.string.selected_sports_title))
                    .setMessage(message)
                    .setPositiveButton(getString(R.string.ok), (d, which) -> d.dismiss())
                    .create();

            dialog.show();

            // Make OK button pink (matches screenshot)
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                    .setTextColor(ContextCompat.getColor(requireContext(), R.color.fai_checkbox_pink));
        });
    }

    private void applyColorToText(String color) {
        // Show exactly like screenshot: "Color selected:\nYellow"
        tvReceived.setText(getString(R.string.color_selected_fmt, color));

        String normalized = color.trim().toLowerCase();

        if (normalized.equals(getString(R.string.color_green).toLowerCase())) {
            tvReceived.setTextColor(ContextCompat.getColor(requireContext(), R.color.fai_green));
        } else if (normalized.equals(getString(R.string.color_yellow).toLowerCase())) {
            tvReceived.setTextColor(ContextCompat.getColor(requireContext(), R.color.fai_yellow));
        } else if (normalized.equals(getString(R.string.color_red).toLowerCase())) {
            tvReceived.setTextColor(ContextCompat.getColor(requireContext(), R.color.fai_red));
        } else if (normalized.equals(getString(R.string.color_other).toLowerCase())) {
            tvReceived.setTextColor(ContextCompat.getColor(requireContext(), R.color.fai_other));
        } else {
            tvReceived.setTextColor(ContextCompat.getColor(requireContext(), R.color.fai_gray));
        }
    }
}
