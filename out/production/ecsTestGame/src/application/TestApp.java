package application;

import Rendering.renderingSystems.RenderSystem;
import components.*;
import core.Window;
import core.coreSystems.ECSSystem;
import systems.*;


import java.io.IOException;
import java.util.Arrays;

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
