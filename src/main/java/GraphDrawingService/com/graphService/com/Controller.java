package GraphDrawingService.com.graphService.com;

import model.Canvas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/draw")
public class Controller {
	
	
	@RequestMapping(value = "/createCanvas", method = RequestMethod.POST)
	public Object createCanvas(@RequestBody CanvasVO canvasVo) {
		Canvas canvas = new Canvas();
		TransformDTO dto = new TransformDTO();
		System.out.println("==========>"+canvasVo.getCommand());
		String command = canvasVo.getCommand();
        String[] inpArray;
            
                	inpArray = command.split(" ");
                    char[][] lineArray = canvas.drawCanvas(Integer.parseInt(inpArray[1]),Integer.parseInt(inpArray[2]));
                    System.out.println(canvas.render(lineArray));
                    dto.setCanvasArray(lineArray);
		return dto;
	}
	
	@RequestMapping(value = "/drawLine", method = RequestMethod.POST)
		public Object drawLine(@RequestBody LineVO lineVo) {
		Canvas canvas = new Canvas();
		TransformDTO dto = new TransformDTO();
		System.out.println("==========>"+lineVo.getCommand());
		System.out.println("==========>"+lineVo.getCanvasArray());
		
		String command = lineVo.getCommand();
		 String[] inpArray = command.split(" ");
		char[][] lineArray = canvas.drawLine(lineVo.getCanvasArray(),Integer.parseInt(inpArray[1]),Integer.parseInt(inpArray[2]),Integer.parseInt(inpArray[3]),Integer.parseInt(inpArray[4]),'X');
        System.out.println(canvas.render(lineArray));
        	dto.setCanvasArray(lineArray);
		return dto;
	}
	
	@RequestMapping(value = "/drawRectangle", method = RequestMethod.POST)
	public Object drawRectangle(@RequestBody LineVO lineVo) {
	Canvas canvas = new Canvas();
	TransformDTO dto = new TransformDTO();
	System.out.println("==========>"+lineVo.getCommand());
	System.out.println("==========>"+lineVo.getCanvasArray());
	
	String command = lineVo.getCommand();
	 String[] inpArray = command.split(" ");
	char[][] lineArray = canvas.drawRectangle(lineVo.getCanvasArray(),Integer.parseInt(inpArray[1]),Integer.parseInt(inpArray[2]),Integer.parseInt(inpArray[3]),Integer.parseInt(inpArray[4]),'X');
    System.out.println(canvas.render(lineArray));
    dto.setCanvasArray(lineArray);
	return dto;
}
	
	@RequestMapping(value = "/bucketFill", method = RequestMethod.POST)
	public Object bucketFill(@RequestBody LineVO lineVo) {
	Canvas canvas = new Canvas();
	TransformDTO dto = new TransformDTO();
	System.out.println("==========>"+lineVo.getCommand());
	System.out.println("==========>"+lineVo.getCanvasArray());
	
	String command = lineVo.getCommand();
	 String[] inpArray = command.split(" ");
	char[][] lineArray =  canvas.bucketFill(lineVo.getCanvasArray(),Integer.parseInt(inpArray[1]),Integer.parseInt(inpArray[2]),inpArray[3].charAt(0));
    System.out.println(canvas.render(lineArray));
    dto.setCanvasArray(lineArray);
	return dto;
}
	
	@RequestMapping(value = "/printGraph", method = RequestMethod.POST)
	public String printGraph(@RequestBody LineVO lineVo) {
	Canvas canvas = new Canvas();
	System.out.println("==========>"+lineVo.getCommand());
	System.out.println("==========>"+lineVo.getCanvasArray());
	String outPut = "";
	outPut =  canvas.render(lineVo.getCanvasArray());
    System.out.println(outPut);

	return outPut;
}
	 
}
