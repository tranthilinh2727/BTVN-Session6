package ra.session6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.session6.model.entity.Movie;
import ra.session6.service.MovieService;

import java.util.List;

@Controller
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;
@GetMapping("")
    public String Movie(Model model) {
    List<Movie> movies = movieService.findAll();
model.addAttribute("movies",movies);
    return "movie";
}
@GetMapping("/addMovie")
    public String addMovie() {
    return "addMovie";
}
}
