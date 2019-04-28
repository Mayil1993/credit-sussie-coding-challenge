package GraphDrawingService.com.graphService.com;

import java.io.Serializable;

public class TransformDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char[][] canvasArray;

	public char[][] getCanvasArray() {
		return canvasArray;
	}

	public void setCanvasArray(char[][] canvasArray) {
		this.canvasArray = canvasArray;
	}
}
