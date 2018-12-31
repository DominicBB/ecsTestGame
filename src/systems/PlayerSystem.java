package systems;

import Rendering.renderUtil.RenderState;
import components.Component;
import components.PlayerComponent;
import components.TransformComponent;
import core.coreSystems.EntitySystem;
import core.coreSystems.GameSystem;
import core.coreSystems.Time;
import util.Mathf.Mathf3D.Quaternion;
import util.Mathf.Mathf3D.Vector3D;

import java.util.Arrays;

public class PlayerSystem extends GameSystem {
    private PlayerMovement playerMovement;
    private Vector3D axis = Vector3D.newUp();

    public PlayerSystem() {
        super(Arrays.asList(PlayerComponent.class, TransformComponent.class));
        playerMovement = new PlayerMovement();
        createPlayer();
    }

    @Override
    public void update() {
        for (int entityID : entityGrabber.getEntityIDsOfInterest()) {
            Component[] components = entityGrabber.getRelevantComponents(entityID);
            TransformComponent transform = (TransformComponent) components[1];

            playerMovement.updateTransforms(transform.transform);
        }

        RenderState.lightingState.lightDir = Quaternion.angleAxis(
                1f * Time.getDeltaTime(), axis).rotate(RenderState.lightingState.lightDir);
//        axis = Quaternion.angleAxis(.5f * Time.getDeltaTime(), Vector3D.FORWARD).rotate(axis);
//        RenderState.lightingState.lightDir.normalise();
    }

    public void createPlayer() {
        EntitySystem.getInstance().createEntity(Arrays.asList(new PlayerComponent(), new TransformComponent()));
    }

}
