package model;

public class Canvas {
    char[][] canvasArray;
    int w, h;
    public Canvas(){}
    public Canvas(int w, int h) {
        if(w < 1 || h < 1) {
            throw new DrawingGraphIOException("Canvas width and height can't be 0");
        }
        h+=2;
        w+=2;
        this.w = w;
        this.h = h;
        this.canvasArray = new char[h][w];
        drawLine(0, 0, this.w-1, 0, '-');
        drawLine(0, this.h-1, this.w-1, this.h-1, '-');
        drawLine(0, 1, 0, this.h-2, '|');
        drawLine(this.w-1, 1, this.w-1, this.h-2, '|');
    }
    
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
    public String render() {
        checkCanvas();
        StringBuilder strBuilder = new StringBuilder();
        for(int i=0;i<this.h;i++) {
            for(int j=0;j<this.w;j++) {
                strBuilder.append(this.canvasArray[i][j] == '\u0000'?' ':this.canvasArray[i][j]);
            }
            strBuilder.append("\n");
        }
        return strBuilder.toString().trim();
    }
    
    
    public String render(char[][] lineArray) {
        checkCanvas(lineArray);
        StringBuilder strBuilder = new StringBuilder();
        int height = lineArray.length;
        System.out.println("height=====>"+height);
        int width = lineArray[0].length;
        System.out.println("width=====>"+width);
        
        for(int i=0;i<height;i++) {
            for(int j=0;j<width;j++) {
                strBuilder.append(lineArray[i][j] == '\u0000'?' ':lineArray[i][j]);
            }
            strBuilder.append("\n");
        }
        return strBuilder.toString().trim();
    }
    
    
    public void drawLine(int x1, int y1, int x2, int y2, char mChar) {
        checkCanvas();
        for(int i=y1; i<=y2; i++) {
            for(int j=x1; j<=x2; j++) {
                this.canvasArray[i][j] = mChar;
            }
        }
    }
    
    public char[][] drawLine(char[][] lineArray,int x1, int y1, int x2, int y2, char mChar) {
        checkCanvas(lineArray);
        for(int i=y1; i<=y2; i++) {
            for(int j=x1; j<=x2; j++) {
            	lineArray[i][j] = mChar;
            }
        }
        return lineArray;
    }
    
    public void drawRectangle(int x1, int y1, int x2, int y2, char mchar) {
        checkCanvas();
        drawLine(x1,y1, x2, y1, mchar);
        drawLine(x1,y1, x1, y2, mchar);
        drawLine(x2,y1, x2, y2, mchar);
        drawLine(x1,y2, x2, y2, mchar);
    }
    
    public char[][] drawRectangle(char[][] lineArray, int x1, int y1, int x2, int y2, char mchar) {
        checkCanvas(lineArray);
        lineArray = drawLine(lineArray,x1,y1, x2, y1, mchar);
        lineArray = drawLine(lineArray,x1,y1, x1, y2, mchar);
        lineArray = drawLine(lineArray,x2,y1, x2, y2, mchar);
        lineArray = drawLine(lineArray,x1,y2, x2, y2, mchar);
        
        return lineArray;
    }
    public void bucketFill(int x, int y, char mchar) {
        checkCanvas();
        if((int)this.canvasArray[y][x] != 0) {
            return;
        }
        if(x > 0 || x < this.h || y > 0 || y  < this.w) {
            if((int)this.canvasArray[y][x] == 0)
                this.canvasArray[y][x] = mchar;
            bucketFill(x+1,y, mchar);
            bucketFill(x-1,y, mchar);
            bucketFill(x,y-1, mchar);
            bucketFill(x,y+1, mchar);
        }
    }
    
    public char[][] bucketFill(char[][] lineArray,int x, int y, char mchar) {
        checkCanvas(lineArray);
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

    private void checkCanvas() {
        if(this.canvasArray == null)
            throw new DrawingGraphIOException("Draw a canvas first");
    }
    
    private void checkCanvas(char[][] lineArray) {
        if(lineArray == null)
            throw new DrawingGraphIOException("Draw a canvas first");
    }
}