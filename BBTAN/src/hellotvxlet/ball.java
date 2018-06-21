

package hellotvxlet;


import java.awt.*;

import org.havi.ui.HVisible;


public class ball extends Sprite{



int xt;
int yt;
int ygr;
int xgr;
int xr = -1;
int yr = -1;
    public ball(int x, int y, int sizeX, int sizeY){
        
        super(x,y,sizeX,  sizeY);
        this.setBordersEnabled(true);
    xt= x;
    yt = y;
    xgr = sizeX;
    ygr = sizeY;
 

     this.setBackground(Color.WHITE);
     
     this.setBackgroundMode(HVisible.BACKGROUND_FILL);
     this.setBounds(x, y, xgr, ygr);
    
     }
    
    Rectangle getRect() {
        return new Rectangle(xt, yt,
                xgr, ygr);
    }
    public void setXDir(int x) {
        xr = x;
    }

    public void setYDir(int y) {
        yr = y;
    }
    
    public int getXDir() {
        return xr;
    }
    
  
       public void update(int tijd) {
      xt = xt + xr;
      yt = yt + yr;
      
      if(xt<=0){
            xr = 1;
        }
      if(xt >= 720-this.getWidth()){
            xr= -1;
        }
      if(yt<=0){
            yr = 1;
        }
      this.setLocation(xt, yt);
    }
}