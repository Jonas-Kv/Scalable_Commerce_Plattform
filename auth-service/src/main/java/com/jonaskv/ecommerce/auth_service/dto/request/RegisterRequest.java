package com.jonaskv.ecommerce.auth_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
  
  @NotBlank(message = "Email can´t be empty")
  @Email(message = "Ungültige Email-Addresse")
  private String email;

  @NotBlank(message = "Passwort darf nicht leer sein")
  @Size(min = 8, message = "Passwort muss mindestens 8 Zeichen haben")
  private String password;
}
