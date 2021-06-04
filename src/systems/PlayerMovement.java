package systems;

import rendering.renderUtil.RenderState;
import components.Camera;
import core.coreSystems.InputSystem;
import core.coreSystems.Time;
import util.mathf.Mathf;
import util.mathf.Mathf3D.Matrix4x4;
import util.mathf.Mathf3D.Transform;
import util.mathf.Mathf3D.Vec4f;

import java.awt.event.KeyEvent;

public class PlayerMovement {
    private float runSpeed = 50f;
    private float rotSpeed = .7f/*Mathf.PI/2f*/;

    public void updateTransforms(Transform transform) {
        // rotate around local Y
        if (InputSystem.keysPressed[KeyEvent.VK_E]) {
            transform.rotate(rotSpeed * Time.getDeltaTime(), transform.getUpDir());
        }
        if (InputSystem.keysPressed[KeyEvent.VK_Q]) {
            transform.rotate(-rotSpeed * Time.getDeltaTime(), transform.getUpDir());
        }


        // up/down
        if (InputSystem.keysPressed[KeyEvent.VK_W]) {
            transform.translate(transform.getUpDir().mul(runSpeed * Time.getDeltaTime()));
        }
        if (InputSystem.keysPressed[KeyEvent.VK_S]) {
            transform.translate(transform.getUpDir().mul(-runSpeed * Time.getDeltaTime()));
        }


        // Strafe
        if (InputSystem.keysPressed[KeyEvent.VK_A]) {
            transform.translate(transform.getRightDir().mul(runSpeed * Time.getDeltaTime()));

        }
        if (InputSystem.keysPressed[KeyEvent.VK_D]) {
            transform.translate(transform.getRightDir().mul(-runSpeed * Time.getDeltaTime()));
        }

        // Forward/back
        if (InputSystem.keysPressed[KeyEvent.VK_UP]) {
            transform.translate(transform.getForwardDir().mul(runSpeed * Time.getDeltaTime()));
        }
        if (InputSystem.keysPressed[KeyEvent.VK_DOWN]) {
            transform.translate(transform.getForwardDir().mul(-runSpeed * Time.getDeltaTime()));
        }

        updateCamera(transform);
    }

    public void updateCamera(Transform transform) {
        RenderState.camera.transform.setPosition(transform.getPosition());
        RenderState.camera.transform.setRotation(transform.getRotation());
        /*Vector3D projection = transform.lookDir.scale(transform.upDir.dotProduct(transform.lookDir));
        RenderState.camera.upDir = transform.upDir.minus(projection).unit();

        RenderState.camera.upDir = transform.upDir;

        RenderState.camera.rightDir = transform.rightDir;

        RenderState.camera.position = transform.position;*/
    }
}
