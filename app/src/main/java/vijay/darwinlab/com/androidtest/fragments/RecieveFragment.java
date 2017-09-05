package vijay.darwinlab.com.androidtest.fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class RecieveFragment extends Fragment {

    @BindView(R.id.adressText)
    TextView adressText;
    @BindView(R.id.btn_copy)
    ImageView btnCopy;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recieve, container, false);
        setUpDrawerAttributes();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void setUpDrawerAttributes() {
        ((MainActivity) getActivity()).
                changeDrawerAttributes(false, ContextCompat.getDrawable(getContext(),
                        R.drawable.backicon), getResources().getString(R.string.title_recieve));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_copy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_copy:
                if (!adressText.getText().toString().isEmpty()) {
                    ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("Text Label", adressText.getText().toString());
                    clipboard.setPrimaryClip(clipData);
                    Toast.makeText(getContext(), "ETHEREUM Code Copied !!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "No Code to copy !!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpDrawerAttributes();
    }
}
