package vijay.darwinlab.com.androidtest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vijay.darwinlab.com.androidtest.R;
import vijay.darwinlab.com.androidtest.main.MainActivity;



/**
 * Created by Vijay on 05-09-2017.
 */

public class EnterPinFragment extends Fragment {
    @BindView(R.id.btn_one)
    TextView btnOne;
    @BindView(R.id.btn_two)
    TextView btnTwo;
    @BindView(R.id.btn_three)
    TextView btnThree;
    @BindView(R.id.btn_four)
    TextView btnFour;
    @BindView(R.id.btn_five)
    TextView btnFive;
    @BindView(R.id.btn_six)
    TextView btnSix;
    @BindView(R.id.btn_seven)
    TextView btnSeven;
    @BindView(R.id.btn_eight)
    TextView btnEight;
    @BindView(R.id.btn_nine)
    TextView btnNine;
    @BindView(R.id.btn_backspace)
    ImageView btnBackspace;
    @BindView(R.id.btn_zero)
    TextView btnZero;
    @BindView(R.id.btn_decimal)
    TextView btnDecimal;
    Unbinder unbinder;
    @BindView(R.id.dot_one)
    TextView dotOne;
    @BindView(R.id.dot_two)
    TextView dotTwo;
    @BindView(R.id.dot_three)
    TextView dotThree;
    @BindView(R.id.dot_four)
    TextView dotFour;
    @BindView(R.id.submitpin)
    ImageView submitpin;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enterpin, container, false);

        setUpDrawerAttributes();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void setUpDrawerAttributes() {
        ((MainActivity) getActivity()).
                changeDrawerAttributes(false, ContextCompat.getDrawable(getContext(),
                        R.drawable.backicon), getResources().getString(R.string.enter_pin));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_one, R.id.btn_two, R.id.btn_three,
            R.id.btn_four, R.id.btn_five, R.id.btn_six, R.id.btn_seven,
            R.id.btn_eight, R.id.btn_nine, R.id.btn_backspace, R.id.btn_zero, R.id.btn_decimal,
            R.id.submitpin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_one:
                setPinDots(btnOne.getText().toString());
                break;
            case R.id.btn_two:
                setPinDots(btnTwo.getText().toString());

                break;
            case R.id.btn_three:
                setPinDots(btnThree.getText().toString());

                break;
            case R.id.btn_four:
                setPinDots(btnFour.getText().toString());

                break;
            case R.id.btn_five:
                setPinDots(btnFive.getText().toString());

                break;
            case R.id.btn_six:
                setPinDots(btnSix.getText().toString());

                break;
            case R.id.btn_seven:
                setPinDots(btnSeven.getText().toString());

                break;
            case R.id.btn_eight:
                setPinDots(btnEight.getText().toString());

                break;
            case R.id.btn_nine:
                setPinDots(btnNine.getText().toString());

                break;
            case R.id.btn_backspace:
                removePinDots();
                break;
            case R.id.btn_zero:
                setPinDots(btnZero.getText().toString());

                break;
            case R.id.btn_decimal:
                setPinDots(btnDecimal.getText().toString());

                break;
        }
    }

    private void removePinDots() {
        if (!dotFour.getText().toString().isEmpty()) {
            dotFour.setText("");
            dotFour.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_shape));
        } else if (!dotThree.getText().toString().isEmpty()) {
            dotThree.setText("");
            dotThree.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_shape));
        } else if (!dotTwo.getText().toString().isEmpty()) {
            dotTwo.setText("");
            dotTwo.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_shape));
        } else if (!dotOne.getText().toString().isEmpty()) {
            dotOne.setText("");
            dotOne.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_shape));
        }
    }

    private void setPinDots(String s) {
        if (dotOne.getText().toString().isEmpty()) {
            dotOne.setText(s);
            dotOne.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_shape_selected));
        } else if (dotTwo.getText().toString().isEmpty()) {
            dotTwo.setText(s);
            dotTwo.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_shape_selected));
        } else if (dotThree.getText().toString().isEmpty()) {
            dotThree.setText(s);
            dotThree.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_shape_selected));
        } else if (dotFour.getText().toString().isEmpty()) {
            dotFour.setText(s);
            dotFour.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button_shape_selected));
        }
    }

    @OnClick(R.id.submitpin)
    public void onViewClicked() {
        String lockValue = dotOne.getText().toString()
                + (dotTwo.getText().toString()) + (dotThree.getText().toString()) +
                (dotFour.getText().toString());
        if (lockValue.length() == 4) {
            HomeFragment homeFragment = new HomeFragment();
            getActivity().getSupportFragmentManager().beginTransaction().
                    add(R.id.frameContainer, homeFragment).commit();
        } else {
            Toast.makeText(getContext(),getResources().getString(R.string.pin_error_msg), Toast.LENGTH_SHORT).show();
        }
    }
}
