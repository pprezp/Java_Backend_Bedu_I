package org.bedu.backend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.User;
import org.bedu.backend.entities.UsersEntity;
import org.bedu.backend.repositories.IUsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class UsersController {

    private IUsersRepository usersRepository;

    private static Logger logger =  LoggerFactory.getLogger(UsersController.class);

    @Autowired
    public UsersController(IUsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @GetMapping("/users")
    public List<UsersEntity> getUsers(){
        return usersRepository.findAll();
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,
                                             @RequestParam(required = false) String name,
                                             @RequestParam(required = false) String lastName,
                                             @RequestParam(required = false) String email,
                                             @RequestParam(required = false) String password) throws Exception{

        logger.info("Obteniendo informacion del usuario con identificador " + id);
        try {
            UsersEntity user = usersRepository.getReferenceById(id);


            if (name != "") {
                user.setName(name);
                logger.info("Actualizando campo name del registro original ");
            }
            if (lastName != "") {
                user.setLastName(lastName);
                logger.info("Actualizando campo last_name del registro original ");
            }
            if (email != "") {
                user.setEmail(email);
                logger.info("Actualizando campo email del registro original ");
            }
            if (password != "") {
                user.setPassword(password);
                logger.info("Actualizando campo password del registro original ");
            }

            usersRepository.save(user);
            logger.info("Actualizando registro en base de datos ");

            return new ResponseEntity<>("OKIS", HttpStatus.OK);
        }catch(Exception e){
            logger.error("Ha ocurrido un error al obtener/actualizar data ");
            return new ResponseEntity<>("Registro no actualizado, ha ocurrido un error", HttpStatus.NOT_MODIFIED);
        }

    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestParam String name, HttpServletRequest request,
                                     UriComponentsBuilder uriComponentsBuilder) throws Exception{
        try{
            logger.info("Recibiendo datos de request");
            UsersEntity entity = new UsersEntity();
            entity.setName(name);
            entity.setLastName(request.getParameter("lastName"));
            entity.setEmail(request.getParameter("email"));
            entity.setPassword(request.getParameter("password"));

            logger.info("Insetando datos de request");
            usersRepository.save(entity);
            logger.info("Registro creado");

            return new ResponseEntity<>("Usuario Creado",HttpStatus.ACCEPTED);
        }catch(Exception e){
            logger.error("Ha ocurrido un error al insertar datos...");
            return new ResponseEntity<>("Usuario No Creado",HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
