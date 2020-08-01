import java.util.ArrayList;
import java.util.Collections;

public class RotationAlgs {
	private Piece[][][] cube;

	public RotationAlgs(RubixCube rubixCube) {
		this.cube = rubixCube.cube;
	}

	public Piece[][][] selectAlg(RotationMoves move, boolean clockwise) {
		switch (move) {
		case U:
			stripHorizontalRotation(0, clockwise);
			faceRotation(1, clockwise);
			break;
		case M:
			break;
		case R:
			stripVertRotation(2, clockwise);
			faceRotation(4, clockwise);
			break;
		case L:
			stripVertRotation(0, clockwise);
			faceRotation(0, clockwise);
			break;
		case F:
			stripRingRotate(2, clockwise);
			faceRotation(2, clockwise);
			break;
		case B:
			stripRingRotate(0, clockwise);
			faceRotation(5, clockwise);
			break;
		case D:
			stripHorizontalRotation(2, !clockwise);
			faceRotation(3, clockwise);
			break;
		case S:
			break;
		default:
			break;
		}
		return cube;
	}

	private void stripRingRotate(int sliceIndex, boolean clockwise) {
		int incCounter = 0;
		int roc = 0;
		int change = 3;
		boolean reverse = false;
		clockwise = ((sliceIndex == 2) ^ clockwise);
		ArrayList<Piece> fullList = ringRotate(addRingToList(roc, change, sliceIndex), clockwise, 3);

		for (int i = 1; incCounter < 4; i += change) {
			int[] slideX;
			if (incCounter < 2) {
				slideX = new int[] { 0, 1, 2 };
			} else {
				slideX = new int[] { 2, 1, 0 };
			}
			int slideY = (i < 2) ? sliceIndex : 0;
			if (sliceIndex == 0) {
				slideY = (i < 2) ? 0 : 2;
			}
			System.out.println(slideY);
			if (reverse) {
				for (int j = 0; j < slideX.length; j++) {
					cube[i][slideX[j]][slideY] = fullList.get(0);
					fullList.remove(0);
				}
			} else {
				for (int j = 0; j < slideX.length; j++) {
					cube[i][slideY][slideX[j]] = fullList.get(0);
					fullList.remove(0);
				}
			}
			reverse = !reverse;
			change += roc;
			roc = (-4 + 2 * incCounter);
			incCounter++;
		}
	}

	private ArrayList<Piece> addRingToList(int roc, int change, int sliceIndex) {
		int incCounter = 0;
		boolean reverse = false;
		ArrayList<Piece> fullList = new ArrayList<Piece>();
		for (int i = 1; incCounter < 4; i += change) {
			int[] slideX;
			if (incCounter < 2) {
				slideX = new int[] { 0, 1, 2 };
			} else {
				slideX = new int[] { 2, 1, 0 };
			}
			int slideY = (i < 2) ? sliceIndex : 0;
			if (sliceIndex == 0) {
				slideY = (i < 2) ? 0 : 2;
			}
			if (reverse) {
				for (int j = 0; j < slideX.length; j++) {
					fullList.add(cube[i][slideX[j]][slideY]);
				}
			} else {
				for (int j = 0; j < slideX.length; j++) {
					fullList.add(cube[i][slideY][slideX[j]]);
				}
			}
			reverse = !reverse;
			change += roc;
			roc = (-4 + 2 * incCounter);
			incCounter++;
		}
		return fullList;
	}

