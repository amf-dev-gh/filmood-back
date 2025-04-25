package site.amfdev.base.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/render/up")
public class UpRenderController {
	
	@GetMapping()
	public ResponseEntity<String> upRenderService(){
		return ResponseEntity.ok("Up Service OK!");
	}

}
