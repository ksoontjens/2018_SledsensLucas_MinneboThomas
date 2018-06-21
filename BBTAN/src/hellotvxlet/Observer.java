
package hellotvxlet;


public class Observer implements ObserverInterface{

    public void update(int tijd) {
        System.out.println(this.toString()+" tijd= "+tijd);
        
    }

}
