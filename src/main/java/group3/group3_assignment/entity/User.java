package group3.group3_assignment.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @NotBlank(message = "Username cannot be blank", groups = {
                        UserValidationGroup.EditUserGroup.class,
                        UserValidationGroup.CreateUserGroup.class })
        @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters", groups = {
                        UserValidationGroup.EditUserGroup.class,
                        UserValidationGroup.CreateUserGroup.class })
        @Column(name = "username", unique = true, nullable = false)
        private String username;

        @NotBlank(message = "Email cannot be blank", groups = {
                        UserValidationGroup.EditUserGroup.class,
                        UserValidationGroup.CreateUserGroup.class })
        @Email(message = "Email should be valid", groups = { UserValidationGroup.EditUserGroup.class,
                        UserValidationGroup.CreateUserGroup.class })
        @Column(name = "email", unique = true, nullable = false)
        private String email;

        @NotBlank(message = "Password cannot be blank", groups = {
                        UserValidationGroup.EditPasswordGroup.class,
                        UserValidationGroup.CreateUserGroup.class })
        @Size(min = 8, message = "Password must be at least 8 characters long", groups = {
                        UserValidationGroup.EditPasswordGroup.class,
                        UserValidationGroup.CreateUserGroup.class })
        @Column(name = "password", nullable = false)
        private String password;

        @Size(min = 8, message = "Password must be at least 8 characters long", groups = {
                        UserValidationGroup.EditUserGroup.class,
                        UserValidationGroup.CreateUserGroup.class })
        @Column(name = "about_me", nullable = true)
        private String aboutMe;

        @Pattern(message = "invalid image URL", regexp = "^(http[s]?://.*\\.(?:png|jpg|jpeg|gif|bmp|svg|webp|.*))$", groups = {
                        UserValidationGroup.EditUserGroup.class,
                        UserValidationGroup.CreateUserGroup.class })
        @Column(name = "profile_picture", nullable = true)
        private String profilePicture;

        @JsonIgnoreProperties({ "recipes", "user" })
        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Recipe> recipes;

        @JsonIgnoreProperties({ "recipe", "user", "remarks" })
        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Favourites> favourites;

}