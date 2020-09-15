import EasyGraphics.*;

public class Meteor {

	private int x, y, width, height, speedX, speedY, frameWid, frameHei;
	private Picture pic;
	private Canvas panel;
	private boolean exists = true;

	public void update() {
		Thread update = new Thread(new Runnable() {
			public void run() {
				while (exists) {
					x += speedX;
					y += speedY;
					
					if(x < -300 - width || x > ((frameWid + 300) + width) || y < -300 - height || y > ((frameHei + 300) + height)) {
						destroyMeteor();
						break;
					}
					
					if(!exists) {
						break;
					}
					
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
		
		update.start();
	}
	
	public void destroyMeteor() {
		panel.removeMeteor(this);
		exists = false;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Picture getPicture() {
		return pic;
	}

	public Meteor(Canvas panel, int frameWid, int frameHei) {
		String meteorName;
		int meteorRandom = (int)(Math.random() * 5);
		int randomChance = (int)(Math.random() * 30);
		
		
		
		this.panel = panel;
		this.frameWid = frameWid;
		this.frameHei = frameHei;
		this.width = (int) (Math.random() * 100) + 30;
		this.height = this.width;
		int posFrom = (int)( Math.random() * 4);
		if (posFrom == 0) {
			this.x = -200;
			this.y = -200;
			this.speedX = (int) (Math.random() * 20) + 5;
			this.speedY = (int) (Math.random() * 20) + 5;
		}
		if (posFrom == 1) {
			this.x = frameWid + 200;
			this.y = -200;
			this.speedX = -(int) (Math.random() * 20)  + 5;
			this.speedY = (int) (Math.random() * 20)  + 5;
		}
		if (posFrom == 2) {
			this.x = -200;
			this.y = frameHei + 200;
			this.speedX = (int) (Math.random() * 20) + 5;
			this.speedY = -(int) (Math.random() * 20) + 5;
		}
		if (posFrom == 3) {
			this.x = frameWid + 200;
			this.y = frameHei + 200;
			this.speedX = -(int) (Math.random() * 20) + 5;
			this.speedY = -(int) (Math.random() * 20) + 5;
		}
		
		if(randomChance == 15) {
			meteorName = "meteor_5.png";
			this.width = 500;
			this.height = 500;
		} else {
			meteorName = "meteor_" + String.valueOf(meteorRandom) + ".png";
		}
		
		pic = new Picture("C:/Users/amera/eclipse-workspace/MeteorSHower/imgs/" + meteorName, x, y, width, height);
		update();
	}

}
