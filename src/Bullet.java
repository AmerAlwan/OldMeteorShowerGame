import EasyGraphics.*;

public class Bullet {
	
	private int x, y, speed, width, height;
	private Picture pic;
	private Canvas panel;
	private boolean exists = true;
	//private Bullet bullet;
	
	private void update() {
		Thread update = new Thread(new Runnable() {

			@Override
			public void run() {
				while(exists) {
					y -= speed;
					
					if (y < 0) {
						destroyBullet();
						break;
					}
					if(!exists) {
						break;
					}
					
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
			
		});
		
		update.start();
	}
	
	public void destroyBullet() {
		panel.removeBullet(this);
		exists = false;
	}
	
	public Bullet(int x, int y, int speed, Canvas panel) {
		
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = 10;
		this.height = 20;
		pic = new Picture("C:/Users/amera/eclipse-workspace/MeteorSHower/imgs/bullet.png", x, y, width, height);
		this.panel = panel;
	//	this.bullet = this;
		//panel.addBullet(this);
		update();
	}
	
	public Picture getPicture() {
		return pic;
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
