package hiveswarm.hiveswarm;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FragmentPageAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;

    public FragmentPageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new HiveFragment();
            case 1:
                return new GraphQuestion1();
            case 2:
                return new PreferencesView();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
