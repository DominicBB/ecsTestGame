package systems;

import components.Component;
import components.MonkeyHead;
import components.RenderableMesh;
import components.Transform;
import util.geometry.Matrix4x4;
import util.geometry.TransformUtil;

import java.util.Arrays;
import java.util.List;

public class MonkeyHeadSystem extends GameSystem{
    public final static String facePath= "C:\\Users\\domin\\Desktop\\3dModels\\downloaded/male_head_obj.obj";
    public final static String monkeyHeadPath = "C:\\Users\\domin\\Desktop\\3dModels\\blender/monkeyNormalisedTriangulated.obj";

//    public final static String texture = "C:\\Users\\domin\\OneDrive\\Documents\\projects\\ecsTestGame\\res/Wall.png";
//    public final static String texture = "C:\\Users\\domin\\OneDrive\\Documents\\projects\\ecsTestGame\\res/Grass.png";
    public final static String texture = "C:\\Users\\domin\\OneDrive\\Documents\\projects\\ecsTestGame\\res/Magic_Projectile.png";
//    public final static String texture = "C:\\Users\\domin\\OneDrive\\Documents\\projects\\ecsTestGame\\res/Clock.png";


    public MonkeyHeadSystem(boolean addToUpdateList) {
        super(Arrays.asList(MonkeyHead.class, Transform.class), addToUpdateList);
    }

    @Override
    public void update(float deltaTime) {
        for(int entityID: entityListner.getEntityIDsOfInterest()){
            int[] componentIndexs = entityListner.getComponentIndexsOfInterest().get(entityID);
            Component[] components = entityListner.getComponentsOnEntity(entityID, componentIndexs);
            Transform transform = (Transform) components[1];

            doMonkeyStuff(transform, deltaTime);
        }
    }

    private void doMonkeyStuff(Transform transform, float deltaTime) {
//        transform.rotHolder.rotate(transform.rotHolder.zRot,transform.rotHolder.zRot,Matrix4x4.newYRotation(0.01f));
//        scale = (float)(30f * Math.sin(deltaTime));
//        transform.scale = Matrix4x4.newScale(scale ,scale,scale);
//        TransformUtil.updateDirections(transform, transform.rotHolder.compose());
    }
}
