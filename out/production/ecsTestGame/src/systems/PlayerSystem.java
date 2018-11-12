package systems;

import components.Camera;
import components.Component;
import components.InputComponent;
import components.Transform;
import util.geometry.Matrix4x4;
import util.geometry.RotationMatrixHolder;
import util.geometry.TransformUtil;
import util.geometry.Vector3D;

import java.awt.event.KeyEvent;
import java.util.Arrays;

public class PlayerSystem extends GameSystem {

    public PlayerSystem(boolean addToUpdateList) {
        super(Arrays.asList(Camera.class, Transform.class, InputComponent.class), addToUpdateList);
    }

    @Override
    public void update(float deltaTime) {
        for (int entityID : entityListner.getEntityIDsOfInterest()) {
            int[] componentIndexs = entityListner.getComponentIndexsOfInterest().get(entityID);
            Component[] components = entityListner.getComponentsOnEntity(entityID, componentIndexs);
            Camera camera = (Camera) components[0];
            Transform transform = (Transform) components[1];
            InputComponent inputComponent = (InputComponent) components[2];

            updateTransforms(inputComponent, transform, deltaTime);
            updateCamera(camera, transform);

        }

    }


    private void updateTransforms(InputComponent inputComponent, Transform transform, float deltaTime) {
        Vector3D deltaVector = new Vector3D(0.0f, 0.0f, 0.0f);
        float tx = 0.0f, ty = 0.0f, tz = 0.0f;
        transform.fYaw = 0;

        // rotate around Y
        Matrix4x4 fYawM = Matrix4x4.newIdentityMatrix();
        if (inputComponent.keysPressed[KeyEvent.VK_E]) {
            transform.fYaw += 0.003;
            fYawM = fYawM.compose(Matrix4x4.newYRotation(transform.fYaw));
        }
        if (inputComponent.keysPressed[KeyEvent.VK_Q]) {
            transform.fYaw -= 0.003;
            fYawM = fYawM.compose(Matrix4x4.newYRotation(transform.fYaw));
        }

        RotationMatrixHolder newRotation = new RotationMatrixHolder(transform.rotHolder.zRot, transform.rotHolder.xRot, fYawM);
        Matrix4x4 rot = newRotation.compose();
        TransformUtil.updateDirections(transform, rot);
        transform.rotHolder.rotate(newRotation);

        // up/down
        if (inputComponent.keysPressed[KeyEvent.VK_W]) {

            deltaVector = deltaVector.plus(transform.upDir.scale(0.11f));
        }
        if (inputComponent.keysPressed[KeyEvent.VK_S]) {
            deltaVector = deltaVector.minus(transform.upDir.scale(0.11f));
        }


        // Strafe
        if (inputComponent.keysPressed[KeyEvent.VK_A]) {
            deltaVector = deltaVector.plus(transform.rightDir.scale(0.11f));

        }
        if (inputComponent.keysPressed[KeyEvent.VK_D]) {
            deltaVector = deltaVector.minus(transform.rightDir.scale(0.11f));
        }

        // Forward/back
        if (inputComponent.keysPressed[KeyEvent.VK_UP]) {
            deltaVector = deltaVector.minus(transform.lookDir.scale(0.11f));
        }
        if (inputComponent.keysPressed[KeyEvent.VK_DOWN]) {
            deltaVector = deltaVector.plus(transform.lookDir.scale(0.11f));
        }


        transform.translation = Matrix4x4.newTranslation(deltaVector.x, deltaVector.y, deltaVector.z);
        transform.position = transform.translation.multiply4x4(transform.position, 1);

    }

    public void updateCamera(Camera camera, Transform transform) {
        camera.lookDir = transform.lookDir;

        /*Vector3D projection = transform.lookDir.scale(transform.upDir.dotProduct(transform.lookDir));
        camera.upDir = transform.upDir.minus(projection).unit();*/

        camera.upDir = transform.upDir;

        camera.rightDir = transform.rightDir;

        camera.position = transform.position;
    }
}
