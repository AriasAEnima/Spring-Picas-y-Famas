package edu.escuelaing.arws.springgame;
import java.util.Random;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@SpringBootApplication
@Controller
public class SpringgameApplication {
    private String lastnumber = "Ninguno";
    private String guessNumber;
    private String result = "Ninguno";

	
    public static void main(String[] args) {
        SpringApplication.run(SpringgameApplication.class, args);
    }
    
    @GetMapping("/")
    public String get(Model model) {
        model.addAttribute("lastnumber", lastnumber);
        model.addAttribute("guessNumber", guessNumber);
        model.addAttribute("result", result);
        return "index";
    }

    
    @RequestMapping(value = "/", method = RequestMethod.POST, params = { "play" })
    public String post(String lastnumber) {       
        this.lastnumber=lastnumber;
        play();
        return "redirect:/";        
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST, params = { "restart" })
    public String restart() {       
        guessNumber=generateNumber();
        lastnumber = "Ninguno";
        result = "Ninguno";
        return "redirect:/";        
    }
    
    
    public void play(){
       int picas=0;
       int famas=0;
       if (lastnumber!="Ninguno"){
           for (int i = 0; i < lastnumber.length(); i++) {
               if(guessNumber.indexOf(lastnumber.charAt(i))==i){
                   famas++;
               }else if(guessNumber.contains(lastnumber.charAt(i)+"")){
                   picas++;
               }
           }
           result="Picas :"+picas+" Famas: "+famas;
       }      
    }
    
    public String generateNumber(){
        Random r= new Random();     
        int i=1;
        String nuevo=(r.nextInt(8)+1)+"";
        String nextdig=r.nextInt(9)+"";
        while(i<=3){
            while(nuevo.contains(nextdig)){
                nextdig=r.nextInt(9)+"";
            }
            nuevo+=nextdig;
            i++;
        }               
        return nuevo;
    }
    
    public SpringgameApplication() {
        guessNumber=generateNumber();             
    }

    public String getLastnumber() {
        return lastnumber;
    }

    public void setLastnumber(String lastnumber) {
        this.lastnumber = lastnumber;
    }

    public String getGuessNumber() {
        return guessNumber;
    }

    public void setGuessNumber(String guessNumber) {
        this.guessNumber = guessNumber;
    }
    
    

   

}
