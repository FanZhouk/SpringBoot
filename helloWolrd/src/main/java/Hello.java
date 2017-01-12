import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class Hello{
	
	@RequestMapping("/")
	public String sayHello(){
		return "Hello,Fan Zhoukai";
	}
	
	/**
	 * 启动项目
	 */
	public static void main(String[] args) throws Exception{
		SpringApplication.run(Hello.class, args);
	}
}