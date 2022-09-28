package nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.controller;

import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.model.Movie;
import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.model.Producer;
import nl.miwgroningen.se.ch9.advanced.emiel.movieRatingDemo.repository.ProducerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Emiel Bloem
 * <p>
 * Dit doet het programma
 */

@Controller
@RequestMapping("/producer")
public class ProducerController {

    private final ProducerRepository producerRepository;

    public ProducerController(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }


    @GetMapping("/overview")
    protected String showProducerOverview(Model model) {
        model.addAttribute("allProducers", producerRepository.findAll());
        model.addAttribute("newProducer", new Producer());
        return "producerOverview";
    }

    @PostMapping("/new")
    protected String saveOrUpdateProducer(@ModelAttribute("newProducer") Producer producer, BindingResult result) {
        if (!result.hasErrors()) {
            producerRepository.save(producer);
        }
        return "redirect:/producer/overview";
    }

    @GetMapping("/update/{producerId}")
    protected String updateBookForm(@PathVariable Long producerId, Model model) {
        Optional<Producer> producer = producerRepository.findById(producerId);

        if (producer.isEmpty()) {
            return "redirect:overview";
        }
        model.addAttribute("newProducer", producer.get());

        return "producerOverview";
    }

    @GetMapping("/delete/{producerId}")
    protected String deleteMovie(@PathVariable Long producerId) {
        producerRepository.deleteById(producerId);
        return "redirect:/producer/overview";

    }
}
