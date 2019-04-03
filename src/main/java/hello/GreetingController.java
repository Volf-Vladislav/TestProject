package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class GreetingController {

    private NewsRepository repository;


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("section", "greeting");
        return "greeting";
    }

    @GetMapping("/news")
    public String news(Model model) throws IOException {
        fileToModel("templates/news.txt",model);
        return "news";
    }

    @GetMapping("/news/{file}")
    public String newsFile(Model model, @PathVariable("file") String fileName) throws IOException{
        fileToModel(fileName, model);
        return "news";
    }

    private void fileToModel(String filename, Model model) throws IOException {
        repository = new FileNewsRepository(ClassLoader.getSystemResourceAsStream("templates/news.txt"));
        List<News> news = repository.readNews();

        model.addAttribute("news", news);
        model.addAttribute("section", "news");
    }

    @GetMapping("/contacts")
    public String contacts(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("section", "greeting");
        return "contacts";
    }

    @PostMapping
    public String post(HttpServletRequest request) {
        System.out.println("post accepted");
        return "index";
    }

}
