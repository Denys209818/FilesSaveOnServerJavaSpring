package program;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import program.storage.ImageStorage;
import program.storage.StorageProperties;
//Оголошення початку програми Spring boot application
@SpringBootApplication
//Реєстрування властивостей конфігурацій. Атрибут приймає тип класу.
@EnableConfigurationProperties(StorageProperties.class)
public class Main {
    //Початок програми. Запуск Spring boot
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    //Налаштування CORS
    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //Дозвіл на запити від ресурсу http://localhost:3000
                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
            }
        };
    }
    //Створення папки з ресурсами. Метод приймає сервіс, у
    //якому створюється папка, якщо її немає
    @Bean
    //Інтерфейс CommandLineRunner вказує, що блок має запускатися,
    //при ініціалізації проекта
    CommandLineRunner initFileStorage(ImageStorage imgStorage)
    {
        //Лямбда, яка використовує сервіс і викликає метод
        //для створення папки
        return (args) -> {
          try
          {
                imgStorage.initDirectory();
          }
          catch(Exception ex)
          {
              System.out.println(ex.getMessage());
          }
        };
    }
}
