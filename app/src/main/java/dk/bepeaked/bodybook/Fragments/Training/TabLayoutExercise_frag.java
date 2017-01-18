package dk.bepeaked.bodybook.Fragments.Training;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dk.bepeaked.bodybook.Backend.Controllers.WorkoutController;
import dk.bepeaked.bodybook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabLayoutExercise_frag extends Fragment {

    Bundle bundleArgs = new Bundle();
    WorkoutController wc;

    public TabLayoutExercise_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tablayout_exercise, container, false);
        wc = new WorkoutController();

        getActivity().setTitle(wc.getExercise(getArguments().getInt("chosenExerciseID", 9999)).getName());

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.exercise));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.description));
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager2);


        viewPager.setAdapter(new PagerAdapter
                (getFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    bundleArgs.putInt("chosenExerciseID", getArguments().getInt("chosenExerciseID"));
                    ChosenExercise_frag frag1 = new ChosenExercise_frag();
                    frag1.setArguments(bundleArgs);
                    return frag1;
                case 1:
                    bundleArgs.putInt("chosenExerciseID", getArguments().getInt("chosenExerciseID"));
                    ExerciseHelp_frag frag2 = new ExerciseHelp_frag();
                    frag2.setArguments(bundleArgs);
                    return frag2;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }
}
