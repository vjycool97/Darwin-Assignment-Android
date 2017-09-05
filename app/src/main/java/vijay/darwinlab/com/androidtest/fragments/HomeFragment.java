package vijay.darwinlab.com.androidtest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class HomeFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.send_btn)
    TextView sendBtn;
    @BindView(R.id.recve_btn)
    TextView recveBtn;
    @BindView(R.id.transactbtn)
    TextView transactbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpDrawerAttributes();
        return view;
    }

    public void setUpDrawerAttributes() {
        ((MainActivity) getActivity()).
                changeDrawerAttributes(true, ContextCompat.getDrawable(getContext(),
                        R.drawable.menuicon), getResources().getString(R.string.menuTitle));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpDrawerAttributes();
    }


    @OnClick({R.id.send_btn, R.id.recve_btn, R.id.transactbtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send_btn:
                SendFragment sendFragment =new SendFragment();
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.frameContainer,sendFragment).addToBackStack("SendFragment").commit();

                break;
            case R.id.recve_btn:
                RecieveFragment recieveFragment = new RecieveFragment();
                getActivity().getSupportFragmentManager().
                        beginTransaction().replace(R.id.frameContainer,recieveFragment).addToBackStack("RecieveFragment").commit();
                break;
            case R.id.transactbtn:
                Toast.makeText(getContext(), "Please try another options !!!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
