

package hellotvxlet;

import java.util.ArrayList;
import java.util.TimerTask;

public class Subject extends TimerTask implements SubjectInterface {
        ArrayList  ol = new ArrayList();
        int tijd = 0;
     public void run() {
       
         tijd++;
         for(int i =0;i<ol.size();i++){
               
             ((ObserverInterface)ol.get(i)).update(tijd);
         }
       
    }
    public void unregister(ObserverInterface oi) {
       ol.remove(oi);
    }
    public void register(ObserverInterface oi) {
       ol.add(oi);
    }

    

}