	public void stripVertRotation(int sliceIndex, boolean clockwise) {
		// if its a mid or left rotation invert the rotation to get the actual rotation
		clockwise = !((sliceIndex == 2) ^ clockwise);
		int flip = Math.abs(sliceIndex - 2);
		ArrayList<Piece> fullList = new ArrayList<Piece>();
		fullList.add(cube[1][0][sliceIndex]);
		fullList.add(cube[1][1][sliceIndex]);
		fullList.add(cube[1][2][sliceIndex]);
		for (int i = 2; i < 5; i++) {
			if (i == 4) {
				i = 5;
				fullList.add(cube[i][2][flip]);
				fullList.add(cube[i][1][flip]);
				fullList.add(cube[i][0][flip]);
			} else {
				fullList.add(cube[i][0][sliceIndex]);
				fullList.add(cube[i][1][sliceIndex]);
				fullList.add(cube[i][2][sliceIndex]);
			}

		}
		fullList = ringRotate(fullList, clockwise, 3);
		int counter = 0;
		for (int i = 1; i < 5; i++) {
			if (i == 4) {
				i = 5;
				cube[i][2][flip] = fullList.get(counter);
				cube[i][1][flip] = fullList.get(counter + 1);
				cube[i][0][flip] = fullList.get(counter + 2);
			} else {
				cube[i][0][sliceIndex] = fullList.get(counter);
				cube[i][1][sliceIndex] = fullList.get(counter + 1);
				cube[i][2][sliceIndex] = fullList.get(counter + 2);
			}

			counter += 3;
		}

	}

	private void stripHorizontalRotation(int sliceIndex, boolean clockwise) {
		ArrayList<Piece> fullList = new ArrayList<Piece>();
		for (int i = 0; i < 7; i += 2) {
			i = (i == 3) ? 4 : ((i == 6) ? 5 : i);
			fullList.add(cube[i][sliceIndex][0]);
			fullList.add(cube[i][sliceIndex][1]);
			fullList.add(cube[i][sliceIndex][2]);
		}
		fullList = ringRotate(fullList, clockwise, 3);
		int counter = 0;
		for (int i = 0; i < 7; i += 2) {
			i = (i == 3) ? 4 : ((i == 6) ? 5 : i);
			cube[i][sliceIndex][0] = fullList.get(counter);
			cube[i][sliceIndex][1] = fullList.get(counter + 1);
			cube[i][sliceIndex][2] = fullList.get(counter + 2);
			counter += 3;
		}

	}

	private void faceRotation(int face, boolean clockwise) {
		ArrayList<Piece> fullList = new ArrayList<Piece>();
		for (int i = 0; i < cube[face].length; i++) {
			fullList.add(cube[face][0][i]);
		}
		fullList.add(cube[face][1][2]);
		for (int i = 2; i >= 0; i--) {
			fullList.add(cube[face][2][i]);
		}
		fullList.add(cube[face][1][0]);
//		for (Piece piece : fullList) {
//			System.out.println(piece.c);
//		}
		fullList = ringRotate(fullList, !clockwise, 2);
//		for (Piece piece : fullList) {
//			System.out.println(piece.c);
//		}
		int counter = 0;
		for (int i = 0; i < cube[face].length; i++) {
			cube[face][0][i] = fullList.get(counter++);
		}
		cube[face][1][2] = fullList.get(counter++);
		for (int i = 2; i >= 0; i--) {
			cube[face][2][i] = fullList.get(counter++);
		}
		cube[face][1][0] = fullList.get(counter++);
	}

	private ArrayList<Piece> ringRotate(ArrayList<Piece> fullList, boolean clockwise, int order) {
		int newOrder = (clockwise ? 1 : 3) * order;
		ArrayList<Piece> partList1 = new ArrayList<>(fullList.subList(0, newOrder));
		ArrayList<Piece> partList2 = new ArrayList<>(fullList.subList(newOrder, fullList.size()));
		Collections.reverse(partList1);
		Collections.reverse(partList2);
		partList1.addAll(partList2);
		Collections.reverse(partList1);
		return partList1;
	}

	public Piece[][][] rotateSHorizontal(Piece[][][] cube, int strip, int n) {
		Piece[] temp;
		for (int a = 0; a < n; a++) {
			for (int i = 3; i > 0; i--) {
				temp = new Piece[] { cube[5][2 - strip][0], cube[5][2 - strip][1], cube[5][2 - strip][2] };
				for (int j = 0; j < 3; j++) {
					cube[5][2 - strip][j] = cube[i][strip][j];
				}
				for (int j = 0; j < 3; j++) {
					cube[i][strip][j] = temp[j];
				}
			}
		}
		return cube;

	}

