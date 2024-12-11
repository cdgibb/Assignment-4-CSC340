package Carter.Assignment4CRUDapi.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AnimalController.java.
 * Includes all REST API endpoint mappings for the Animal object.
 */
@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService service;

    /**
     * Get a list of all Animals in the database.
     * http://localhost:8080/animals/all
     *
     * @return a list of Animals objects.
     */
    @GetMapping("/all")
    public List<Animal> getAllStudents() {
        return service.getAllAnimals();
    }

    /**
     * Get a specific Animal by Id.
     * http://localhost:8080/animals/2
     *
     * @param animalId the unique Id for an Animal.
     * @return One Animal object.
     */
    @GetMapping("/{animalId}")
    public Animal getOneAnimal(@PathVariable int animalId) {
        return service.getAnimalById(animalId);
    }

    /**
     * Create a new Animal entry.
     * http://localhost:8080/animals/new
     *
     * @param animal the new Animal object.
     * @return the updated list of Animals.
     */
    @PostMapping("/new")
    public List<Animal> addNewStudent(@RequestBody Animal animal) {
        service.addNewAnimal(animal);
        return service.getAllAnimals();
    }

    /**
     * Update an existing Animal object.
     * http://localhost:8080/animals/update/2
     *
     * @param animalId the unique Animal Id.
     * @param animal   the new updated Animal details.
     * @return the updated Animal object.
     */
    @PutMapping("/update/{animalId}")
    public Animal updateAnimal(@PathVariable int animalId, @RequestBody Animal animal) {
        service.updateAnimal(animalId, animal);
        return service.getAnimalById(animalId);
    }

    /**
     * Delete an Animal object.
     * http://localhost:8080/animals/delete/
     *
     * @param animalId the unique Animal Id.
     * @return the updated list of Animals.
     */
    @DeleteMapping("/delete/{animalId}")
    public List<Animal> deleteAnimalById(@PathVariable int animalId) {
        service.deleteAnimalById(animalId);
        return service.getAllAnimals();
    }

    /**
     * Get a list of animals of a species.
     * http://localhost:8080/animals/species/
     *
     * @param species the target species.
     * @return list of Animal objects matching the search key.
     */
    @GetMapping("/species")
    public List<Animal> getAnimalsBySpecies(@RequestParam(name = "species") String species) {
        return service.getAnimalsBySpecies(species);
    }

    /**
     * Get a list of animals whose name contains the string given.
     * http://localhost:8080/animals/namecontains/
     *
     * @param name the target species.
     * @return list of Animal objects matching the search key.
     */
    @GetMapping("/namecontains")
    public List<Animal> getAnimalsByNameContaining(@RequestParam(name = "name") String name) {
        return service.getAnimalsByNameContaining(name);
    }
}
