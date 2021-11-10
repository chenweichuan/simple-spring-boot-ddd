package example.simple.springboot.ddd.presentation.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenweichuan
 */
@Slf4j
@RestController
@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
public class IndexController extends BaseController {
    @RequestMapping(value = "/")
    public String index(@RequestParam(value = "message", defaultValue = "", required = false) String message) {
        return (new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(new Date()));
    }
}
