package dk.bepeaked.bodybook.Fragments.Settings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeasureUnit_frag extends Fragment {


    public MeasureUnit_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_about_frag, container, false);

        TextView link = (TextView) view.findViewById(R.id.linkView);
        link.setText(
                Html.fromHtml(
                        "<a href=\"http://bepeaked.com\">Link til hjemmeside</a> "));
        link.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }

}
