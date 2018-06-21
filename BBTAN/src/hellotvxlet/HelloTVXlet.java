package hellotvxlet;


import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Timer;
import javax.tv.xlet.*;
import org.havi.ui.event.*;
import org.dvb.event.*;
import java.awt.event.*;
import org.havi.ui.*;



public class HelloTVXlet implements Xlet,UserEventListener, ObserverInterface, HActionListener{
    
    static HScene scene = null; 
    static Subject publisher = null;
    brick brick = null;
    ball bal = null;
    brick ending = null;
    Rectangle[][] brickranden = new Rectangle[10][10];
    brick[][] enemybricks = new brick[10][10];
    private HTextButton resetbtn;
    private HTextButton restartbtn;
    Timer timer;
    boolean space = false;
    
    // ----------------------------
    
    private HText pointsText;
    private HText highscore;
    private HText gameOverText;
    int points = 0;
    
    
    

   public static HScene getScene(){
    return scene;
    }
    public static Subject getPublisher(){
    return publisher;
    }
    public void destroyXlet(boolean unconditional) throws XletStateChangeException {
        
    }
    
    int snelheid = 7;

    public void initXlet(XletContext ctx) throws XletStateChangeException {
        
        startGame();
    }
    
    public void pauseXlet() {
        
    }

    public void startXlet() throws XletStateChangeException {
        
        EventManager  mngr = EventManager.getInstance();
        UserEventRepository repo = new UserEventRepository("Keys");
        repo.addAllArrowKeys();
        repo.addKey(HRcEvent.VK_ENTER);
        mngr.addUserEventListener(this, repo);     
    }

    
    public void userEventReceived(UserEvent e) {
      if(e.getType() == KeyEvent.KEY_PRESSED){
      switch(e.getCode()){
           
            case HRcEvent.VK_ENTER:  
                if (space == false)
                {
                    publisher.register(bal);
                    space = true;
                }
               
              break;               
           case HRcEvent.VK_RIGHT: 
              brick.MoveRight();
              break; 
           case HRcEvent.VK_LEFT: 
              brick.MoveLeft();
              break;
            }
      
      }
    }
    public void CollideBalk(){
        
        
        
        Rectangle balkrand = brick.getRect();
        Rectangle balrand = bal.getRect();
        if( gameOver() == 0) {
            testEndGame();
        }
        if(balrand.y > 720)
        {
            testEndGame();
        }
        if(balrand.intersects(balkrand))
        {
            bal.setYDir(-1);
        }
        
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                    if(balrand.intersects(brickranden[i][j])){
                        
                        

                        Point pointRight = new Point(balrand.x + balrand.width + 1, balrand.y);
                        Point pointLeft = new Point(balrand.x - 1, balrand.y);
                        Point pointTop = new Point(balrand.x, balrand.y - 1);
                        Point pointBottom = new Point(balrand.x, balrand.y + balrand.height + 1);
                        if(!enemybricks[i][j].isDestroyed()){
                            
                            points++;
                            
                            scene.remove(pointsText);
                            
                            
                            pointsText = new HText("Score: " + points);
                            pointsText.setLocation(5,3);
                            pointsText.setSize(100,50);
                            pointsText.setBordersEnabled(false);
                            scene.add(pointsText);
                            
                            
                            if (brickranden[i][j].contains(pointTop)) {
                            bal.setYDir(1);
                                if(bal.getXDir() == 1){
                                    bal.setXDir(1);
                                }
                                else{
                                    bal.setXDir(-1);
                                }
                                      
                            } 
                            if (enemybricks[i][j].contains(pointBottom)) {
                                bal.setYDir(-1);
                                if(bal.getXDir() == 1){
                                    bal.setXDir(-1);
                                }
                                else{
                                    bal.setXDir(1);
                                }
                            }
                            if (brickranden[i][j].contains(pointRight)) {
                            bal.setXDir(-1);
                                
                            } else if (brickranden[i][j].contains(pointLeft)) {
                            bal.setXDir(1);
                            }
                           
                        }
                        enemybricks[i][j].setDestroyed(true);
                        
                        
                        
                        scene.remove(enemybricks[i][j]);
                        
                                           
                        scene.repaint();
                        scene.validate();
                        scene.setVisible(true);
                        
                         
                    }
            }
        }
    }
    
    public void update(int tijd) {
       CollideBalk();
       
       
    }
    
    public int gameOver() {
        
        int x;
        x = 0;
        
        for(int i=0;i<10;i++){
           
            for(int j=0;j<10;j++){
                
                if(!enemybricks[i][j].isDestroyed())
                {
                    x = 1;
                }
            }
       }
       
        return x;
       
        
    }
    
     public void actionPerformed(ActionEvent arg0) {
        String action = arg0.getActionCommand();
        if(action.equals("quit")){
            System.exit(0);        
        } 
        
    }
    public void endGame(){
        
        scene.remove(brick);
        scene.remove(bal);
        
        
        if( gameOver() == 1) {
            
            for(int i=0;i<10;i++){
                   for(int j=0;j<10;j++){
                       scene.remove(enemybricks[i][j]);
                   }
            }
        }
        
        scene.remove(pointsText);
        
        highscore = new HText("Highscore: " + points);
         
        highscore.setForeground(Color.BLACK);
        highscore.setLocation(250,250);
        highscore.setSize(200,50);
        highscore.setBordersEnabled(false);
        scene.add(highscore);
        
        gameOverText = new HText("GAME OVER");
         
        gameOverText.setForeground(Color.BLACK);
        gameOverText.setLocation(250,175);
        gameOverText.setSize(200,100);
        gameOverText.setBordersEnabled(false);
        scene.add(gameOverText);
        
        
        ending = new brick(200,200,300,200,Color.WHITE);
        
        
        resetbtn = new HTextButton("QUIT");
        resetbtn.setLocation(300,325);
        resetbtn.setBackground(Color.BLACK);
        resetbtn.setBackgroundMode(HVisible.BACKGROUND_FILL);
        resetbtn.setSize(100,50);
        resetbtn.setActionCommand("quit");
        resetbtn.addHActionListener(this);
        scene.add(resetbtn); 
        
        scene.add(ending);
        resetbtn.requestFocus();
        scene.validate();
        scene.setVisible(true);
        scene.repaint();
    }
    
    public void removeDeathScreen() {
        
        
        
        scene.remove(restartbtn);
        scene.remove(ending);
        scene.remove(gameOverText);
        scene.remove(highscore);
        
        scene.validate();
        scene.setVisible(true);
        scene.repaint();
    }
    
    public void startGame() {
        
        points = 0;
        
        scene  = HSceneFactory.getInstance().getDefaultHScene();
        
        publisher = new Subject();
        timer = new Timer();
        timer.scheduleAtFixedRate(publisher,0, snelheid);
        
        //-------------------------------------------------
        pointsText = new HText("Score: " + points);
        pointsText.setLocation(5,3);
        pointsText.setSize(100,50);
        pointsText.setBordersEnabled(false);
        scene.add(pointsText);
        //-------------------------------------------------
        
        brick= new brick(280,510,150,15, Color.RED);
        scene.add(brick);
        int width = 60;
        int height = 15;
               for(int i=0;i<10;i++){
                   for(int j=0;j<10;j++){
                      brick bricks = new brick(47+62*i,60+16*j,width,height, Color.GREEN);
                      scene.add(bricks);
                      brickranden[i][j] = bricks.getRect();
                      enemybricks[i][j] = bricks;
                   }
               }
        bal =  new ball(350,400,13,13);
        scene.add(bal);
        
        scene.repaint();
        scene.validate();
        scene.setVisible(true);
        
        
        
        
        publisher.register(this);
        
        
            
    }
    
    public void testEndGame() {
        
        timer.cancel();
        endGame();
    }


   
 
   
}
