package tech.mbsoft.simplemvvm.di;

import dagger.Component;
import tech.mbsoft.simplemvvm.MainActivity;

@Component
public interface MainComponent {
    void inject(MainActivity mainActivity);
}

