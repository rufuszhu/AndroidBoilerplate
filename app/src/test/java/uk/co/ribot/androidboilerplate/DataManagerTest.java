package ca.co.rufus.androidboilerplate;


import android.database.Cursor;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;
import ca.co.rufus.androidboilerplate.data.local.Db;
import ca.co.rufus.androidboilerplate.data.model.Ribot;
import ca.co.rufus.androidboilerplate.test.common.TestDataFactory;
import ca.co.rufus.androidboilerplate.test.common.TestDataManager;
import ca.co.rufus.androidboilerplate.test.common.rules.ClearDataRule;
import ca.co.rufus.androidboilerplate.test.common.rules.TestComponentRule;
import ca.co.rufus.androidboilerplate.util.DefaultConfig;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK)
public class DataManagerTest {

    private TestDataManager mDataManager;

    @Rule
    public final TestComponentRule component =
            new TestComponentRule(RuntimeEnvironment.application);
    @Rule
    public final ClearDataRule clearDataRule = new ClearDataRule(component);

    @Before
    public void setUp() {
        mDataManager = component.getDataManager();
    }

    @Test
    public void syncRibots() {
        List<Ribot> ribots = Arrays.asList(TestDataFactory.makeRibot(),
                TestDataFactory.makeRibot());
        when(component.getMockRibotsService().getRibots())
                .thenReturn(Observable.just(ribots));

        TestSubscriber<Ribot> result = new TestSubscriber<>();
        mDataManager.syncRibots().subscribe(result);
        result.assertNoErrors();
        result.assertReceivedOnNext(ribots);

        Cursor cursor = component.getDatabaseHelper().getBriteDb()
                .query("SELECT * FROM " + Db.RibotProfileTable.TABLE_NAME);
        assertEquals(2, cursor.getCount());
        cursor.close();
    }

}
