import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.swing.JPanel;

import EasyGraphics.*;

public class Canvas extends Panel {

	private boolean collide = false;

	public Canvas(Frame frame) {
		super(frame);
		// TODO Auto-generated constructor stub
	}

	private Player player;
	private Bullet[] bullet = new Bullet[0];
	private Meteor[] meteor = new Meteor[0];
	private Picture explosionPic = null;
	private Explosion explosion = null;
	private int score = 0;

	public int getNumMeteors() {
		int num = 0;
		for (int i = 0; i < meteor.length; i++) {
			if (meteor[i] != null) {
				num += 1;
			}
		}

		return num;
	}

	public void setExplosionPic(Picture explosion) {
		this.explosionPic = explosion;
	}

	public void addBullet(Bullet bullet) {
		this.bullet = Arrays.copyOf(this.bullet, this.bullet.length + 1);
		this.bullet[this.bullet.length - 1] = bullet;
	}

	public void removeBullet(Bullet bullet) {
		int index = Arrays.asList(this.bullet).indexOf(bullet);
		this.bullet[index] = null;
	}

	public void addMeteor(Meteor meteor) {
		this.meteor = Arrays.copyOf(this.meteor, this.meteor.length + 1);
		this.meteor[this.meteor.length - 1] = meteor;
	}

	public void removeMeteor(Meteor meteor) {
		int index = Arrays.asList(this.meteor).indexOf(meteor);
		this.meteor[index] = null;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isCollide(int x1, int y1, int wid1, int hei1, int x2, int y2, int wid2, int hei2) {
		int x1m = x1 + wid1;
		int y1m = y1 + hei1;
		int x2m = x2 + wid2;
		int y2m = y2 + hei2;
		if (((x1m > x2 && x1m < x2m) || (x1 < x2m && x1 > x2) || (x1 == x2 && x1m == x2m) || (x1 < x2 && x1m >= x2m))
				&& ((y1m > y2 && y1m < y2m) || (y1 < y2m && y1 > y2) || (y1 == y2 && y1m == y2m)
						|| (y1 < y2 && y1m >= y2m))) {

			return true;
		}
		return false;
	}

	@Override
	public void paintComponent(Graphics gPar) {
		super.paintComponent(gPar);
		Graphics2D g = (Graphics2D) gPar;
		setBackground(Color.white);

		for (int i = 0; i < bullet.length; i++) {
			if (bullet[i] != null) {
				g.drawImage(bullet[i].getPicture().getImage(), bullet[i].getX(), bullet[i].getY(), bullet[i].getWidth(),
						bullet[i].getHeight(), null);
			}
		}
		for (int z = 0; z < meteor.length; z++) {
			if (meteor[z] != null) {
				g.drawImage(meteor[z].getPicture().getImage(), meteor[z].getX(), meteor[z].getY(), meteor[z].getWidth(),
						meteor[z].getHeight(), null);
			}
		}

		for (int i = 0; i < bullet.length; i++) {
			if (bullet[i] != null) {

				for (int z = 0; z < meteor.length; z++) {
					if (meteor[z] != null) {
						if (isCollide(meteor[z].getX(), meteor[z].getY(), meteor[z].getWidth(), meteor[z].getHeight(),
								bullet[i].getX(), bullet[i].getY(), bullet[i].getWidth(), bullet[i].getHeight())) {
							score += (int) (200 / meteor[z].getWidth());
							bullet[i].destroyBullet();
							meteor[z].destroyMeteor();
							collide = true;
							break;
						}

						if (collide) {
							collide = false;
							break;
						}
					}
				}
			}
		}

		if (explosion != null) {
			g.drawImage(explosionPic.getImage(), explosion.getX(), explosion.getY(), explosion.getWidth(),
					explosion.getHeight(), null);
		}

		g.setFont(new Font("Times New Roman", Font.BOLD, 40));
		g.setPaint(Color.WHITE);
		g.drawString("Score: " + String.valueOf(score), 5, 40);

		if (player != null) {
			g.drawImage(player.getPicture().getImage(), player.getX(), player.getY(), player.getWidth(),
					player.getHeight(), null);
			if (meteor != null) {
				for (int z = 0; z < meteor.length; z++) {
					if (meteor[z] != null) {
						if (isCollide(meteor[z].getX(), meteor[z].getY(), meteor[z].getWidth(), meteor[z].getHeight(),
								player.getX(), player.getY(), player.getWidth(), player.getHeight())) {
							//System.out.println("Collide");
							explosion = new Explosion(player.getX(), player.getY(), this);
							player.setStatus(false);
							player = null;
							break;
						}
					}
				}
			}
		}
		repaint();
	}
}
