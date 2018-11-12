package application;

import components.*;
import core.Window;
import systems.*;
import util.Bitmap;
import util.Mesh;
import util.ObjFileReader;
import util.geometry.Matrix4x4;
import util.geometry.TransformUtil;

import java.io.IOException;
import java.util.Arrays;

public class TestApp extends ECSSystem {

    public TestApp() {
        super();
        createCamera();
        createMonkeyHead();
        createSystems();
        start();
    }

    private void createSystems() {
        new PlayerSystem(true);
        new MonkeyHeadSystem(true);
        new SceneSystem(true);
        renderSystem = new RenderSystem(false);
    }

    private void createCamera() {
        Camera camera = new Camera();
        Scene scene = new Scene(Window.defaultWidth, Window.defaultHeight);

        entitySystem.createEntity(Arrays.asList(camera, new Transform(), new InputComponent()));
        entitySystem.createEntity(Arrays.asList(scene));
    }

    private void createMonkeyHead() {
        Mesh mesh = ObjFileReader.loadOBJFromPath(MonkeyHeadSystem.facePath);
        Bitmap texture = null;
        try {
            texture = new Bitmap(MonkeyHeadSystem.texture);
        } catch (IOException e) {
            e.printStackTrace();
        }
        RenderableMesh renderableMesh = new RenderableMesh(mesh, texture);

        Transform transform = new Transform();
        transform.minimumBoundingBox = renderableMesh.mesh.minimumBoundingBox;
        TransformUtil.rotate(transform, 0f, (float) Math.toRadians(200), 0f);
        TransformUtil.scale(transform, Matrix4x4.newScale(0.5f, 0.5f, 0.5f));
        TransformUtil.translate(transform, Matrix4x4.newTranslation(0f, -15f, 40f));

        entitySystem.createEntity(Arrays.asList(renderableMesh, transform, new MonkeyHead()));
    }


    @Override
    public void update(float deltaTime) {

    }


}
