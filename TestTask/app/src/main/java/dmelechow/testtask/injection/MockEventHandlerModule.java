package dmelechow.testtask.injection;

import dagger.Module;
import dagger.Provides;
import dmelechow.testtask.MockEventHandler;
import dmelechow.testtask.interactor.ChatGeneratorInteractor;

/**
 * Created by a1 on 7/24/17.
 */

@Module
public class MockEventHandlerModule {
    @Provides
    MockEventHandler provideHandler() {
        return new MockEventHandler();
    }
}
