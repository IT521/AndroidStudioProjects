package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.kpgsoftworks.apps.moneycounter.R;
import com.kpgsoftworks.apps.moneycounter.Util;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChecksFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChecksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChecksFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String TAG = "mc.ChecksFragment";
    private static Bundle mSavedInstanceState;
    private LinearLayout checksLayout;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ChecksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChecksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChecksFragment newInstance(String param1, String param2) {
        ChecksFragment fragment = new ChecksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Util.logDebug(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Util.logDebug(TAG, "onCreateView");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_checks, container, false);
        checksLayout = (LinearLayout) view.findViewById(R.id.checks_layout);
        // Restore values
        mSavedInstanceState = Util.getSavedInstanceState();
        String checks = Util.getCHECKS().toLowerCase();
        for (String key : mSavedInstanceState.keySet()) {
            if (key.contains(checks)) {
                Object entry = mSavedInstanceState.get(key);
                if (entry != null) {
                    String value = entry.toString();
                    value = value.equals("") ? "0" : value;
                    Util.logDebug(TAG, "key: " + key + " value: " + value);
                    if (!value.equals("0")) {
                        EditText editText = (EditText) checksLayout.findViewWithTag(key.replace(checks, ""));
                        editText.setText(value);
                        RelativeLayout rl = (RelativeLayout) editText.getParent();
                        rl.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        Util.logDebug(TAG, "onAttach");
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        Util.logDebug(TAG, "onDetach");
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPause() {
        Util.logDebug(TAG, "onPause");
        Util.saveValues(checksLayout, Util.getCHECKS());
        super.onPause();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        Util.logDebug(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);

//        Util.saveValues(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedState) {
        Util.logDebug(TAG, "onActivityCreated");
        super.onActivityCreated(savedState);
//        Util.restoreNotes(savedState);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void setData(Bundle data) {
        this.mSavedInstanceState = data;
    }

    public Bundle getData() {
        return mSavedInstanceState;
    }
}
