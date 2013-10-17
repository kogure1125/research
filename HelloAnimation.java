
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.input.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.*;

 
/** Sample 7 - how to load an OgreXML model and play an animation,
 * using channels, a controller, and an AnimEventListener. */
public class HelloAnimation extends SimpleApplication
  implements AnimEventListener{
  private AnimChannel channel;
  private AnimControl control;
  Node player;
  public String x,y;
  
  public static void main(String[] args){}
  @Override
  public void simpleInitApp() {
	  initKeys();
    viewPort.setBackgroundColor(ColorRGBA.LightGray);
    DirectionalLight dl = new DirectionalLight();
    dl.setDirection(new Vector3f(-0.1f, -1f, -1).normalizeLocal());
    rootNode.addLight(dl);
    player = (Node) assetManager.loadModel("/Models/Oto/Oto.mesh.xml");
    player.setLocalScale(0.5f);
    rootNode.attachChild(player);
    control = player.getControl(AnimControl.class);
    control.addListener(this);
    channel = control.createChannel();
    channel.setAnim("Walk");
    y="walk";
    this.onAnimCycleDone(control,channel,y);
	}

   
  public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
	if(y.equals("walk")){
	  System.out.println(y);
      channel.setAnim("Walk", 0.30f);
      channel.setLoopMode(LoopMode.DontLoop);
      channel.setSpeed(0.5f);
	}
	if(y.equals("しなる")){
	  channel.setAnim("push",0.30f);
	  channel.setLoopMode(LoopMode.DontLoop);
	  channel.setSpeed(0.5f);
	}
	if(y.equals("push")){
	  channel.setAnim("pull",1.00f);
	  channel.setLoopMode(LoopMode.DontLoop);
	  channel.setSpeed(0.5f);
	}
 }
	
  public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
	}

 
  /** Custom Keybinding: Map named actions to inputs. */
  public void initKeys() {
    inputManager.addMapping("Walk",new KeyTrigger(KeyInput.KEY_SPACE));
    inputManager.addListener(actionListener, "Walk");
    inputManager.addMapping("pull",new KeyTrigger(KeyInput.KEY_Y));
    inputManager.addListener(actionListener, "pull");
    inputManager.addMapping("push",new KeyTrigger(KeyInput.KEY_L));
    inputManager.addListener(actionListener,"push");
    inputManager.addMapping("stand",new KeyTrigger(KeyInput.KEY_O));
    inputManager.addListener(actionListener,"stand");
  }
  
   ActionListener actionListener = new ActionListener() {
    public void onAction(String name, boolean keyPressed, float tpf) {
      if (name.equals("Walk") && !keyPressed) {
          y="walk";
          channel.setAnim("Walk", 0.30f);
          channel.setLoopMode(LoopMode.DontLoop);
      }
      if(name.equals("push") && !keyPressed) {
          y="walk";
      }
      if(name.equals("pull") && !keyPressed){
    	  y="しなる";
    	  channel.setAnim("pull",0.30f);
    	  channel.setLoopMode(LoopMode.DontLoop);
      }
      if(name.equals("stand") && !keyPressed){
    	  y="stand";
      }
    }
  };
	public void setValue(String _y){
		y=_y;
	}
	}

