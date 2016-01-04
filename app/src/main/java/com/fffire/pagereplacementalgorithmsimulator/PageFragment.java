package com.fffire.pagereplacementalgorithmsimulator;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PageFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    private String ropt;
    private String rfifo;
    private String rlru;
    private String rclock;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);

        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);

        ropt = ((ApplicationHelper)getActivity().getApplicationContext()).getOpt();
        rfifo = ((ApplicationHelper)getActivity().getApplicationContext()).getFifo();
        rlru = ((ApplicationHelper)getActivity().getApplicationContext()).getLru();
        rclock = ((ApplicationHelper)getActivity().getApplicationContext()).getClock();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        //TextView tvs = (TextView)getActivity().findViewById(R.id.textView_show);
        //tvs.setText("Fragment #");

        //ropt = (ApplicationHelper)(getActivity().getApplicationContext()).

        TextView textView = (TextView) view;
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());

        if(mPage==1){
            textView.setText("Fragment #" + mPage + "\n"+ ropt);
        }else if(mPage==2){
            textView.setText("Fragment #" + mPage + "\n"+ rfifo);
        }else if(mPage==3){
            textView.setText("Fragment #" + mPage + "\n"+ rlru);
        }else if(mPage==4){
            textView.setText("Fragment #" + mPage + "\n"+ rclock);
        }

        //textView.setText("Fragment #" + mPage + ropt);

        return view;
    }
}