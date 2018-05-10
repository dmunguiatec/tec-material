package ac.tec.ic6821.ej4.controllers

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping('/')
    @ResponseBody
    String index() { 'Ejemplo4 está corriendo.' }
}
