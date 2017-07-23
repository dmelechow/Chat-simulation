package dmelechow.testtask.injection;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import dmelechow.testtask.view.adapter.ChatAdapter;

/**
 * Created by a1 on 7/23/17.
 */

@Module
public class ChatAdapterModule {

    @Provides
    ChatAdapter provideAdapter(Context context) {
        return new ChatAdapter(context);
    }

    @Provides
    LinearLayoutManager provideManager(Context context) {
        return new LinearLayoutManager(context);
    }
}
