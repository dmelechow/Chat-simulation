package dmelechow.testtask.injection;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import dmelechow.testtask.interactor.ChatGeneratorInteractor;

/**
 * Created by a1 on 7/24/17.
 */

@Module
public class ChatGeneratorInteractorModule {

    private Context context;

    public ChatGeneratorInteractorModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return context;
    }

    @Provides
    ChatGeneratorInteractor provideInteractor(Context context) {
        return new ChatGeneratorInteractor(context);
    }
}