	public Piece[][][] rotate_L(Piece[][][] cube, int n) {
		// rotate_M(cube, n, 0);
		singleFaceSideRRotation(cube, 0, n);
		return cube;

	}

	public Piece[][][] rotate_R(Piece[][][] cube, int n) {
		// rotate_M(cube, n, 2);
		singleFaceSideRRotation(cube, 2, n);
		return cube;

	}

	public Piece[][][] rotate_U(Piece[][][] cube, int n) {
		singleFaceURotation(cube, 5, n);
		singleFaceSideURotation(cube, 2, n);
		return cube;

	}

	public Piece[][][] rotate_D(Piece[][][] cube, int n) {
		singleFaceURotation(cube, 2, n);
		singleFaceSideURotation(cube, 0, n);
		return cube;

	}

	public Piece[][][] singleFaceSideRRotation(Piece[][][] cube, int face, int n) {
		Piece[] temp;
		Piece temp1;
		int face2 = face + 1;
		for (int a = 0; a < n; a++) {
			for (int x = 0; x < 2; x++) {
				for (int i = 1; i >= 0; i--) {
					temp1 = cube[face2][0][2];
					cube[face2][0][2] = cube[face2][0][i];
					cube[face2][0][i] = temp1;
				}
				temp1 = cube[face2][0][2];
				cube[face2][0][2] = cube[face2][1][0];
				cube[face2][1][0] = temp1;
				for (int i = 0; i < 3; i++) {
					temp1 = cube[face2][0][2];
					cube[face2][0][2] = cube[face2][2][i];
					cube[face2][2][i] = temp1;
				}
				for (int i = 1; i > 0; i--) {
					temp1 = cube[face2][0][2];
					cube[face2][0][2] = cube[face2][i][2];
					cube[face2][i][2] = temp1;
				}
			}
		}
		return cube;
	}

	public Piece[][][] singleFaceSideURotation(Piece[][][] cube, int face, int n) {
		Piece[] temp1;
		int iFace = 2 - face;
		for (int a = 0; a < n; a++) {
			temp1 = new Piece[] { cube[4][face][0], cube[4][face][1], cube[4][face][2] };
			for (int j = 0; j < 3; j++) {
				cube[4][face][j] = cube[1][j][iFace];
			}
			for (int j = 0; j < 3; j++) {
				cube[1][j][iFace] = temp1[j];
			}
			temp1 = new Piece[] { cube[4][face][0], cube[4][face][1], cube[4][face][2] };
			for (int j = 0; j < 3; j++) {
				cube[4][face][j] = cube[0][iFace][2 - j];
			}
			for (int j = 0; j < 3; j++) {
				cube[0][iFace][2 - j] = temp1[j];
			}
			temp1 = new Piece[] { cube[4][face][0], cube[4][face][1], cube[4][face][2] };
			for (int j = 0; j < 3; j++) {
				cube[4][face][j] = cube[3][2 - j][face];
			}
			for (int j = 0; j < 3; j++) {
				cube[3][j][face] = temp1[2 - j];
			}
		}

		return cube;
	}

	public Piece[][][] singleFaceURotation(Piece[][][] cube, int face, int n) {
		Piece temp;
		for (int a = 0; a < n; a++) {
			for (int i = 0; i < 2; i++) {
				int counter = 0;
				int xC = 0;
				int yC = 2;
				boolean switchY = false;
				while (counter < 8) {
					temp = cube[face][0][2];
					cube[face][0][2] = cube[face][xC][yC];
					cube[face][xC][yC] = temp;
					if (yC < 3 && yC > 0 && !switchY) {
						yC--;
					} else if (yC == 0 && xC < 2) {
						xC++;
					} else if (xC == 2 && yC < 2) {
						switchY = true;
						yC++;
					} else {
						xC--;
					}
					System.out.println(xC + ", " + yC);
					counter++;
				}
			}
		}

		return cube;

	}

}
