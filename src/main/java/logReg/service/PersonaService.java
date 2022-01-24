package logReg.service;

import logReg.Repository.PersonRepository;
import logReg.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
    @Autowired
    private PersonRepository personRepository;

    public void savePersone(String username, String password, String email){
        Person person = new Person();
        person.setEmail(email);
        person.setPassword(password);
        person.setUsername(username);
        personRepository.save(person);
    }

    public Person getPerson(String userame, String password) {
        return personRepository.findByUsernameAndPassword(userame, password).orElse(null);
    }
}
