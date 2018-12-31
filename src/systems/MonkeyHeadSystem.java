package systems;

import Rendering.Materials.Material;
import Rendering.Materials.MaterialPresets;
import Rendering.renderUtil.Bitmaps.BitmapABGR;
import Rendering.renderUtil.Bitmaps.Texture;
import Rendering.renderUtil.Meshes.IndexedMesh;
import Rendering.renderUtil.Meshes.RenderableMeshFactory;
import Rendering.shaders.Shaders;
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

import java.io.IOException;
import java.util.Arrays;

public class MonkeyHeadSystem extends GameSystem {
    public final static String facePath = "res\\models/male_head_obj.obj";
    public final static String monkeyHeadPath = "res\\models/monkeyNormalisedTriangulated.obj";

    public final static String texturePath = "res/Wall.png";
//    public final static String texturePath = "res/TextureSizePerformanceTesting64x64.png";

//    public final static String texturePath = "res/Grass.png";
//    public final static String texturePath = "res/Magic_Projectile.png";
//    public final static String texturePath = "res/Clock.png";


    public MonkeyHeadSystem() {
        super(Arrays.asList(MonkeyHead.class, TransformComponent.class));
        IndexedMesh indexedMesh = createIndexedMesh(facePath);
        IndexedMesh indexedMeshMonkey = createIndexedMesh(monkeyHeadPath);

        Material m1 = MaterialPresets.MATERIAL_1;
        Material m2 = MaterialPresets.MATERIAL_2;
        Material m3 = MaterialPresets.MATERIAL_3;

        m1.setShader(Shaders.GOURAUD_SHADER);
        m2.setShader(Shaders.FLAT_SHADER);
        m3.setShader(Shaders.FLAT_SHADER);

        m3.setColor(new Vector3D(167f, 90f, 111f));
        try {
            Texture texture = new Texture(new BitmapABGR(texturePath));
            m1.setTexture(texture);
            m2.setTexture(texture);
        } catch (IOException e) {
            e.printStackTrace();
        }
        createMonkeyHead(new Vector3D(10f, 0f, -70f), indexedMesh, m1);
        createMonkeyHead(new Vector3D(-10f, 0f, -70f), indexedMesh, m2);
        createMonkeyHead(new Vector3D(25f, 0f, -70f), indexedMesh, m1);
        createActualMonkeyHead(new Vector3D(0f, 0f, -10f), indexedMeshMonkey, m3);

        createMonkeyHead(new Vector3D(10f, -20f, -70f), indexedMesh, m2);
        createMonkeyHead(new Vector3D(-10f, -20f, -70f), indexedMesh, m1);
        createMonkeyHead(new Vector3D(25f, -20f, -70f), indexedMesh, m2);

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

    private IndexedMesh createIndexedMesh(String path) {
        return RenderableMeshFactory.loadMesh(path);
    }


    public void createMonkeyHead(Vector3D offset, IndexedMesh indexedMesh, Material material) {
        Transform transform = new Transform();
//        transform.rotate((float) Math.toRadians(200), Vector3D.UP);
        transform.setScale(0.5f, 0.5f, 0.5f);
        transform.translate(offset);
        RenderableMesh renderableMesh = new RenderableMesh(indexedMesh, material);
        EntityFactory.createEntity(Arrays.asList(renderableMesh,
                new TransformComponent(transform), new MonkeyHead()));
    }

    public void createActualMonkeyHead(Vector3D offset, IndexedMesh indexedMesh, Material material) {
        Transform transform = new Transform();
//        transform.rotate((float) Math.toRadians(200), Vector3D.UP);
        transform.translate(offset);
        RenderableMesh renderableMesh = new RenderableMesh(indexedMesh, material);
        EntityFactory.createEntity(Arrays.asList(renderableMesh,
                new TransformComponent(transform), new MonkeyHead()));
    }

}
