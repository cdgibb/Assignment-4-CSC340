package Carter.Assignment4CRUDapi.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AnimalController.java.
 * Includes all REST API endpoint mappings for the Animal object.
 */
@Controller
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
    public String getAllAnimals(Model model) {
        model.addAttribute("animalList", service.getAllAnimals());
        model.addAttribute("title", "All Animals");
                return "animal-list";
    }

    /**
     * Get a specific Animal by Id.
     * http://localhost:8080/animals/2
     *
     * @param animalId the unique Id for an Animal.
     * @return One Animal object.
     */
    @GetMapping("/{animalId}")
    public String getOneAnimal(@PathVariable int animalId, Model model) {
        model.addAttribute("animal", service.getAnimalById(animalId));
        model.addAttribute("title", animalId);
        return "animal-details";
    }

    /**
     * Create a new Animal entry.
     * http://localhost:8080/animals/new
     *
     * @param animal the new Animal object.
     * @return the updated list of Animals.
     */
    @PostMapping("/new")
    public String addNewAnimal(Animal animal) {
        service.addNewAnimal(animal);
        return "redirect:/animals/all";
    }

    /**
     * Show the update form.
     *
     * @param animalId, model
     * @return
     */
    @GetMapping("/update/{studentId}")
    public String showUpdateForm(@PathVariable int animalId, Model model) {
        model.addAttribute("animal", service.getAnimalById(animalId));
        return "animal-update";
    }

    /**
     * Perform the update.
     *
     * @param animal
     * @return
     */
    @PostMapping("/update")
    public String updateAnimal(Animal animal) {
        service.addNewAnimal(animal);
        return "redirect:/animals/" + animal.getAnimalId();
    }


    /**
     * Delete an Animal object.
     * http://localhost:8080/animals/delete/
     *
     * @param animalId the unique Animal Id.
     * @return the updated list of Animals.
     */
    @GetMapping("/delete/{animalId}")
    public String deleteAnimalById(@PathVariable int animalId) {
        service.deleteAnimalById(animalId);
        return "redirect:/animals/all";
    }

    /**
     * Get a list of animals of a species.
     * http://localhost:8080/animals/species/
     *
     * @param species the target species.
     * @return list of Animal objects matching the search key.
     */
    @GetMapping("")
    public String getAnimalsBySpecies(@RequestParam(name = "species") String species, Model model) {
        model.addAttribute("animalList", service.getAnimalsBySpecies(species));
        model.addAttribute("title", "Animals of: "+species);
        return "animal-list";
    }

    /**
     * Get a list of animals whose name contains the string given.
     * http://localhost:8080/animals/namecontains/
     *
     * @param name the target species.
     * @return list of Animal objects matching the search key.
     */
    @GetMapping("/namecontains")
    public String getAnimalsByNameContaining(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("animalList", service.getAnimalsByNameContaining(name));
        model.addAttribute("title", "Animals containing: "+name);
        return "animal-list";
    }
}
