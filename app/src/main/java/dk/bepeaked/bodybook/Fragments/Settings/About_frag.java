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
public class About_frag extends Fragment {


    public About_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_about_frag, container, false);

        TextView bepeakedHomepage = (TextView) view.findViewById(R.id.linkView);
        bepeakedHomepage.setText(
                Html.fromHtml(
                        getString(R.string.link_to_bepeaked)));
        bepeakedHomepage.setMovementMethod(LinkMovementMethod.getInstance());

        TextView everkineticHomepage = (TextView) view.findViewById(R.id.linkView2);
        everkineticHomepage.setText(
                Html.fromHtml(
                        getString(R.string.link_to_everkinetic)));
        everkineticHomepage.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }

}
