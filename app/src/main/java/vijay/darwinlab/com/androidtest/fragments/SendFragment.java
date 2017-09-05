package vijay.darwinlab.com.androidtest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vijay.darwinlab.com.androidtest.R;
import vijay.darwinlab.com.androidtest.main.MainActivity;

/**
 * Created by Vijay on 05-09-2017.
 */

public class SendFragment extends Fragment {

    @BindView(R.id.relative_amount)
    RelativeLayout relativeAmount;
    @BindView(R.id.amount_textview)
    TextView amountTextview;
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
    private String textInAmountView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_send, container, false);

        setUpDrawerAttributes();
        unbinder = ButterKnife.bind(this, view);
        amountTextview.setText("0.0");
        return view;
    }

    private void setUpDrawerAttributes() {
        ((MainActivity) getActivity()).
                changeDrawerAttributes(false, ContextCompat.getDrawable(getContext(),
                        R.drawable.backicon), getResources().getString(R.string.send));
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpDrawerAttributes();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.amount_textview, R.id.btn_one, R.id.btn_two, R.id.btn_three, R.id.btn_four, R.id.btn_five, R.id.btn_six, R.id.btn_seven, R.id.btn_eight, R.id.btn_nine, R.id.btn_backspace, R.id.btn_zero, R.id.btn_decimal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.amount_textview:
                break;
            case R.id.btn_one:
                setTextInAmountView(btnOne.getText().toString());
                break;
            case R.id.btn_two:
                setTextInAmountView(btnTwo.getText().toString());
                break;
            case R.id.btn_three:
                setTextInAmountView(btnThree.getText().toString());
                break;
            case R.id.btn_four:
                setTextInAmountView(btnFour.getText().toString());
                break;
            case R.id.btn_five:
                setTextInAmountView(btnFive.getText().toString());

                break;
            case R.id.btn_six:
                setTextInAmountView(btnSix.getText().toString());

                break;
            case R.id.btn_seven:
                setTextInAmountView(btnSeven.getText().toString());

                break;
            case R.id.btn_eight:
                setTextInAmountView(btnEight.getText().toString());

                break;
            case R.id.btn_nine:
                setTextInAmountView(btnNine.getText().toString());

                break;
            case R.id.btn_backspace:
                if(!amountTextview.getText().toString().isEmpty())
                amountTextview.setText(removeTextInAmountView(amountTextview.getText().toString()));
                break;
            case R.id.btn_zero:
                setTextInAmountView(btnZero.getText().toString());

                break;
            case R.id.btn_decimal:
                setTextInAmountView(btnDecimal.getText().toString());
                break;
        }
    }

    private String removeTextInAmountView(String amountString) {
        return amountString.substring(0,amountString.length()-1);
    }

    public void setTextInAmountView(String textInAmountView) {
        if (amountTextview.getText().toString().equalsIgnoreCase("0.0")) {
            amountTextview.setText(textInAmountView);
        } else {
            amountTextview.append(textInAmountView);
        }
    }
}
