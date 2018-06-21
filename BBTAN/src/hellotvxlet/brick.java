package hellotvxlet;

import java.awt.Color;
import java.awt.Rectangle;
import org.havi.ui.HVisible;


public class brick extends Sprite{


public boolean destroyed; 
int xt;
int yt;
int ygr;
int xgr;
Color mColor;

    public brick(int x, int y, int sizeX, int sizeY, Color color){
        
        super(x,y,sizeX,  sizeY);
    xt= x;
    yt = y;
    xgr = sizeX;
    ygr = sizeY;
    mColor = color;
    this.setBackground(mColor);
    this.setBackgroundMode(HVisible.BACKGROUND_FILL);
     
     }
     
      Rectangle getRect() {
        return new Rectangle(xt, yt,
                xgr, ygr);
    }
     public boolean isDestroyed() {
        
        return destroyed;
    }
     public void setDestroyed(boolean a) {
        destroyed = a;
        
    }
    
     
     
     public void MoveRight(){
     
     xt += 10;
     if(xt >= 520){
        xt =520;
     }
      this.setBounds(xt,yt,xgr,ygr);
     }
      public void MoveLeft(){
     xt -= 10;
    
     if(xt <= 0){
        xt =0;
     }
      this.setBounds(xt,yt,xgr,ygr);
     }
      public void update(int tijd) {
      
      
    }
     
    
}
    
    

