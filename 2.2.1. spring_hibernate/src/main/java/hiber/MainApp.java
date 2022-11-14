package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println();
      }

      User userWithCar = new User("Vasiliy", "Petrov", "123@gmail.com");
      userWithCar.setUserCar(new Car("BMW", 5));
      User userWithCar2 = new User("Ivan", "Koleso", "345@gmail.com");
      userWithCar2.setUserCar(new Car("Lada", 14));

      userService.add(userWithCar);
      userService.add(userWithCar2);

      userWithCar2 = userService.getUserByCar("BMW", 5);
      userWithCar = userService.getUserByCar("Lada", 14);

      context.close();
   }
}