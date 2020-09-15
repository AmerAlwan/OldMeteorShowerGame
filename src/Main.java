import EasyGraphics.*;

public class Main {

	private int width = 1400, height = 900;
	private Frame frame = new Frame("Meteor Shower", 100, 100, width, height, false);
	private Canvas panel = new Canvas(frame);
	private String dir = "C:/Users/amera/eclipse-workspace/MeteorSHower/imgs/";
	private Player player;
	private Picture background;

	public void init() {
		background = new Picture(dir + "background.png");
		panel.setBackground(background, width, height);
		frame.add(panel);
		frame.update();
		mainMenu();
	}

	public void startGame() {
		panel.clear();
		panel.setBackground(background, width, height);
		player = new Player(this.width, this.height, panel);
	}

	public void store() {
		System.out.println("Store");
	}

	public void mainMenu() {
		Text title = new Text("METEOR\nSHOWER", 0, 100, 100);
		Button start_b = new Button("START", 0, 0, 300, 100, "240, 240, 240");
		Button store_b = new Button("SCORES", 0, 0, 300, 100, "white");
		Button exit_b = new Button("EXIT", 0, 0, 300, 100, "red");
		exit_b.centerButHor(width);
		exit_b.setY(exit_b.getCenterVer(height) + 225);
		store_b.centerButHor(width);
		store_b.setY(store_b.getCenterVer(height) + 100);
		start_b.centerButHor(width);
		start_b.setY(start_b.getCenterVer(height) - 25);
		title.centerTextHor(width);
		panel.add(title);
		panel.add(start_b);
		panel.add(store_b);
		panel.add(exit_b);

		while (true) {
			if (start_b.isPressed()) {
				startGame();
			}

			if (store_b.isPressed()) {
				store();
			}

			if (exit_b.isPressed()) {
				System.exit(0);
			}

			frame.update();
		}

	}

	public static void main(String[] args) {
		Main m = new Main();
		m.init();
	}
}
