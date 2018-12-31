package application;

import Rendering.renderUtil.RenderState;
import core.coreSystems.ECSSystem;
import systems.MonkeyHeadSystem;
import systems.PlayerSystem;

public class TestApp extends ECSSystem {
    @Override
    public void update() {

    }

    public TestApp() {
        super();
        createSystems();
        start();
        RenderState.lightingState.ambientColor.add(0.5f, .5f, .5f, 0f);
    }

    private void createSystems() {
        new PlayerSystem();
        new MonkeyHeadSystem();
    }


}
