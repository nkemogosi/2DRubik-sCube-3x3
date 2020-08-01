import java.awt.EventQueue;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class GUI {

	private JFrame frame;
	ArrayList<JButton[][]> listJB = new ArrayList<>();
	RubixCube r = new RubixCube();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.update();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

//		listJB.add(create3x3(251, 25));
//		listJB.add(create3x3(206, 70));
//		listJB.add(create3x3(251, 70));
//		listJB.add(create3x3(296, 70));
//		listJB.add(create3x3(251, 115));
//		listJB.add(create3x3(251, 160));

		listJB.add(create3x3(25, 251));
		listJB.add(create3x3(70, 206));
		listJB.add(create3x3(70, 251));
		listJB.add(create3x3(70, 296));
		listJB.add(create3x3(115, 251));
		listJB.add(create3x3(160, 251));
		r.createCube();

		JButton btnR = new JButton("R");
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				r.rotateCube(RotationMoves.R, true);
				update();
			}
		});
		btnR.setBounds(0, 0, 89, 23);
		frame.getContentPane().add(btnR);

		JButton btnL = new JButton("L");
		btnL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				r.rotateCube(RotationMoves.L, true);
				update();
			}
		});
		btnL.setBounds(0, 111, 89, 23);
		frame.getContentPane().add(btnL);

		JButton btnU = new JButton("U");
		btnU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				r.rotateCube(RotationMoves.U, true);
				update();
			}

		});
		btnU.setBounds(0, 21, 89, 23);
		frame.getContentPane().add(btnU);

		JButton btnM = new JButton("M");
		btnM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// r.rotateCube(RotationMoves.M);
				update();

			}
		});
		btnM.setBounds(0, 88, 89, 23);
		frame.getContentPane().add(btnM);

		JButton btnS = new JButton("S");
		btnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// r.rotateCube(RotationMoves.S);
				update();
			}
		});
		btnS.setBounds(0, 45, 89, 23);
		frame.getContentPane().add(btnS);

		JButton btnU_1 = new JButton("U!");
		btnU_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				r.rotateCube(RotationMoves.U, false);
				update();
			}
		});
		btnU_1.setBounds(87, 21, 89, 23);
		frame.getContentPane().add(btnU_1);

		JButton btnR_1 = new JButton("R!");
		btnR_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				r.rotateCube(RotationMoves.R, false);
				update();
			}
		});
		btnR_1.setBounds(87, 0, 89, 23);
		frame.getContentPane().add(btnR_1);

		JButton btnD = new JButton("D");
		btnD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				r.rotateCube(RotationMoves.D, true);
				update();
			}
		});
		btnD.setBounds(0, 66, 89, 23);
		frame.getContentPane().add(btnD);

		JButton btnL_1 = new JButton("L!");
		btnL_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				r.rotateCube(RotationMoves.L, false);
				update();
			}
		});
		btnL_1.setBounds(87, 111, 89, 23);
		frame.getContentPane().add(btnL_1);

		JButton btnF = new JButton("F");
		btnF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				r.rotateCube(RotationMoves.F, true);
				update();
			}
		});
		btnF.setBounds(0, 134, 89, 23);
		frame.getContentPane().add(btnF);

		JButton btnF_1 = new JButton("F!");
		btnF_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				r.rotateCube(RotationMoves.F, false);
				update();
			}
		});
		btnF_1.setBounds(87, 134, 89, 23);
		frame.getContentPane().add(btnF_1);

		JButton btnB = new JButton("B");
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				r.rotateCube(RotationMoves.B, true);
				update();
			}
		});
		btnB.setBounds(0, 156, 89, 23);
		frame.getContentPane().add(btnB);

		JButton btnB_1 = new JButton("B!");
		btnB_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				r.rotateCube(RotationMoves.B, false);
				update();
			}
		});
		btnB_1.setBounds(87, 156, 89, 23);
		frame.getContentPane().add(btnB_1);

		textField = new JTextField();
		textField.setBounds(188, 67, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(textField.getText());
				String[] moves = textField.getText().split(" ");
				int count = 0;
				for (String string : moves) {
					boolean clockwise = !string.contains("'");
					if (!clockwise) {
						string = string.substring(0, string.length() - 1);
					}
					int counter = 1;
					if (string.length() > 1) {
						String temp = string;
						string = string.substring(0, 1);
						counter = Integer.parseInt(temp.substring(1));
					}
					for (int i = 0; i < counter; i++) {
						switch (string) {
						case "R":
							r.rotateCube(RotationMoves.R, clockwise);
							break;
						case "U":
							r.rotateCube(RotationMoves.U, clockwise);
							break;
						case "L":
							r.rotateCube(RotationMoves.L, clockwise);
							break;
						case "F":
							r.rotateCube(RotationMoves.F, clockwise);
							break;
						case "B":
							r.rotateCube(RotationMoves.B, clockwise);
							break;
						case "D":
							r.rotateCube(RotationMoves.D, clockwise);
							break;
						default:
							System.out.println("REE");
							break;
						}
						//System.out.println(string + ": " + (clockwise));
						update();
					}

				}

			}
		});
		btnRun.setBounds(284, 66, 89, 23);
		frame.getContentPane().add(btnRun);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				r.createCube();
				update();
			}
		});
		btnReset.setBounds(284, 88, 89, 23);
		frame.getContentPane().add(btnReset);

		JButton button = new JButton("D!");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				r.rotateCube(RotationMoves.D, false);
			}
		});
		button.setBounds(87, 66, 89, 23);
		frame.getContentPane().add(button);
		System.out.println("Test");
	}

	public JButton[][] create3x3(int x, int y) {
		JButton[][] bArray = new JButton[3][3];
		int tempY = y;
		int counter = 0;
		for (int i = 0; i < 3; i++) {
			int tempX = x;
			for (int j = 0; j < 3; j++) {
				JButton b = new JButton();
				b.setBounds(tempX, tempY, 15, 15);
				bArray[i][j] = b;
				frame.getContentPane().add(b);
				tempX += 15;
			}
			tempY += 15;
		}
		return bArray;
	}

	public void update() {
		for (int i = 0; i < listJB.size(); i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					JButton[][] b = listJB.get(i);
					b[j][k].setBackground(r.cube[i][j][k].c);

				}
			}
		}
	}
}
