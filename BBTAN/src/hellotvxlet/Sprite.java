

package hellotvxlet;


import org.havi.ui.HStaticIcon;


public abstract class Sprite extends HStaticIcon implements ObserverInterface{

    
    
    public Sprite(int x, int y,int sizeX,int sizeY){
        super();
        
        this.setSize(sizeX,sizeY);
        this.setLocation(x, y);
        
  
    }

   
}
