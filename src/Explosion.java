import EasyGraphics.Picture;

public class Explosion {
	
	private int x, y, width = 100, height = 100;
	private Canvas panel;
	private Picture[] pic;
	
	private void update() {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				while(true) {
					for(int i = 0; i < pic.length; i++) {
						panel.setExplosionPic(pic[i]);
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					break;
				}
			}
		});
		thread.start();
	}
	
	public Explosion(int x, int y, Canvas panel) {
		this.x = x;
		this.y = y;
		this.panel = panel;
		pic = new Picture[16];
		for(int i = 0; i < pic.length; i++) {
			pic[i] = new Picture("C:/Users/amera/eclipse-workspace/MeteorSHower/imgs/" + String.valueOf(i) + ".png", x, y, width, height);
		}
		update();
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
