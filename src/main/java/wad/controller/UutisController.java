package wad.controller;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import wad.domain.Gif;

import wad.domain.Uutinen;
import wad.repository.GifRepository;

import wad.repository.UutisRepository;

@Controller
public class UutisController {

    @Autowired
    private UutisRepository uutisrepository;

    @Autowired
    private GifRepository gifRepoitory;

    @GetMapping("/etusivu")
    public String list(Model model) {

        PageRequest pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "pvm");
        model.addAttribute("uutiset", uutisrepository.findAll(pageable));

        return "etusivu";
    }

    @GetMapping(path = "/gifs/{id}/content", produces = "image/gif")
    @ResponseBody
    public byte[] get(@PathVariable Long id) {

        return gifRepoitory.getOne(id).getContent();
    }

    @GetMapping("/etusivu/{uutisId}")
    public String uutinen(@PathVariable Long uutisId, Model model) {
        model.addAttribute("uutinen", uutisrepository.getOne(uutisId));
        Uutinen uutinen = uutisrepository.getOne(uutisId);

        int i = uutinen.getVisits() + 1;
        uutinen.setVisits(i);

        uutisrepository.save(uutinen);

        PageRequest pageable = PageRequest.of(0, 99, Sort.Direction.DESC, "visits");
        model.addAttribute("luetuimmat", uutisrepository.findAll(pageable));

        PageRequest pageableU = PageRequest.of(0, 99, Sort.Direction.DESC, "pvm");
        model.addAttribute("uusimmat", uutisrepository.findAll(pageableU));

        return "uutinen";
    }

    @GetMapping("/{kategoria}")
    public String kategoria(@PathVariable String kategoria, Model model) {

        if (kategoria.matches("luetuimmat")) {
            PageRequest pageable = PageRequest.of(0, 99, Sort.Direction.DESC, "visits");
            model.addAttribute("uutiset", uutisrepository.findAll(pageable));
            

            return "etusivu";
        }

        if (kategoria.matches("uusimmat")) {
            PageRequest pageable = PageRequest.of(0, 99, Sort.Direction.DESC, "pvm");
            model.addAttribute("uutiset", uutisrepository.findAll(pageable));

            return "etusivu";
        }

        model.addAttribute("uutiset", uutisrepository.findByAihe(kategoria));

        return "etusivu";
    }

    @GetMapping("/hallinta")
    public String hallinta(Model model) {

        model.addAttribute("uutiset", uutisrepository.findAll());

        return "hallinta";
    }

    @GetMapping("/muokkaa/{uutisId}")
    public String muokkaaUutista(@PathVariable Long uutisId, Model model) {

        model.addAttribute("uutiset", uutisrepository.getOne(uutisId));

        return "muokkaus";
    }

    @PostMapping("/muokkaa/{uutisId}")
    public String muokkaaUutinen(@PathVariable Long uutisId, @RequestParam String otsikko, @RequestParam String kirjoittaja,
            @RequestParam String sisalto, @RequestParam String ingressi, @RequestParam("aihe") List<String> aiheet, @RequestParam("file") MultipartFile file) throws IOException {

        String ok = "image/gif";

        if (!file.getContentType().equals(ok)) {
            return "redirect:/hallinta";
        }

        Gif gif = new Gif();
        gif.setContent(file.getBytes());

        uutisrepository.getOne(uutisId).setOtsikko(otsikko);
        uutisrepository.getOne(uutisId).setKirjoittaja(kirjoittaja);
        uutisrepository.getOne(uutisId).setSisalto(sisalto);
        uutisrepository.getOne(uutisId).setIngressi(ingressi);
        uutisrepository.getOne(uutisId).setGif(gif);
        uutisrepository.getOne(uutisId).setPvm(LocalDateTime.now());
        uutisrepository.getOne(uutisId).setAihe(aiheet);

        gifRepoitory.save(gif);
        uutisrepository.save(uutisrepository.getOne(uutisId));

        return "redirect:/etusivu";
    }

    @PostMapping("/hallinta")
    public String addUutinen(@RequestParam String otsikko, @RequestParam String kirjoittaja,
            @RequestParam String sisalto, @RequestParam String ingressi, @RequestParam("aihe") List<String> aiheet, @RequestParam("file") MultipartFile file) throws IOException {

        String ok = "image/gif";

        if (!file.getContentType().equals(ok)) {
            return "redirect:/hallinta";
        }

        Gif gif = new Gif();
        gif.setContent(file.getBytes());

        Uutinen uutinen = new Uutinen();

        uutinen.setOtsikko(otsikko);
        uutinen.setKirjoittaja(kirjoittaja);
        uutinen.setSisalto(sisalto);
        uutinen.setIngressi(ingressi);
        uutinen.setGif(gif);
        uutinen.setPvm(LocalDateTime.now());
        uutinen.setAihe(aiheet);

        gifRepoitory.save(gif);
        uutisrepository.save(uutinen);

        return "redirect:/etusivu";
    }

}
