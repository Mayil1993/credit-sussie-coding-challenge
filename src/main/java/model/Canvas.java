package model;

public class Canvas {
    
    public char[][] drawCanvas(int width, int height){
    	height+=2;
    	width+=2;
    	
    	char[][] lineArray = new char[height][width];
    	lineArray =  drawLine(lineArray,0, 0, width-1, 0, '-');
    	lineArray = drawLine(lineArray,0, height-1, width-1, height-1, '-');
    	lineArray =  drawLine(lineArray,0, 1, 0, height-2, '|');
    	lineArray = drawLine(lineArray,width-1, 1, width-1, height-2, '|');
    	
    	return lineArray;
    }
    
    public String render(char[][] lineArray) {
        validateCanvas(lineArray);
        StringBuilder strBuilder = new StringBuilder();
        int height = lineArray.length;
        int width = lineArray[0].length;
        
        for(int i=0;i<height;i++) {
            for(int j=0;j<width;j++) {
                strBuilder.append(lineArray[i][j] == '\u0000'?' ':lineArray[i][j]);
            }
            strBuilder.append("\n");
        }
        return strBuilder.toString().trim();
    }
    
    
    
    public char[][] drawLine(char[][] lineArray,int x1, int y1, int x2, int y2, char mChar) {
        validateCanvas(lineArray);
        for(int i=y1; i<=y2; i++) {
            for(int j=x1; j<=x2; j++) {
            	lineArray[i][j] = mChar;
            }
        }
        return lineArray;
    }
  
    
    public char[][] drawRectangle(char[][] lineArray, int x1, int y1, int x2, int y2, char mchar) {
        validateCanvas(lineArray);
        lineArray = drawLine(lineArray,x1,y1, x2, y1, mchar);
        lineArray = drawLine(lineArray,x1,y1, x1, y2, mchar);
        lineArray = drawLine(lineArray,x2,y1, x2, y2, mchar);
        lineArray = drawLine(lineArray,x1,y2, x2, y2, mchar);
        
        return lineArray;
    }
    
    public char[][] bucketFill(char[][] lineArray,int x, int y, char mchar) {
        validateCanvas(lineArray);
        int height = lineArray.length-1;
        int width = lineArray[0].length-1;
        if((int)lineArray[y][x] != 0) {
            return lineArray;
        }
        if(x > 0 || x < height || y > 0 || y  < width) {
            if((int)lineArray[y][x] == 0)
            	lineArray[y][x] = mchar;
            lineArray = bucketFill(lineArray,x+1,y, mchar);
            lineArray = bucketFill(lineArray,x-1,y, mchar);
            lineArray = bucketFill(lineArray,x,y-1, mchar);
            lineArray = bucketFill(lineArray,x,y+1, mchar);
        }
        return lineArray;
    }

    private void validateCanvas(char[][] lineArray) {
        if(lineArray == null)
            throw new DrawingGraphIOException("Draw a canvas first");
    }
}