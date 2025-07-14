package by.lyofchik.AppSpring.Service.UserService;

import by.lyofchik.AppSpring.Model.Entities.User;
import by.lyofchik.AppSpring.Repository.UserRepository;
import by.lyofchik.AppSpring.Service.EntityInterfaces.DeleteEntity;
import by.lyofchik.AppSpring.Service.EntityInterfaces.FindAllEntities;
import by.lyofchik.AppSpring.Service.EntityInterfaces.FindEntity;
import by.lyofchik.AppSpring.Service.EntityInterfaces.SaveEntity;
import by.lyofchik.AppSpring.Service.Hashing.PasswordHasher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.PropertyException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserService implements
        ChangePassword,
        DeleteEntity,
        FindAllEntities<User>,
        FindEntity<User>,
        SaveEntity<User>,
        UserDetailsService {
    private final UserRepository repository;

    @Override
    public User changePassword(User user, String newPassword) {
        var hashPassword = PasswordHasher.hash(newPassword);
        user.setPassword(hashPassword);
        repository.save(user);
        return user;
    }

    @Override
    public boolean delete(String entityName) {
        try {
            repository.deleteByUserName(entityName);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> find(String name) {
        return repository.findByUserName(name);
    }

    @Override
    public User save(User entity) {
        try {
            return repository.save(entity);
        }catch (Exception e){
            log.warn(e.getMessage());
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUserName(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUserName(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to find " + username));
    }

    public String toJSON(User user) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(user);
    }

    public String toXML(User user) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        jaxbMarshaller.marshal(user, writer);
        return writer.toString();
    }
}

