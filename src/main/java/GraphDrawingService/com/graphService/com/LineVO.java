package GraphDrawingService.com.graphService.com;

public class LineVO {

	private String command;
	private char[][] canvasArray;
	
	public char[][] getCanvasArray() {
		return canvasArray;
	}

	public void setCanvasArray(char[][] canvasArray) {
		this.canvasArray = canvasArray;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
}
