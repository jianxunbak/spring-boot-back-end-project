package group3.group3_assignment.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import group3.group3_assignment.entity.Favourites;
import group3.group3_assignment.entity.Recipe;
import group3.group3_assignment.entity.User;
import group3.group3_assignment.repository.FavouritesRepository;
import group3.group3_assignment.repository.RecipeRepo;
import group3.group3_assignment.repository.UserRepo;
import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {
        private RecipeRepo recipeRepo;
        private UserRepo userRepo;
        private final PasswordEncoder passwordEncoder;

        private FavouritesRepository favouritesRepository;

        public DataLoader(RecipeRepo recipeRepo, UserRepo userRepo, FavouritesRepository favouritesRepository,
                        PasswordEncoder passwordEncoder) {
                this.recipeRepo = recipeRepo;
                this.userRepo = userRepo;
                this.favouritesRepository = favouritesRepository;
                this.passwordEncoder = passwordEncoder;
        }

        @PostConstruct
        public void loadData() {
                favouritesRepository.deleteAll();
                recipeRepo.deleteAll();
                userRepo.deleteAll();

                String dateToParse1 = "2018-11-22";
                String dateToParse2 = "2018-11-01";
                String dateToParse3 = "2018-12-15";
                String dateToParse4 = "2024-05-01";
                String dateToParse5 = "2024-05-15";
                LocalDate parsedDate1 = LocalDate.parse(dateToParse1);
                LocalDate parsedDate2 = LocalDate.parse(dateToParse2);
                LocalDate parsedDate3 = LocalDate.parse(dateToParse3);
                LocalDate parsedDate4 = LocalDate.parse(dateToParse4);
                LocalDate parsedDate5 = LocalDate.parse(dateToParse5);

                User user1 = userRepo.save(User.builder().email("abc@gmail.com").username("John")
                                .password(passwordEncoder.encode("password")).build());
                User user2 = userRepo.save(User.builder().email("123@gmail.com").username("Stark")
                                .password(passwordEncoder.encode("password")).build());

                User user3 = userRepo.save(User.builder().email("marytan@email.com").username("marytangourmet")
                                .password(passwordEncoder.encode("password"))
                                .build());
                User user4 = userRepo.save(User.builder().email("davidlee@email.com").username("davidcooks")
                                .password(passwordEncoder.encode("password"))
                                .build());
                User user5 = userRepo.save(User.builder().email("sarahchan@email.com").username("sarahbakes")
                                .password(passwordEncoder.encode("password"))
                                .build());
                Recipe recipe1 = recipeRepo.save(Recipe.builder()
                                .user(user4)
                                .title("Stir Fried Noodles")
                                .imgSrc("https://www.seriouseats.com/thmb/KOV3OvnLeh6RW64lEnRixbRxOq4=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/SEA-QiAi-stir-fried-lo-mein-noodles-pork-vegetables-recipe-hero-a55a4baa9f22449fbe036142f1047430.jpg")
                                .description("A quick and tasty stir-fried noodle recipe packed with veggies and savory sauce.")
                                .ingredients(Arrays.asList("Egg noodles",
                                                "Soy sauce",
                                                "Garlic cloves",
                                                "Carrots",
                                                "Bell peppers",
                                                "Sesame oil",
                                                "Green onions"))
                                .steps(Arrays.asList("Cook the egg noodles according to package instructions.",
                                                "Stir-fry garlic, carrots, and bell peppers in sesame oil.",
                                                "Add cooked noodles and soy sauce, tossing until combined.",
                                                "Garnish with green onions and serve."))
                                .city("Shanghai, China")
                                .latitude(31.2304)
                                .longitude(121.4737)
                                .cuisine("Chinese")
                                .build());

                Recipe recipe2 = recipeRepo.save(Recipe.builder()
                                .user(user5)
                                .title("Seared Eggplant")
                                .imgSrc("https://www.sweetashoney.co/wp-content/uploads/Sauteed-Eggplant-2.jpg")
                                .description("This seared eggplant dish is perfect for a light and flavorful vegetarian meal.")
                                .ingredients(Arrays.asList("Eggplant",
                                                "Olive oil",
                                                "Garlic cloves",
                                                "Lemon juice",
                                                "Salt",
                                                "Pepper",
                                                "Parsley"))
                                .steps(Arrays.asList("Slice eggplant and season with salt and pepper.",
                                                "Sear in olive oil until golden and soft.",
                                                "Drizzle with lemon juice and garnish with parsley before serving."))
                                .city("Athens, Greece")
                                .latitude(37.9838)
                                .longitude(23.7275)
                                .cuisine("Mediterranean")
                                .build());

                Recipe recipe3 = recipeRepo.save(Recipe.builder()
                                .user(user3)
                                .title("Savory Carrot Muffins")
                                .imgSrc("https://images.contentstack.io/v3/assets/blt8a393bb3b76c0ede/blt653e46ad897ffac9/65482c54d2a103040ad8d9ef/savoury-breakfast-muffins-recipe-header.jpg?height=675.0&width=1200.0&crop=1200.0%2C675.0%2Cx0.0%2Cy45.0&format=pjpg&auto=webp")
                                .description("Delicious savory muffins made with carrots and a blend of spices.")
                                .ingredients(Arrays.asList("Grated carrots",
                                                "Whole wheat flour",
                                                "Eggs",
                                                "Milk",
                                                "Baking powder",
                                                "Cheddar cheese",
                                                "Paprika"))
                                .steps(Arrays.asList("Preheat oven to 180°C and line a muffin tray.",
                                                "Mix grated carrots, flour, eggs, milk, baking powder, and paprika.",
                                                "Fold in cheddar cheese and pour the mixture into the muffin tray.",
                                                "Bake for 20 minutes until golden."))
                                .city("New York, USA")
                                .latitude(40.7128)
                                .longitude(-74.006)
                                .cuisine("American")
                                .build());

                Recipe recipe4 = recipeRepo.save(Recipe.builder()
                                .user(user3)
                                .title("Dijon Mustard Salmon")
                                .imgSrc("https://getfish.com.au/cdn/shop/articles/Step_4_-_crispy_salmon.png?v=1715832861")
                                .description("A simple yet flavorful Dijon mustard salmon baked to perfection.")
                                .ingredients(Arrays.asList("Salmon fillets",
                                                "Dijon mustard",
                                                "Honey",
                                                "Garlic cloves",
                                                "Lemon juice",
                                                "Salt",
                                                "Pepper"))
                                .steps(Arrays.asList("Preheat oven to 200°C.",
                                                "Whisk Dijon mustard, honey, garlic, and lemon juice together.",
                                                "Brush the salmon fillets with the mustard mixture and season with salt and pepper.",
                                                "Bake for 12-15 minutes until salmon is cooked through."))
                                .city("Dijon, France")
                                .latitude(47.322)
                                .longitude(5.0415)
                                .cuisine("French")
                                .build());

                Recipe recipe5 = recipeRepo.save(Recipe.builder()
                                .user(user5)
                                .title("Rice Noodles with Vegetables")
                                .imgSrc("https://static01.nyt.com/images/2014/06/02/dining/Rice-Noodles/Rice-Noodles-superJumbo-v2.jpg")
                                .description("A light and refreshing rice noodle dish with sautéed vegetables.")
                                .ingredients(Arrays.asList("Rice noodles",
                                                "Soy sauce",
                                                "Carrots",
                                                "Zucchini",
                                                "Garlic cloves",
                                                "Sesame oil"))
                                .steps(Arrays.asList("Cook rice noodles according to package instructions.",
                                                "Stir-fry garlic, carrots, and zucchini in sesame oil.",
                                                "Add the cooked noodles and soy sauce, toss well, and serve."))
                                .city("Bangkok Thailand")
                                .latitude(13.7563)
                                .longitude(100.5018)
                                .cuisine("Asian")
                                .build());

                Recipe recipe6 = recipeRepo.save(Recipe.builder()
                                .user(user4)
                                .title("Garlic Roasted Potatoes")
                                .imgSrc("https://www.allrecipes.com/thmb/ogHu01q-lng_VdeEP6Pd-SPsNMk=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/7937621-e1fb4dfc9dbd46bf979dafa94b83f35e.jpg")
                                .description("Crispy roasted potatoes with a delicious garlic and herb seasoning.")
                                .ingredients(Arrays.asList("Potatoes",
                                                "Garlic cloves",
                                                "Olive oil",
                                                "Rosemary",
                                                "Salt",
                                                "Pepper"))
                                .steps(Arrays.asList("Preheat oven to 200°C.",
                                                "Toss potato wedges with olive oil, garlic, rosemary, salt, and pepper.",
                                                "Roast for 30-35 minutes until crispy and golden."))
                                .city("Portland, USA")
                                .latitude(45.5051)
                                .longitude(-122.675)
                                .cuisine("American")
                                .build());
                Recipe recipe7 = recipeRepo.save(Recipe.builder()
                                .user(user2)
                                .title("Meatloaf Burger")
                                .imgSrc("https://cdn.apartmenttherapy.info/image/upload/f_jpg,q_auto:eco,c_fill,g_auto,w_1500,ar_4:3/k%2FPhoto%2FRecipes%2F2022-10-meatloaf-burgers%2Fmeatloaf-burgers-1158")
                                .description("A hearty meatloaf patty served in a burger bun with your favorite toppings.")
                                .ingredients(Arrays.asList("Ground beef",
                                                "Onion",
                                                "Breadcrumbs",
                                                "Ketchup",
                                                "Egg",
                                                "Burger buns",
                                                "Lettuce",
                                                "Cheese"))
                                .steps(Arrays.asList("Mix ground beef, chopped onion, breadcrumbs, ketchup, and egg.",
                                                "Form into patties and cook on a skillet until browned.",
                                                "Serve in a bun with lettuce, cheese, and additional toppings of choice."))
                                .city("Chicago, USA")
                                .latitude(41.8781)
                                .longitude(-87.6298)
                                .cuisine("American")
                                .build());
                Recipe recipe8 = recipeRepo.save(Recipe.builder()
                                .user(user5)
                                .title("Sloppy Joes")
                                .imgSrc("https://www.allrecipes.com/thmb/wgfzwyWYvsiiZNkrkb2ei2oz52s=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/24264-sloppy-joes-dianne-2x1-1-8f9d52b9b47446eb96700b404096b8f9.jpg")
                                .description("A tangy and delicious sloppy joes sandwich made with seasoned ground beef.")
                                .ingredients(Arrays.asList("Ground beef",
                                                "Onion",
                                                "Tomato sauce",
                                                "Worcestershire sauce",
                                                "Brown sugar",
                                                "Burger buns"))
                                .steps(Arrays.asList("Cook ground beef and onions in a skillet until browned.",
                                                "Stir in tomato sauce, Worcestershire sauce, and brown sugar, simmer until thickened.",
                                                "Serve in a burger bun and enjoy."))
                                .city("Kansas City, USA")
                                .latitude(39.0997)
                                .longitude(-94.5786)
                                .cuisine("American")
                                .build());
                Recipe recipe9 = recipeRepo.save(Recipe.builder()
                                .user(user1)
                                .title("Smoked Salmon Bowl")
                                .imgSrc("https://letthebakingbegin.com/wp-content/uploads/2020/10/Cold-Smoked-Salmon-Recipe-3.jpg")
                                .description("A fresh and healthy smoked salmon bowl with rice, avocado, and pickled veggies.")
                                .ingredients(Arrays.asList("Smoked salmon",
                                                "Rice",
                                                "Avocado",
                                                "Pickled cucumber",
                                                "Soy sauce",
                                                "Sesame seeds"))
                                .steps(Arrays.asList("Cook rice and divide into bowls.",
                                                "Top with smoked salmon, avocado, and pickled cucumber.",
                                                "Drizzle with soy sauce and sprinkle sesame seeds on top."))
                                .city("Seattle, USA")
                                .latitude(47.6062)
                                .longitude(-122.3321)
                                .cuisine("American")
                                .build());
                Recipe recipe10 = recipeRepo.save(Recipe.builder()
                                .user(user2)
                                .title("Beef Chili")
                                .imgSrc("https://thesaltycooker.com/wp-content/uploads/2023/01/Chili-BLOG.jpg")
                                .description("A hearty beef chili made with beans, tomatoes, and a blend of spices.")
                                .ingredients(Arrays.asList("Ground beef",
                                                "Kidney beans",
                                                "Tomato sauce",
                                                "Chili powder",
                                                "Onions",
                                                "Garlic cloves"))
                                .steps(Arrays.asList("Cook ground beef, onions, and garlic in a pot until browned.",
                                                "Add kidney beans, tomato sauce, and chili powder.",
                                                "Simmer for 30 minutes and serve hot."))
                                .city("Austin, USA")
                                .latitude(30.2672)
                                .longitude(-97.7431)
                                .cuisine("American")
                                .build());
                Recipe recipe11 = recipeRepo.save(Recipe.builder()
                                .user(user2)
                                .title("Avocado and Chickpea Salad")
                                .imgSrc("https://i0.wp.com/www.cearaskitchen.com/wp-content/uploads/2015/07/IMG_26131.jpg")
                                .description("A refreshing salad combining creamy avocado and protein-packed chickpeas.")
                                .ingredients(Arrays.asList("Avocado",
                                                "Chickpeas",
                                                "Red onion",
                                                "Cilantro",
                                                "Lime juice",
                                                "Olive oil",
                                                "Salt"))
                                .steps(Arrays.asList("In a bowl, mash the avocado and mix in chickpeas.",
                                                "Add chopped red onion and cilantro.",
                                                "Drizzle with lime juice and olive oil, and season with salt."))
                                .city("Athens, Greece")
                                .latitude(37.9838)
                                .longitude(23.7275)
                                .cuisine("Mediterranean")
                                .build());
                Recipe recipe12 = recipeRepo.save(Recipe.builder()
                                .user(user5)
                                .title("Classic Peach Cobbler")
                                .imgSrc("https://www.allrecipes.com/thmb/_g_SFdKUwSniBWbzaQWEiGQw6SY=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/51535-fresh-southern-peach-cobbler-ddmfs-0652-3x4-cb8d3d5a1e8548728fa1fc3d21fec1f0.jpg")
                                .description("A warm dessert featuring juicy peaches and a buttery crust.")
                                .ingredients(Arrays.asList("Fresh peaches",
                                                "Sugar",
                                                "Flour",
                                                "Butter",
                                                "Baking powder",
                                                "Milk",
                                                "Cinnamon"))
                                .steps(Arrays.asList("Preheat oven to 180°C.",
                                                "Mix sliced peaches with sugar and place in a baking dish.",
                                                "Combine flour, baking powder, sugar, and milk for the batter.",
                                                "Pour batter over peaches and bake until golden."))
                                .city("Atlanta, USA")
                                .latitude(33.749)
                                .longitude(-84.388)
                                .cuisine("American")
                                .build());
                Recipe recipe13 = recipeRepo.save(Recipe.builder()
                                .user(user1)
                                .title("Spinach and Feta Quiche")
                                .imgSrc("https://nz.simplynootropics.com/cdn/shop/articles/Copy_of_Copy_of_Untitled_1200_x_800_px_16.png?v=1714490425&width=1100")
                                .description("A savory quiche loaded with spinach and feta cheese.")
                                .ingredients(Arrays.asList("Spinach",
                                                "Feta cheese",
                                                "Eggs",
                                                "Milk",
                                                "Pie crust",
                                                "Onion",
                                                "Nutmeg"))
                                .steps(Arrays.asList("Preheat oven to 190°C.",
                                                "Sauté onions and spinach until wilted.",
                                                "In a bowl, whisk eggs and milk, then stir in spinach, feta, and nutmeg.",
                                                "Pour mixture into pie crust and bake until set."))
                                .city("Paris, France")
                                .latitude(48.8566)
                                .longitude(2.3522)
                                .cuisine("French")
                                .build());
                Recipe recipe14 = recipeRepo.save(Recipe.builder()
                                .user(user2)
                                .title("Moist Banana Bread")
                                .imgSrc("https://i0.wp.com/smittenkitchen.com/wp-content/uploads//2020/03/sk-ultimate-banana-bread.jpg?fit=1200%2C800&ssl=1")
                                .description("A deliciously moist banana bread perfect for breakfast or a snack.")
                                .ingredients(Arrays.asList("Preheat oven to 175°C.",
                                                "Mash bananas and mix with melted butter.",
                                                "Add sugar, egg, and vanilla, mixing well.",
                                                "Stir in flour and baking soda, then pour into a loaf pan and bake until golden."))
                                .steps(Arrays.asList("Cook ground beef, onions, and garlic in a pot until browned.",
                                                "Add kidney beans, tomato sauce, and chili powder.",
                                                "Simmer for 30 minutes and serve hot."))
                                .city("Honolulu, USA")
                                .latitude(21.3069)
                                .longitude(-157.8583)
                                .cuisine("American")
                                .build());
                Recipe recipe15 = recipeRepo.save(Recipe.builder()
                                .user(user1)
                                .title("Zucchini Bread")
                                .imgSrc("https://www.simplyrecipes.com/thmb/iRTpAzEQ7TP8_prwXjuf_tyxtPA=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Simply-Recipes-Easy-Zucchini-Bread-LEAD-1v2-10c6fca11bab4da8b5b388436a919328.jpg")
                                .description("A flavorful bread made with fresh zucchini and warm spices.")
                                .ingredients(Arrays.asList("Zucchini",
                                                "Flour",
                                                "Sugar",
                                                "Eggs",
                                                "Cinnamon",
                                                "Nutmeg",
                                                "Walnuts"))
                                .steps(Arrays.asList("Preheat oven to 175°C.",
                                                "Grate zucchini and squeeze out excess moisture.",
                                                "Mix zucchini with eggs, sugar, and spices.",
                                                "Fold in flour and walnuts, then pour into a loaf pan and bake until cooked through."))
                                .city("Portland, USA")
                                .latitude(45.5051)
                                .longitude(-122.675)
                                .cuisine("American")
                                .build());
                Recipe recipe16 = recipeRepo.save(Recipe.builder()
                                .user(user4)
                                .title("Chickpea Coconut Curry")
                                .imgSrc("https://www.sidechef.com/recipe/e2488170-7e2d-45de-a5fd-b6a4ec24df9a.jpg?d=1408x1120")
                                .description("A creamy and aromatic chickpea curry with coconut milk.")
                                .ingredients(Arrays.asList("1 can (15 oz) chickpeas, drained and rinsed",
                                                "1 can (13.5 oz) coconut milk",
                                                "1 small onion, finely chopped",
                                                "2 cloves garlic, minced",
                                                "1-inch piece ginger, grated",
                                                "1 tablespoon curry powder",
                                                "1 teaspoon ground cumin",
                                                "1/2 teaspoon ground turmeric",
                                                "1/2 teaspoon red chili flakes (optional)",
                                                "1 cup fresh spinach",
                                                "1 tablespoon oil (coconut or vegetable)",
                                                "Salt and black pepper to taste"))
                                .steps(Arrays.asList("Sauté onion, garlic, and ginger until fragrant.",
                                                "Add chickpeas and curry powder, stirring to coat.",
                                                "Pour in coconut milk and simmer until thickened.",
                                                "Stir in spinach until wilted and serve hot."))
                                .city("Mumbai, India")
                                .latitude(19.076)
                                .longitude(72.8777)
                                .cuisine("Indian")
                                .build());
                Recipe recipe17 = recipeRepo.save(Recipe.builder()
                                .user(user5)
                                .title("Greek Flatbread Pizza")
                                .imgSrc("https://jimcooksfoodgood.com/wp-content/uploads/2022/12/NEw-ENgland-Greek-pizza-scaled.jpg")
                                .description("A Mediterranean-inspired pizza topped with fresh ingredients.")
                                .ingredients(Arrays.asList("Flatbread",
                                                "Olive oil",
                                                "Feta cheese",
                                                "Tomatoes",
                                                "Olives",
                                                "Red onion",
                                                "Spinach"))
                                .steps(Arrays.asList("Preheat oven to 220°C.",
                                                "Brush flatbread with olive oil.",
                                                "Top with feta, sliced tomatoes, olives, red onion, and spinach.",
                                                "Bake until the edges are crispy."))
                                .city("Santorini, Greece")
                                .latitude(36.3932)
                                .longitude(25.4615)
                                .cuisine("Mediterranean")
                                .build());
                Recipe recipe18 = recipeRepo.save(Recipe.builder()
                                .user(user2)
                                .title("Classic Chocolate Chip Cookies")
                                .imgSrc("https://www.foodandwine.com/thmb/4_UScMzHQCxZzACBITHHmT_EM3U=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Chocolate-Chunk-Halwah-Cookies-FT-RECIPE0923-1f8df755df6d468da98887aa846a2fe3.jpg")
                                .description("Soft and chewy chocolate chip cookies perfect for any occasion.")
                                .ingredients(Arrays.asList("Butter",
                                                "Brown sugar",
                                                "Sugar",
                                                "Eggs",
                                                "Vanilla extract",
                                                "Flour",
                                                "Chocolate chips"))
                                .steps(Arrays.asList("Preheat oven to 190°C.",
                                                "Cream butter and sugars together, then add eggs and vanilla.",
                                                "Mix in flour and chocolate chips, then drop spoonfuls onto a baking sheet.",
                                                "Bake until edges are golden."))
                                .city("New York City, USA")
                                .latitude(40.7128)
                                .longitude(-74.006)
                                .cuisine("American")
                                .build());
                Recipe recipe19 = recipeRepo.save(Recipe.builder()
                                .user(user3)
                                .title("Hainanese Chicken Rice")
                                .imgSrc("https://www.seriouseats.com/thmb/OVPH7U5DQfboRHeAJ-8VH4DBGBQ=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/hainanese-chicken-rice-set-recipe-hero-Fred-Hardy-d04b51b0338144dc8549c89802b721e4.JPG")
                                .description("A fragrant and flavorful poached chicken dish served with rice cooked in chicken broth.")
                                .ingredients(Arrays.asList("Whole chicken",
                                                "Jasmine rice",
                                                "Ginger",
                                                "Garlic",
                                                "Spring onions",
                                                "Soy sauce",
                                                "Chili sauce"))
                                .steps(Arrays.asList("Poach chicken in a pot with ginger and garlic.",
                                                "Cook rice using chicken broth.",
                                                "Serve sliced chicken over rice with sauces."))
                                .city("Bukit Timah, Singapore")
                                .latitude(1.3294)
                                .longitude(103.8021)
                                .cuisine("Singaporean")
                                .build());
                Recipe recipe20 = recipeRepo.save(Recipe.builder()
                                .user(user4)
                                .title("Singapore Chili Crab")
                                .imgSrc("https://upload.wikimedia.org/wikipedia/commons/c/c6/Chilli_crab-02.jpg")
                                .description("A rich and spicy crab dish in a sweet and savory tomato-based chili sauce.")
                                .ingredients(Arrays.asList("Mud crab",
                                                "Garlic",
                                                "Ginger",
                                                "Chili paste",
                                                "Tomato sauce",
                                                "Egg",
                                                "Cornstarch"))
                                .steps(Arrays.asList("Stir-fry garlic, ginger, and chili paste.",
                                                "Add crab and tomato sauce, simmer until cooked.",
                                                "Thicken sauce with cornstarch and stir in beaten egg."))
                                .city("East Coast, Singapore")
                                .latitude(1.3052)
                                .longitude(103.9123)
                                .cuisine("Singaporean")
                                .build());
                Recipe recipe21 = recipeRepo.save(Recipe.builder()
                                .user(user3)
                                .title("Laksa")
                                .imgSrc("https://www.marionskitchen.com/wp-content/uploads/2023/05/Katong-Laksa-03.jpg")
                                .description("A spicy coconut curry noodle soup with prawns and fish cakes.")
                                .ingredients(Arrays.asList("Rice noodles",
                                                "Coconut milk",
                                                "Laksa paste",
                                                "Prawns",
                                                "Fish cakes",
                                                "Bean sprouts",
                                                "Coriander"))
                                .steps(Arrays.asList("Cook laksa paste in a pot, then add coconut milk and water.",
                                                "Add prawns and fish cakes, simmer until cooked.",
                                                "Serve over noodles with bean sprouts and coriander."))
                                .city("Katong, Singapore")
                                .latitude(1.3036)
                                .longitude(103.8972)
                                .cuisine("Singaporean")
                                .build());
                Recipe recipe22 = recipeRepo.save(Recipe.builder()
                                .user(user2)
                                .title("Char Kway Teow")
                                .imgSrc("https://www.elmundoeats.com/wp-content/uploads/2024/09/Char-kway-teow-for-FP-2.jpg")
                                .description("A popular stir-fried flat rice noodle dish with eggs, prawns, and Chinese sausages.")
                                .ingredients(Arrays.asList("Flat rice noodles",
                                                "Eggs",
                                                "Prawns",
                                                "Chinese sausages",
                                                "Bean sprouts",
                                                "Dark soy sauce",
                                                "Chili paste"))
                                .steps(Arrays.asList("Stir-fry sausages, prawns, and eggs in a wok.",
                                                "Add noodles, bean sprouts, soy sauce, and chili paste.",
                                                "Fry until well combined and serve hot."))
                                .city("Tiong Bahru, Singapore")
                                .latitude(1.2853)
                                .longitude(103.8261)
                                .cuisine("Singaporean")
                                .build());
                Recipe recipe23 = recipeRepo.save(Recipe.builder()
                                .user(user1)
                                .title("Hokkien Mee")
                                .imgSrc("https://i0.wp.com/www.gingerandcilantro.com/wp-content/uploads/2016/07/Singapore-Hokkien-Mee-20160731-6.jpg?fit=714%2C550&ssl=1")
                                .description("A stir-fried prawn noodle dish with a rich seafood broth.")
                                .ingredients(Arrays.asList("Egg noodles",
                                                "Prawns",
                                                "Squid",
                                                "Garlic",
                                                "Eggs",
                                                "Pork lard",
                                                "Lime"))
                                .steps(Arrays.asList("Fry garlic and pork lard in a wok.",
                                                "Add noodles, prawns, and squid, stir-fry with stock.",
                                                "Serve with lime wedges and chili sambal."))
                                .city("Bedok, Singapore")
                                .latitude(1.324)
                                .longitude(103.927)
                                .cuisine("Singaporean")
                                .build());
                Recipe recipe24 = recipeRepo.save(Recipe.builder()
                                .user(user2)
                                .title("Bak Kut Teh")
                                .imgSrc("https://noobcook.com/wp-content/uploads/2012/09/klang.jpg")
                                .description("A peppery pork rib soup slow-cooked with Chinese herbs and spices.")
                                .ingredients(Arrays.asList("Pork ribs",
                                                "Garlic",
                                                "White pepper",
                                                "Soy sauce",
                                                "Star anise",
                                                "Cinnamon"))
                                .steps(Arrays.asList("Simmer pork ribs with garlic, spices, and white pepper.",
                                                "Cook for at least an hour until tender.",
                                                "Serve with rice and youtiao."))
                                .city("Geylang, Singapore")
                                .latitude(1.315)
                                .longitude(103.886)
                                .cuisine("Singaporean")
                                .build());
                Recipe recipe25 = recipeRepo.save(Recipe.builder()
                                .user(user2)
                                .title("Kaya Toast")
                                .imgSrc("https://tarasmulticulturaltable.com/wp-content/uploads/2017/06/Kaya-Toast-Toast-with-Coconut-Jam-and-Butter-6-of-6.jpg")
                                .description("A traditional Singaporean breakfast featuring toasted bread with kaya jam and butter.")
                                .ingredients(Arrays.asList("White bread",
                                                "Kaya jam",
                                                "Butter",
                                                "Soft-boiled eggs",
                                                "Soy sauce",
                                                "Black pepper"))
                                .steps(Arrays.asList("Toast bread until golden brown.",
                                                "Spread kaya jam and butter on toast.",
                                                "Serve with soft-boiled eggs, soy sauce, and black pepper."))
                                .city("Holland Village, Singapore")
                                .latitude(1.3114)
                                .longitude(103.7943)
                                .cuisine("Singaporean")
                                .build());
                Recipe recipe26 = recipeRepo.save(Recipe.builder()
                                .user(user3)
                                .title("Carrot Cake (Chai Tow Kway)")
                                .imgSrc("https://recipeswecherish.com/wp-content/uploads/2014/11/Chai-Tow-Kuey.jpg")
                                .description("A savory fried radish cake dish, available in white or black versions.")
                                .ingredients(Arrays.asList("Radish",
                                                "Rice flour",
                                                "Eggs",
                                                "Garlic",
                                                "Dark soy sauce",
                                                "Spring onions"))
                                .steps(Arrays.asList("Steam grated radish and mix with rice flour batter.",
                                                "Fry in a wok with garlic, eggs, and soy sauce.",
                                                "Garnish with spring onions and serve."))
                                .city("Clementi, Singapore")
                                .latitude(1.3151)
                                .longitude(103.7654)
                                .cuisine("Singaporean")
                                .build());

                Favourites favourites1 = favouritesRepository.save(Favourites.builder()
                                .remarks("faved")
                                .favouritesDate(parsedDate1)
                                .recipe(recipe1)
                                .user(user1)
                                .build()

                );

                Favourites favourites2 = favouritesRepository.save(Favourites.builder()
                                .remarks("faved")
                                .favouritesDate(parsedDate2)
                                .recipe(recipe2)
                                .user(user1)
                                .build()

                );

                Favourites favourites3 = favouritesRepository.save(Favourites.builder()
                                .remarks("faved")
                                .favouritesDate(parsedDate3)
                                .recipe(recipe3)
                                .user(user1)
                                .build()

                );

                Favourites favourites4 = favouritesRepository.save(Favourites.builder()
                                .remarks("faved")
                                .favouritesDate(parsedDate4)
                                .recipe(recipe3)
                                .user(user2)
                                .build()

                );

                Favourites favourites5 = favouritesRepository.save(Favourites.builder()
                                .remarks("faved")
                                .favouritesDate(parsedDate5)
                                .recipe(recipe5)
                                .user(user2)
                                .build()

                );

        }
}