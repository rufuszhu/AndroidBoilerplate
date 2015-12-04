package ca.co.rufus.androidboilerplate;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import ca.co.rufus.androidboilerplate.data.model.Ribot;
import ca.co.rufus.androidboilerplate.test.common.TestDataFactory;
import ca.co.rufus.androidboilerplate.test.common.rules.TestComponentRule;
import ca.co.rufus.androidboilerplate.ui.main.MainMvpView;
import ca.co.rufus.androidboilerplate.ui.main.MainPresenter;
import ca.co.rufus.androidboilerplate.util.DefaultConfig;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK)
public class MainPresenterTest {

    private MainPresenter mMainPresenter;
    private MainMvpView mMockMainMvpView;

    // We mock the DataManager because there is not need to test the dataManager again
    // from the presenters because there is already a DataManagerTest class.
    @Rule
    public final TestComponentRule component =
            new TestComponentRule((BoilerplateApplication) RuntimeEnvironment.application, true);

    @Before
    public void setUp() {
        mMockMainMvpView = mock(MainMvpView.class);
        mMainPresenter = new MainPresenter(RuntimeEnvironment.application);
        mMainPresenter.attachView(mMockMainMvpView);
    }

    @After
    public void detachView() {
        mMainPresenter.detachView();
    }

    @Test
    public void loadRibotsReturnsRibots() {
        List<Ribot> ribots = TestDataFactory.makeListRibots(10);
        doReturn(Observable.just(ribots))
                .when(component.getDataManager())
                .getRibots();

        mMainPresenter.loadRibots();
        verify(mMockMainMvpView).showRibots(ribots);
        verify(mMockMainMvpView, never()).showRibotsEmpty();
        verify(mMockMainMvpView, never()).showError(anyString());
    }

    @Test
    public void loadRibotsReturnsEmptyList() {
        doReturn(Observable.just(Collections.emptyList()))
                .when(component.getDataManager())
                .getRibots();

        mMainPresenter.loadRibots();
        verify(mMockMainMvpView).showRibotsEmpty();
        verify(mMockMainMvpView, never()).showRibots(anyListOf(Ribot.class));
        verify(mMockMainMvpView, never()).showError(anyString());
    }

    @Test
    public void loadRibotsFails() {
        doReturn(Observable.error(new RuntimeException()))
                .when(component.getDataManager())
                .getRibots();

        mMainPresenter.loadRibots();
        verify(mMockMainMvpView).showError(anyString());
        verify(mMockMainMvpView, never()).showRibotsEmpty();
        verify(mMockMainMvpView, never()).showRibots(anyListOf(Ribot.class));
    }
}
