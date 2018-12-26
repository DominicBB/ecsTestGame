package application;

import core.coreSystems.ECSSystem;
import systems.*;

public class TestApp extends ECSSystem {
    @Override
    public void update() {

    }

    public TestApp() {
        super();
        createSystems();
        start();
    }

    private void createSystems() {
        new PlayerSystem();
        new MonkeyHeadSystem();
    }



}
