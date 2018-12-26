package systems;

import Rendering.Materials.MaterialPresets;
import Rendering.Materials.RenderMode;
import Rendering.shaders.GouraudShader;
import components.Component;
import components.MonkeyHead;
import components.RenderableMesh;
import components.TransformComponent;
import core.EntityFactory;
import core.coreSystems.GameSystem;
import core.coreSystems.Time;
import util.Mathf.Mathf;
import util.Mathf.Mathf3D.Quaternion;
import util.Mathf.Mathf3D.Transform;
import util.Mathf.Mathf3D.Vector3D;

import java.util.Arrays;

public class MonkeyHeadSystem extends GameSystem {
    public final static String facePath = "res\\models/male_head_obj.obj";
    public final static String monkeyHeadPath = "res\\models/monkeyNormalisedTriangulated.obj";

    public final static String texturePath = "res/Wall.png";
//    public final static String texturePath = "res/TextureSizePerformanceTesting64x64.png";

//    public final static String texturePath = "C:\\Users\\domin\\OneDrive\\Documents\\projects\\ecsTestGame\\res/Grass.png";
//    public final static String texturePath = "res/Magic_Projectile.png";
//    public final static String texturePath = "C:\\Users\\domin\\OneDrive\\Documents\\projects\\ecsTestGame\\res/Clock.png";


    public MonkeyHeadSystem() {
        super(Arrays.asList(MonkeyHead.class, TransformComponent.class));
        createMonkeyHead(new Vector3D(10f, 0f, -70f));
        createMonkeyHead(new Vector3D(-10f, 0f, -70f));
        createMonkeyHead(new Vector3D(25f, 0f, -70f));

        createMonkeyHead(new Vector3D(10f, -20f, -70f));
        createMonkeyHead(new Vector3D(-10f, -20f, -70f));
        createMonkeyHead(new Vector3D(25f, -20f, -70f));
    }

    @Override
    public void update() {
        for (int entityID : entityGrabber.getEntityIDsOfInterest()) {
            int[] componentIndexs = entityGrabber.getComponentIndexsOfInterest().get(entityID);
            Component[] components = entityGrabber.getComponentsOnEntity(entityID, componentIndexs);
            TransformComponent transform = (TransformComponent) components[1];
            doMonkeyStuff(transform.transform);
        }
    }

    private void doMonkeyStuff(Transform transform) {
        Quaternion rot = new Quaternion(Mathf.toRadians(2f) * Time.getDeltaTime(), Vector3D.UP);
//        transform.rotate(rot);
    }

    public void createMonkeyHead(Vector3D offset) {
        Transform transform = new Transform();
//        transform.rotate((float) Math.toRadians(200), Vector3D.UP);
        transform.setScale(0.5f, 0.5f, 0.5f);
        transform.translate(offset);
        RenderableMesh renderableMesh = new RenderableMesh(null, null);
        renderableMesh.renderMode = RenderMode.MESH;
        renderableMesh.material = MaterialPresets.material1;
        renderableMesh.material.setSpecular(true);
        renderableMesh.material.setShader(new GouraudShader());
        EntityFactory.createEntity(facePath, texturePath, renderableMesh, Arrays.asList(renderableMesh,
                new TransformComponent(transform), new MonkeyHead()));
    }

}
