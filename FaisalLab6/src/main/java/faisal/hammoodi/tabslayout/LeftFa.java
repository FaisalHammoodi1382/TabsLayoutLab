/*
 Name: Faisal Hammoodi
 Student ID: n01701382
*/

package faisal.hammoodi.tabslayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LeftFa extends Fragment {

    public static final String RESULT_KEY = "fai_color_result";
    public static final String BUNDLE_COLOR = "fai_selected_color";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_left, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RadioGroup rgColors = view.findViewById(R.id.faiRgColors);
        Button btnSubmit = view.findViewById(R.id.faiBtnSubmit);

        btnSubmit.setOnClickListener(v -> {
            int checkedId = rgColors.getCheckedRadioButtonId();
            if (checkedId == -1) {
                Toast.makeText(requireContext(), getString(R.string.no_data), Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton rb = view.findViewById(checkedId);
            if (rb == null) {
                Toast.makeText(requireContext(), getString(R.string.no_data), Toast.LENGTH_SHORT).show();
                return;
            }

            String selectedColor = rb.getText().toString();
            Toast.makeText(requireContext(), selectedColor, Toast.LENGTH_LONG).show();

            Bundle result = new Bundle();
            result.putString(BUNDLE_COLOR, selectedColor);
            getParentFragmentManager().setFragmentResult(RESULT_KEY, result);
        });
    }
}
