package hiveswarm.hiveswarm;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FragmentPageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public FragmentPageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                HiveFragment orchardActivity = new HiveFragment();
                return orchardActivity;

            case 1:
                GraphFragment GraphTab = new GraphFragment();
                return GraphTab;
            case 2:
                PreferencesView preferencesView = new PreferencesView();
                return preferencesView;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
