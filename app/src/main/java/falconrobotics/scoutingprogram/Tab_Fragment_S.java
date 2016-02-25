package falconrobotics.scoutingprogram;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Francisco Martinez on 2/7/2016.
 */
public class Tab_Fragment_S extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 4;
    private static View rootView;
    private static EditText teamNumInput;
    private static int matchNum;
    private static Spinner teams;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView =  inflater.inflate(R.layout.tab_layout_g,null);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs_g);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager_g);
        teams = (Spinner) rootView.findViewById(R.id.pre_match_spinner_teams);

        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });


        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(rootView.getContext());
        View promptsView = li.inflate(R.layout.prompt_layout_s, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                rootView.getContext());
        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);
        teamNumInput = (EditText) promptsView
                .findViewById(R.id.pre_match_team_number);
        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("SUBMIT",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                matchNum = Integer.parseInt(teamNumInput.getText().toString());
                            }
                        })
                .setNegativeButton("CANCEL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();


        return rootView;
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */
        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new Fragment_S_Auto();
                case 1 : return new Fragment_S_Tele_Off();
                case 2 : return new Fragment_S_Tele_Def();
                case 3 : return new Fragment_S_Post();
            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0:
                    return "Auto";
                case 1 :
                    return "Tele - Offensive";
                case 2:
                    return "Tele - Defensive";
                case 3 :
                    return "Post-Match";
            }
            return null;
        }
    }
}
