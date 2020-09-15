import java.awt.event.KeyEvent;

import EasyGraphics.Key;
import EasyGraphics.Picture;

public class Player extends Thread {
	
	private int x, y, width, height, fWidth, fHeight;
	private volatile int speed = 30, speedx = 0, speedy = 0;
	private Picture picture;
	private Canvas panel;
	private volatile Key W, S, A, D, Space;
	private boolean status = true;
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Player(int width, int height, Canvas panel) {
		this.x = width / 2;
		this.y = height / 2;
		this.fWidth = width;
		this.fHeight = height;
		this.width = 75;
		this.height = 75;
		this.panel = panel;
		W = new Key(this.panel, KeyEvent.VK_W);
		S = new Key(this.panel, KeyEvent.VK_S);
		A = new Key(this.panel, KeyEvent.VK_A);
		D = new Key(this.panel, KeyEvent.VK_D);
		Space = new Key(this.panel, KeyEvent.VK_SPACE);
		this.picture = new Picture("C:/Users/amera/eclipse-workspace/MeteorSHower/imgs/ship.png", this.x, this.y, this.width, this.height);	
		this.panel.setPlayer(this);
		this.start();
		for(int i = 0; i < 20; i++) {
			Meteor meteor = new Meteor(panel, width, height);
			panel.addMeteor(meteor);
		}
	}
	
	@Override
	public void run() {
		
		
		boolean createBullet = false;
		
		while(true) {
			//System.out.println(panel.getNumMeteors());
			
			if(panel.getNumMeteors() >= 1 && panel.getNumMeteors() <= 5) {
				for(int i = 0; i < 10; i++) {
					Meteor meteor = new Meteor(panel, fWidth, fHeight);
					panel.addMeteor(meteor);
				}
			}
			
			if(Space.pressed()) {
				createBullet = true;
				
			}
			
			if(Space.released()) {
				createBullet = false;
			}
			
			if (createBullet) {
				Bullet bullet = new Bullet(((x + (x + width)) / 2) - 5, y, 30, panel);
				panel.addBullet(bullet);
			}
			
			boolean wPres = W.pressed();
			boolean sPres = S.pressed();
			boolean aPres = A.pressed();
			boolean dPres = D.pressed();
			
			if (wPres) {
				speedy = -speed;
				//S.release();
			} 
			if (sPres) {
				speedy = speed;
				//W.release();
			} 
			if ((W.released() || S.released())) {
				speedy = 0;
			}
			if(aPres) {
				speedx = -speed;
				//D.release();
			} 
			if (dPres) {
				speedx = speed;
				//A.release();
			}
			if (A.released() || D.released()) {
				speedx = 0;
			}
			
			x += speedx;
			y += speedy;
			
			if(x + width > fWidth) {
				x = fWidth - width;
			}
			if(y + height > fHeight) {
				y = fHeight - height;
			}
			
			if(x < 0) {
				x = 0;
			}
			if( y < 0) {
				y = 0;
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Picture getPicture() {
		return picture;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	
}
